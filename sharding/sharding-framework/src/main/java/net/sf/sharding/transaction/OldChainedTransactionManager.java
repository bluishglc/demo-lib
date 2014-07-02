package net.sf.sharding.transaction;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 * The spring implementation of <a href="http://www.javaworld.com/javaworld/jw-01-2009/jw-01-spring-transactions.html?page=5">Best Efforts 1PC Pattern</a> 
 * NOTE: this class work only on Spring 2.5, for Spring 3, please use {@link ChainedTransactionManager}
 * 
 * @author laurence.geng
 * @deprecated
 */
public class OldChainedTransactionManager extends AbstractPlatformTransactionManager {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(OldChainedTransactionManager.class);
	
	private static final long serialVersionUID = -7937858824088193850L;
	private List<PlatformTransactionManager> transactionManagers = new ArrayList<PlatformTransactionManager>();
	private ArrayList<PlatformTransactionManager> reversed;

	public void setTransactionManagers(
			List<PlatformTransactionManager> transactionManagers) {
		this.transactionManagers = transactionManagers;
		reversed = new ArrayList<PlatformTransactionManager>(
				transactionManagers);
		Collections.reverse(reversed);
	}

	/**
	 * 
     * We need to disable transaction synchronization so that the shared
     * transaction synchronization state will not collide with each other. BUT,
     * for LOB creators to use, we have to pay attention here:
     * <ul>
     * <li>if the LOB creator use standard preparedStatement methods, this
     * transaction synchronization setting is OK;</li>
     * <li>if the LOB creator don't use standard PS methods, you have to find
     * other way to make sure the resources your LOB creator used should be
     * cleaned up after the transaction.</li>
     * </ul>
     */
	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition)
			throws TransactionException {
		logger.debug("Begin multi-datasource transaction!");
		@SuppressWarnings("unchecked")
		List<DefaultTransactionStatus> list = (List<DefaultTransactionStatus>) transaction;
		for (PlatformTransactionManager transactionManager : transactionManagers) {
			DefaultTransactionStatus element = (DefaultTransactionStatus) transactionManager
					.getTransaction(definition);
			list.add(0, element);
		}
		logger.debug("----------------------------------");
	}

	@Override
	protected void doCommit(DefaultTransactionStatus status)
			throws TransactionException {
		@SuppressWarnings("unchecked")
		List<DefaultTransactionStatus> list = (List<DefaultTransactionStatus>) status
				.getTransaction();
		int i = 0;
		for (PlatformTransactionManager transactionManager : reversed) {
			TransactionStatus local = list.get(i++);
			try {
				transactionManager.commit(local);
			} catch (TransactionException e) {
				logger.error("Error in commit", e);
				// Rollback will ensue as long as rollbackOnCommitFailure=true
				throw e;
			}
		}
	}

	@Override
	protected Object doGetTransaction() throws TransactionException {
		return new ArrayList<DefaultTransactionStatus>();
	}

	@Override
	protected void doRollback(DefaultTransactionStatus status)
			throws TransactionException {
		@SuppressWarnings("unchecked")
		List<DefaultTransactionStatus> list = (List<DefaultTransactionStatus>) status
				.getTransaction();
		int i = 0;
		TransactionException lastException = null;
		for (PlatformTransactionManager transactionManager : reversed) {
			TransactionStatus local = list.get(i++);
			try {
				transactionManager.rollback(local);
			} catch (TransactionException e) {
				// Log exception and try to complete rollback 
				lastException = e;
				logger.error("Error in rollback", e);
			}
		}
		if (lastException!=null) {
			throw lastException;
		}
	}

}

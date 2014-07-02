package com.threeti.umax.sample.financialmockapp.service.impl;

import org.apache.log4j.Logger;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threeti.umax.sample.financialmockapp.dao.CreditDao;
import com.threeti.umax.sample.financialmockapp.model.Credit;
import com.threeti.umax.sample.financialmockapp.service.CreditManager;
import com.threeti.umax.sample.financialmockapp.service.CreditService;


/**
 * Implementation of CreditManager interface.
 *
 * @author Laurence Geng
 */
@Service("creditManager")
@WebService(serviceName = "CreditService", 
			endpointInterface = "com.threeti.umax.sample.financialmockapp.service.CreditService", 
			targetNamespace="http://service.financialmockapp.sample.umax.threeti.com/")
public class CreditManagerImpl extends GenericManagerImpl<Credit, Long> implements CreditManager, CreditService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CreditManagerImpl.class);

    private CreditDao creditDao;


    @Autowired
    public void setCreditDao(CreditDao creditDao) {
        this.dao = creditDao;
        this.creditDao = creditDao;
    }
    
    /**
     * {@inheritDoc}
     */
    public Credit getCustomerCredit(String customerId) {
    	Credit credit = creditDao.getCustomerCredit(new Long(customerId));
        if(logger.isDebugEnabled()){
        	if(credit==null){
        		logger.debug("There's no credit record for customer: ID"+customerId);
        	}else{
        		logger.debug("Credit for customer: ID"+customerId+" has loaded!");
        	}
        }
        return credit;
    }
 

    /**
     * {@inheritDoc}
     */
    public Credit getCredit(String creditId) {
        return creditDao.get(new Long(creditId));
    }

    /**
     * {@inheritDoc}
     */
    public List<Credit> getCredits() {
        return creditDao.getAllDistinct();
    }

    /**
     * {@inheritDoc}
     */
	public Credit saveCredit(Credit credit) {
		return creditDao.save(credit);
	}

 
}

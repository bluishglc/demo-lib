package net.sf.verification;

import java.util.ArrayList;
import java.util.List;


/**
 * The BrokerCredentialVerificationProgress is a dynamic model of BC verification progress.
 * When this object just return from {@link BrokerCredentialVerificationService#verifyBrokerCredential}, 
 * there're many verification threads is running. You can query the rate of progress by {@link #getProgress}.
 * You can also get the final verification result by {@link #getResult}, NOTE, if the verification progress is not finished yet,
 * this method will wait until all verifications done or a certain verification return passed!
 * 
 * @author laurence.geng
 */
public class BrokerCredentialVerificationProgress {
	
	private static final int NOT_STARTED = -1;
	
	private static final int IN_PROGRESS = 0;
	
	private static final int COMPLETED = 1;
	
	private int status = NOT_STARTED;
	
	private int failedVerificationCount = 0;
	
	private VerificationReport verificationReport = new VerificationReport();
	
	private List<VerificationTask> verificationTasks = new ArrayList<VerificationTask>();
	
	public synchronized void createVerificationTask(Long brokerId,
			String username,
			String password,
			String mt4ServerName,
			String mt4SymbolSuffix,
			BrokerCredentialVerificationService service){
			VerificationTask verificationTask = new VerificationTask(brokerId, 
				   username, 
				   password, 
				   mt4ServerName, 
				   mt4SymbolSuffix,
				   service,
				   this);	
			verificationTasks.add(verificationTask);
	}

	public synchronized void start(){
		System.out.println("Starting     Thread: "+Thread.currentThread().getName()+" //Begin to start progress!");
		for (VerificationTask verificationTask : verificationTasks) {
			new Thread(verificationTask).start();
		}
		status = IN_PROGRESS;
		System.out.println("Starting     Thread: "+Thread.currentThread().getName()+" //Progress is running now!");
	}
	public synchronized boolean isInProgress(){
		return status == IN_PROGRESS;
	}
	
	public synchronized boolean isNotStarted(){
		return status == NOT_STARTED;
	}

	public synchronized boolean isCompleted(){
		return status == COMPLETED;
	}
	
	public synchronized VerificationReport getReport(){
		System.out.println("Fatch Result Thread: "+Thread.currentThread().getName()+ " //Begin to get Report ...");
		try {
			while(!isCompleted()){
				wait();
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Fatch Result Thread: "+Thread.currentThread().getName()+ " //Report is returned! Value:##"+verificationReport+"##");
		return verificationReport;
	}
	
	/**
	 * Gets the rate of progress.Say, there are 10 threads in total,
	 * if 5 threads done, return 0.5.
	 *
	 * @return the progress
	 */
	public synchronized double getProgress(){
		double progress = -1.0;
		if(isInProgress()){
			progress= (double)failedVerificationCount/verificationTasks.size();
		}else if(isCompleted()){
			progress = 1.0;
		}else if(isNotStarted()){
			progress = 0.0; 
		}
		return progress;
	}

	/**
	 * If any single verification passed, the whole verification passed, and the progress
	 * will end. Save the mt4ServerName and mt4SymbolSuffix as valid mt4ServerName and mt4SymbolSuffix
	 * setting, and at the same time, mark the whole process as done!
	 *
	 * @param validMt4ServerName the valid mt4 server name
	 * @param validMt4SymbolSuffix the valid mt4 symbol suffix
	 */
	public synchronized void acceptPassedVerification(String availableMt4ServerName, String availableMt4SymbolSuffix){
		if(status == IN_PROGRESS){
			verificationReport.setSummary(VerificationReport.PASS);
			verificationReport.setAvailableMt4ServerName(availableMt4ServerName);
			verificationReport.setAvailableMt4SymbolSuffix(availableMt4SymbolSuffix);
			status = COMPLETED;
			System.out.println("Verification Thread: "+Thread.currentThread().getName()+"_"+availableMt4ServerName+"_"+availableMt4SymbolSuffix+" //Passed!");
			notify();
		}
	}
	
	/**
	 * If a certain verification failed, invoke this method, this method will increase failed verification count.
	 * If failed verification count is equal or greater than total verification count, it means all verification tasks
	 * are done, so, make the progress status is completed.
	 */
	public synchronized void acceptFailedVerification(){
		if(status == IN_PROGRESS){
			failedVerificationCount++;
			if(failedVerificationCount>=verificationTasks.size()){
				status = COMPLETED;
			}
			System.out.println("Verification Thread: "+Thread.currentThread().getName()+" //Failed!");
			notify();
		}
	}	
	
	/**
	 * If a certain verification failed with an exception, invoke this method, this method will increase failed verification count,
	 * and keep the exception object. If failed verification count is equal or greater than total verification count, it means all verification tasks
	 * are done, so, make the progress status is completed.
	 *
	 * @param e the exception from sub verification thread.
	 */
	public synchronized void acceptVerificationWithException(Exception e){
		if(status == IN_PROGRESS){
			verificationReport.setSummary(VerificationReport.FAIL_WITH_EXCEPTIONS);
			verificationReport.addException(e);
			failedVerificationCount++;
			if(failedVerificationCount>=verificationTasks.size()){
				status = COMPLETED;
			}
			System.out.println("Verification Thread: "+Thread.currentThread().getName()+" //Throw Exception!");
			notify();
		}
	}
	
	public static class VerificationTask implements Runnable{		
		private Long brokerId;
		private String username;
		private String password;
		private String mt4ServerName;
		private String mt4SymbolSuffix;		
		private BrokerCredentialVerificationService service;	
		private BrokerCredentialVerificationProgress progress;		

		public VerificationTask(
				Long brokerId,
				String username,
				String password,
				String mt4ServerName,
				String mt4SymbolSuffix,
				BrokerCredentialVerificationService service,
				BrokerCredentialVerificationProgress progress) {
			this.brokerId = brokerId;
			this.username = username;
			this.password = password;
			this.mt4ServerName = mt4ServerName;
			this.mt4SymbolSuffix = mt4SymbolSuffix;
			this.service = service;
			this.progress = progress;
		}

		public void run() {
			try {
				boolean result = service.verifyBrokerCredential(brokerId,username,password,mt4ServerName,mt4SymbolSuffix);
				synchronized (progress) {		
					if(result){
						progress.acceptPassedVerification(mt4ServerName, mt4SymbolSuffix);
					}else{
						progress.acceptFailedVerification();
					}		
				}
			} catch (Exception e) {
				// mark this verification failed and rethrow out.
				progress.acceptVerificationWithException(e);
			}			
		}		
	}
	
	
	public static class VerificationReport {
		
		public static final String PASS = "PASS";
		public static final String FAIL = "FAIL";
		public static final String FAIL_WITH_EXCEPTIONS = "FAIL_WITH_EXCEPTIONS";
		
		private String summary = FAIL;	// Default, we make report summary as FAIL!	
		private String availableMt4ServerName;		
		private String availableMt4SymbolSuffix;
		private List<Exception> exceptions = new ArrayList<Exception>();
		
		public void addException(Exception e){
			exceptions.add(e);
		}

		public String getSummary() {
			return summary;
		}

		public void setSummary(String summary) {
			this.summary = summary;
		}

		public List<Exception> getExceptions() {
			return exceptions;
		}

		public void setExceptions(List<Exception> exceptions) {
			this.exceptions = exceptions;
		}

		public String getAvailableMt4ServerName() {
			return availableMt4ServerName;
		}

		public void setAvailableMt4ServerName(String availableMt4ServerName) {
			this.availableMt4ServerName = availableMt4ServerName;
		}

		public String getAvailableMt4SymbolSuffix() {
			return availableMt4SymbolSuffix;
		}

		public void setAvailableMt4SymbolSuffix(String availableMt4SymbolSuffix) {
			this.availableMt4SymbolSuffix = availableMt4SymbolSuffix;
		}

		@Override
		public String toString() {
			return "summary:["+summary+"],avalidMt4ServerName:["+availableMt4ServerName+"],validMt4SymbolSuffix:["+availableMt4SymbolSuffix+"],exceptions:["+exceptions+"]";
		}	
			
	}
}
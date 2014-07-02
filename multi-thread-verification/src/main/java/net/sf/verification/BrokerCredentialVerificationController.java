package net.sf.verification;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BrokerCredentialVerificationController {

	
	/* ----------------------------------------------------   METHODS NONLY FOR TEST ---------------------------------------------------- */

	public void verify() throws Exception{
		
		List<String> mt4ServerNames=new ArrayList<String>();
		mt4ServerNames.add("server0");
		mt4ServerNames.add("server1");
		mt4ServerNames.add("server2");
		mt4ServerNames.add("server3");
		mt4ServerNames.add("server4");
		mt4ServerNames.add("server5");
		mt4ServerNames.add("server6");
		mt4ServerNames.add("server7");
		mt4ServerNames.add("server8");
		mt4ServerNames.add("server9");

		List<String> mt4SymbolSuffixes = new ArrayList<String>();
		mt4SymbolSuffixes.add("suffix0");
		mt4SymbolSuffixes.add("suffix1");
		mt4SymbolSuffixes.add("suffix2");
		mt4SymbolSuffixes.add("suffix3");
		mt4SymbolSuffixes.add("suffix4");
		mt4SymbolSuffixes.add("suffix5");
		mt4SymbolSuffixes.add("suffix6");
		mt4SymbolSuffixes.add("suffix7");
		mt4SymbolSuffixes.add("suffix8");
		mt4SymbolSuffixes.add("suffix9");

		BrokerCredentialVerificationService service = new BrokerCredentialVerificationService();
		
		System.out.println("------------------------------------------------------ Test Case 1 ------------------------------------------------------\n");
		//Return true as soon as one thread returns true; Do not wait till all threads return in this case.
		int[] mockVerificationCases1 = {0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,1,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0}; // A verification passed!
		service.setMockVerificationCases(mockVerificationCases1);
		service.setCurrentCaseIndex(0);
		BrokerCredentialVerificationProgress progress1 = service.verifyBrokerCredential(1L, "", "", mt4ServerNames, mt4SymbolSuffixes);			
		// simulate first user request thread: get verification result.
		new VerificationHttpRequest(progress1).start();		
		// simulate subsequent user request threads: query verification progress.
		new QueryProgressHttpRequest(progress1).start();
		
		TimeUnit.SECONDS.sleep(20);
		
		System.out.println("\n\n------------------------------------------------------ Test Case 2 ------------------------------------------------------\n");
		//If all threads return false, throw custom exception: NoSuchBrokerCredentialException 
		int[] mockVerificationCases2 = {0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0};// ALL FAILED!
		service.setMockVerificationCases(mockVerificationCases2);
		service.setCurrentCaseIndex(0);		
		// ISSUE: if move this line into run method of VerificationHttpRequest, when QueryProgressHttpRequest is running,
		// a null pointer exception will pop up! how to cache the progress object is a problem.
		BrokerCredentialVerificationProgress progress2 = service.verifyBrokerCredential(1L, "", "", mt4ServerNames, mt4SymbolSuffixes);		
		// simulate first user request thread: get verification result.
		new VerificationHttpRequest(progress2).start();		
		// simulate subsequent user request threads: query verificationprogress.
		new QueryProgressHttpRequest(progress2).start();

		TimeUnit.SECONDS.sleep(15);

		System.out.println("\n\n------------------------------------------------------ Test Case 3 ------------------------------------------------------\n");
		//return true as soon as one thread returns true; Do not wait till all threads return in this case.
		int[] mockVerificationCases3 = {0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,2,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0,
										0,0,3,0,0,0,0,0,0,0,
										0,0,0,0,0,0,0,0,0,0}; // throw 2 exceptions.
		service.setMockVerificationCases(mockVerificationCases3);
		service.setCurrentCaseIndex(0);

		BrokerCredentialVerificationProgress progress3 = service.verifyBrokerCredential(1L, "", "", mt4ServerNames, mt4SymbolSuffixes);		
		// simulate first user request thread: get verification result.
		new VerificationHttpRequest(progress3).start();		
		// simulate subsequent user request threads: query verificationprogress.
		new QueryProgressHttpRequest(progress3).start();

	}
	
	/* A Thread to simulate a verification request thread */
	private static class VerificationHttpRequest extends Thread {
		
		private BrokerCredentialVerificationProgress progress;

		public VerificationHttpRequest(BrokerCredentialVerificationProgress progress) {
			super();
			this.progress = progress;
		}

		@Override
		public void run() {
			synchronized (progress) {
				progress.start();
				progress.getReport();
			}
		}		
	}
	
	/* A Thread to simulate a query progress request thread */
	private static class QueryProgressHttpRequest extends Thread {
		
		private BrokerCredentialVerificationProgress progress;

		public QueryProgressHttpRequest(BrokerCredentialVerificationProgress progress) {
			super();
			this.progress = progress;
		}

		@Override
		public void run() {
			while(true){
				try {
					synchronized (progress) {
						int progressNum = (int)(progress.getProgress()*100);
						System.out.println("Query Progre Thread: "+Thread.currentThread().getName()+" //Progress: "+progressNum+"%");
						if(progressNum==100){break;}
					}
					TimeUnit.MILLISECONDS.sleep(1000);					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		}		
		
	}

	public static void main(String[] args) {
		try {
			new BrokerCredentialVerificationController().verify();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

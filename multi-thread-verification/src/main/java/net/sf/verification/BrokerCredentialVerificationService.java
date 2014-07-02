package net.sf.verification;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BrokerCredentialVerificationService {
	
	// NONLY FOR TEST
	private int[] mockVerificationCases;
	
	// NONLY FOR TEST
	private int currentCaseIndex;
	
	// MOCK STUB METHOD & NONLY FOR TEST
	public boolean verifyBrokerCredential (Long brokerId, String username, String password, String mt4ServerName, String mt4SymbolSuffix) throws Exception{		
		int vcase = mockVerificationCases[currentCaseIndex++];
		TimeUnit.SECONDS.sleep(new Random().nextInt(10)); 
		boolean result = false;
		switch (vcase) {
		case 0: // 0: verification failed.
			result = false;
			break;			
		case 1: // 1: verification passed.
			result = true;
			break;
		case 2: // 2: Throw null pointer exception.
			throw new RuntimeException("Thread: "+Thread.currentThread().getName() +" Throw Exception A.");
		case 3: // 2: Throw null pointer exception.
			throw new RuntimeException("Thread: "+Thread.currentThread().getName() +" Throw Exception B.");
		}
		
		return result;
	}
	
	/**
	 * Verify broker credentials by given mt4 server names & symbol suffixes composition and return
	 * a verification progress object immediately. The caller can get verification result or query
	 * the rate of progress by the progress object.
	 *
	 * @param brokerId the broker id
	 * @param username the username
	 * @param password the password
	 * @param mt4ServerNames the mt4 server names
	 * @param mt4SymbolSuffixes the mt4 symbol suffixes
	 * @return the broker credential verification progress
	 */
	public BrokerCredentialVerificationProgress verifyBrokerCredential(Long brokerId, 
																	   String username, 
																	   String password, 
																	   List<String> mt4ServerNames, 
																	   List<String> mt4SymbolSuffixes){
		BrokerCredentialVerificationProgress progress = new BrokerCredentialVerificationProgress();
		for (String mt4ServerName : mt4ServerNames) {
			for (String mt4SymbolSuffix : mt4SymbolSuffixes) {
				progress.createVerificationTask(brokerId, username, password, mt4ServerName, mt4SymbolSuffix, this);
			}
		}
		return progress;
	}
	
	// NONLY FOR TEST
	public void setMockVerificationCases(int[] mockVerificationCases) {
		this.mockVerificationCases = mockVerificationCases;
	}

	// NONLY FOR TEST
	public void setCurrentCaseIndex(int currentCaseIndex) {
		this.currentCaseIndex = currentCaseIndex;
	}
}

package com.threeti.umax.sample.salesmockapp.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threeti.umax.sample.salesmockapp.dao.ContractDao;
import com.threeti.umax.sample.salesmockapp.model.Contract;
import com.threeti.umax.sample.salesmockapp.service.ContractManager;
import com.threeti.umax.sample.salesmockapp.service.ContractService;


/**
 * Implementation of ContractManager interface.
 *
 * @author Laurence Geng
 */
@Service("contractManager")
@WebService(serviceName = "ContractService", 
		    endpointInterface = "com.threeti.umax.sample.salesmockapp.service.ContractService",
		    targetNamespace="http://service.salesmockapp.sample.umax.threeti.com/")
public class ContractManagerImpl extends GenericManagerImpl<Contract, Long> implements ContractManager, ContractService {

    private ContractDao contractDao;
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ContractManagerImpl.class);

    @Autowired
    public void setContractDao(ContractDao contractDao) {
        this.dao = contractDao;
        this.contractDao = contractDao;
    }

    /**
     * {@inheritDoc}
     */
    public Contract getContract(String contractId) {
        return contractDao.get(new Long(contractId));
    }
    
    /**
     * {@inheritDoc}
     */
	public void setContractState(String contractId, String state) {
		Contract contract = getContract(contractId);
		contract.setState(state);
		saveContract(contract);
		logger.info("The contract ID" + contractId +" has set state as: "+state);
	}

	/**
     * {@inheritDoc}
     */
    public List<Contract> getContracts() {
        return contractDao.getAllDistinct();
    }

    /**
     * {@inheritDoc}
     */
	public Contract saveContract(Contract contract) {
		return contractDao.save(contract);
	}

 
}

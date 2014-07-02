package com.threeti.umax.sample.workflow.service.impl;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.threeti.umax.sample.salesmockapp.service.Contract;
import com.threeti.umax.sample.salesmockapp.service.ContractService;
import com.threeti.umax.sample.workflow.service.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/applicationContext.xml")
public class TaskServiceImplTest {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private ContractService contractService;
	
	@Test
	public void testStartProcessInstanceByKey() {
		//Prepare variables for starting process.
		Map<String, Object> variables = new HashMap<String, Object>();
		String contractId = "-1";
	    Contract contract = contractService.getContract(contractId);
	    variables.put("contractId", contractId);
	    variables.put("contract", contract);	  
	    
	    //Starts process
	    taskService.startProcessInstanceByKeyWithVariables("ExamineContractProcess",variables);
	}

}

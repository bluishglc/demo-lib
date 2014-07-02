package com.threeti.umax.sample.workflow;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.activiti.spring.impl.test.SpringActivitiTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.threeti.umax.sample.salesmockapp.service.Contract;
import com.threeti.umax.sample.salesmockapp.service.ContractService;

@ContextConfiguration("classpath:/applicationContext-Test.xml")
public class ExamineContractProcessTest extends SpringActivitiTestCase {
	
	@Autowired
	private ContractService contractService;

	@Deployment(resources={"diagrams/ExamineContractProcess.bpmn20.xml"})
	public void testExamineContractProcessWithoutRisk(){
		
		//Prepare variables for starting process.
		Map<String, Object> variables = new HashMap<String, Object>();
		String contractId = "-1";
	    Contract contract = contractService.getContract(contractId);
	    variables.put("contractId", contractId);
	    variables.put("contract", contract);	  
	    
	    //Starts process
		ProcessInstance ps = runtimeService.startProcessInstanceByKey("ExamineContractProcess",variables);
//		waitForJobExecutorToProcessAllJobs(1000L, 250L);
		
		// Assert evaluateFinancialRiskTask's result!
		String executionId = ps.getId();
		Boolean existCreditRisk = (Boolean) runtimeService.getVariable(executionId, "existCreditRisk");
		assertEquals(new Boolean(false), existCreditRisk);
		
		// Assert salesManagerReviewTask is generated!
		Task salesManagerReviewTask = taskService.createTaskQuery().executionId(executionId).taskName("salesManagerReviewTask").singleResult();
		assertNotNull(salesManagerReviewTask);
		
		// Assert salesManagerReviewTask is not generated!
		Task salesDirectorReviewTask = taskService.createTaskQuery().executionId(executionId).taskName("salesDirectorReviewTask").singleResult();
		assertNull(salesDirectorReviewTask);
		
		// Complete salesManagerReviewTask with userId = -1 & var: salesManagerApproved = true
		variables.clear();
		variables.put("salesManagerApproved", new Boolean(true));
		processEngine.getTaskService().claim(salesManagerReviewTask.getId(), "-1");
		processEngine.getTaskService().complete(salesManagerReviewTask.getId(), variables);
		
		//Assert there is one and only one historic process.
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().singleResult();
		assertNotNull(historicProcessInstance);
	}
	
	@Deployment(resources={"diagrams/ExamineContractProcess.bpmn20.xml"})
	public void testExamineContractProcessWithRisk(){
		
		//Prepare variables for starting process.
		Map<String, Object> variables = new HashMap<String, Object>();
		String contractId = "-2";
	    Contract contract = contractService.getContract(contractId);
	    variables.put("contractId", contractId);
	    variables.put("contract", contract);	  
	    
	    //Starts process
		ProcessInstance ps = runtimeService.startProcessInstanceByKey("ExamineContractProcess",variables);
//		waitForJobExecutorToProcessAllJobs(1000L, 250L);
		
		// Assert evaluateFinancialRiskTask's result!
		String executionId = ps.getId();
		Boolean existCreditRisk = (Boolean) runtimeService.getVariable(executionId, "existCreditRisk");
		assertEquals(new Boolean(true), existCreditRisk);
		
		// Assert salesManagerReviewTask is generated!
		Task salesManagerReviewTask = taskService.createTaskQuery().executionId(executionId).taskName("salesManagerReviewTask").singleResult();
		assertNull(salesManagerReviewTask);
		
		// Assert salesManagerReviewTask is not generated!
		Task salesDirectorReviewTask = taskService.createTaskQuery().executionId(executionId).taskName("salesDirectorReviewTask").singleResult();
		assertNotNull(salesDirectorReviewTask);
		
		// Complete salesManagerReviewTask with userId = -1 & var: salesManagerApproved = true
		variables.clear();
		variables.put("salesDirectorApproved", new Boolean(false));
		processEngine.getTaskService().claim(salesDirectorReviewTask.getId(), "-2");
		processEngine.getTaskService().complete(salesDirectorReviewTask.getId(), variables);
		
		//Assert there is one and only one historic process.
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().singleResult();
		assertNotNull(historicProcessInstance);
	}
}

package com.threeti.umax.sample.workflow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threeti.umax.sample.financialmockapp.service.Credit;
import com.threeti.umax.sample.salesmockapp.service.Contract;
import com.threeti.umax.sample.workflow.model.Task;
import com.threeti.umax.sample.workflow.service.TaskService;
import com.threeti.umax.sample.workflow.util.KeyValueEntry;
import com.threeti.umax.sample.workflow.util.TaskAssembler;
import com.threeti.umax.sample.workflow.util.VariableMapAdaptor;



/**
 * The Activiti Implementation for 
 * 
 * @author laurence.geng
 * 
 */
@Service("taskService")
@WebService(serviceName = "TaskService", 
endpointInterface = "com.threeti.umax.sample.workflow.service.TaskService", 
targetNamespace="http://service.workflow.sample.umax.threeti.com/")
public class TaskServiceImpl implements TaskService{ 
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TaskServiceImpl.class);
	
	protected org.activiti.engine.TaskService activitiTaskService;
	protected org.activiti.engine.RepositoryService repositoryService;
	protected org.activiti.engine.RuntimeService runtimeService;
	protected org.activiti.engine.HistoryService historyService;
	protected org.activiti.engine.ManagementService managementService;

	@Override
	public void startExamineContractProcess(Contract contract) {
		logger.info("Starts ExamineContractProcess for contract: ID:"+contract.getId());
		String contractId =  contract.getId().toString();
		Map<String, Object> variables = new HashMap<String, Object>();
	    variables.put("contractId", contractId);
	    variables.put("contract", contract);	
	    runtimeService.startProcessInstanceByKey("ExamineContractProcess",variables);
	    logger.info("ExamineContractProcess for contract: ID:"+contract.getId()+" has started!");
	}

	@Override
	public void completeTask(String taskId, String variable, Boolean approved) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(variable, approved);
		activitiTaskService.complete(taskId, variables);
		logger.info("Task ID:" + taskId + " has completed!");
	}

	@Override
	public Contract getContractVariable(String taskId) {
		Contract contract = (Contract) activitiTaskService.getVariable(taskId,"contract");
		return contract;
	}
	
	/* Below is generic workflow API */

	@Override
	public void startProcessInstanceByKey(String processDefinitionKey) {
		runtimeService.startProcessInstanceByKey(processDefinitionKey);
	}

	@Override
	public void startProcessInstanceByKeyWithVariables(String processDefinitionKey,	Map<String, Object> variables) {
		runtimeService.startProcessInstanceByKey(processDefinitionKey,variables);
	}



	@Override
	public void claim(String taskId, String userId) {
		activitiTaskService.claim(taskId, userId);
		logger.info("Task ID:" + taskId + " has claimed by user ID:"+userId);
	}


	@Override
	public void complete(String taskId) {
		activitiTaskService.complete(taskId);
	}

	@Override
	public void completeWithVariables(String taskId, Map<String, Object> variables) {
		activitiTaskService.complete(taskId, variables);
	}

	@Override
	public List<Task> getTasks(String userName,List<String> userGroups) {
		
		List<org.activiti.engine.task.Task> candidateUserTasks = activitiTaskService.createTaskQuery().taskCandidateUser(userName).list();
		List<org.activiti.engine.task.Task> candidateGroupTasks = new ArrayList<org.activiti.engine.task.Task>();
		for (String userGroup : userGroups) {
			List<org.activiti.engine.task.Task> perCandidateGroupTasks = activitiTaskService.createTaskQuery().taskCandidateGroup(userGroup).list();
			candidateGroupTasks.addAll(perCandidateGroupTasks);
		}
		List<org.activiti.engine.task.Task> allTasks = new ArrayList<org.activiti.engine.task.Task>();
		allTasks.addAll(candidateUserTasks);
		allTasks.addAll(candidateGroupTasks);
		TaskAssembler taskAssembler = new TaskAssembler();
		List<Task> allMyTasks = filterDuplicatedTasks(taskAssembler.extractAll(allTasks));
		return allMyTasks;
	}
	
	/**
	 * Filter duplicated tasks.
	 *
	 * @param tasks the tasks
	 * @return the list
	 */
	private List<Task> filterDuplicatedTasks(List<Task> tasks){
		Map<String, Task> distinctTasks = new HashMap<String, Task>();
		for (Task task : tasks) {
			if(!distinctTasks.containsKey(task.getId())){
				distinctTasks.put(task.getId(), task);
			}
		}
		List<Task> allDistinctTasks = new ArrayList<Task>();
		allDistinctTasks.addAll(distinctTasks.values());
		return allDistinctTasks;		
	}

//	@Override
//	public Object getVariable(String taskId, String variableName) {
//		return activitiTaskService.getVariable(taskId, variableName);
//	}

	@Override
	public Map<String, Object> getVariables(String taskId) {
		return activitiTaskService.getVariables(taskId);
	}

	@Autowired
	public void setActivitiTaskService(
			org.activiti.engine.TaskService activitiTaskService) {
		this.activitiTaskService = activitiTaskService;
	}

	@Autowired
	public void setRepositoryService(
			org.activiti.engine.RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	@Autowired
	public void setRuntimeService(
			org.activiti.engine.RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	@Autowired
	public void setHistoryService(
			org.activiti.engine.HistoryService historyService) {
		this.historyService = historyService;
	}

	@Autowired
	public void setManagementService(
			org.activiti.engine.ManagementService managementService) {
		this.managementService = managementService;
	}

}

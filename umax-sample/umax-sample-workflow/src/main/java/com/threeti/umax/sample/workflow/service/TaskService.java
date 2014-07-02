
package com.threeti.umax.sample.workflow.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.threeti.umax.sample.financialmockapp.service.Credit;
import com.threeti.umax.sample.salesmockapp.service.Contract;
import com.threeti.umax.sample.workflow.model.Task;
import com.threeti.umax.sample.workflow.util.KeyValueEntry;
import com.threeti.umax.sample.workflow.util.VariableMap;
import com.threeti.umax.sample.workflow.util.VariableMapAdaptor;

/**
 * Service which provides access to {@link Task} and form related operations.
 * 
 * @author Laurence Geng
 */
@WebService
public interface TaskService {
	
	/**
	 * Starts examine contract process.
	 *
	 * @param contractId the contract id
	 * @param contract the contract
	 */
	void startExamineContractProcess(Contract contract);
	
	/**
	 * Complete.
	 *
	 * @param taskId the task id
	 * @param variable the variable
	 * @param approved the approved
	 */
	void completeTask(String taskId, String variable, Boolean approved);
	
	/**
	 * Gets the contract variable.
	 *
	 * @param taskId the task id
	 * @return the contract variable
	 */
	Contract getContractVariable(String taskId);
	
	/* Below is generic API for workflow, there is potential problems when marshal & unmarshal for variables. */
	
	/**
	 * Starts a new process instance in the latest version of the process
	 * definition with the given key.
	 * 
	 * @param processDefinitionKey
	 *            key of process definition, cannot be null.
	 * @throws ActivitiException
	 *             when no process definition is deployed with the given key.
	 */
	void startProcessInstanceByKey(String processDefinitionKey);
	
	  /** 
	   * Starts a new process instance in the latest version of the process definition with the given key 
	   * @param processDefinitionKey key of process definition, cannot be null.
	   * @param variables the variables to pass, can be null.
	   * @throws ActivitiException when no process definition is deployed with the given key. 
	   */
	 void startProcessInstanceByKeyWithVariables(String processDefinitionKey, @XmlJavaTypeAdapter(VariableMapAdaptor.class)Map<String, Object> variables);

	/**
	 * Claim responsibility for a task: the given user is made assignee for the
	 * task. The difference with {@link #setAssignee(String, String)} is that
	 * here a check is done if the provided user is known by the identity
	 * component.
	 * 
	 * @param taskId
	 *            task to claim, cannot be null.
	 * @param userId
	 *            user that claims the task. When userId is null the task is
	 *            unclaimed, assigned to no one.
	 * @throws ActivitiException
	 *             when the user or task doesn't exist or when the task is
	 *             already claimed by another user.
	 */
	void claim(String taskId, String userId);

	/**
	 * Called when the task is successfully executed.
	 * 
	 * @param taskId
	 *            the id of the task to complete, cannot be null.
	 * @throws ActivitiException
	 *             when no task exists with the given id or when this task is
	 *             {@link DelegationState#PENDING} delegation.
	 */
	void complete(String taskId);

	/**
	 * Called when the task is successfully executed, and the required task
	 * parameters are given by the end-user.
	 * 
	 * @param taskId
	 *            the id of the task to complete, cannot be null.
	 * @param variables
	 *            task parameters. May be null or empty.
	 * @throws ActivitiException
	 *             when no task exists with the given id.
	 */
	void completeWithVariables(String taskId, @XmlJavaTypeAdapter(VariableMapAdaptor.class)Map<String, Object> variables);

	/**
	 * Gets tasks for given user.
	 *
	 * @param userId the user id
	 * @return the tasks
	 */
	List<Task> getTasks(String userName,List<String> userGroups);

	/**
	 * get a variables and search in the task scope and if available also the
	 * execution scopes.
	 */
	//Object getVariable(String taskId, String variableName);

	/**
	 * get all variables and search in the task scope and if available also the
	 * execution scopes. If you have many variables and you only need a few,
	 * consider using {@link #getVariables(String, Collection)} for better
	 * performance.
	 */
	@XmlJavaTypeAdapter(VariableMapAdaptor.class)Map<String, Object> getVariables(String taskId);

}

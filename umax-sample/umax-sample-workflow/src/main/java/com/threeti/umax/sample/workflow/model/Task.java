package com.threeti.umax.sample.workflow.model;

import java.io.Serializable;
import java.util.Date;

/**
 * The Task is similar to  humane-task in workflow.
 * 
 * @author laurence.geng
 */
public class Task implements Serializable {

	private static final long serialVersionUID = -5175166910189787914L;

	protected String id;
	protected String assignee;
	protected String name;
	protected String description;
	protected Date createTime;
	protected String executionId; // Activiti specific field, temp reserved!
	protected String processInstanceId; // Activiti specific field, temp reserved!
	protected String processDefinitionId; // Activiti specific field, temp reserved!

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	// protected int priority = PRIORITY_NORMAL;
	// protected String parentTaskId;
	// protected String owner;
	// protected Date dueDate;
	// protected boolean isIdentityLinksInitialized = false;
	// protected List<IdentityLinkEntity> taskIdentityLinkEntities = new
	// ArrayList<IdentityLinkEntity>();
	// protected String executionId;
	// protected ExecutionEntity execution;
	// protected ExecutionEntity processInstance;
	// protected TaskDefinition taskDefinition;
	// protected String taskDefinitionKey;
	// protected boolean isDeleted;
	// protected String eventName;
	// protected DelegationState delegationState;

}

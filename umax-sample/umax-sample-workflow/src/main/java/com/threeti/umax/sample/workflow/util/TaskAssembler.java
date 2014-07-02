package com.threeti.umax.sample.workflow.util;

import java.util.ArrayList;
import java.util.List;

import com.threeti.umax.sample.workflow.model.Task;

public class TaskAssembler {
	
	public Task extract(org.activiti.engine.task.Task task){
		Task mytask = new Task();
		mytask.setAssignee(task.getAssignee());
		mytask.setCreateTime(task.getCreateTime());
		mytask.setDescription(task.getDescription());
		mytask.setExecutionId(task.getExecutionId());
		mytask.setId(task.getId());
		mytask.setName(task.getName());
		mytask.setProcessDefinitionId(task.getProcessDefinitionId());
		mytask.setProcessInstanceId(task.getProcessInstanceId());
		return mytask;
	}
	
	public List<Task> extractAll(List<org.activiti.engine.task.Task> tasks){
		List<Task> mytasks = new ArrayList<Task>();
		for (org.activiti.engine.task.Task task : tasks) {
			mytasks.add(extract(task));
		}
		return mytasks;
	}
}

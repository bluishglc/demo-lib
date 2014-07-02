package com.threeti.UMax.task.business
{
	import mx.collections.ArrayCollection;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.soap.WebService;

	public class TaskDelegate
	{
		private var resultFunc:Function;
		private var faultFunc:Function;
		private var service:WebService;
		
		public function TaskDelegate(resultFunc:Function=null, faultFunc:Function=null)
		{
			this.resultFunc = resultFunc;
			this.faultFunc = faultFunc;
			this.service = new WebService()
			service.loadWSDL("http://localhost:9090/services/TaskService?wsdl");
			service.getOperation("taskService");
			
			if(resultFunc != null) {
				service.addEventListener(ResultEvent.RESULT, resultFunc);
			}
			
			if(faultFunc != null) {
				service.addEventListener(FaultEvent.FAULT, faultFunc);
			}
		}
		
		/**
		 * 启动处理合同
		 * @param contract 合同
		 */ 
		public function startExamineContract(contract:Object):void {
			service.startExamineContractProcess(contract);
		}
		
		/**
		 * 取得相关任务信息
		 * @param username		用户名
		 * @param usergroup		用户组名
		 */ 
		public function getTasks(username:String, usergroup:ArrayCollection):void 
		{
			service.getTasks(username, usergroup);
		}
		
		/**
		 * 根据任务ID取的相关合同信息
		 * @param taskID	任务ID
		 */ 
		public function getContractVariable(taskID:String):void {
			service.getContractVariable(taskID);
		}
		
		/**
		 * Claim responsibility for a task 
		 * @param taskID	任务ID
		 * @param userID	用户ID
		 */
		public function claim(taskID:String, userID:String):void {
			service.claim(taskID, userID);
		}
		
		public function completeTask(taskID:String, variable:String, isApprove:Boolean):void {
			service.completeTask(taskID, variable, isApprove);
		}
	}
}
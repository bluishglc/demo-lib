package com.threeti.UMax.main.command
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.view.ViewLocator;
	import com.threeti.UMax.main.business.UserDelegate;
	import com.threeti.UMax.main.model.MainModelLocator;
	import com.threeti.UMax.main.view.LoginViewHelper;
	import com.threeti.UMax.task.business.TaskDelegate;
	import com.threeti.UMax.task.model.TaskModel;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.rpc.events.ResultEvent;

	public class GetUserByUsernameCommand extends BaseCommand
	{
		public function GetUserByUsernameCommand()
		{
			super();
		}
		
		override public function execute(event:CairngormEvent):void
		{
			var loginViewHelper:LoginViewHelper = 
				ViewLocator.getInstance().getViewHelper(LoginViewHelper.LOGIN_VIEW) as LoginViewHelper;
			var delegate:UserDelegate = new UserDelegate(this);
			delegate.getUserByUsername(loginViewHelper.currentView.txtUserName.text);
		}
		
		override public function result(data:Object):void
		{
			var event:ResultEvent = data as ResultEvent;
//			var userVO:UserVO = event.result as UserVO;
			MainModelLocator.getInstance().userVO = event.result;
			getTasks();
		}
		
		private function getTasks():void {
			var delegate:TaskDelegate = new TaskDelegate(getTasksResultHandler);
			var loginUser:Object = MainModelLocator.getInstance().userVO;
			var roleNames:ArrayCollection = new ArrayCollection();
			for each(var o:Object in loginUser.roles) {
				roleNames.addItem(o.name);
			}
			delegate.getTasks(loginUser.username, roleNames);
		}
		
		private function getTasksResultHandler(event:ResultEvent):void {
			if(event.result) {
				if(event.result is ArrayCollection) {
					TaskModel.getInstance().taskList = event.result as ArrayCollection;
					var sort:Sort = new Sort();
					sort.fields = [new SortField("createTime", true, true)];
					TaskModel.getInstance().taskList.sort = sort;
				} else {
					TaskModel.getInstance().taskList.removeAll();
					TaskModel.getInstance().taskList.addItem(event.result);
				}
			}else {
				TaskModel.getInstance().taskList.removeAll();
			}
		}
	}
}
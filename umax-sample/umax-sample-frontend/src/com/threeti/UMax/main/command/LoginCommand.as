package com.threeti.UMax.main.command
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.adobe.cairngorm.view.ViewLocator;
	import com.threeti.UMax.main.business.UserDelegate;
	import com.threeti.UMax.main.control.MainController;
	import com.threeti.UMax.main.view.LoginViewHelper;
	import com.threeti.UMax.main.view.MainViewHelper;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.events.ResultEvent;

	public class LoginCommand extends BaseCommand
	{
		public function LoginCommand()
		{
			super();
		}
		
		override public function execute(event:CairngormEvent):void
		{
			var loginViewHelper:LoginViewHelper = 
				ViewLocator.getInstance().getViewHelper(LoginViewHelper.LOGIN_VIEW) as LoginViewHelper;
			var delegate:UserDelegate = new UserDelegate(this);
			delegate.login(loginViewHelper.currentView.txtUserName.text, loginViewHelper.currentView.txtPwd.text);
		}
		
		override public function result(data:Object):void
		{
			var event:ResultEvent = data as ResultEvent;
			var result:int = event.result as int;
			
			var mainViewHelper:MainViewHelper = 
				ViewLocator.getInstance().getViewHelper(MainViewHelper.MAIN_VIEW) as MainViewHelper;
			
			if (result == 1) {
				
				mainViewHelper.login();
				mainViewHelper.currentView.currentState = "main";
				mainViewHelper.currentView.loginView.lblError.text = "";
				CairngormEventDispatcher.getInstance().dispatchEvent(new CairngormEvent(MainController.GET_USER_BY_USERNAME));
				
			} else if (result == 2) {
				
				mainViewHelper.currentView.loginView.lblError.text = "用户名或密码错误!";
				
			}
		}
	}
}
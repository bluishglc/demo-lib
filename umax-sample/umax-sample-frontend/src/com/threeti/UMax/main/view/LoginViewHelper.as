package com.threeti.UMax.main.view
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.adobe.cairngorm.view.ViewHelper;
	import com.adobe.cairngorm.view.ViewLocator;
	import com.threeti.UMax.main.control.MainController;
	
	import flash.events.KeyboardEvent;
	import flash.ui.Keyboard;
	
	import mx.events.ValidationResultEvent;
	
	public class LoginViewHelper extends ViewHelper
	{
		public static const LOGIN_VIEW:String = "login_view";
		
		public function LoginViewHelper()
		{
			super();
		}
		
		public function get currentView():LoginView
		{
			return view as LoginView;
		}
		
		override public function initialized(document:Object, id:String):void
		{
			super.initialized(document, id);
			ViewLocator.getInstance().register(LOGIN_VIEW, this);
		}
		
		public function init():void
		{
			currentView.addEventListener(
				KeyboardEvent.KEY_UP, function(e:KeyboardEvent):void 
				{
					if (e.keyCode == Keyboard.ENTER) {
						login();
					}
				});
			
			currentView.txtUserName.setFocus();
		}
		
		public function login():void 
		{
			if(currentView.nameValidator.validate().type == ValidationResultEvent.INVALID) 
			{
				currentView.lblError.text = "请输入用户名!";
				return;
			}
			
			if(currentView.pwdValidator.validate().type == ValidationResultEvent.INVALID) 
			{
				currentView.lblError.text = "请输入密码!";
				return;
			}
			
			var e:CairngormEvent = new CairngormEvent(MainController.LOGIN);
			CairngormEventDispatcher.getInstance().dispatchEvent(e);
		}
		
	}
}
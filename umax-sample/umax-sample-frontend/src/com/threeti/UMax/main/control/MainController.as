package com.threeti.UMax.main.control
{
	import com.adobe.cairngorm.control.FrontController;
	import com.threeti.UMax.main.command.GetUserByUsernameCommand;
	import com.threeti.UMax.main.command.LoadConfigurationCommand;
	import com.threeti.UMax.main.command.LoginCommand;
	

	public class MainController extends FrontController
	{
		public static const LOAD_APPLICATION_CONFIGURATION:String = "load_application_configuration";
		public static const LOGIN:String = "login";
		public static const GET_USER_BY_USERNAME:String = "Get_User_By_Username";
		
		public function MainController()
		{
			addCommand(LOAD_APPLICATION_CONFIGURATION, LoadConfigurationCommand);
			addCommand(LOGIN, LoginCommand);
			addCommand(GET_USER_BY_USERNAME,GetUserByUsernameCommand);
		}
		
	}
}
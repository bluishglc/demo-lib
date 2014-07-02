package com.threeti.UMax.main.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	import mx.rpc.soap.WebService;

	public class UserDelegate
	{
		private var responder:IResponder;
		private var service:WebService;
		
		public function UserDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getWebService("UserService");
		}
		
		public function login(userName:String, pwd:String):void 
		{
			var call:AsyncToken = service.authenticate(userName, pwd);
			call.addResponder(responder);
		}
		
		public function getUserByUsername(userName:String):void
		{
			var call:AsyncToken = service.getUserByUsername(userName);
			call.addResponder(responder);
		}
		
	}
}
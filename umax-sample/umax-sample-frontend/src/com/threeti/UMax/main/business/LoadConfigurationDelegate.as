package com.threeti.UMax.main.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.http.HTTPService;
	import mx.rpc.remoting.RemoteObject;
	
	/**
	 * 加载配置文件的delegate
	 */	
	public class LoadConfigurationDelegate
	{
		private var responder:IResponder;
		private var service:HTTPService;
		
		public function LoadConfigurationDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getHTTPService("loadConfiguration");
		}
		
		public function loadConfiguration():void 
		{
			var call:AsyncToken = service.send();
			call.addResponder(responder);
		}
		
	}
}
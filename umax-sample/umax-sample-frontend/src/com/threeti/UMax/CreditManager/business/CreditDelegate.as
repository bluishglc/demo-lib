package com.threeti.UMax.CreditManager.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.messaging.ChannelSet;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.soap.Operation;
	import mx.rpc.soap.WebService;

	public class CreditDelegate
	{
		private var resultFunc:Function;
		private var faultFunc:Function;
		private var service:WebService;
		
		private var o:Operation;
		
		public function CreditDelegate(resultFunc:Function, faultFunc:Function)
		{
			this.resultFunc = resultFunc;
			this.faultFunc = faultFunc;
			this.service = new WebService() //ServiceLocator.getInstance().getWebService("contractService");
			service.loadWSDL("http://localhost:9090/services/CreditServices?wsdl");
			service.getOperation("getCredits");
			
		}
		
		public function GetCredit():void 
		{
			service.getCredits();
			service.addEventListener(ResultEvent.RESULT, resultFunc);
			service.addEventListener(FaultEvent.FAULT, faultFunc);
		}
	}
}
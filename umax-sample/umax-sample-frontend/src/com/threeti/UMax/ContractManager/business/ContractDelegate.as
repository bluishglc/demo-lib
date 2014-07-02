package com.threeti.UMax.ContractManager.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.collections.ArrayCollection;
	import mx.messaging.ChannelSet;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.soap.Operation;
	import mx.rpc.soap.WebService;
	import mx.rpc.soap.mxml.Operation;

	public class ContractDelegate
	{
		private var resultFunc:Function;
		private var faultFunc:Function;
		private var service:WebService;
		
		public function ContractDelegate(resultFunc:Function=null, faultFunc:Function=null)
		{
			this.resultFunc = resultFunc;
			this.faultFunc = faultFunc;
			this.service = new WebService() //ServiceLocator.getInstance().getWebService("contractService");
			service.loadWSDL("http://localhost:9090/services/ContractServices?wsdl");
			service.getOperation("contractService").resultType = ArrayCollection;
			
			if(resultFunc != null) {
				service.addEventListener(ResultEvent.RESULT, resultFunc);
			}
			
			if(faultFunc != null) {
				service.addEventListener(FaultEvent.FAULT, faultFunc);
			}
		}
		
		public function GetContracts():void 
		{
			service.getContracts();
		}
	}
}
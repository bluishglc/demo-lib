package com.threeti.UMax.main.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.commands.SequenceCommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.view.ViewLocator;
	import com.threeti.UMax.main.constant.ErrorList;
	import com.threeti.UMax.main.view.MainViewHelper;
	import com.threeti.UMax.main.vo.Results;
	
	import flash.events.IOErrorEvent;
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	
	import mx.controls.Alert;
	import mx.core.FlexGlobals;
	import mx.messaging.messages.ErrorMessage;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;

	/**
	 * 系统中command类的基类，最好所有command均继承自该类，提供统一的错误处理
	 * @author 王成
	 * @version 1.0
	 */	
	public class BaseCommand extends SequenceCommand implements IResponder
	{
		/**
		 * 错误代码 
		 */		
		public var errorCode:int;
		
		/**
		 * 错误信息
		 */		
		public var errorDesc:String;
		
		public function BaseCommand()
		{
			super();
		}
		
		public function result(data:Object):void 
		{
			var event:ResultEvent = data as ResultEvent;
			var results:Results = event.result as Results;
			if (results.ret == -10) {
				var request:URLRequest = new URLRequest(FlexGlobals.topLevelApplication.loaderInfo.url);
				navigateToURL(request, "_self");
			}
		}
		
		public function fault(info:Object):void 
		{
			//统一失败处理，例如根据errorCode作出相应处理，
			//处理动作均可被子类重写，但一般情况下是不需要重写的
			
			trace(ObjectUtil.toString(info));
			
			getErrorMessage(info as FaultEvent);
			
			handleException();
		}
		
		/**
		 * 提取错误信息
		 */		
		protected function getErrorMessage(event:FaultEvent):void
		{
			errorCode = ErrorList.UNKNOWN_EXCEPTIN;
			errorDesc = "未知异常";
			var faultMessage:ErrorMessage = event.message as ErrorMessage;
			try
			{
				var serverException:Object = faultMessage.rootCause;
				
				//errorCode和errorDesc是服务端自定义异常中的信息，
				//服务端可捕捉到异常然后抛出该自定义异常反馈给客户端
				if(serverException.hasOwnProperty("errorCode"))
				{
					errorCode = serverException.errorCode;
					errorDesc = serverException.errorDesc;
				}
				else if(serverException.hasOwnProperty("localizedMessage"))//服务端未捕捉到的异常
				{
					errorCode = ErrorList.UNCATCHED_EXCEPTION;
					errorDesc = serverException.localizedMessage as String;
				}
				else if(serverException is IOErrorEvent)
				{
					errorCode = ErrorList.LOADING_EXCEPTION;
					errorDesc = IOErrorEvent(serverException).text;
				}
			}
			catch(e:Error)
			{
				//使用默认的未知异常
			}
		}
		
		/**
		 * 根据errorCode进行统一处理，子类可重写做特殊处理
		 */		
		protected function handleException():void
		{
			//根据errorCode进行处理
			with(ErrorList)
			{
				switch(errorCode)
				{
					case UNKNOWN_EXCEPTIN:
						
						break;
					case UNCATCHED_EXCEPTION:
						
						break;
				}
			}
			//Alert.show(errorDesc);
		}
	}
}
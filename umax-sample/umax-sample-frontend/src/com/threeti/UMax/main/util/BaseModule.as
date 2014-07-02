package com.threeti.UMax.main.util
{
	import flash.events.Event;
	
	import mx.managers.PopUpManager;
	import mx.modules.Module;
	
	public class BaseModule extends Module
	{
		public function BaseModule()
		{
			super();
		}
		
		/**
		 * 关闭当前窗口事件
		 */ 
		public function redispatchEvent(event:Event):void
		{
			parent.dispatchEvent(event);
			
			if(event.type == "myClose") {
				PopUpManager.removePopUp(this);
			}
		}
	}
}
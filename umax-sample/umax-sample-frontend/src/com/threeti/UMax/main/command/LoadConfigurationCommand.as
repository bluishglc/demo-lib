package com.threeti.UMax.main.command
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.threeti.UMax.main.business.LoadConfigurationDelegate;
	import com.threeti.UMax.main.manager.ConfigurationManager;
	
	import mx.rpc.events.ResultEvent;

	/**
	 * 获取配置文件的command
	 */	
	public class LoadConfigurationCommand extends BaseCommand
	{
		public function LoadConfigurationCommand()
		{
			super();
		}
		
		override public function execute(event:CairngormEvent):void
		{
			var delegate:LoadConfigurationDelegate = new LoadConfigurationDelegate(this);
			delegate.loadConfiguration();
		}
		
		override public function result(data:Object):void
		{
			var event:ResultEvent = data as ResultEvent;
			
			ConfigurationManager.getInstance().config = event.result as XML;
			
		}
	}
}
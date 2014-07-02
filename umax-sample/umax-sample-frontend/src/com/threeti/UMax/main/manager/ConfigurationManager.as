package com.threeti.UMax.main.manager
{
	import com.adobe.cairngorm.CairngormError;
	import com.adobe.cairngorm.CairngormMessageCodes;
	import com.adobe.cairngorm.view.ViewLocator;
	import com.threeti.UMax.main.model.MainModelLocator;
	import com.threeti.UMax.main.view.MainViewHelper;
	import com.threeti.UMax.main.vo.AppItemVO;
	
	import mx.collections.ArrayCollection;
	import mx.collections.XMLListCollection;
	
	/**
	 * 配置文件管理类 
	 */	
	[Bindable]
	public class ConfigurationManager
	{
		private static var configurationManager:ConfigurationManager;
		
		public static function getInstance():ConfigurationManager
		{
			if (configurationManager == null)
				configurationManager=new ConfigurationManager();
			return configurationManager;
		}
		
		public function ConfigurationManager()
		{
			if (ConfigurationManager.configurationManager != null)
				throw new CairngormError(CairngormMessageCodes.SINGLETON_EXCEPTION, "ConfigurationManager");
		}
		
		private var _config:XML;
		
		private var _XXXUrl:String;
		
		public var appList:ArrayCollection;
		
		public function set config(value:XML):void
		{
			_config = value;
			
			if(_config.hasOwnProperty("Urls"))
			{
				XXXUrl = _config.Urls.Url.(@name == "XX1Url")[0].@value;
			}
			
			if(_config.hasOwnProperty("Apps"))
			{
				appList = new ArrayCollection();
				var xmlAppList:XMLListCollection = new XMLListCollection(_config.Apps.children());
				for each(var xmlApp:XML in xmlAppList)
				{
					var app:AppItemVO = new AppItemVO();
					app.id = int(xmlApp.@id);
					app.name = String(xmlApp.@name);
					app.dispName = String(xmlApp.@dispName);
					app.state = String(xmlApp.@state);
					appList.addItem(app);
				}
			}
			
		}
		
		public function getAppById(id:int):AppItemVO
		{
			for each (var app:AppItemVO in appList) {
				if (app.id == id) return app;
			}
			return null;
		}
		
		public function get XXXUrl():String
		{
			return _XXXUrl;
		}
		
		public function set XXXUrl(value:String):void
		{
			_XXXUrl = value;
		}
		
		
		
		
	}
}
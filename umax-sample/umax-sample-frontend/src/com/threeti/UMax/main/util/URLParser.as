package com.threeti.UMax.main.util
{
	import mx.utils.URLUtil;

	/**
	 * Module的URL的解析工具类
	 * @author 王成
	 * @version 1.0
	 */	
	public class URLParser
	{
		/**
		 * 获取不带参数的模块URL
		 */		
		public static function getBaseModuleUrl(url:String):String
		{
			if(!url)
				return null;
			var index:int = getIndex(url);
			
			var baseUrl:String = url;
			if(index != -1)
			{
				baseUrl = url.substring(0, index);
			}
			
			return baseUrl;
		}
		
		/**
		 * 获取模块参数
		 */		
		public static function getParameters(url:String):Object
		{
			var index:int = getIndex(url);
			var parameter:String = "";
			if(index != -1)
			{
				parameter = url.substr(index + 1);
			}
			return URLUtil.stringToObject(parameter);
		}
		
		/**
		 * 获取模块URL与参数的分界点index
		 */		
		public static function getIndex(url:String):int
		{
			var index:int = url.lastIndexOf(".swf");;
			
			return index == -1? -1 : index + 4;
		}
		
		/**
		 * 是否是相同模块
		 */		
		public static function isSameModule(url1:String, url2:String):Boolean
		{
			return getBaseModuleUrl(url1) == getBaseModuleUrl(url2);
		}
	}
}
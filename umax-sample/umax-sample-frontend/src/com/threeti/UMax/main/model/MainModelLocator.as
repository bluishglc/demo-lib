package com.threeti.UMax.main.model
{
	import com.adobe.cairngorm.CairngormError;
	import com.adobe.cairngorm.CairngormMessageCodes;
	import com.adobe.cairngorm.model.IModelLocator;
	import com.threeti.UMax.main.vo.UserVO;
	
	import mx.collections.ArrayCollection;

	[Bindable]
	public class MainModelLocator implements IModelLocator	
	{
		private static var mainModelLocator:MainModelLocator;
		
		public static function getInstance():MainModelLocator 
		{
			if (mainModelLocator == null)
				mainModelLocator=new MainModelLocator();
			return mainModelLocator;
		}
		
		public function MainModelLocator() 
		{
			if (MainModelLocator.mainModelLocator != null)
				throw new CairngormError(CairngormMessageCodes.SINGLETON_EXCEPTION, "MainModelLocator");
		}
		
		//存放全局数据
		
		public var appList:ArrayCollection;
		
		public var userVO:Object;
		
		/**
		 * 当前使用的合同
		 */ 
		public var currentContract:Object;
		
	}
}
package com.threeti.UMax.main.view
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.adobe.cairngorm.view.ViewHelper;
	import com.adobe.cairngorm.view.ViewLocator;
	import com.threeti.UMax.ContractManager.vo.Contract;
	import com.threeti.UMax.main.control.MainController;
	import com.threeti.UMax.main.manager.ConfigurationManager;
	import com.threeti.UMax.main.util.IconView;
	import com.threeti.UMax.main.vo.AppItemVO;
	import com.threeti.UMax.main.vo.UserVO;
	import com.threeti.UMax.task.view.TaskTitleWindow;
	import com.threeti.UMax.task.vo.TasKVO;
	
	import flash.events.Event;
	import flash.system.ApplicationDomain;
	import flash.system.Security;
	
	import mx.core.FlexGlobals;
	import mx.managers.PopUpManager;
	import mx.modules.ModuleLoader;
	import mx.rpc.xml.SchemaTypeRegistry;

	/**
	 * 主页面的逻辑处理类
	 * @author 王成
	 * @version 1.0
	 */	
	public class MainViewHelper extends ViewHelper
	{
		public static const MAIN_VIEW:String = "main_view";
		
		public var moduleHistory:Object = []; 
		
		public function MainViewHelper()
		{
			super();
		}
		
		/**
		 * 当前页面
		 */		
		public function get currentView():Main
		{
			return view as Main;
		}
		
		
		/**
		 * viewHelper的初始化
		 * @private
		 */		
		override public function initialized(document:Object, id:String):void
		{
			super.initialized(document, id);
			
			ViewLocator.getInstance().register(MAIN_VIEW, this);
			
			CairngormEventDispatcher.getInstance()
				.dispatchEvent(new CairngormEvent(MainController.LOAD_APPLICATION_CONFIGURATION));
			
			currentView.currentState = "login";
			
		}
		
		public function login():void
		{
			
			currentView.currentState = "main";
			
			var iconSpace:int = 20;
			var iconVCount:int = 5;
			
			if (ConfigurationManager.getInstance().appList != null) {
				for (var i:int = 0;i<ConfigurationManager.getInstance().appList.length;i++) {
					
					var app:AppItemVO = ConfigurationManager.getInstance().appList.getItemAt(i) as AppItemVO;
					var iconView:IconView = new IconView();
					iconView.appVO = app;
					iconView.icon.source = "./assets/images/icons/" + app.name + ".png";
					iconView.lable.text = app.dispName;
					iconView.x = (int(i / iconVCount)) * (iconView.width + iconSpace) + iconSpace;
					iconView.y = (i % iconVCount) * (iconView.height + iconSpace) + iconSpace;
					currentView.addElement(iconView);
					
				}
			}
			
			var taskWindow:TaskTitleWindow = new TaskTitleWindow();
			taskWindow.width = 400;
			taskWindow.height = 200;
			taskWindow.x = currentView.stage.stageWidth - taskWindow.width - 20;
			taskWindow.y = 330;
			PopUpManager.addPopUp(taskWindow,currentView);
			
		}
		
		private function onClose(event:Event):void
		{
			var moduleLoader:ModuleLoader = event.currentTarget as ModuleLoader;
			moduleLoader.unloadModule();
			moduleLoader = null;
		} 
		
		private function onMax(event:Event):void
		{
			
		}
		
		private function onMin(event:Event):void
		{
			
		}
		
		private function onRestore(event:Event):void
		{
			//还原按钮代码
		}
		
		public function newAppView(appVO:AppItemVO):void
		{
			var moduleLoader:ModuleLoader = new ModuleLoader();
			moduleLoader.applicationDomain = ApplicationDomain.currentDomain;
			moduleLoader.url = "com\\threeti\\UMax\\" + appVO.name + "\\" + appVO.name + "Module.swf";
			moduleLoader.addEventListener("myClose",onClose);
			moduleLoader.addEventListener("myMax",onMax);
			moduleLoader.addEventListener("myMin",onMin);
			moduleLoader.addEventListener("myRestore",onRestore);
			PopUpManager.addPopUp(moduleLoader, currentView);
		}
		
		public function UserInfoClicked():void
		{
			currentView.userInfo.visible = !currentView.userInfo.visible;
		}
		
		public function NewApp():void
		{
			currentView.newAppView.visible = true;
		}
		
		/**
		 * 注册后台数据对象
		 */ 
		public function registerClass():void {
			var registry:SchemaTypeRegistry = SchemaTypeRegistry.getInstance(); 
			//合同
			registry.registerClass(new QName("http://service.salesmockapp.sample.umax.threeti.com/","getContractsResponse"),Contract);
			//任务
			registry.registerClass(new QName("http://service.workflow.sample.umax.threeti.com/", "getTasksResponse"), TasKVO);
			
			registry.registerClass(new QName("http://service.security.sample.umax.threeti.com/","getUserByUsernameResponse"), UserVO);
			
			Security.loadPolicyFile("");
		}
		
	}
}
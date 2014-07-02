package com.threeti.UMax.ContractManager.view
{
	import com.threeti.UMax.ContractManager.business.ContractDelegate;
	import com.threeti.UMax.main.model.MainModelLocator;
	import com.threeti.UMax.task.business.TaskDelegate;
	import com.threeti.UMax.task.model.TaskModel;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.collections.IList;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.controls.Alert;
	import mx.controls.dataGridClasses.DataGridColumn;
	import mx.core.UIComponent;
	import mx.managers.PopUpManager;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	public class ContractListHelper
	{
		
		public var view:UIComponent;
		
		[Bindable]
		public var currentContract:Object;
		
		public function get currentView():ContractList 
		{
			return view as ContractList;
		}
		
		public function ContractListHelper()
		{
			super();
		}
		

		
		public function requestList():void
		{
			var delegate:ContractDelegate = new ContractDelegate(getList,fault);
			delegate.GetContracts();
			currentView.title = "合同管理";

		}
		
		protected function getList(event:ResultEvent):void
		{
		currentView.myGrid.dataProvider=event.result as IList;
		}
		
		protected function fault(event:FaultEvent):void
		{
			
		}
		
		/**
		 * 取得列表的序号
		 */
		public function getSerialNo(item:Object, column:DataGridColumn):String {
			return IList(currentView.myGrid.dataProvider).getItemIndex(item) + 1 + "";
		}
		
		public function contractapprove1_backHandler(event:Event):void
		{
			currentView.currentState = "main";
			currentView.width=800;
			currentView.height=540;
		}
		
		public function myGrid_doubleClickHandler(event:MouseEvent):void
		{
			if(currentView.myGrid.selectedItem==2)
			{
			
				currentView.btntask.enabled=true;
			}else{
			
			
			if(!currentView.myGrid.selectedItem) {
				return;
			}
			
			currentView.currentState = "edit";
			currentContract = currentView.myGrid.selectedItem;
			currentView.width=506;
			currentView.height=300;
			}			
		}

		public function examineContractHandler(obj:Object):void
		{
			if(!obj) return;
			
			var delegate:TaskDelegate = new TaskDelegate();
			delegate.startExamineContract(obj);
			obj.state = "审核中";
			MainModelLocator.getInstance().currentContract = obj;
			
			var delegate2:TaskDelegate = new TaskDelegate(getTasksResultHandler);
			var loginUser:Object = MainModelLocator.getInstance().userVO;
			var roleNames:ArrayCollection = new ArrayCollection();
			for each(var o:Object in loginUser.roles) {
				roleNames.addItem(o.name);
			}
			delegate2.getTasks(loginUser.username, roleNames);
		}
		
		private function getTasksResultHandler(event:ResultEvent):void {
			Alert.show("送审成功，合同正在审核中。", "信息提示");
			if(event.result) {
				if(event.result is ArrayCollection) {
					TaskModel.getInstance().taskList = event.result as ArrayCollection;
					var sort:Sort = new Sort();
					sort.fields = [new SortField("createTime", true, true)];
					TaskModel.getInstance().taskList.sort = sort;
				} else {
					TaskModel.getInstance().taskList.removeAll();
					TaskModel.getInstance().taskList.addItem(event.result);
				}
			} else {
				TaskModel.getInstance().taskList.removeAll();
			}
		}
	}
}
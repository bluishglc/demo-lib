package com.threeti.UMax.CreditManager.view
{
	import com.threeti.UMax.CreditManager.business.CreditDelegate;
	import com.threeti.UMax.CreditManager.vo.credit;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.IList;
	import mx.controls.Alert;
	import mx.controls.List;
	import mx.controls.dataGridClasses.DataGridColumn;
	import mx.core.UIComponent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	

	public class CreditListHelper
	{
		
		public var view:UIComponent;
		[Bindable]
		public var current:Object;
		
		public function get currView():CreditList
		{
		
			return view as CreditList;
		}
		
	 	public function CreditListHelper():void
		{
		super();
		
		}

		
		public function requesrList():void
		{
			var delegate:CreditDelegate=new CreditDelegate(getList,fault);
			delegate.GetCredit();
			currView.title = "信用管理";

		}
		protected function  getList (event:ResultEvent):void
		{
			currView.myGrid.dataProvider=event.result as IList;
		}
		protected function fault(event:FaultEvent):void
		{
		
		}
		
		/**
		 * 取得列表的序号
		 */
		
		public function getSerialNo(item:Object,column:DataGridColumn):String
		{
			return IList(currView.myGrid.dataProvider).getItemIndex(item)+1+"";		
		}
		
		public function contractapprovel_backHandler(event:Event):void
		{
			currView.currentState="main";
			currView.width = 800;
			currView.height = 540;
		}

		public function myGrid_doubleClickHander(event:MouseEvent):void
		{
			if(!currView.myGrid.selectedItem)
			{
			return;		
			}
			currView.currentState="edit";
			current=currView.myGrid.selectedItem;
			currView.width = 276;
			currView.height = 260;
		
		}
		
	}
}
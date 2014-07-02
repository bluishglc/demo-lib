package com.threeti.UMax.task.model
{
	import mx.collections.ArrayCollection;
	import mx.collections.IList;
	
	[Bindable]
	public class TaskModel
	{
		private static var _instance:TaskModel;
		public var taskList:ArrayCollection = new ArrayCollection();
		
		public static function getInstance():TaskModel {
			if(!_instance) {
				_instance = new TaskModel();
			}
			
			return _instance;
		}
	}
}
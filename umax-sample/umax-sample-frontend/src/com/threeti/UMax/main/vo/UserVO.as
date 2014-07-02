package com.threeti.UMax.main.vo
{
	import mx.collections.ArrayCollection;

	public class UserVO
	{
		public var id:int;
		public var username:String;
		public var password:String;
		public var firstName:String;
		public var lastName:String;
		public var email:String;
		public var phoneNumber:String;
		public var website:String;
		public var version:int;
		public var roles:ArrayCollection;
		public var enabled:Boolean;
		public var accountExpired:Boolean;
		public var accountLocked:Boolean;
		public var credentialsExpired:Boolean;
	}
}
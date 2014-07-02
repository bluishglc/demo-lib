package com.threeti.UMax.main.vo
{
	[Bindable]
	[RemoteClass(alias='JiaYu.CoinSys.Domain.Common.Results')]
	public class Results
	{
		public var ret:int;
		public var obj:Object;
		public var totalRecords:int;
		public var pageSize:int;
		public var page:int;
	}
}
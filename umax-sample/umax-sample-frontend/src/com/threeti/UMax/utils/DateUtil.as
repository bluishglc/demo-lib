package com.threeti.UMax.utils
{
	import mx.formatters.DateFormatter;

	public class DateUtil
	{
		private static var dateformater:DateFormatter = new DateFormatter();
		
		public static function format(date:Date, formatString:String="YYYY-MM-DD"):String {
			dateformater.formatString = formatString;
			return dateformater.format(date);
		}
	}
}
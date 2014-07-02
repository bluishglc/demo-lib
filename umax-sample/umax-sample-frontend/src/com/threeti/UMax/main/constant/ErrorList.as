package com.threeti.UMax.main.constant
{
	public class ErrorList
	{
		/**
		 * 未知的异常，同时没有被客户端和服务端所捕获的异常 
		 */		
		public static const UNKNOWN_EXCEPTIN:int = -100;
		
		/**
		 * 服务端未捕捉到的异常，例如NullPointException等 
		 */		
		public static const UNCATCHED_EXCEPTION:int = -10;
		
		/**
		 * 文件加载失败异常，例如所加载的配置文件路径不存在
		 */		
		public static const LOADING_EXCEPTION:int = 99;
		
		/**
		 * 前后端统一的异常类型 
		 */		
		public static const EXCEPTION_A_a:int = 200;
		
		public static const EXCEPTION_A_b:int = 201;
		
		public static const EXCEPTION_B_a:int = 300;
	}
}
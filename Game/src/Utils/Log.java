package utils;

public class Log {


	public static void out(String s){
		String ss = "[OUT]" + s;
		System.out.println(ss);
	}

	public static void err(String s){
		String ss = "[ERROR]" + s;
		System.out.println(ss);
	}

	public static void info(String s){
		String ss = "[INFO]" + s;
		System.out.println(ss);
	}

}

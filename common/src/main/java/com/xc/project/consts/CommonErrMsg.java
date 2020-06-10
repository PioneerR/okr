package com.xc.project.consts;


import java.util.Hashtable;

public class CommonErrMsg {

	public static Hashtable<String, String> errorTable;

	public static String getMsg(int errorCode){
		if(errorTable != null){
			String msg = errorTable.get(String.valueOf(errorCode));
			return  msg != null ? msg : "";
		}
		return "";
	}






}
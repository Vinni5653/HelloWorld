package com.app.validator;

public class AuthValidator {


	public static boolean isValid(String un,String pwd){

		boolean flag=false;
		if("admin".equalsIgnoreCase(un)
				&& "sathya".equals(pwd) ){

			flag=true;
		}
		return flag;
	}
}

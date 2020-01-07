package com.app.service;

import com.app.dao.IEmployeeDao;
import com.app.dao.impl.EmployeeDaoImpl;
import com.app.model.Employee;




public class EmployeeService {

	public static String processRequest(Employee emp){
		
		String message=null;
		
		IEmployeeDao dao=new EmployeeDaoImpl(); 
		
		//check emp exist in db?
		boolean flag=dao.isEmployeeExist(emp.getEmpId());
		
		if(flag){
			//true - employee exist
			message="Employee '"+emp.getEmpId()+"' already exist";
		}else{//false - emp not exist
			int c=dao.saveEmployee(emp);
			if(c>0)
				message="Employee  '"+emp.getEmpId()+"' created successfully";
		}
		
		//return final message back to IL
		return message;
	}
}

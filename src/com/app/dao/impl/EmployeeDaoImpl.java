package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.app.dao.IEmployeeDao;
import com.app.model.Employee;
import com.app.util.ConnectionUtil;



public class EmployeeDaoImpl implements IEmployeeDao{

	@Override
	public int saveEmployee(Employee emp) {
		String SQL="insert into emptab values(?,?,?)";
		Connection con=null;
		PreparedStatement pstmt=null;
		int count=0;
		try {
			con=ConnectionUtil.getConn();
			pstmt=con.prepareStatement(SQL);
			
			
			pstmt.setInt(1, emp.getEmpId());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setDouble(3, emp.getEmpSal());
			
			count=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(con!=null)  con=null;
			
		}
		return count;
	}

	@Override
	public boolean isEmployeeExist(int empId) {
		
		
		String SQL="select count(eid) from emptab where eid=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean flag=false;
		try {
			con=ConnectionUtil.getConn();
			pstmt=con.prepareStatement(SQL);
			
			pstmt.setInt(1, empId);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				int c=rs.getInt(1);
				if(c>=1) flag=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			if(con!=null) con=null;
		}
		return flag;
	}

}

package com.app.rest;

import java.util.StringTokenizer;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.codec.binary.Base64;

import com.app.model.Employee;
import com.app.service.EmployeeService;
import com.app.validator.AuthValidator;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;




@Path("/employee")
public class EmployeeRestController {

	
	@Path("/save")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveEmployee(@HeaderParam("Authorization")String auth, Employee emp){
		
		ResponseBuilder rb=new ResponseBuilderImpl();
		
		
		//1. is Auth header empty or null?
		if(null==auth || "".equals(auth.trim())){
			rb.entity("Please Provide Identity Details...");
			rb.status(Status.BAD_REQUEST);
		}else{
			//remove Basic<space> 
			auth=auth.replace("Basic ", "");
			//Decode Auth header
			byte[] arr=Base64.decodeBase64(auth.getBytes());
			auth=new String(arr);
			
			//read un, pwd as two Strings using -STR
			StringTokenizer str=new StringTokenizer(auth, ":");
			String user=str.nextToken();
			String password=str.nextToken();
			
			//do validate un,pwd
			boolean flag=AuthValidator.isValid(user, password);
			
			if(flag){ //valid user
				String entity=EmployeeService.processRequest(emp);
				rb.entity(entity);
				rb.status(Status.OK);
			}else{ //invalid user
				rb.entity("Invalid UserId/Password provided");
				rb.status(Status.UNAUTHORIZED);
			}
			
		}
		
		Response res=rb.build();
		return res;
	}
}

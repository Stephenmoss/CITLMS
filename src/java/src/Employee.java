/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;
import java.util.*;

/**
 *
 * @author smoss
 */
public class Employee extends People {
    
    private String userName;			//Username for BLog & Email system - kyle
    
    public Employee(String pName, int roleID, int pDisplayOrder, String empFName, String empLName, String birthday, int age)
	{
    		super(empFName, empLName, new UserRole(pName, roleID, pDisplayOrder));
			this.firstname = empFName;		//First Name
			this.lastname = empLName;		//Last Name
			this.birthday = birthday;		//(yyyy-mm-dd)
			this.age = age;

	}
            
    //Getters
	
	//Get UserName
	public String getUserName() {
		return userName;
	}
        
        //Setter
	
	public void setUserName(String userName) { //Kyle
		this.userName = userName;
		this.email = this.userName + "@catalystvetservices.med";
	}
        
	
	@Override
	public String toString(){
		return "Username: " + getUserName();
	}
}


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
public class UserRole
{
	private String roleName;
	private int ID;
	private int displayOrder;
	
	public UserRole(String pName, int pID, int pDisplayOrder)
	{
		this.roleName = pName;
		this.ID = pID;
		this.displayOrder = pDisplayOrder;
	}
	
	public String getRoleName()
	{
		return this.roleName;
	}
	
	public int getRoleID()
	{
		return this.ID;
	}
	
	public int getDisplayOrder()
	{
		return this.displayOrder;
	}
	
	public String toString()
	{
		return getRoleName();
	}
}

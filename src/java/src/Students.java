/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;

/**
 *
 * @author smoss
 */
public class Students {
    private String name;
	private GradeLevel gradeLevel;
    private boolean isActive;
    private Client owner;
    private Teacher currentTeacher;
    private ArrayList<Medication> meds;
    private School school;
    private People creator;
    
    private int ID;
    
    public Students(String name, GradeLevel type)
    {
    	this.name = name;
    	gradeLevel = type;
    	meds = new ArrayList<Medication>();
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Client getOwner() {
		return owner;
	}

	public void setOwner(Client owner) {
		this.owner = owner;
	}

	public GradeLevel getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(GradeLevel gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Teacher getCurrentTeacher() {
		return currentTeacher;
	}

	public void setCurrentTeacher(Teacher currentTeacher) {
		this.currentTeacher = currentTeacher;
	}

	public ArrayList<Medication> getMeds() {
		return meds;
	}

	public void setMeds(ArrayList<Medication> meds) {
		this.meds = meds;
	}
	
	public void addMed(Medication med){
		this.meds.add(med);
	}
	
	public void removeMed(Medication med){
		this.meds.remove(med);
	}

	public int getID()
	{
		return ID;
	}
	public void setID(int iD)
	{
		ID = iD;
	}
	
	public School getSchool()
	{
		return school;
	}
	
	public void setSchool(School school)
	{
		this.school = school;
	}
	
	public People getCreator()
	{
		return creator;
	}
	
	public void setCreator(People creator)
	{
		this.creator = creator;
	}
	
}

	
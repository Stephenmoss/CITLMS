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
public abstract class People
{
	protected String firstname;	//First name
	protected String lastname;	//Last name
	protected String name;          //Add first and last name to get full name
	protected String birthday;	//Birthday Day(YYYY-MM-DD)
	protected String type;		//Type:  Job, breed, etc.
	protected String address;	//Street Address
	protected String city;		//City
	protected String state;		//State/Province
	protected String zip;		//ZIP Code
	protected String phone;		//Phone number
	protected int age;		//Age
	protected String email;		//Email
	protected ArrayList<Students> pets;
	
	protected int ID;			// ID in Persons table.
	
	protected UserRole role;

	public People(String pFirstName, String pLastName, UserRole pRole)
	{
		this.firstname = pFirstName;
		this.lastname = pLastName;
		this.role = pRole;
        this.pets = new ArrayList<Students>();
	}


	//Getters
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public String getFullName() {
		this.name = firstname + " " + lastname;
		return this.name;
	}
	public String getBirthday() {
		return birthday;
	}
	public int getAge() {
		return age;
	}
	public String getType() {
		return type;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getZip() {
		return zip;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}

	//Setters
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getID()
	{
		return ID;
	}
	public void setID(int iD)
	{
		ID = iD;
	}
	
	public void addPet(Students pet){
		if(!pets.contains(pet)){
			pets.add(pet);
		}
		else{
			System.out.println("This pet already exists for this person.");
		}
	}
	
	public void removePet(Students pet){
		if(pets.contains(pet)){
			pets.remove(pet);
		}
		else{
			System.out.println("This pet does not currently belong to this person.");
		}
	}
	
	public ArrayList<Students> getPets(){
		return this.pets;
	}
	
	public void setPersonPets(ArrayList<Students> pets){
		this.pets = pets;
	}


	public UserRole getRole()
	{
		return role;
	}


	public void setRole(UserRole role)
	{
		this.role = role;
	}
}

package src;

import java.util.ArrayList;

public class School
{
	private String name;
	private int ID;
	private ArrayList<Teacher> vets;		//Vets ArrayList
    private ArrayList<Counselor> vetTechs;	//Vettech ArrayList
    private ArrayList<Principal> administrators;	//Admin ArrayList
	
	public School (String pName, int pID)
	{
		this.name = pName;
		this.ID = pID;
		
		//Initialize ArrayLists
		this.vets = new ArrayList<>();
		this.vetTechs = new ArrayList<>();
		this.administrators = new ArrayList<>();
	}
	
	// Get Employees in ArrayLists
	public ArrayList<Teacher> getVets(){
		return this.vets;
	}
	public ArrayList<Counselor> getVetTechs(){
		return this.vetTechs;
	}
	public ArrayList<Principal> getAdministrators() {
		return this.administrators;
	}
	
	public void setEmployeeVets(ArrayList<Teacher> vets){
	    this.vets = vets;
	}
	public void setEmployeeVetTechs(ArrayList<Counselor> vetTechs){
	    this.vetTechs = vetTechs;
	}
	public void setAdministrators(ArrayList<Principal> administrators) {
		this.administrators = administrators;
	}
	
	//Add Teacher to vets
	public void addVet(Teacher vet){
		this.vets.add(vet);
	}
	//Remove Teacher from vets
	public void removeVet(Teacher vet){
		this.vets.remove(vet);
	}
	//Add Teacher Tech to Teacher Techs
	public void addVetTech(Counselor vetTech){
		this.vetTechs.add(vetTech);
	}
	//Remove Teacher Tech
	public void removeVetTech(Counselor vetTech){
		this.vetTechs.remove(vetTech);
	}
	
	//Add Principal
	public void addAdministrator(Principal admin){
		this.administrators.add(admin);
	}
	//Remove Principal
	public void removeAdministrator(Principal admin){
		this.administrators.remove(admin);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getID()
	{
		return ID;
	}

	public void setID(int iD)
	{
		ID = iD;
	}
}

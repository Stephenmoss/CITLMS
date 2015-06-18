package src;

public class GradeLevel
{
	private String type;
	private int ID;
	
	public GradeLevel(String pType, int pID)
	{
		this.type = pType;
		this.ID = pID;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public int getID()
	{
		return this.ID;
	}
	
	public boolean equals(GradeLevel other)
	{
		return this.type.equals(other.getType());
	}
}

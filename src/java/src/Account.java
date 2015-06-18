package src;

public class Account
{
	private String userName;
	private String passWord;
	private int ID = -1;
	
	public Account(String pUserName, String pPassWord)
	{
		this.setUserName(pUserName);
		this.setPassWord(pPassWord);
	}
	
	public Account(String pUserName, String pPassWord, int pID)
	{
		this.setUserName(pUserName);
		this.setPassWord(pPassWord);
		this.setID(pID);
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassWord()
	{
		return passWord;
	}

	public void setPassWord(String passWord)
	{
		this.passWord = passWord;
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

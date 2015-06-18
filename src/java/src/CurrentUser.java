package src;


public class CurrentUser implements java.io.Serializable
{
	private static final long	serialVersionUID	= 1L;
	
	private People person;
	private Account account;
	
	public CurrentUser(People pPerson, Account pAccount)
	{
		person = pPerson;
		account = pAccount;
	}
	
	public People getPerson()
	{
		return this.person;
	}
	
	public void setPerson(People pPerson)
	{
		this.person = pPerson;
	}
	
	public String getFullName()
	{
		return this.person.getFullName();
	}
	
	public String getRoleString()
	{
		return this.person.getRole().toString();
	}
	
	public Account getAccount()
	{
		return this.account;
	}
	
	public String getUserName()
	{
		return this.account.getUserName();
	}
	
	public String getPassWord()
	{
		return this.account.getPassWord();
	}
}

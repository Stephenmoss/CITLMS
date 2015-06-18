package database;

import src.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A helper class that handles the communication with the database. It will run the queries necessary to retrieve
 * the requested data. 
 * @author mjensen
 */
public class DBHelper
{
    /**
     * Creates a database and its tables if they don't already exist.
     * @param pDBName
     * @param pConnection
     * @return 
     */
    public static boolean createDatabase(String pDBName, DBConn pConnection)
    {
        // If the database name or connection are null, return early.
        if (pDBName == null || pConnection == null)
            return false;
        // If the database name is empty, return early.
        if (pDBName.length() <= 0)
            return false;

        boolean connectionSetup = true;
        
		// Set up the connection if it's not already connected.
		if (!pConnection.IsConnected())
		{
			pConnection.SetUpConnection();
			connectionSetup = false;
		}
        // If the connection wasn't set up correctly, return false now.
        if (!pConnection.IsConnected()) return false;
        
        ArrayList<Boolean> results = new ArrayList<Boolean>();

        // Set up the query strings.
        StringBuilder sbDB = new StringBuilder();
        StringBuilder sbTables = new StringBuilder();

        // Set up the query string for creating the database.
        sbDB.append(" USE [master]; ");
        sbDB.append(" IF NOT EXISTS (SELECT name FROM sys.databases WHERE name='");
        sbDB.append(pDBName);
        sbDB.append("') BEGIN ");
        sbDB.append("	CREATE DATABASE [");
        sbDB.append(pDBName);
        sbDB.append("]; END ");

        // Set up the query string for creating the tables.
        // Switch to the database first.
        sbTables.append(" USE ");
        sbTables.append(pDBName);
        sbTables.append("; ");
        // Create each table.
        // Schools
        sbTables.append(" IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='DTb_Schools') ");
	sbTables.append(" BEGIN ");
        sbTables.append(" CREATE TABLE [dbo].[DTb_Schools]( ");
        sbTables.append(" 	[SchoolID] [int] IDENTITY(1,1) NOT NULL, ");
        sbTables.append(" 	[SchoolActive] [bit] NOT NULL, ");
        sbTables.append(" 	[SchoolName] [varchar](50) NOT NULL, ");
        sbTables.append("       CONSTRAINT [PK_DTb_Schools] PRIMARY KEY CLUSTERED ([SchoolID] ASC)WITH (PAD_INDEX = OFF, ");
        sbTables.append("       STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] ");
        sbTables.append(" 	) ON [PRIMARY]; ");
        sbTables.append(" 	ALTER TABLE [dbo].[DTb_Schools] ADD  CONSTRAINT [DF_DTb_Schools_Active]  DEFAULT ((1)) FOR [SchoolActive]; ");
	sbTables.append(" END ");
        
        // Roles
        sbTables.append(" IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='DTb_Roles') ");
	sbTables.append(" BEGIN ");
        sbTables.append(" CREATE TABLE [dbo].[DTb_Roles]( ");
        sbTables.append(" 	[RoleID] [int] IDENTITY(1,1) NOT NULL, ");
        sbTables.append(" 	[RoleActive] [bit] NOT NULL, ");
        sbTables.append(" 	[RoleName] [varchar](20) NOT NULL, ");
        sbTables.append(" 	[RoleDisplayOrder] [int] NOT NULL, ");
        sbTables.append(" 	CONSTRAINT [PK_DTb_Roles] PRIMARY KEY CLUSTERED ([RoleID] ASC)WITH (PAD_INDEX = OFF, ");
        sbTables.append(" 	STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] ");
        sbTables.append(" 	) ON [PRIMARY]; ");
        sbTables.append(" 	ALTER TABLE [dbo].[DTb_Roles] ADD  CONSTRAINT [DF_DTb_Roles_Active]  DEFAULT ((1)) FOR [RoleActive]; ");
        sbTables.append(" 	ALTER TABLE [dbo].[DTb_Roles] ADD  CONSTRAINT [DF_DTb_Roles_DisplayOrder]  DEFAULT ((1)) FOR [RoleDisplayOrder]; ");
        sbTables.append("	INSERT INTO [dbo].[DTb_Roles] (RoleName, RoleDisplayOrder) VALUES ('Principal', '1'); ");
        sbTables.append("	INSERT INTO [dbo].[DTb_Roles] (RoleName, RoleDisplayOrder) VALUES ('Teacher', '2'); ");
        sbTables.append("	INSERT INTO [dbo].[DTb_Roles] (RoleName, RoleDisplayOrder) VALUES ('Counselor', '3'); ");
        sbTables.append("	INSERT INTO [dbo].[DTb_Roles] (RoleName, RoleDisplayOrder) VALUES ('Librarian', '4'); ");
        sbTables.append("	INSERT INTO [dbo].[DTb_Roles] (RoleName, RoleDisplayOrder) VALUES ('Parent', '100'); ");
	sbTables.append(" END ");
        
        // Persons
        sbTables.append(" IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='DTb_Persons') ");
	sbTables.append(" BEGIN ");
        sbTables.append(" CREATE TABLE [dbo].[DTb_Persons]( ");
        sbTables.append(" 	[PersonID] [int] IDENTITY(1,1) NOT NULL, ");
        sbTables.append(" 	[PersonActive] [bit] NOT NULL, ");
        sbTables.append(" 	[PersonFirstName] [varchar](35) NOT NULL, ");
        sbTables.append(" 	[PersonLastName] [varchar](35) NOT NULL, ");
        sbTables.append(" 	[PersonRoleID] [int] NOT NULL, ");
        sbTables.append(" 	[PersonSchoolID] [int] NULL, ");
        sbTables.append(" 	CONSTRAINT [PK_DTb_Persons] PRIMARY KEY CLUSTERED ([PersonID] ASC)WITH (PAD_INDEX = OFF, ");
        sbTables.append(" 	STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] ");
        sbTables.append(" 	) ON [PRIMARY]; ");
        sbTables.append(" 	ALTER TABLE [dbo].[DTb_Persons] ADD  CONSTRAINT [DF_DTb_Persons_Active]  DEFAULT ((1)) FOR [PersonActive]; ");
        sbTables.append(" 	ALTER TABLE [dbo].[DTb_Persons]  WITH CHECK ADD  CONSTRAINT [Link_Persons_Schools] FOREIGN KEY([PersonSchoolID]) ");
        sbTables.append(" 	REFERENCES [dbo].[DTb_Schools] ([SchoolID]); ");
        sbTables.append(" 	ALTER TABLE [dbo].[DTb_Persons] CHECK CONSTRAINT [Link_Persons_Schools]; ");
        sbTables.append(" 	ALTER TABLE [dbo].[DTb_Persons]  WITH CHECK ADD  CONSTRAINT [Link_Persons_Roles] FOREIGN KEY([PersonRoleID]) ");
        sbTables.append(" 	REFERENCES [dbo].[DTb_Roles] ([RoleID]); ");
        sbTables.append(" 	ALTER TABLE [dbo].[DTb_Persons] CHECK CONSTRAINT [Link_Persons_Roles]; ");
        sbTables.append("	INSERT INTO [dbo].[DTb_Persons] (PersonFirstName, PersonLastName, PersonRoleID) VALUES ('Principal', 'Principal', 1); ");
	sbTables.append(" END ");
        
        // Accounts
        sbTables.append(" IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='DTb_Accounts') ");
	sbTables.append(" BEGIN ");
        sbTables.append(" CREATE TABLE [dbo].[DTb_Accounts]( ");
        sbTables.append(" 	[AccountID] [int] IDENTITY(1,1) NOT NULL, ");
        sbTables.append(" 	[AccountActive] [bit] NOT NULL, ");
        sbTables.append(" 	[AccountUsername] [varchar](20) NOT NULL, ");
        sbTables.append(" 	[AccountPassword] [varchar](20) NOT NULL, ");
        sbTables.append(" 	[AccountPersonID] [int] NOT NULL, ");
        sbTables.append(" 	CONSTRAINT [PK_DTb_Accounts] PRIMARY KEY CLUSTERED ([AccountID] ASC)WITH (PAD_INDEX = OFF, ");
        sbTables.append(" 	STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] ");
        sbTables.append(" 	) ON [PRIMARY]; ");
        sbTables.append(" 	ALTER TABLE [dbo].[DTb_Accounts] ADD  CONSTRAINT [DF_DTb_Accounts_Active]  DEFAULT ((1)) FOR [AccountActive]; ");
        sbTables.append(" 	ALTER TABLE [dbo].[DTb_Accounts]  WITH CHECK ADD  CONSTRAINT [Link_Accounts_Persons] FOREIGN KEY([AccountPersonID]) ");
        sbTables.append(" 	REFERENCES [dbo].[DTb_Persons] ([PersonID]); ");
        sbTables.append(" 	ALTER TABLE [dbo].[DTb_Accounts] CHECK CONSTRAINT [Link_Accounts_Persons]; ");
        sbTables.append("	INSERT INTO [dbo].[DTb_Accounts] (AccountUsername, AccountPassword, AccountPersonID) VALUES ('Principal', 'Principal', 1); ");
	sbTables.append(" END ");
        
        // GradeLevels
        sbTables.append(" IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='DTb_GradeLevels') ");
	sbTables.append(" BEGIN ");
        sbTables.append(" CREATE TABLE [dbo].[DTb_GradeLevels]( ");
        sbTables.append(" 	[GradeLevelID] [int] IDENTITY(1,1) NOT NULL, ");
        sbTables.append(" 	[GradeLevelActive] [bit] NULL, ");
        sbTables.append(" 	[GradeLevelName] [varchar](20) NULL, ");
        sbTables.append(" 	CONSTRAINT [PK_DTb_GradeLevels] PRIMARY KEY CLUSTERED ([GradeLevelID] ASC)WITH (PAD_INDEX = OFF, ");
        sbTables.append(" 	STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] ");
        sbTables.append(" 	) ON [PRIMARY]; ");
        sbTables.append(" 	ALTER TABLE [dbo].[DTb_GradeLevels] ADD  CONSTRAINT [DF_DTb_GradeLevels_Active]  DEFAULT ((1)) FOR [GradeLevelActive]; ");
	sbTables.append(" END ");
        
        // Students
        sbTables.append(" IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='DTb_Students') ");
	sbTables.append(" BEGIN ");
        sbTables.append(" CREATE TABLE [dbo].[DTb_Students]( ");
        sbTables.append(" 	[StudentID] [int] IDENTITY(1,1) NOT NULL, ");
        sbTables.append(" 	[StudentActive] [bit] NOT NULL, ");
        sbTables.append(" 	[StudentName] [varchar](35) NOT NULL, ");
        sbTables.append(" 	[StudentGradeLevelID] [int] NOT NULL, ");
        sbTables.append("	[StudentCreatorPersonID] [int] NULL, ");
        sbTables.append("	[StudentSchoolID][int] NULL, ");
        sbTables.append(" 	CONSTRAINT [PK_DTb_Students] PRIMARY KEY CLUSTERED ([StudentID] ASC)WITH (PAD_INDEX = OFF, ");
        sbTables.append(" 	STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] ");
        sbTables.append(" 	) ON [PRIMARY]; ");
        sbTables.append(" 	ALTER TABLE [dbo].[DTb_Students] ADD  CONSTRAINT [DF_DTb_Students_Active]  DEFAULT ((1)) FOR [StudentActive]; ");
        sbTables.append(" 	ALTER TABLE [dbo].[DTb_Students]  WITH CHECK ADD  CONSTRAINT [Link_Students_GradeLevels] FOREIGN KEY([StudentGradeLevelID]) ");
        sbTables.append(" 	REFERENCES [dbo].[DTb_GradeLevels] ([GradeLevelID]); ");
        sbTables.append(" 	ALTER TABLE [dbo].[DTb_Students] CHECK CONSTRAINT [Link_Students_GradeLevels]; ");
        sbTables.append("	ALTER TABLE [dbo].[DTb_Students]  WITH CHECK ADD  CONSTRAINT [Link_Students_Schools] FOREIGN KEY([StudentSchoolID]) ");
        sbTables.append("	REFERENCES [dbo].[DTb_Schools] ([SchoolID]); ");
        sbTables.append("	ALTER TABLE [dbo].[DTb_Students] CHECK CONSTRAINT [Link_Students_Schools]; ");
        sbTables.append("	ALTER TABLE [dbo].[DTb_Students]  WITH CHECK ADD  CONSTRAINT [Link_Students_Persons_Creator] FOREIGN KEY([StudentCreatorPersonID]) ");
        sbTables.append("	REFERENCES [dbo].[DTb_Persons] ([PersonID]); ");
        sbTables.append("	ALTER TABLE [dbo].[DTb_Students] CHECK CONSTRAINT [Link_Students_Persons_Creator]; ");
	sbTables.append(" END ");
        
        // Students Persons Links
        sbTables.append(" IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='LTb_StudentsPersons') ");
	sbTables.append(" BEGIN ");
        sbTables.append(" CREATE TABLE [dbo].[LTb_StudentsPersons]( ");
        sbTables.append(" 	[LStudentPersonID] [int] IDENTITY(1,1) NOT NULL, ");
        sbTables.append(" 	[LStudentPersonActive] [bit] NOT NULL, ");
        sbTables.append(" 	[LStudentPersonStudentID] [int] NOT NULL, ");
        sbTables.append(" 	[LStudentPersonPersonID] [int] NOT NULL, ");
        sbTables.append(" 	CONSTRAINT [PK_LTb_StudentsPersons] PRIMARY KEY CLUSTERED ([LStudentPersonID] ASC)WITH (PAD_INDEX = OFF, ");
        sbTables.append(" 	STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] ");
        sbTables.append(" 	) ON [PRIMARY]; ");
        sbTables.append(" 	ALTER TABLE [dbo].[LTb_StudentsPersons] ADD  CONSTRAINT [DF_LTb_StudentsPersons_Active]  DEFAULT ((1)) FOR [LStudentPersonActive]; ");
        sbTables.append(" 	ALTER TABLE [dbo].[LTb_StudentsPersons]  WITH CHECK ADD  CONSTRAINT [Link_StudentsPersons_Persons] FOREIGN KEY([LStudentPersonPersonID]) ");
        sbTables.append(" 	REFERENCES [dbo].[DTb_Persons] ([PersonID]); ");
        sbTables.append(" 	ALTER TABLE [dbo].[LTb_StudentsPersons] CHECK CONSTRAINT [Link_StudentsPersons_Persons]; ");
        sbTables.append(" 	ALTER TABLE [dbo].[LTb_StudentsPersons]  WITH CHECK ADD  CONSTRAINT [Link_StudentsPersons_Students] FOREIGN KEY([LStudentPersonStudentID]) ");
        sbTables.append(" 	REFERENCES [dbo].[DTb_Students] ([StudentID]); ");
        sbTables.append(" 	ALTER TABLE [dbo].[LTb_StudentsPersons] CHECK CONSTRAINT [Link_StudentsPersons_Students]; ");
	sbTables.append(" END ");

        // Run the queries and add their results to the results list.
        results.add(pConnection.RunUpdate(sbDB.toString(), null, true));
        results.add(pConnection.RunUpdate(sbTables.toString(), null));
        
		if (!connectionSetup)
			pConnection.Close();

        // If any of the results came back as false, assume the database was not built correctly and return false.
        return (!results.contains(false));
    }
    
    /**
     * Sets up the database with dummy data.
     * @param pConnection
     * @return 
     */
    public static boolean setupDatabase(DBConn pConnection)
    {
        // todo
        return false;
    }
    
    /**
     * Given a username and password, will search the database for an account with the given information
     * and return a person from that account. Returns null if no matching account or Person exists.
     * @param pUsername
     * @param pPassword
     * @param pConnection
     * @return People with the given account information, null if no such account or Person exists.
     */
    public static People getPersonFromDatabaseWithAccount(String pUsername, String pPassword, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pUsername == null || pPassword == null || pConnection == null)
            return null;

		boolean connectionSetup = true;
        People results = null;
        
        try
        {
			// Set up the connection if it's not already connected.
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            // If the connection wasn't set up correctly, return false now.
            if (!pConnection.IsConnected()) return null;
            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pUsername);
            vars.add(pPassword);
            // Check the database for the account.
            ResultSet rs = pConnection.RunQuery("SELECT * FROM DTb_Accounts "
                            + "RIGHT JOIN DTb_Persons ON DTb_Persons.PersonID=DTb_Accounts.AccountPersonID "
                            + "RIGHT JOIN DTb_Roles ON DTb_Roles.RoleID=DTb_Persons.PersonRoleID "
                            + "WHERE AccountUsername= ? AND AccountPassword= ? AND AccountActive=1;",
                            vars);
            if (rs == null)
            	return null;
            // If we successfully found both an account and Person, set them to the results.
            if (rs.next())
            {
                // Create the new Person.
            	results = new Employee(rs.getString("RoleName"), rs.getInt("RoleID"), rs.getInt("RoleDisplayOrder"),
            							rs.getString("PersonFirstName"), rs.getString("PersonLastName"),
            							"0000-00-00", 0);
            	results.setID(rs.getInt("PersonID"));
            }
        }
        catch (SQLException e)
        {
            System.out.println("DBHelper.getPersonFromDatabaseWithAccount SQL error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
        return results;
    }
    
    /**
     * Gets a person from the database with the given ID.
     * @param pID
     * @param pConnection
     * @return
     */
    public static People getPersonFromDatabaseWithID(int pID, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pConnection == null)
            return null;

		boolean connectionSetup = true;
        People results = null;
        
        try
        {
			// Set up the connection if it's not already connected.
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            // If the connection wasn't set up correctly, return false now.
            if (!pConnection.IsConnected()) return null;
            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pID);
            // Check the database for the account.
            ResultSet rs = pConnection.RunQuery("SELECT * FROM DTb_Persons "
                            + "RIGHT JOIN DTb_Roles ON DTb_Roles.RoleID=DTb_Persons.PersonRoleID "
                            + "WHERE PersonID=?;",
                            vars);
            if (rs == null)
            	return null;
            // If we successfully found both an account and Person, set them to the results.
            if (rs.next())
            {
                // Create the new Person.
            	results = new Employee(rs.getString("RoleName"), rs.getInt("RoleID"), rs.getInt("RoleDisplayOrder"),
            							rs.getString("PersonFirstName"), rs.getString("PersonLastName"),
            							"0000-00-00", 0);
            	results.setID(rs.getInt("PersonID"));
            }
        }
        catch (SQLException e)
        {
            System.out.println("DBHelper.getPersonFromDatabaseWithID SQL error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
        return results;
    }
    
    /**
     * Takes a given Person and adds a file for them into the database.
     * @param pPeople
     * @param pConnection
     * @return True if updated successfully, false otherwise.
     */
    public static boolean insertPersonIntoDatabase(People pPeople, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pPeople == null || pConnection == null)
            return false;

		boolean connectionSetup = true;
		boolean results = false;
        
        try
        {
			// Set up the connection if it's not already connected.
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            // If the connection wasn't set up correctly, return false now.
            if (!pConnection.IsConnected()) return false;
            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pPeople.getFirstname());
            vars.add(pPeople.getLastname());
            vars.add(pPeople.getRole().getRoleID());
            // Insert the person into the database.
            results = pConnection.RunUpdate("INSERT INTO DTb_Persons (PersonFirstName, PersonLastName, PersonRoleID) VALUES (?, ?, ?);", vars);
            // Use a different update if the Person doesn't have an office.
            /*if (pPeople.getSchool() == null)
            	results = pConnection.RunUpdate("INSERT INTO DTb_Persons (FirstName, LastName) VALUES (?, ?, ?);", vars);
            // Otherwise, update including the office.
            else
            {
	            vars.add(pPeople.getSchool().getID());
	            results = pConnection.RunUpdate("INSERT INTO DTb_Persons (FirstName, LastName, SchoolID) "
	            						   + " VALUES (?, ?, ?, ?);", vars);
            }//*/
            // Get and set the person's ID.
            ResultSet rs = pConnection.RunQuery("SELECT TOP 1 PersonID FROM DTb_Persons ORDER BY PersonID DESC;", null);
            if (rs.next())
            {
            	pPeople.setID(rs.getInt("PersonID"));
            }
            return results;
        }
        catch (SQLException e)
        {
            System.out.println("DBHelper.insertPersonIntoDatabase SQL error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
    }
    
    /**
     * Takes a given Person and edits their file in the database to match.
     * @param pPeople
     * @param pConnection
     * @return True if updated successfully, false otherwise.
     */
    public static boolean editPersonInDatabase(People pPeople, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pPeople == null || pConnection == null)
            return false;

		boolean connectionSetup = true;
		boolean results = false;
        
        try
        {
			// Set up the connection if it's not already connected.
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            // If the connection wasn't set up correctly, return false now.
            if (!pConnection.IsConnected()) return false;

            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pPeople.getFirstname());
            vars.add(pPeople.getLastname());
            vars.add(pPeople.getRole().getRoleID());
            vars.add(pPeople.getID());
            // Insert the person into the database.
            results = pConnection.RunUpdate("UPDATE DTb_Persons SET PersonFirstName=?, PersonLastName=?, PersonRoleID=? "
            							 +  " WHERE PersonID=?;", vars);
            // Use a different update if the Person doesn't have an office.
            /*if (pPeople.getSchool() == null)
            {
            	vars.add(pPeople.getID());
            	results = pConnection.RunUpdate("UPDATE DTb_Persons SET FirstName=?, LastName=?, RoleID=?, SchoolID=NULL "
            						   + " WHERE ID=?;", vars);
            }
            // Otherwise, update including the office.
            else
            {
	            vars.add(pPeople.getSchool().getID());
	            vars.add(pPeople.getID());
	            results = pConnection.RunUpdate("UPDATE DTb_Persons SET FirstName=?, LastName=?, RoleID=?, SchoolID=? "
	            						   + " WHERE ID=?;", vars);
            }//*/
            return results;
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
    }

    /**
     * Takes a given Person and deactivates their file.
     * @param pPeople
     * @param pConnection
     * @return True if updated successfully, false otherwise.
     */
    public static boolean deactivatePersonInDatabase(People pPeople, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pPeople == null || pConnection == null)
            return false;

		boolean connectionSetup = true;
        
        try
        {
			// Set up the connection if it's not already connected.
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            // If the connection wasn't set up correctly, return false now.
            if (!pConnection.IsConnected()) return false;
            
            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pPeople.getID());
            
            // Update the person's active flag in the database..
            return pConnection.RunUpdate("UPDATE DTb_Persons SET PersonActive=0 WHERE PersonID=?", vars);
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
    }

    /**
     * Gets an account from the database with the given ID.
     * @param pID
     * @param pConnection
     * @return
     */
    public static Account getAccountFromDatabaseWithID(int pID, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pConnection == null)
            return null;

		boolean connectionSetup = true;
        Account results = null;
        
        try
        {
			// Set up the connection if it's not already connected.
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            // If the connection wasn't set up correctly, return false now.
            if (!pConnection.IsConnected()) return null;
            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pID);
            // Check the database for the account.
            ResultSet rs = pConnection.RunQuery("SELECT * FROM DTb_Accounts "
            								  + "WHERE AccountID=?;", vars);
            if (rs == null)
            	return null;
            if (rs.next())
            {
                // Create the new Person.
            	results = new Account(rs.getString("AccountUsername"), rs.getString("AccountPassword"), rs.getInt("AccountID"));
            	results.setID(pID);
            }
        }
        catch (SQLException e)
        {
            System.out.println("DBHelper.getAccountFromDatabaseWithID SQL error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
        return results;
    }
    
    /**
     * Inserts a given account into the database (and updates the given account's ID to match the new one in the database)
     * @param pPeople
     * @param pAccount
     * @param pConnection
     * @return
     */
    public static boolean insertAccountIntoDatabase(People pPeople, Account pAccount, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pPeople == null || pAccount == null || pConnection == null)
            return false;

		boolean connectionSetup = true;
		boolean results = false;
        
        try
        {
			// Set up the connection if it's not already connected.
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            // If the connection wasn't set up correctly, return false now.
            if (!pConnection.IsConnected()) return false;
            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pAccount.getUserName());
            vars.add(pAccount.getPassWord());
            vars.add(pPeople.getID());
            // Insert the account into the database.
            results =  pConnection.RunUpdate("INSERT INTO DTb_Accounts (AccountUsername, AccountPassword, AccountPersonID) "
            							  + " VALUES (?, ?, ?);", vars);

            ResultSet rs = pConnection.RunQuery("SELECT TOP 1 AccountID FROM DTb_Persons ORDER BY AccountID DESC;", null);
            if (rs.next())
            {
            	pPeople.setID(rs.getInt("AccountID"));
            }
            return results;
        }
        catch (SQLException e)
        {
            System.out.println("DBHelper.insertAccountIntoDatabase SQL error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
    }
    
    /**
     * Given an account, edits its corresponding entry in the database to match.
     * @param pAccount
     * @param pConnection
     * @return
     */
    public static boolean editAccountInDatabase(Account pAccount, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pAccount == null || pConnection == null)
            return false;

		boolean connectionSetup = true;
        
        try
        {
			// Set up the connection if it's not already connected.
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            // If the connection wasn't set up correctly, return false now.
            if (!pConnection.IsConnected()) return false;

            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pAccount.getUserName());
            vars.add(pAccount.getPassWord());
            vars.add(pAccount.getID());
            // Insert the person into the database.
            return pConnection.RunUpdate("UPDATE DTb_Accounts SET AccountUsername=?, AccountPassword=?, "
            							 +  " WHERE AccountID=?;", vars);
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
    }
    
    /**
     * Given an account, deactivates its entry in the database.
     * @param pAccount
     * @param pConnection
     * @return
     */
    public static boolean deactivateAccountInDatabase(Account pAccount, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pAccount == null || pConnection == null)
            return false;

		boolean connectionSetup = true;
        
        try
        {
			// Set up the connection if it's not already connected.
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            // If the connection wasn't set up correctly, return false now.
            if (!pConnection.IsConnected()) return false;

            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pAccount.getID());
            // Insert the person into the database.
            return pConnection.RunUpdate("UPDATE DTb_Accounts SET AccountActive=0 "
            							 +  " WHERE AccountID=?;", vars);
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
    }
    
    /**
     * Gets a pet from the database with the given ID.
     * @param pID
     * @param pConnection
     * @return
     */
    public static Students getStudentFromDatabaseWithID(int pID, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pConnection == null)
            return null;

		boolean connectionSetup = true;
        Students results = null;
        
        try
        {
			// Set up the connection if it's not already connected.
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            // If the connection wasn't set up correctly, return false now.
            if (!pConnection.IsConnected()) return null;
            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pID);
            // Check the database for the pet.
            ResultSet rs = pConnection.RunQuery("SELECT * FROM DTb_Students "
            								  + "RIGHT JOIN DTb_GradeLevels ON DTb_GradeLevels.GradeLevelID=DTb_Students.StudentGradeLevelID "
            								  + "RIGHT JOIN DTb_Schools ON DTb_Schools.SchoolID=DTb_Students.StudentSchoolID "
            								  + "WHERE StudentID=?;", vars);
            if (rs == null)
            	return null;
            if (rs.next())
            {
                // Create the new pet.
            	results = new Students(rs.getString("StudentName"), new GradeLevel(rs.getString("GradeLevelName"), rs.getInt("GradeLevelID")));
            	results.setID(pID);
            	results.setSchool(new School(rs.getString("SchoolName"), rs.getInt("SchoolID")));
            	results.setCreator(getPersonFromDatabaseWithID(rs.getInt("StudentCreatorPersonID"), pConnection));
            }
        }
        catch (SQLException e)
        {
            System.out.println("DBHelper.getPersonFromDatabaseWithAccount SQL error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
        return results;
    }
    
    /**
     * Gets a list of pets from the database that are associated with the given office.
     * @param pSchool
     * @param pConnection
     * @return
     */
    public static ArrayList<Students> getStudentsFromDatabaseForSchool(School pSchool, DBConn pConnection)
    {
    	if (pConnection == null)
	    	return null;

    	ArrayList<Students> results = new ArrayList<Students>();
    	boolean connectionSetup = true;
    	
    	try
        {
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            if (!pConnection.IsConnected()) return null;
            ArrayList<Object> vars = new ArrayList<Object>();
            ResultSet rs = null;
            // If the given office is null, just get all the pets. ALL OF THEM.
            if (pSchool == null)
            	rs = pConnection.RunQuery("SELECT * FROM DTb_Students "
            					+ "RIGHT JOIN DTb_GradeLevels ON DTb_GradeLevels.GradeLevelID=DTb_Students.StudentGradeLevelID;",
            					vars);
            else
            {
                vars.add(pSchool.getID());
	            rs = pConnection.RunQuery("SELECT * FROM DTb_Students "
						  		+ "RIGHT JOIN DTb_GradeLevels ON DTb_GradeLevels.GradeLevelID=DTb_Students.StudentGradeLevelID "
	                            + "WHERE StudentSchoolID=?;",
	                            vars);
            }
            if (rs == null)
            	return null;
            if (rs.next())
            {
            	// Add the current pet to the results list.
            	Students tempStudent = new Students(rs.getString("StudentName"), new GradeLevel(rs.getString("GradeLevelName"), rs.getInt("GradeLevelID")));
            	tempStudent.setID(rs.getInt("StudentID"));
            	results.add(tempStudent);
            }
        }
        catch (SQLException e)
        {
            System.out.println("DBHelper.getPersonFromDatabaseWithID SQL error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
        return results;
    }
    
    /**
     * Inserts a given pet into the database.
     * @param pStudent
     * @param pConnection
     * @return
     */
    public static boolean insertStudentIntoDatabase(Students pStudent, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pStudent == null || pConnection == null)
            return false;

		boolean connectionSetup = true;
		boolean results = false;
        
        try
        {
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            if (!pConnection.IsConnected()) return false;
            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pStudent.getName());
            vars.add(pStudent.getGradeLevel().getID());
            if (pStudent.getCreator() == null) vars.add(null);
            else vars.add(pStudent.getCreator().getID());
            if (pStudent.getSchool() == null) vars.add(null);
            else vars.add(pStudent.getSchool().getID());
            
            results = pConnection.RunUpdate("INSERT INTO DTb_Students "
					  + " (StudentName, StudentGradeLevelID, StudentCreatorPersonID, StudentSchoolID) "
					  + " VALUES (?, ?, ?, ?);", vars);
            
            
            // Get and set the lesson's ID.
            ResultSet rs = pConnection.RunQuery("SELECT TOP 1 StudentID FROM DTb_Students ORDER BY StudentID DESC;", null);
            if (rs.next())
            {
            	pStudent.setID(rs.getInt("StudentID"));
            }
            return results;
        }
        catch (SQLException e)
        {
            System.out.println("DBHelper.insertStudentIntoDatabase SQL error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
    }
    
    /**
     * Given a pet, edits its corresponding entry in the database to match.
     * @param pStudent
     * @param pConnection
     * @return
     */
    public static boolean editStudentInDatabase(Students pStudent, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pStudent == null || pConnection == null)
            return false;

		boolean connectionSetup = true;
        
        try
        {
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            if (!pConnection.IsConnected()) return false;
            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pStudent.getName());
            vars.add(pStudent.getGradeLevel().getID());
            if (pStudent.getCreator() == null) vars.add(null);
            else vars.add(pStudent.getCreator().getID());
            if (pStudent.getSchool() == null) vars.add(null);
            else vars.add(pStudent.getSchool().getID());
            vars.add(pStudent.getID());
            // Insert the lesson into the database.
            return pConnection.RunUpdate("UPDATE DTb_Students SET StudentName=?, StudentGradeLevelID=?, StudentCreatorPersonID=?, StudentSchoolID=? "
            							  + " WHERE StudentID=?;", vars);
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
    }
    
    /**
     * Given a pet, deactivates its entry in the database.
     * @param pStudent
     * @param pConnection
     * @return
     */
    public static boolean deactivateStudentInDatabase(Students pStudent, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pStudent == null || pConnection == null)
            return false;

		boolean connectionSetup = true;
        
        try
        {
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            if (!pConnection.IsConnected()) return false;
            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pStudent.getID());
            // Insert the lesson into the database.
            return pConnection.RunUpdate("UPDATE DTb_Students SET StudentActive=0 "
            							  + " WHERE StudentID=?;", vars);
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
    }
    
    /**
     * Given a pet and a person, links them together as pet and owner in the database.
     * @param pStudent
     * @param pPeople
     * @param pConnection
     * @return
     */
    public static boolean linkStudentToPersonInDatabase(Students pStudent, People pPeople, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pStudent == null || pConnection == null)
            return false;

		boolean connectionSetup = true;
        
        try
        {
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            if (!pConnection.IsConnected()) return false;
            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pStudent.getID());
            vars.add(pPeople.getID());
            // Insert the lesson into the database.
            return pConnection.RunUpdate("INSERT INTO LTb_StudentsPersons "
            							  + " (LStudentPersonStudentID, LStudentPersonPersonID) "
            							  + " VALUES (?, ?);", vars);
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
    }
    
    /**
     * Gets an office from the database with the given ID.
     * @param pID
     * @param pConnection
     * @return
     */
    public static School getSchoolFromDatabaseWithID(int pID, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pID <= 0 || pConnection == null)
            return null;

		boolean connectionSetup = true;
        School results = null;
        
        try
        {
			// Set up the connection if it's not already connected.
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            // If the connection wasn't set up correctly, return false now.
            if (!pConnection.IsConnected()) return null;
            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pID);
            // Check the database for the account.
            ResultSet rs = pConnection.RunQuery("SELECT * FROM DTb_Schools "
                            + "WHERE SchoolID= ? AND SchoolActive=1;",
                            vars);
            // If we successfully found both an account and Person, set them to the results.
            if (rs.next())
            {
                // Create the new Person.
            	results = new School(rs.getString("SchoolName"), pID);
            }
        }
        catch (SQLException e)
        {
            System.out.println("DBHelper.getSchoolFromDatabaseWithID SQL error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
        return results;
    }

    /**
     * Inserts a given office into the database.
     * @param pSchool
     * @param pConnection
     * @return
     */
    public static boolean insertSchoolIntoDatabase(School pSchool, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pSchool == null || pConnection == null)
            return false;

		boolean connectionSetup = true;
		boolean results = false;
        
        try
        {
			// Set up the connection if it's not already connected.
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            // If the connection wasn't set up correctly, return false now.
            if (!pConnection.IsConnected()) return false;
            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pSchool.getName());
            // Insert the office into the database.
            results = pConnection.RunUpdate("INSERT INTO DTb_Schools (Name) VALUES (?);", vars);
            // Get and set the office's ID.
            ResultSet rs = pConnection.RunQuery("SELECT TOP 1 SchoolID FROM DTb_Schools ORDER BY SchoolID DESC;", null);
            if (rs.next())
            {
            	pSchool.setID(rs.getInt("SchoolID"));
            }
            return results;
        }
        catch (SQLException e)
        {
            System.out.println("DBHelper.insertSchoolIntoDatabase SQL error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
    }

    /**
     * Given an office, edits the corresponding entry in the database.
     * @param pSchool
     * @param pConnection
     * @return
     */
    public static boolean editSchoolInDatabase(School pSchool, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pSchool == null || pConnection == null)
            return false;

		boolean connectionSetup = true;
		boolean results = false;
        
        try
        {
			// Set up the connection if it's not already connected.
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            // If the connection wasn't set up correctly, return false now.
            if (!pConnection.IsConnected()) return false;

            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pSchool.getName());
            vars.add(pSchool.getID());
            // Edit the office in the database.
            	results = pConnection.RunUpdate("UPDATE DTb_Schools SET SchoolName=? "
            						   + " WHERE SchoolID=?;", vars);
            return results;
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
    }

    /**
     * Given an office, deactivates its entry in the database.
     * @param pSchool
     * @param pConnection
     * @return
     */
    public static boolean deactivateSchoolInDatabase(School pSchool, DBConn pConnection)
    {
        // If any of the things passed in are null, return null early.
        if (pSchool == null || pConnection == null)
            return false;

		boolean connectionSetup = true;
        
        try
        {
			// Set up the connection if it's not already connected.
			if (!pConnection.IsConnected())
			{
				pConnection.SetUpConnection();
				connectionSetup = false;
			}
            // If the connection wasn't set up correctly, return false now.
            if (!pConnection.IsConnected()) return false;
            
            // Set up the array list of query variables.
            ArrayList<Object> vars = new ArrayList<Object>();
            vars.add(pSchool.getID());
            
            // Update the person's active flag in the database..
            return pConnection.RunUpdate("UPDATE DTb_Schools SET SchoolActive=0 WHERE SchoolID=?", vars);
        }
		finally
		{
			if (!connectionSetup)
				pConnection.Close();
		}
    }
}

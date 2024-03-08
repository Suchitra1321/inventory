package com.genericUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtils 
{
Connection con=null;
	
	/**
	 * 
	 * @throws SQLException
	 */
	public void connectToDatabase() throws SQLException
	{
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con=DriverManager.getConnection(IpathConstants.DBURL, IpathConstants.DBUN, IpathConstants.DBPWD);	
	}
	
	/**
	 * 
	 * @param query
	 * @param colIndex
	 * @param expdata
	 * @throws SQLException 
	 */
	public void executeAndGetData(String query, int colIndex, String expdata) throws SQLException
	{
		Statement state=con.createStatement();
		ResultSet result = state.executeQuery(query);
		boolean flag=false;
		while(result.next())
		{
			String actualData = result.getString(colIndex);
			if(actualData.equalsIgnoreCase(expdata))
			{
				flag=true;
				break;
			}
		}
		if(flag)
		{
			System.out.println("----data is present----");
		}
		else
		{
			System.out.println("----data is not present----");
		}
		
	}
	
	/**
	 * 
	 * @param query
	 * @throws SQLException 
	 */
	public void updateQuery(String query) throws SQLException
	{
		Statement state = con.createStatement();
		int result = state.executeUpdate(query);
	}
	
	/**
	 * @throws SQLException 
	 * 
	 */
	public void disconnectDB() throws SQLException
	{
		con.close();
	}
}

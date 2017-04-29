package com.quantumimplosion.parser;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DataInserter {
	

	private static final String dbHost = "67.205.170.157";
	private static final int dbPort = 5432;
	private static final String dbName = "overload";
	private static final String dbUsername = "quantumimplosion";
	private static final String dbPassword = "notpassword";
	private Connection connection;

	public DataInserter()
	{
		
	}
	
	private boolean connect()
	{
		if (connection == null)
		{
			try
			{
				connection = DriverManager.getConnection(
						   String.format("jdbc:postgresql://%s:%d/%s", dbHost, dbPort, dbName),dbUsername, dbPassword);
				//				connection = new PgConnection(dbHost);
			}
			catch (Exception e)
			{
				System.out.println("Connection failed");
				return false;
			}
		}
		return true;
	}

	public void close()
	{
		if (connection != null)
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean insertData(String tableName,List<Object> values)
	{
		if (connection == null)
		{
			connect();
		}
		List<Integer> argTypes = CSVParserConstants.tableToArgTypeMap.get(tableName);
		if (argTypes == null)
		{
			System.out.println("Invalid table name: " + tableName);
			return false;
		}
		else if (argTypes.size() != values.size())
		{
			System.out.println(String.format("Incorrect number of arguments for table: %s. Required: %d, Given: %d", tableName, argTypes.size(), values.size()));
			return false;
		}

		//Can definitely clean up the logic here.
		try {
			PreparedStatement insert = connection.prepareStatement(CSVParserConstants.tableToStatementMap.get(tableName));
			for (int i = 0; i < values.size(); i++)
			{
				Object val = values.get(i);
				if(val != null)
				{
					try
					{
						switch (argTypes.get(i)) //Type can only be string or double, used a switch statement to more easily accomodate more types
						{
						case CSVParserConstants.STRINGTYPE:
							insert.setString(i+1, (String) values.get(i));
							break;
						case CSVParserConstants.INTTYPE:
							insert.setInt(i+1, (Integer)values.get(i));
						case CSVParserConstants.DATETYPE:
							insert.setDate(i+1, (Date) values.get(i));
						default:
							insert.setDouble(i+1, (Double) values.get(i));
							break;
						}
					}
					catch(Exception e)
					{
						System.out.println("Error inserting value: " + values.get(i).toString() + ". Inserting null.");
						insert.setNull(i+1, argTypes.get(i) == CSVParserConstants.STRINGTYPE ? java.sql.Types.VARCHAR : java.sql.Types.DOUBLE);
					}
				}
				else
				{
					insert.setNull(i+1, argTypes.get(i) == CSVParserConstants.STRINGTYPE ? java.sql.Types.VARCHAR : java.sql.Types.DOUBLE);
				}
			}
			insert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

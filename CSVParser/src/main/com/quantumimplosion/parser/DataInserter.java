package com.quantumimplosion.parser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataInserter {
	public static final int STRINGTYPE = 1;
	public static final int DOUBLETYPE = 2;
	private static final Map<String, String> tableToStatementMap;
	public static final Map<String, List<Integer>> tableToArgTypeMap;
	static
	{
		Map<String, String> temp = new HashMap<String, String>();
		temp.put("node_information", "INSERT INTO node_information (system_id, from, to, nodeUpSinceMostRecent, nodeCountOffline, nodeCountMissing) VALUES ('%s', '%s', '%s', '%s', %f, %f);");
		temp.put("system", "INSERT INTO system (system_id, system_companyName, system_model, updated, system_install_date) VALUES ('%s', '%s', '%s', '%s', '%s');");
		temp.put("file_io", "INSERT INTO file_io (system_id, from, to, totalWriteIOsHistVlun, totalWriteIOsHistPortTargets, delAcks, delAcksPct, writesGt16s, writesGtXmsPct, writes1msPct, readsGtXmsPct, readsXmsPct, totalsGtXmsPct, totalsXmsPct, portReadAvgIOSizeKB, portWriteAvgIOSizeKB, portTotalAvgIOSizeKB) VALUES ('%s', '%s', '%s', %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f);");
		temp.put("bandwidth", "INSERT INTO bandwidth (system_id, from, to, portReadBandwidthMBPS, portWriteBandwidthMBPS, portTotalBandwidthMBPS) VALUES ('%s', '%s', '%s', %f, %f, %f);");
		temp.put("deduplication", "INSERT INTO deduplication(system_id, from, to, ddsSizeUsedTiB , ddsSizeUsedTiBPrevious, capacity_total_dedupeRatio) VALUES ('%s', '%s', '%s', %f, %f, %f);");
		temp.put("storage_usage", "INSERT INTO storage_usage (system_id, from, to, capacity_total_sizeTiB, capacity_total_freePct, capacity_byType_fc_sizeTiB, capacity_byType_nl_sizeTiB, capacity_byType_ssd_sizeTiB, virtualCapacity_byType_tdvv_vvCount, virtualCapacity_byType_tdvv_sizeTiB, vvCountHistVlun, vvCountHistVlunPrevious) VALUES ('%s', '%s', '%s', %f, %f, %f, %f, %f, %f, %f, %f, %f);");
		temp.put("cpu_usage", "INSERT INTO cpu_usage (system_id, from, to, cpuLatestSysAvgPct, cpuLatestUserAvgPct, cpuLatestTotalAvgPct, cpuLatestSysMaxPct, cpuLatestUserMaxPct, cpuLatestTotalMaxPct) VALUES ('%s', '%s', '%s', %f, %f, %f, %f, %f, %f);");

		Map<String, List<Integer>> temp2 = new HashMap<String, List<Integer>>();
		temp2.put("node_information", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE));
		temp2.put("system", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, STRINGTYPE, STRINGTYPE));
		temp2.put("file_io", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		temp2.put("bandwidth", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		temp2.put("deduplication", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		temp2.put("storage_usage", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		temp2.put("cpu_usage", Arrays.asList(STRINGTYPE, STRINGTYPE, STRINGTYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE, DOUBLETYPE));
		tableToArgTypeMap = Collections.unmodifiableMap(temp2);
		tableToStatementMap = Collections.unmodifiableMap(temp);
	}

	private static final String dbHost = "67.205.170.157";
	private static final int dbPort = 5432;
	private static final String dbName = "quantumimplosion";
	private static final String dbUsername = "quantumimplosion";
	private static final String dbPassword = "notpassword";
	private static Connection connection;

	private static boolean connect()
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
				return false;
			}
		}
		return true;
	}

	private static void close()
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
		List<Integer> argTypes = tableToArgTypeMap.get(tableName);
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
			PreparedStatement insert = connection.prepareStatement(tableToStatementMap.get(tableName));
			for (int i = 0; i < values.size(); i++)
			{
				Object val = values.get(i);
				if(val != null)
				{
					try
					{
						switch (argTypes.get(i)) //Type can only be string or double, used a switch statement to more easily accomodate more types
						{
						case STRINGTYPE:
							insert.setString(i+1, (String) values.get(i));
							break;
						default:
							insert.setDouble(i+1, (Double) values.get(i));
							break;
						}
					}
					catch(Exception e)
					{
						System.out.println("Error inserting value: " + values.get(i).toString() + ". Inserting null.");
						insert.setNull(i+1, argTypes.get(i) == STRINGTYPE ? java.sql.Types.VARCHAR : java.sql.Types.DOUBLE);
					}
				}
				else
				{
					insert.setNull(i+1, argTypes.get(i) == STRINGTYPE ? java.sql.Types.VARCHAR : java.sql.Types.DOUBLE);
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

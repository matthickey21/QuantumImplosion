package com.rest.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import java.net.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

@Path("/rest")
public class restServ {
  private static final String dbHost = "67.205.170.157";
  private static final int dbPort = 5432;
  private static final String dbName = "overload";
  private static final String dbUsername = "quantumimplosion";
  private static final String dbPassword = "notpassword";
  private static Connection connection = null;

  @GET
 @Produces(MediaType.APPLICATION_JSON)
 public JSONArray getTest() throws SQLException, JSONException{
	  	connect();
		PreparedStatement select = connection.prepareCall("SELECT * FROM public.\"System\"");
        ResultSet selectResults = select.executeQuery();
        close();
		return convert(selectResults);
 }
 
 
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/{user}/sets")
 public ResultSet retrieveSystemSets(@PathParam("user") String user) throws ConnectException, SQLException{
	 if(connect() == false)
		 	throw new ConnectException("Cannot connect to database: retrieveSystemSets");
		 PreparedStatement select = connection.prepareCall("SELECT set_id FROM public.\"System Set\" WHERE company = '" + user + "';");
	     ResultSet selectResults = select.executeQuery();
		 close();
		 return selectResults;
 }

 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/{user}/systems")
 public ResultSet retrieveSystem(@PathParam("user") String user) throws ConnectException, SQLException {
	 if(connect() == false)
		 	throw new ConnectException("Cannot connect to database: retrieveSystem");
		 PreparedStatement select = connection.prepareCall("SELECT * FROM public.\"System\" WHERE \"system_companyName\" = '" + user + "';");
	     ResultSet selectResults = select.executeQuery();
		 close();
		 return selectResults; 
 }
 
 @POST
 @Path("/{user}/sets/create")
 public void createSystemSet(@PathParam("user") String user, String setName, List<String> systemList) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectException("Cannot connect to database: createSystemSet");
	 Statement statement = connection.createStatement();
	 close();
	 
 }
 
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/{system}")
 public ResultSet systemOverview(@PathParam("system") String system) throws ConnectException, SQLException{
	 if(connect() == false)
	 	throw new ConnectException("Cannot connect to database: systemOverview");
	 PreparedStatement select = connection.prepareCall("SELECT * FROM public.\"System\" WHERE system_id = " + system + ";");
     ResultSet selectResults = select.executeQuery();
	 close();
	 return selectResults;
	 
 }
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/sets/{system_set}")
 public ResultSet systemSetOverview(@PathParam("system_set") List<String> system_set) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: systemSetOverview");
	 close();
	 return null;
	 
 }
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/{system}/storage")
 public ResultSet systemStorageData(@PathParam("system") String system) throws ConnectException, SQLException{
	 if(connect() == false)
		 	throw new ConnectException("Cannot connect to database: systemStorageData");
		 PreparedStatement select = connection.prepareCall("SELECT * FROM public.\"Storage Usage\" WHERE system_id = " + system + ";");
	     ResultSet selectResults = select.executeQuery();
		 close();
		 return selectResults;
	 
 }
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/sets/{system_set}/storage")
 public ResultSet systemSetStorageData(@PathParam("system_set") List<String> system_set) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: systemSetStorageData");
	 close();
	 return null;
	 
 }
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/{system}/cpu")
 public ResultSet systemCPUdata(@PathParam("system") String system) throws ConnectException, SQLException{
	 if(connect() == false)
		 	throw new ConnectException("Cannot connect to database: systemCPUdata");
		 PreparedStatement select = connection.prepareCall("SELECT * FROM public.\"CPU Usage\" WHERE system_id = " + system + ";");
	     ResultSet selectResults = select.executeQuery();
		 close();
		 return selectResults;
	 
 }
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/sets/{system_set}/cpu")
 public ResultSet systemSetCPUdata(@PathParam("system_set") List<String> system_set) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: systemSetCPUdata");
	 close();
	 return null;
	 
 }
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/{system}/io")
 public ResultSet systemFileIOdata(@PathParam("system") String system) throws ConnectException, SQLException{
	 if(connect() == false)
		 	throw new ConnectException("Cannot connect to database: systemOverview");
		 PreparedStatement select = connection.prepareCall("SELECT * FROM public.\"File I/O\" WHERE system_id = " + system + ";");
	     ResultSet selectResults = select.executeQuery();
		 close();
		 return selectResults;
	 
 }
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/sets/{system_set}/io")
 public ResultSet systemSetFileIOdata(@PathParam("system_set") List<String> system_set) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: systemSetFileIOdata");
	 close();
	 return null;
	 
 }
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/{system}/bandwidth")
 public ResultSet systemBandwidthData(@PathParam("system") String system) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: systemBandwidthData");
	 close();
	 return null;
	 
 }
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/{system}/node")
 public ResultSet systemNodeInfo(@PathParam("system") String system) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: systemNodeInfo");
	 close();
	 return null;
	 
 }

 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/sets/{system_set}/node")
 public ResultSet systemSetNodeInfo(@PathParam("system_set") List<String> system_set) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: systemSetNodeInfo");
	 close();
	 return null;
	 
 }
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/{system}/dedupe")
 public ResultSet systemDeduplicationData(@PathParam("system") String system) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: systemDeduplicationData");
	 close();
	 return null;
	 
 }
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/sets/{system_set}/dedupe")
 public ResultSet systemSetDeduplicationData(@PathParam("system_set") List<String> system_set) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: systemSetDeduplicationData");
	 close();
	 return null;
	 
 }
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/{system}/compare")
 public ResultSet likeSystemComparison(@PathParam("system") String system) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: likeSystemComparison");
	 close();
	 return null;
	 
 }
 
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/{system_1}/compare/{system_2}")
 public ResultSet specificSystemComparison(@PathParam("system_1") String system_1, @PathParam("system_2") String system_2) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: specificSystemComparison");
	 close();
	 return null;
	 
 }
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/sets/{system_set_2}/compare/{system_set_2}")
 public ResultSet specificSystemSetComparison(@PathParam("system_set_1") List<String> system_set_1, @PathParam("system_set_2") List<String> system_set_2) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: specificSystemSetComparison");
	 close();
	 return null;
	 
 }

 
 

 
 
 
 
 
 
 
 
 
 
 private static boolean connect()
 {
     if (connection == null)
     {
         try
         {
        	 connection = DriverManager.getConnection(
                     String.format("jdbc:postgresql://%s:%d/%s", dbHost, dbPort, dbName),dbUsername, dbPassword);
             //                connection = new PgConnection(dbHost);
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

 public static JSONArray convert( ResultSet rs )
		    throws SQLException, JSONException
		  {
		    JSONArray json = new JSONArray();
		    ResultSetMetaData rsmd = rs.getMetaData();

		    while(rs.next()) {
		      int numColumns = rsmd.getColumnCount();
		      JSONObject obj = new JSONObject();

		      for (int i=1; i<numColumns+1; i++) {
		        String column_name = rsmd.getColumnName(i);

		        if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
		         obj.put(column_name, rs.getArray(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT){
		         obj.put(column_name, rs.getInt(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
		         obj.put(column_name, rs.getBoolean(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
		         obj.put(column_name, rs.getBlob(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
		         obj.put(column_name, rs.getDouble(column_name)); 
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
		         obj.put(column_name, rs.getFloat(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
		         obj.put(column_name, rs.getInt(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
		         obj.put(column_name, rs.getNString(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
		         obj.put(column_name, rs.getString(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
		         obj.put(column_name, rs.getInt(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
		         obj.put(column_name, rs.getInt(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
		         obj.put(column_name, rs.getDate(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
		        obj.put(column_name, rs.getTimestamp(column_name));   
		        }
		        else{
		         obj.put(column_name, rs.getObject(column_name));
		        }
		      }

		      json.put(obj);
		    }

		    return json;
		  }


}

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

import java.net.ConnectionException;

@Path("/rest")
public class restServ {
  private static final String dbHost = "67.205.170.157";
  private static final int dbPort = 5432;
  private static final String dbName = "quantumimplosion";
  private static final String dbUsername = "quantumimplosion";
  private static final String dbPassword = "notpassword";
  private static Connection connection;

  @GET
 @Produces(MediaType.WILDCARD)
 public ResultSet getTest() throws SQLException{
	 	connect();
		PreparedStatement select = connection.prepareCall("SELECT * FROM test_table");
        ResultSet selectResults = select.executeQuery();
        while(selectResults.next()){
        	System.out.println(selectResults.getString(1));
        }
        close();
		return selectResults;
 }
 
 
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/{user}/sets")
 public ResultSet retrieveSystemSets(@PathParam("user") String user) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: retrieveSystemSets");
	 close();
	return null;
 }

 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/{user}/systems")
 public ResultSet retrieveSystem(@PathParam("user") String user) throws ConnectionException {
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: retrieveSystem");
	 close();
	 return null;
	 
 }
 @POST
 @Path("/{user}/sets/create")
 public void createSystemSet(@PathParam("user") String user, String setName, List<String> systemList) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: createSystemSet");
	 Statement statement = connection.createStatement();
	 close();
	 
 }
 
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/{system}")
 public ResultSet systemOverview(@PathParam("system") String system) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: systemOverview");
	 close();
	 return null;
	 
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
 public ResultSet systemStorageData(@PathParam("system") String system) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: systemStorageData");
	 close();
	 return null;
	 
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
 public ResultSet systemCPUdata(@PathParam("system") String system) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: systemCPUdata");
	 close();
	 return null;
	 
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
 public ResultSet systemFileIOdata(@PathParam("system") String system) throws ConnectionException{
	 if(connect() == false)
	 	throw new ConnectionException("Cannot connect to database: systemFileIOdata");
	 close();
	 return null;
	 
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




}

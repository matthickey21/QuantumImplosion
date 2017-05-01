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
        close();
	return selectResults;
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
 }    private static void close()
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

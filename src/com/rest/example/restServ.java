package com.rest.example;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import javax.ws.rs.GET;
	import javax.ws.rs.Path;
	import javax.ws.rs.PathParam;
	import javax.ws.rs.Produces;
	import javax.ws.rs.core.MediaType;
	import java.net.*;
	import org.apache.tomcat.jdbc.pool.DataSource;
	import org.apache.tomcat.jdbc.pool.PoolProperties;
	import org.json.JSONArray;
	import org.json.JSONException;
	import org.json.JSONObject;
	import java.sql.ResultSetMetaData;

@Path("/rest")
public class restServ {

  private static Connection connection = null;
  static PoolProperties p = null;
  static DataSource s = null;
 
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
 
 @GET
 @Produces(MediaType.APPLICATION_JSON)
 @Path("/data/{system}")
 public String systemOverview(@PathParam("system") String system) throws ConnectException, SQLException, JSONException{
	 if(connect() == false)
	 	throw new ConnectException("Cannot connect to database: systemOverview");
	 PreparedStatement select = connection.prepareCall("SELECT * FROM public.\"System\" WHERE system_id = " + system + ";");
     ResultSet selectResults = select.executeQuery();
	 close();
	 return convert(selectResults).toString();
	 
 }
 
 @GET
 @Produces(MediaType.APPLICATION_JSON)
 @Path("/data/{system}/storage")
 public String systemStorageData(@PathParam("system") String system) throws ConnectException, SQLException, JSONException{
	 if(connect() == false)
		 	throw new ConnectException("Cannot connect to database: systemStorageData");
		 PreparedStatement select = connection.prepareCall("SELECT * FROM public.\"Storage Usage\" WHERE system_id = " + system + ";");
	     ResultSet selectResults = select.executeQuery();
		 close();
		 return convert(selectResults).toString();
	 
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
 @Path("/data/{system}/bandwidth")
 public ResultSet systemBandwidthData(@PathParam("system") String system) throws ConnectException, SQLException{
	 if(connect() == false)
	 	throw new ConnectException("Cannot connect to database: systemBandwidthData");
	 close();
	 return null;
	 
 }
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/{system}/node")
 public ResultSet systemNodeInfo(@PathParam("system") String system) throws ConnectException, SQLException{
	 if(connect() == false)
	 	throw new ConnectException("Cannot connect to database: systemNodeInfo");
	 close();
	 return null;
	 
 }

 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/{system}/dedupe")
 public ResultSet systemDeduplicationData(@PathParam("system") String system) throws ConnectException, SQLException{
	 if(connect() == false)
	 	throw new ConnectException("Cannot connect to database: systemDeduplicationData");
	 close();
	 return null;
	 
 }

 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/{system}/compare")
 public ResultSet likeSystemComparison(@PathParam("system") String system) throws ConnectException, SQLException{
	 if(connect() == false)
	 	throw new ConnectException("Cannot connect to database: likeSystemComparison");
	 close();
	 return null;
	 
 }
 
 @GET
 @Produces(MediaType.WILDCARD)
 @Path("/data/{system_1}/compare/{system_2}")
 public ResultSet specificSystemComparison(@PathParam("system_1") String system_1, @PathParam("system_2") String system_2) throws ConnectException, SQLException{
	 if(connect() == false)
	 	throw new ConnectException("Cannot connect to database: specificSystemComparison");
	 close();
	 return null;
	 
 }
 
 private static boolean connect() throws SQLException
 {
	 p = new PoolProperties();
		p.setUrl("jdbc:postgresql://67.205.170.157:5432/" + "overload" );
		p.setDriverClassName( "org.postgresql.Driver" );
		p.setUsername( "quantumimplosion" );
		p.setPassword( "notpassword" );
		s = new org.apache.tomcat.jdbc.pool.DataSource( p );
		s.setPoolProperties(p);
		connection = s.getConnection();
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

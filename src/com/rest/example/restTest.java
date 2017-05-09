package com.rest.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.*;

import java.sql.ResultSet;

public class restTest {
	
	public static void main(String[] args) throws SQLException, ConnectException, JSONException {
		restServ r = new restServ();
		JSONArray rs = r.getTest();
}
}

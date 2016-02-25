/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Blacksteath
 */
public class DBUtil {
     private static  DataSource dataSource;
    
    static
  {
    try
    {
      System.out.println("Attempting to set up Connection DataSource");
      Context ctx = new InitialContext();
      Context envCtx = (Context)ctx.lookup("java:comp/env");
      dataSource = (DataSource)envCtx.lookup("jdbc/btdatabase");
      System.out.println("DATA SOURCE IS NOT NULL IT HAS BEEN SET TO:" + dataSource.toString());
    }
    catch (NamingException ne)
    {
      System.out.println("Naming Error during Initial Context lookup JNDI");
      ne.printStackTrace();
    }
  }
  
  public static Connection getPooledConnectionInstance()
  {
    if (DBUtil.dataSource != null) {
      try
      {
        return DBUtil.dataSource.getConnection();
      }
      catch (SQLException ex)
      {
        ex.printStackTrace();
        System.out.println("We ran into an error when calling dataSource.getConnection");
      }
    }
    return null;
  }
    
    
    
    
    
    
    public static void closeConnection(Connection con){
        try {
            if(con != null)con.close();
        } catch (SQLException ex) {
            System.out.println("Could not close Connection");
            ex.printStackTrace();
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ResultSet executeQuery(Statement stmt,  String sqlStatement){
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sqlStatement);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static void closeStatement(Statement statement){
        try {
            if(statement != null) statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void closeRS(ResultSet rs){
        try {
            if(rs != null)rs.close();
        } catch (SQLException ex) {
             ex.printStackTrace();
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Statement createStatement(Connection con){
        try {
            return con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void rollback(Connection con)
  {
    try
    {
      if (con != null) {
        con.rollback();
      }
    }
    catch (SQLException e)
    {
      System.out.println("Error, ROLLBACK DID NOT SUCCEED");
    }
  }
    
    }
    


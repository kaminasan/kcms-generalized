/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.SQL;

import com.kam.DBUtil.DBUtil;
import java.sql.Connection;

/**
 *
 * @author Blacksteath
 */
public class ConnectionFactory extends AbstractConnectionFactory {

    private static final ConnectionFactory instance = new ConnectionFactory();
    private static int numberOfConnectionsCalled = 0;
    public static final String URL = "jdbc:mysql://yourserver:yourport/databasename";
    public static final String USER = "username";
    public static final String PASSWORD = "password";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    @Override
   Connection makeConnection(String type)
  {
    if (type.equalsIgnoreCase("single"))
    {
      SingleConnection newConnection = new SingleConnection();
      return newConnection.getConnection();
    }
   else if (type.equals("pooled"))
    {
      Connection con = DBUtil.getPooledConnectionInstance();
      if (con == null) {
        System.out.println("The connection from DB UTIL POOLED IS NULL!");
      }
      return con;
    }
   else return null;
  }
    static public Connection returnConnection(String type) {
        ConnectionFactory.numberOfConnectionsCalled++;
        return instance.makeConnection(type);

    }

}

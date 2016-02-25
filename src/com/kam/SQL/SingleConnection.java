/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Blacksteath
 */
public class SingleConnection {
    private Connection myConnection = null;
    
    public SingleConnection(){
        try{
            Class.forName(ConnectionFactory.DRIVER_CLASS);
        }
        catch(ClassNotFoundException cnf){
            cnf.printStackTrace();   
        }
    }
        public Connection getConnection(){
            try{
                myConnection = DriverManager.getConnection(ConnectionFactory.URL, ConnectionFactory.USER, ConnectionFactory.PASSWORD);
            }
            catch(SQLException sexc){
                sexc.printStackTrace();
            }
            return myConnection;
        }
    }


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.SQL;

import java.sql.Connection;

/**
 *
 * @author KaminaSan <www.kaminasan.com>
 */
abstract class AbstractConnectionFactory {
     
    abstract Connection makeConnection(String type);

    
     
    
}

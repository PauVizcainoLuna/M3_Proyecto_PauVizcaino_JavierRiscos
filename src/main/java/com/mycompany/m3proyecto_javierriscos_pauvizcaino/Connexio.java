package com.mycompany.m3proyecto_javierriscos_pauvizcaino;

/**
 *
 * @author PauVizcaino
 */
import java.sql.*;
import java.sql.Connection;

public class Connexio {

    private final String URL = "jdbc:mysql://192.168.56.4:3306/base_dades_projecte";//nom bd
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String USER = "root";
    private final String PASSWD = "";   
   

    public Connection connecta() {
        Connection connexio = null;
        try {
            //Carreguem el driver          
            Class.forName(DRIVER); 
            connexio = DriverManager.getConnection(URL, USER, PASSWD); 
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getLocalizedMessage());
        }    
        return connexio;
    }
}

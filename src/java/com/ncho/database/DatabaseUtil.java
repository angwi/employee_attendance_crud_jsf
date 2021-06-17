package com.ncho.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dominic E. Ncho
 */
public class DatabaseUtil {

   
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/employeeDB?serverTimezone=Africa/Douala";
    private static Connection connection;
    private static final String USERNAME = "your_database_username";
    private static final String PASSWORD = "your_database_password";

    public static Connection getDatabaseConnection() {
    
        try {

            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return connection;
    }
    
    public static void closeConnection(Connection conn){
        try {
            if(!conn.isClosed()){
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

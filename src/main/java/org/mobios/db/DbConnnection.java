package org.mobios.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnnection {
    private static DbConnnection instance;
    private Connection connection;

    private DbConnnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/NIC_dataService", "root", "1234");
    }

    public static DbConnnection getInstance() throws SQLException {
        if(instance==null){
            return instance = new DbConnnection();

        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}

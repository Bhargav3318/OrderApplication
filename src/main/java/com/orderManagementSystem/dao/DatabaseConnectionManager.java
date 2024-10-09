package com.orderManagementSystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionManager {

    private final String url;
    private final Properties properties;

    // Constructor using @Value to inject property values
    public DatabaseConnectionManager(
            @Value("${db.host}") String host,
            @Value("${db.name}") String databaseName,
            @Value("${db.username}") String username,
            @Value("${db.password}") String password) {
        
        this.url = "jdbc:postgresql://" + host + "/" + databaseName;
        this.properties = new Properties();
        this.properties.setProperty("user", username);
        this.properties.setProperty("password", password);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.properties);
    }
}

package com.projectodonto.checkpointjava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigurationJDBC {
    private String jdbcDriver;
    private String dbUrl;
    private String user;
    private String password;

    public ConfigurationJDBC(String jdbcDriver, String dbUrl, String user, String password) {
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(this.jdbcDriver);
            return DriverManager.getConnection(this.dbUrl, this.user, this.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }

    }

}

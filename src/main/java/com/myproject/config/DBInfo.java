package com.myproject.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

@Component
public class DBInfo {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void printDbInfo() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println("\r\n :::::::Database Info:::::::::::");
            System.out.println("    Database Product Name: " + metaData.getDatabaseProductName());
            System.out.println(" Database Product Version: " + metaData.getDatabaseProductVersion());
            System.out.println("              Driver Name: " + metaData.getDriverName());
            System.out.println("           Driver Version: " + metaData.getDriverVersion());
            System.out.println("                      URL: " + metaData.getURL());
            System.out.println("                     User: " + metaData.getUserName());
            System.out.println("-------------------------");
            System.out.println("\r\n");
        }
    }
}

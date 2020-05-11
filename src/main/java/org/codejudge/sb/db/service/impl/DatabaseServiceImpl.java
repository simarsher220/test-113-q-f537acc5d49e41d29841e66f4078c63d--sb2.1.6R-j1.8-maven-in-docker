package org.codejudge.sb.db.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.codejudge.sb.common.CoreProperties;
import org.codejudge.sb.db.service.api.DatabaseService;
import org.codejudge.sb.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
@Service
public class DatabaseServiceImpl implements DatabaseService {

    private static final String JDBC_DRIVER = "org.h2.Driver";

    @Autowired
    private CoreProperties properties;

    @Override
    public void resetDB() throws CustomException {
        Connection conn = null;
        Statement stmt = null;
        String dataSourceUrl = properties.getDataSourceUrl() + ";EXCLUSIVE=1";
        String dataSourceUsername = properties.getDataSourceUsername();
        String dataSourcePassword = properties.getDataSourcePassword();
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(dataSourceUrl, dataSourceUsername, dataSourcePassword);

            //STEP 3: Execute a query
            System.out.println("Reinitializing the database from sql file...");
            stmt = conn.createStatement();
            String sql =  "RUNSCRIPT FROM 'classpath:data.sql'";
            stmt.execute(sql);
            System.out.println("Database reinitialized...");

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception se) {
            throw new CustomException("Error reinitializing database!", HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            //finally block used to close resources
            try{
                if(stmt != null) {
                    stmt.close();
                }
            } catch(SQLException se2) {
                se2.printStackTrace();
            } // nothing we can do
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}

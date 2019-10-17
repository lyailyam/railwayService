package kz.edu.nu.cs.se;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnector {
    public static Connection getDatabaseConnection() {
        Connection conn = null;

        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
            conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}

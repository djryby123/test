package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    public static final String url = "jdbc:mysql://127.0.0.1/employee";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password="root";

    public static Connection conn = null;

    public static Connection getConn() {
        try {
            Class.forName(name);
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

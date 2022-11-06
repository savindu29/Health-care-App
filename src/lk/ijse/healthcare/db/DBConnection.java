package lk.ijse.healthcare.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection DBConnection;
    private Connection connection;
    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/healthcare",
                "root","1234"
        );
    }
    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        return DBConnection ==null? DBConnection = new DBConnection():
                DBConnection;
    }
    public Connection getConnection(){
        return connection;
    }
}

package BRS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null)
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BRS",
                    "admin", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

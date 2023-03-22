package BRS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthenticationManager {
    private static User loggedInUser;

    private static boolean isAuthentic(String userId, String userPassword) throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("SELECT id FROM User WHERE id = '%s' and password = sha('%s')", userId, userPassword);

        ResultSet rs = stmt.executeQuery(command);

        String id = null;
        if (rs.next())
            id = rs.getString("id");

        stmt.close();

        return id != null;
    }

    public static boolean authenticate(String userId, String userPassword) throws SQLException {
        boolean check = isAuthentic(userId, userPassword);

        if (check) {
            if (getUserRole(userId).equals(User.CUSTOMER))
                AuthenticationManager.loggedInUser = Customer.getCustomer(userId);
            else if (getUserRole(userId).equals(User.ADMIN)) {
                AuthenticationManager.loggedInUser = Administrator.getAdmin(userId);
            }
        }

        return check;
    }

    public static User getLoggedInUser() {
        return AuthenticationManager.loggedInUser;
    }

    public static String getUserRole(String userId) throws SQLException{
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("SELECT role FROM User WHERE id = '%s'", userId);
        ResultSet rs = stmt.executeQuery(command);

        String role = null;
        while (rs.next()) {
            role = rs.getString("role");
        }

        stmt.close();

        return role;
    }

    public static void registerUser(String userId, String userPassword, String userRole) throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("INSERT INTO User VALUES ('%s', sha('%s'), '%s')", userId, userPassword, userRole);
        stmt.executeUpdate(command);
        stmt.close();
    }

    public static void unregisterUser(String userId) throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("DELETE FROM User WHERE id = '%s'", userId);
        stmt.executeUpdate(command);
        stmt.close();
    }
}
package BRS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Driver extends User {

    public Driver(String id, String firstname, String middlename, String lastname, Address address, String phoneNumber) {
        super(id, firstname, middlename, lastname, address, phoneNumber);
    }

    public static Driver getDriver(String driverId) throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("SELECT * FROM Driver WHERE id = '%s';", driverId);

        ResultSet rs = stmt.executeQuery(command);
        Driver driver = null;
        while (rs.next()) {
            driver = new Driver(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), new Address(rs.getString(5)), rs.getString(6));
        }

        stmt.close();

        return driver;
    }

    public void add() throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("INSERT INTO Driver (id, firstname, middlename, lastname, address, phonenumber) " +
                        "values ('%s', '%s', '%s', '%s', '%s', '%s')", id, firstname, middlename, lastname, address.toString(), phoneNumber);
        stmt.executeUpdate(command);
        stmt.close();
    }

    public List<Route> getScheduledRoutes() throws SQLException {
        List<Route> routes = new ArrayList<>();

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();

        String command = String.format("SELECT r.id, r.source, r.destination, r.departureTime, r.arrivalTime " +
                        "FROM BusAssignment as ba " +
                        "JOIN ScheduledRoute r ON ba.routeId = r.id AND ba.driverId = '%s';"
                , id);

        ResultSet rs = stmt.executeQuery(command);

        while (rs.next()) {
            routes.add(new Route(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getDate(4), rs.getDate(5)));
        }
        stmt.close();

        return routes;
    }

    public boolean isActive() throws SQLException {
        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        String command = String.format("SELECT COUNT(routeId) as count_route FROM BusAssignment WHERE driverId = '%s'",
                id);

        ResultSet rs = stmt.executeQuery(command);

        rs.next();
        int count = rs.getInt("count_route");

        rs.close();
        stmt.close();

        return count != 0;
    }

    public void remove() throws ReservationSystemException, SQLException {
        if (isActive())
            throw new ReservationSystemException("Can't Remove Active Driver");

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("DELETE FROM Driver WHERE id = '%s'", id);
        stmt.executeUpdate(command);
        stmt.close();
    }

    public static int getTotalNumber() throws SQLException {
        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        String command = String.format("SELECT COUNT(id) as count_driver FROM Driver");

        ResultSet rs = stmt.executeQuery(command);

        rs.next();
        int count = rs.getInt("count_driver");

        rs.close();
        stmt.close();

        return count;
    }

}

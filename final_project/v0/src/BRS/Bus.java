package BRS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Bus {
    private String id;
    private int maxCapacity;

    public Bus(String id, int maxCapacity) {
        this.id = id;
        this.maxCapacity = maxCapacity;
    }
    
    public static Bus getBus(String busId) throws SQLException{
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("SELECT * FROM Bus WHERE id = '%s'", busId);
        
        ResultSet rs = stmt.executeQuery(command);
        Bus bus = null;
        while (rs.next()) {
             bus = new Bus(rs.getString(1), rs.getInt(2));
        }

        stmt.close();

        return bus;
    }

    public void add() throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("INSERT INTO Bus (id, capacity) values ('%s', %s)", id, maxCapacity);
        stmt.executeUpdate(command);
        stmt.close();
    }

    public void remove() throws SQLException, ReservationSystemException {
        if (getScheduledRoutes().size() != 0)
            throw new ReservationSystemException("Can't Remove Assigned Bus");

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("DELETE FROM Bus WHERE id = '%s'", id);
        stmt.executeUpdate(command);
        stmt.close();
    }

    public static int getTotalNumber() throws SQLException {
        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        String command = String.format("SELECT COUNT(id) as count_bus FROM Bus");

        ResultSet rs = stmt.executeQuery(command);

        rs.next();
        int count = rs.getInt("count_bus");

        rs.close();
        stmt.close();

        return count;
    }

    public List<Route> getScheduledRoutes() throws SQLException {
        List<Route> routes = new ArrayList<>();

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();

        String command = String.format("SELECT r.id, r.source, r.destination, r.departureTime, r.arrivalTime " +
                        "FROM BusAssignment as ba " +
                        "JOIN ScheduledRoute r ON ba.routeId = r.id AND ba.busId = '%s' ;"
                , id);

        ResultSet rs = stmt.executeQuery(command);

        while (rs.next()) {
            routes.add(new Route(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getDate(4), rs.getDate(5)));
        }
        stmt.close();

        return routes;
    }

    public List<Integer> getAvailableSeats(String routeId) throws SQLException {
        List<Integer> takenSeats = new ArrayList<>();

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();

        String command = String.format("SELECT seatNumber " +
                        "FROM Reservation " +
                        "WHERE busId = '%s' AND routeId = '%s'"
                , id, routeId);

        ResultSet rs = stmt.executeQuery(command);

        while (rs.next()) {
            takenSeats.add(rs.getInt("seatNumber"));
        }

        rs.close();
        stmt.close();

        List<Integer> availableSeats = new ArrayList<>();
        for (int i=0; i<maxCapacity; i++) {
            if (!takenSeats.contains(i))
                availableSeats.add(i);
        }

        return availableSeats;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}

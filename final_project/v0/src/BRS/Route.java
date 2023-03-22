package BRS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Route {
    private String id;
    private String source;
    private String destination;
    private Date departureTime;
    private Date arrivalTime;

    public Route(String id, String source, String destination, Date departureTime, Date arrivalTime) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public static Route getRoute(String routeId) throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("SELECT id, source, destination, departureTime, arrivalTime" +
                " FROM ScheduledRoute WHERE id = '%s'", routeId);

        ResultSet rs = stmt.executeQuery(command);
        Route route = null;
        while (rs.next()) {
            route = new Route(rs.getString("id"), rs.getString("source"),
                    rs.getString("destination"), rs.getDate("departureTime"),
                    rs.getDate("arrivalTime"));
        }

        stmt.close();

        return route;
    }

    public void add() throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String command = String.format("INSERT INTO ScheduledRoute(id, source, destination, departureTime, arrivalTime) " +
                        "values ('%s', '%s', '%s', '%s', '%s')", id, source, destination, sdf.format(departureTime),
                sdf.format(arrivalTime));
        stmt.executeUpdate(command);
        stmt.close();
    }

    public static List<Route> filter () throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("Select * from ScheduledRoute");
        ResultSet rs = stmt.executeQuery(command);

        List<Route> routes = new ArrayList<>();
        while (rs.next()) {
            routes.add(new Route(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getDate(4), rs.getDate(5) ));
        }
        stmt.close();
        return routes;
    }

    public static int getTotalNumber() throws SQLException {
        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        String command = String.format("SELECT COUNT(id) as count_route FROM ScheduledRoute");

        ResultSet rs = stmt.executeQuery(command);

        rs.next();
        int count = rs.getInt("count_route");

        rs.close();
        stmt.close();

        return count;
    }

    public void remove() throws ReservationSystemException, SQLException {
        if (isActive())
            throw new ReservationSystemException("Can't Remove Active Route!");

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("DELETE FROM ScheduledRoute WHERE id = '%s'", id);
        stmt.executeUpdate(command);

        command = String.format("DELETE FROM BusAssignment WHERE routeId = '%s'", id);
        stmt.executeUpdate(command);
        stmt.close();
    }

    public int countReservation() throws SQLException {
        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        String command = String.format("SELECT COUNT(routeId) as count_route FROM Reservation WHERE routeId = '%s'", id);

        ResultSet rs = stmt.executeQuery(command);

        rs.next();
        int count = rs.getInt("count_route");

        rs.close();
        stmt.close();

        return count;
    }

    public int countAssignment() throws SQLException {
        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        String command = String.format("SELECT COUNT(routeId) as count_route FROM BusAssignment WHERE routeId = '%s'", id);

        ResultSet rs = stmt.executeQuery(command);

        rs.next();
        int count = rs.getInt("count_route");

        rs.close();
        stmt.close();

        return count;
    }

    public boolean isActive() throws SQLException {
        return countReservation() != 0 || countAssignment() != 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}

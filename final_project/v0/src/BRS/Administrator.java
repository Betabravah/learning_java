package BRS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Administrator extends User {

    public Administrator(String id, String firstname, String middlename, String lastname, Address address, String phoneNumber) {
        super(id, firstname, middlename, lastname, address, phoneNumber);
    }

    public static Administrator getAdmin(String id) throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("SELECT * FROM Administrator WHERE id = '%s'", id);

        ResultSet rs = stmt.executeQuery(command);
        Administrator admin = null;
        while (rs.next()) {
            admin = new Administrator(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), new Address(rs.getString(5)), rs.getString(6));
        }

        stmt.close();

        return admin;
    }

    private boolean checkScheduleConflict(List<Route> routes, Route new_route) {
        long new_dep_time = new_route.getDepartureTime().getTime();
        long new_arr_time = new_route.getArrivalTime().getTime();

        long dep_time;
        long arr_time;
        for (Route r: routes) {
            dep_time = r.getDepartureTime().getTime();
            arr_time = r.getArrivalTime().getTime();

            if ( dep_time <= new_dep_time && new_dep_time <= arr_time || dep_time <= new_arr_time && new_arr_time <= arr_time )
                return true;
        }

        return false;
    }

    public void allocateDriverBusRoute(String driverId, String busId, String routeId) throws ReservationSystemException, SQLException {
        Bus bus = Bus.getBus(busId);
        Driver driver = Driver.getDriver(driverId);
        Route route = Route.getRoute(routeId);

        if (driver == null)
            throw new ReservationSystemException("Driver not found!");
        if (bus == null)
            throw new ReservationSystemException("Bus not found!");
        if (route == null)
            throw new ReservationSystemException("Route not found");

        List<Route> driverSchedule = driver.getScheduledRoutes();
        List<Route> busSchedule = bus.getScheduledRoutes();

        if (checkScheduleConflict(driverSchedule, route) || checkScheduleConflict(busSchedule, route))
            throw new ReservationSystemException("The Assignment Creates Schedule Conflict!");

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("INSERT INTO BusAssignment(busid, routeid, driverid) values ('%s', '%s', '%s')", busId, routeId, driverId);
        stmt.executeUpdate(command);
        stmt.close();
    }

    public void removeAssignment(String busId, String routeId) throws ReservationSystemException, SQLException {
        Route route = Route.getRoute(routeId);
        Bus bus = Bus.getBus(busId);

        if (bus == null)
            throw new ReservationSystemException("Bus not found!");
        if (route == null)
            throw new ReservationSystemException("Route not found!");
        if (route.isActive())
            throw new ReservationSystemException("Can't Remove Assignment With Active Reservation!");

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("DELETE FROM BusAssignment WHERE busId = '%s' AND routeId = '%s'", busId, routeId);
        stmt.executeUpdate(command);
        stmt.close();
    }

    public void updateInfo(String firstname, String middlename, String lastname, Address address, String phoneNumber) throws SQLException{
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("UPDATE Administrator set firstname = '%s', " +
                "middlename = '%s', lastname='%s', address='%s', phonenumber='%s' WHERE id = '%s';",
                firstname, middlename, lastname, address.toString(), phoneNumber, id);
        stmt.executeUpdate(command);
        stmt.close();
    }


}

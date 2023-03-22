package BRS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Customer extends User {

    public Customer(String id, String firstname, String middlename, String lastname, Address address, String phoneNumber) {
        super(id, firstname, middlename, lastname, address, phoneNumber);
    }

    public static Customer getCustomer(String customerId) throws SQLException{
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("SELECT * FROM Customer WHERE id = '%s'", customerId);

        ResultSet rs = stmt.executeQuery(command);
        Customer customer = null;
        while (rs.next()) {
            customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), new Address(rs.getString(5)), rs.getString(6));
        }

        rs.close();
        stmt.close();

        return customer;
    }

    public void add(String password) throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("INSERT INTO Customer values ('%s', '%s', '%s', '%s', '%s', '%s')",
                id, firstname, middlename, lastname, address.toString(), phoneNumber);
        stmt.executeUpdate(command);
        stmt.close();

        AuthenticationManager.registerUser(id, password, User.CUSTOMER);
    }

    public static int getTotalNumber() throws SQLException {
        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        String command = String.format("SELECT COUNT(id) as count_customer FROM Customer");

        ResultSet rs = stmt.executeQuery(command);

        rs.next();
        int count = rs.getInt("count_customer");

        rs.close();
        stmt.close();

        return count;
    }

    public ArrayList<Ticket> getTickets() throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("SELECT id FROM Reservation WHERE customerId = '%s'", id);

        ResultSet rs = stmt.executeQuery(command);
        ArrayList<Ticket> tickets = new ArrayList<>();
        while (rs.next()) {
            tickets.add(Ticket.getTicket(rs.getString("id")));
        }

        rs.close();
        stmt.close();

        return tickets;
    }

    public void reserveSeat(String busId, String routeId, int seatNumber) throws ReservationSystemException, SQLException {
        Bus bus = Bus.getBus(busId);
        Route route = Route.getRoute(routeId);

        if (bus == null)
            throw new ReservationSystemException("Bus not found!");
        if (route == null)
            throw new ReservationSystemException("Route not found!");
        if (!bus.getAvailableSeats(routeId).contains(seatNumber))
            throw new ReservationSystemException("Seat is already taken!");

        Ticket ticket = new Ticket(id, busId, routeId, seatNumber, Calendar.getInstance().getTime());
        ticket.reserve();
    }

    public void cancelReservation(String ticketId) throws SQLException {
        Ticket ticket = Ticket.getTicket(ticketId);
        if (ticket != null)
            ticket.cancel();
    }

    public void cancelAllReservations() throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("DELETE FROM Reservation WHERE customerId = '%s'", id);
        stmt.executeUpdate(command);
        stmt.close();
    }

    public void updateInfo(String firstname, String middlename, String lastname, Address address, String phoneNumber) throws SQLException{
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("UPDATE Customer set firstname = '%s', " +
                        "middlename = '%s', lastname='%s', address='%s', phonenumber='%s' WHERE id = '%s';",
                firstname, middlename, lastname, address.toString(), phoneNumber, id);
        stmt.executeUpdate(command);
        stmt.close();
    }

    public void delete() throws SQLException {
        AuthenticationManager.unregisterUser(id);

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("DELETE FROM Customer WHERE id = '%s'", id);
        stmt.executeUpdate(command);
        stmt.close();

        cancelAllReservations();
    }

}

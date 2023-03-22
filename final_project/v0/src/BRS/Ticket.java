package BRS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {
    private String id;
    private String customerId;
    private String busId;
    private String routeId;
    private int seatNumber;
    private Date purchaseDate;

    public Ticket(String customerId, String busId, String routeId, int seatNumber, Date purchaseDate) throws SQLException, ReservationSystemException {
        this.id = generateTicketId();
        this.customerId = customerId;
        this.busId = busId;
        this.routeId = routeId;
        this.seatNumber = seatNumber;
        this.purchaseDate = purchaseDate;
    }

    private Ticket(String id, String customerId, String busId, String routeId, int seatNumber, Date purchaseDate) throws SQLException {
        this.id = id;
        this.customerId = customerId;
        this.busId = busId;
        this.routeId = routeId;
        this.seatNumber = seatNumber;
        this.purchaseDate = purchaseDate;
    }

    public static Ticket getTicket(String ticketId) throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("SELECT * FROM Reservation WHERE id = '%s'", ticketId);

        ResultSet rs = stmt.executeQuery(command);
        Ticket ticket = null;
        while (rs.next()) {
            ticket = new Ticket(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getInt(5), rs.getDate(6));
        }

        stmt.close();

        return ticket;
    }

    private static String generateTicketId() throws SQLException, ReservationSystemException {
//        int ticketCounter = 0;
//
//        Connection con = Database.getConnection();
//
//        Statement stmt = con.createStatement();
//        String command = String.format("SELECT COUNT(id) as count_tickets FROM Reservation");
//
//        ResultSet rs = stmt.executeQuery(command);
//
//        rs.next();
//        ticketCounter = rs.getInt("count_tickets");
//
//        rs.close();
//        stmt.close();

        int id = 0;
        for (; id<99999; id++) {
            if (getTicket(String.format("TKT-%05d", id)) == null)
                return String.format("TKT-%05d", id);
        }
        throw new ReservationSystemException("Ticket ID Runned Out!");

    }

    public void reserve() throws SQLException {
        // To format the purchase date to meet MySQL datetime format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("INSERT INTO Reservation VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                id, customerId, busId, routeId, seatNumber, dateFormat.format(purchaseDate));
        stmt.executeUpdate(command);
        stmt.close();
    }

    public void cancel() throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        String command = String.format("DELETE FROM Reservation WHERE id = '%s'", id);
        stmt.executeUpdate(command);
        stmt.close();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}

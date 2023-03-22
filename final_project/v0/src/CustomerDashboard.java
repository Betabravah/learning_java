import BRS.*;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class CustomerDashboard extends JPanel {
    public static String NAME = "CUSTOMER_DASHBOARD";

    private JDialog profileDiag;

    private JPanel card;
    private GridBagConstraints gbc = new GridBagConstraints();

    private JPanel topPanel;
    private JPanel mainContent;
    private JPanel ticketPanel;
    private JPanel inputPanel;
    private JPanel selectionPanel;

    private JTextField dateInput;
    private JTextField sourceInput;
    private JTextField destinationInput;

    JFrame parent;

    public CustomerDashboard (JPanel card, JFrame parent) {
        this.card = card;
        this.parent = parent;

        setLayout(new BorderLayout());

        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.NORTHWEST;

        // Setup Panel
        topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainContent = new JPanel();
        ticketPanel = new JPanel();
        inputPanel = new JPanel(new GridBagLayout());
        selectionPanel = new JPanel();

        BoxLayout mainLayout = new BoxLayout(mainContent, BoxLayout.Y_AXIS);
        mainContent.setLayout(mainLayout);

        inputPanel.setBorder(BorderFactory.createTitledBorder("Date & Location"));
        selectionPanel.setBorder(BorderFactory.createTitledBorder("Available Buses"));
        selectionPanel.setLayout(new GridBagLayout());

        ((javax.swing.border.TitledBorder) inputPanel.getBorder()).setTitleFont(new Font("Tahoma", Font.PLAIN, 14));
        ((javax.swing.border.TitledBorder) selectionPanel.getBorder()).setTitleFont(new Font("Tahoma", Font.PLAIN, 14));

        add(topPanel, BorderLayout.NORTH);
        add(mainContent, BorderLayout.CENTER);

        inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputPanel.getMinimumSize().height));

        mainContent.add(inputPanel);
        mainContent.add(Box.createRigidArea(new Dimension(0, 20)));

        JScrollPane selectionScroll = new JScrollPane(selectionPanel);
        mainContent.add(selectionScroll);


        // Top Panel
        JButton profileBtn = new JButton("Profile");
        JButton logoutBtn = new JButton("Logout");

        topPanel.add(profileBtn);
        topPanel.add(logoutBtn);


        // Input Panel
        JLabel date = new JLabel("Date");
        dateInput = new JTextField(20);

        JLabel from = new JLabel("From");
        sourceInput = new JTextField(20);

        JLabel to = new JLabel("To");
        destinationInput = new JTextField(20);

        JButton searchBtn = new JButton("Search");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        inputPanel.add(from, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        inputPanel.add(sourceInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        inputPanel.add(to, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        inputPanel.add(destinationInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        inputPanel.add(searchBtn, gbc);


        ticketPanel.setLayout(new GridBagLayout());
        gbc.anchor = GridBagConstraints.NORTHWEST;

        JScrollPane scroller = new JScrollPane(ticketPanel);
        add(scroller, BorderLayout.EAST);

        scroller.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                JLabel ticketTitle = new JLabel("Booked Tickets");
                ticketTitle.setFont(new Font("Serif", Font.BOLD, 16));
                ticketTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
                ticketTitle.setPreferredSize(new Dimension(250, 20));

                gbc.gridx = gbc.gridy = 0;
                gbc.weighty = 0.01;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                ticketPanel.add(ticketTitle, gbc);

                try {
                    Customer customer = (Customer) AuthenticationManager.getLoggedInUser();

                    ArrayList<Ticket> tickets = customer.getTickets();

                    int i = 1;
                    for (Ticket ticket: tickets) {
                        if (i == tickets.size())
                            gbc.weighty = 1;
                        gbc.gridy = i++;
                        ticketPanel.add(new TicketContainer(ticket), gbc);
                    }

                } catch (SQLNonTransientConnectionException e) {
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                ticketPanel.revalidate();
                ticketPanel.repaint();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
                for (Component comp: ticketPanel.getComponents()) {
                    ticketPanel.remove(comp);
                }
                ancestorAdded(event);
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }
        });


        /********************** Buttons Functionality ****************************/
        profileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profileDiag = new ProfileDialog(parent, card);
                profileDiag.setVisible(true);
            }
        });

        logoutBtn.addActionListener(new ActionListener() {
            CardLayout c;

            @Override
            public void actionPerformed(ActionEvent e) {
                c = (CardLayout) card.getLayout();
                c.show(card, LoginView.NAME);
            }
        });

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySearchResult();
            }
        });
        /*************************************************************************/

    }

    private void displaySearchResult() {
        for (Component comp: selectionPanel.getComponents()) {
            selectionPanel.remove(comp);
        }

        String[] columnNames = {"Bus", "Source", "Destination", "Available Seats", "Departure Time", ""};

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel label;
        for (int i=0; i<columnNames.length; i++) {
            label = new JLabel(columnNames[i]);
            label.setFont(new Font("Serif", Font.BOLD, 12));
            selectionPanel.add(label, gbc);
            gbc.gridx += 1;
        }

        JSeparator separator = new JSeparator();

        gbc.gridwidth = columnNames.length;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;

        ArrayList<String[]> searchResult;
        try {
            searchResult = getSearchResult(sourceInput.getText(), destinationInput.getText());
        } catch (SQLException e) {
            e.printStackTrace();
            searchResult = new ArrayList<>();
        }

        if (searchResult.size() != 0)
            gbc.weighty = 0;

        selectionPanel.add(separator, gbc);

        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton reserveButton;
        int i = 1;
        for (String[] row: searchResult) {
            if (i == searchResult.size())
                gbc.weighty = 1;
            gbc.gridx = 0;
            for (int j=0; j < row.length - 1; j++) {
                label = new JLabel(row[j]);
                label.setFont(new Font("Serif", Font.BOLD, 12));
                selectionPanel.add(label, gbc);
                gbc.gridx += 1;
            }
            reserveButton = new JButton("Reserve");
            reserveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog diag = new ReservationDialog(parent, card, row[0], row[5]);    // row[0] busid  and row[5] routeid
                    diag.setVisible(true);
                }
            });
            selectionPanel.add(reserveButton, gbc);
            gbc.gridy += 1;
            i++;
        }

        selectionPanel.revalidate();
        selectionPanel.repaint();

    }

    private ArrayList<String[]> getSearchResult(String source, String destination) throws SQLException {
        String bySource = String.format("SELECT ba.busid, r.source, r.destination, r.id, r.departuretime " +
                        "FROM BusAssignment AS ba " +
                        "JOIN ScheduledRoute AS r ON r.id = ba.routeid AND r.source LIKE '%%%s%%' " +
                        "JOIN Bus ON ba.busid = bus.id;"
                , source);

        String byDestination = String.format("SELECT ba.busid, r.source, r.destination, r.id, r.departuretime " +
                        "FROM BusAssignment AS ba " +
                        "JOIN ScheduledRoute AS r ON r.id = ba.routeid AND r.destination LIKE '%%%s%%' " +
                        "JOIN Bus ON ba.busid = bus.id;"
                , destination);

        String bySourceDestination = String.format("SELECT ba.busid, r.source, r.destination, r.id, r.departuretime " +
                        "FROM BusAssignment AS ba " +
                        "JOIN ScheduledRoute AS r ON r.id = ba.routeid AND r.source LIKE '%%%s%%' " +
                        "AND r.destination LIKE '%%%s%%' " +
                        "JOIN Bus ON ba.busid = bus.id;"
                , source, destination);


        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        if (!(source.isBlank() || destination.isBlank())) {
            rs = stmt.executeQuery(bySourceDestination);
//            System.out.println(bySourceDestination);
        }
        else if (source.isBlank() && !destination.isBlank()) {
//            System.out.println(byDestination);
            rs = stmt.executeQuery(byDestination);
        }
        else {
//            System.out.println(bySource);
            rs = stmt.executeQuery(bySource);
        }

        ArrayList<String[]> results = new ArrayList<>();
        String[] row;

        Bus b;
        while (rs.next()) {
            row = new String[6];
            row[0] = rs.getString(1);                                                   // bus id
            row[1] = rs.getString(2);                                                   // source
            row[2] = rs.getString(3);                                                   // destination
            b = Bus.getBus(row[0]);
            row[3] = String.format("%d", b.getAvailableSeats(rs.getString(4)).size()); // available seats
            row[4] = rs.getString(5);
            row[5] = rs.getString(4);                                                  // route id
            results.add(row);
        }
        stmt.close();

        return results;
    }


    private class TicketContainer extends JPanel{
        JLabel bus;
        JLabel src;
        JLabel dest;
        JLabel depTime;
        JLabel seatNumber;

        JButton cancelBtn;

        String ticketId;

        TicketContainer (Ticket ticket) throws SQLException {
            this(ticket.getId(),
                    ticket.getBusId(),
                    Route.getRoute(ticket.getRouteId()).getSource(),
                    Route.getRoute(ticket.getRouteId()).getDestination(),
                    Route.getRoute(ticket.getRouteId()).getDepartureTime().toString(),
                    String.format("%d", ticket.getSeatNumber()));
        }

        TicketContainer (String id, String bus, String src, String dest, String depTime, String seatNumber) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(3, 3, 3, 3);

            setBorder(BorderFactory.createTitledBorder("ID : " + id));

            ticketId = id;
            this.bus = new JLabel("Bus: " + bus);
            this.src = new JLabel("From: " + src);
            this.dest = new JLabel("To: " + dest);
            this.depTime = new JLabel("Departure Time: " + depTime);
            this.seatNumber = new JLabel("Seat: " + seatNumber);
            cancelBtn = new JButton("Cancel");

            this.bus.setHorizontalAlignment(JLabel.LEFT);
            this.src.setHorizontalAlignment(JLabel.LEFT);
            this.dest.setHorizontalAlignment(JLabel.LEFT);
            this.depTime.setHorizontalAlignment(JLabel.LEFT);
            this.seatNumber.setHorizontalAlignment(JLabel.LEFT);

            setLayout(new GridBagLayout());

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;

            add(this.bus, gbc);

            gbc.gridy++;
            add(this.src, gbc);

            gbc.gridy++;
            add(this.dest, gbc);

            gbc.gridy++;
            add(this.depTime, gbc);

            gbc.gridy++;
            add(this.seatNumber, gbc);

            gbc.gridx++;
            gbc.gridy++;
            add(this.cancelBtn, gbc);

            cancelBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    try {
                        Ticket.getTicket(ticketId).cancel();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    ticketPanel.remove(TicketContainer.this);
                    ticketPanel.revalidate();
                    ticketPanel.repaint();
                    CardLayout c = (CardLayout) (card.getLayout());
                    c.show(card, LoginView.NAME);
                    c.show(card, CustomerDashboard.NAME);
                }
            });

        }
    }
}


class ProfileDialog extends JDialog {

    private JLabel username;
    private JLabel firstName;
    private JLabel middleName;
    private JLabel lastName;
    private JLabel phoneNumber;
    private JLabel town;
    private JLabel city;
    private JLabel country;
    private JLabel zipcode;

    private JTextField usernameField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField middleNameField;
    private JTextField phoneNumberField;
    private JTextField townField;
    private JTextField cityField;
    private JTextField countryField;
    private JTextField zipcodeField;

    private JButton updateBtn;
    private JButton deleteAccountBtn;

    private GridBagConstraints gbc;

    public ProfileDialog (JFrame parent, JPanel card) {
        super(parent, "Profile");

//        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//            SwingUtilities.updateComponentTreeUI(this);
//        } catch (Exception e) {
////            e.printStackTrace();
//        }

        setModal(true);

        BoxLayout blayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        setLayout(blayout);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JPanel personalInfo = new JPanel(new GridBagLayout());
        personalInfo.setBorder(BorderFactory.createTitledBorder("Personal Information"));

        JPanel address = new JPanel(new GridBagLayout());
        address.setBorder(BorderFactory.createTitledBorder("Address"));

        add(personalInfo);
        add(address);

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weighty = gbc.weightx = 1;
        gbc.gridx = gbc.gridy = 0;

        username = new JLabel("Username");
        firstName = new JLabel("First name");
        middleName = new JLabel("Middle name");
        lastName = new JLabel("Last name");
        phoneNumber = new JLabel("Phone number");

        town = new JLabel("Town");
        city = new JLabel("City");
        country = new JLabel("Country");
        zipcode = new JLabel("Zip Code");

        usernameField = new JTextField(20);
        usernameField.setEnabled(false);
        firstNameField = new JTextField(20);
        middleNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        phoneNumberField = new JTextField(20);

        townField = new JTextField(20);
        cityField = new JTextField(20);
        countryField = new JTextField(20);
        zipcodeField = new JTextField(20);

        updateBtn = new JButton("Update");
        deleteAccountBtn = new JButton("Delete Account");

        gbc.gridy++;
        personalInfo.add(username, gbc);
        gbc.gridy++;
        personalInfo.add(firstName, gbc);
        gbc.gridy++;
        personalInfo.add(middleName, gbc);
        gbc.gridy++;
        personalInfo.add(lastName, gbc);
        gbc.gridy++;
        personalInfo.add(phoneNumber, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridy++;
        personalInfo.add(usernameField, gbc);

        gbc.gridy++;
        personalInfo.add(firstNameField, gbc);
        gbc.gridy++;
        personalInfo.add(middleNameField, gbc);
        gbc.gridy++;
        personalInfo.add(lastNameField, gbc);
        gbc.gridy++;
        personalInfo.add(phoneNumberField, gbc);

        gbc.gridx = gbc.gridy = 0;
        address.add(town, gbc);
        gbc.gridy++;
        address.add(city, gbc);
        gbc.gridy++;
        address.add(country, gbc);
        gbc.gridy++;
        address.add(zipcode, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        address.add(townField, gbc);
        gbc.gridy++;
        address.add(cityField, gbc);
        gbc.gridy++;
        address.add(countryField, gbc);
        gbc.gridy++;
        address.add(zipcodeField, gbc);

        add(updateBtn);
        add(deleteAccountBtn);

        Customer customer = (Customer) AuthenticationManager.getLoggedInUser();

        usernameField.setText(customer.getId());
        firstNameField.setText(customer.getFirstname());
        middleNameField.setText(customer.getMiddlename());
        lastNameField.setText(customer.getLastname());
        phoneNumberField.setText(customer.getPhoneNumber());
        townField.setText(customer.getAddress().getTown());
        cityField.setText(customer.getAddress().getCity());
        countryField.setText(customer.getAddress().getCountry());
        zipcodeField.setText(customer.getAddress().getZipcode());


        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (firstNameField.getText().isBlank() || middleNameField.getText().isBlank() ||
                        lastNameField.getText().isBlank() || townField.getText().isBlank() ||  cityField.getText().isBlank() ||
                        countryField.getText().isBlank() || zipcodeField.getText().isBlank() ||
                        phoneNumberField.getText().isBlank()) {

                    JOptionPane.showMessageDialog(ProfileDialog.this, "All fields are required!",
                            "Alert", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    customer.updateInfo(firstNameField.getText(), middleNameField.getText(), lastNameField.getText(),
                            new Address(townField.getText(), cityField.getText(), countryField.getText(), zipcodeField.getText()),
                            phoneNumberField.getText()
                    );
                    CardLayout c = (CardLayout) card.getLayout();
                    c.show(card, LoginView.NAME);
                    ProfileDialog.this.dispose();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(ProfileDialog.this, "Unable to edit profile!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        deleteAccountBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(ProfileDialog.this,
                            "Do you want to delete your account? All Your Reservations Will Be Cancelled.",
                            "Warning!", JOptionPane.YES_NO_OPTION)) {
                        customer.delete();
                        CardLayout c = (CardLayout) card.getLayout();
                        c.show(card, LoginView.NAME);
                        ProfileDialog.this.dispose();
                    }

                } catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(ProfileDialog.this, "Unable to delete account!");

                }
            }
        });

        pack();

    }
}


class ReservationDialog extends JDialog {

    private GridBagConstraints gbc = new GridBagConstraints();
    private JComboBox<Integer> selection;

    public ReservationDialog (JFrame parent, JPanel card, String busId, String routeId) {
        super(parent, "Make Reservation");

//        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//            SwingUtilities.updateComponentTreeUI(this);
//        } catch (Exception e) {
////            e.printStackTrace();
//        }

        setModal(true);

        setLayout(new GridBagLayout());

        setSize(480, 240);

        gbc.insets = new Insets(5,5,5,5);

        JLabel title = new JLabel("Please choose a seat");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weighty = 1;
        // gbc.gridwidth = 3;
        add(title, gbc);

        Integer[] list;

        try {
            List<Integer> availableSeats = Bus.getBus(busId).getAvailableSeats(routeId);
            list = new Integer[availableSeats.size()];
            list = availableSeats.toArray(list);

            selection = new JComboBox<Integer>(list);
            gbc.gridx = 1;
            gbc.weightx = 1;
            add(selection, gbc);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton reserve = new JButton("Reserve");

        reserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Customer customer = (Customer) AuthenticationManager.getLoggedInUser();
                int seatNumber = (int) selection.getSelectedItem();

                try{
                    customer.reserveSeat(busId, routeId, seatNumber);
                }
                catch (ReservationSystemException e) {
                    JOptionPane.showMessageDialog(parent, e.getMessage(), "Alert", JOptionPane.ERROR_MESSAGE);
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }

                CardLayout c = (CardLayout) (card.getLayout());
                c.show(card, LoginView.NAME);
                c.show(card, CustomerDashboard.NAME);

            }
        });

        gbc.gridy++;
        add(reserve, gbc);
    }
}
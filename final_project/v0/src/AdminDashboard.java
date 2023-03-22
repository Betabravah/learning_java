import BRS.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class AdminDashboard extends JPanel {
    public static String NAME = "ADMIN_DASHBOARD";

    private JPanel card;

    public AdminDashboard (JPanel card, JFrame frame) {
        this.card = card;

        setLayout(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // The top
        JLabel title = new JLabel("Bus Reservation System");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 20));

        add(title, BorderLayout.NORTH);;


        // Side bar
        JPanel sideBar = new JPanel(new GridLayout(7, 1));

        add(sideBar, BorderLayout.WEST);

        JButton profileBtn = new JButton("My Profile");
        JButton dashboardBtn = new JButton("Dashboard");
        JButton scheduleBtn = new JButton("Schedules");
        JButton driverBtn = new JButton("Drivers");
        JButton busBtn = new JButton("Buses");
        JButton routeBtn = new JButton("Routes");
        JButton logoutBtn = new JButton("Log Out");

        sideBar.add(profileBtn);
        sideBar.add(dashboardBtn);
        sideBar.add(scheduleBtn);
        sideBar.add(driverBtn);
        sideBar.add(busBtn);
        sideBar.add(routeBtn);
        sideBar.add(logoutBtn);

        // Content
        JPanel content = new JPanel(new CardLayout());
        add(content, BorderLayout.CENTER);
        // content.setBackground(new Color(0, 130, 200));

        content.add(new ProfilePanel(card), ProfilePanel.NAME);
        content.add(new Dashboard(), Dashboard.NAME);
        content.add(new SchedulePanel(frame), SchedulePanel.NAME);
        content.add(new DriverPanel(frame), DriverPanel.NAME);
        content.add(new BusPanel(frame), BusPanel.NAME);
        content.add(new RoutePanel(frame), RoutePanel.NAME);

        CardLayout layout = (CardLayout) content.getLayout();
        layout.show(content, Dashboard.NAME);

        /****************************** Buttons Functionality ***********************************/
        profileBtn.addActionListener(e -> layout.show(content, ProfilePanel.NAME));
        dashboardBtn.addActionListener(e -> layout.show(content, Dashboard.NAME));
        scheduleBtn.addActionListener(e -> layout.show(content, SchedulePanel.NAME));
        driverBtn.addActionListener(e -> layout.show(content, DriverPanel.NAME));
        busBtn.addActionListener(e -> layout.show(content, BusPanel.NAME));
        routeBtn.addActionListener(e -> layout.show(content, RoutePanel.NAME));

        logoutBtn.addActionListener(new ActionListener() {
            CardLayout c;

            @Override
            public void actionPerformed(ActionEvent e) {
                c = (CardLayout) card.getLayout();
                c.show(card, LoginView.NAME);
            }
        });
        /*************************************************************************/
    }
}


class ProfilePanel extends JPanel {
    public static final String NAME = "PROFILE";

    private JLabel username;
    private JLabel password;
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

    private JButton editBtn;
    private JButton saveBtn;

    public ProfilePanel(JPanel card) {
        // BoxLayout blayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        // setLayout(blayout);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JPanel personalInfo = new JPanel(new GridBagLayout());
        personalInfo.setBorder(BorderFactory.createTitledBorder("Personal Information"));

        JPanel address = new JPanel(new GridBagLayout());
        address.setBorder(BorderFactory.createTitledBorder("Address"));

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1;
        gbc.gridx = gbc.gridy = 0;
        add(personalInfo, gbc);

        gbc.gridx = 1;
        add(address, gbc);

        gbc.gridx = gbc.gridy = 0;
        username = new JLabel("Username");
        password = new JLabel("Password");
        firstName = new JLabel("First name");
        middleName = new JLabel("Middle name");
        lastName = new JLabel("Last name");
        phoneNumber = new JLabel("Phone number");

        town = new JLabel("Town");
        city = new JLabel("City");
        country = new JLabel("Country");
        zipcode = new JLabel("Zip Code");

        usernameField = new JTextField(20);
        firstNameField = new JTextField(20);
        middleNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        phoneNumberField = new JTextField(20);

        townField = new JTextField(20);
        cityField = new JTextField(20);
        countryField = new JTextField(20);
        zipcodeField = new JTextField(20);

        editBtn = new JButton("Edit");
        saveBtn = new JButton("Save");

        usernameField.setEnabled(false);
        zipcodeField.setEnabled(false);
        firstNameField.setEnabled(false);
        middleNameField.setEnabled(false);
        lastNameField.setEnabled(false);
        phoneNumberField.setEnabled(false);
        townField.setEnabled(false);
        cityField.setEnabled(false);
        countryField.setEnabled(false);


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

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(editBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 1;
        add(saveBtn, gbc);

        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administrator admin = (Administrator) AuthenticationManager.getLoggedInUser();

                usernameField.setText(admin.getId());
                firstNameField.setText(admin.getFirstname());
                middleNameField.setText(admin.getMiddlename());
                lastNameField.setText(admin.getLastname());
                phoneNumberField.setText(admin.getPhoneNumber());
                townField.setText(admin.getAddress().getTown());
                cityField.setText(admin.getAddress().getCity());
                countryField.setText(admin.getAddress().getCountry());
                zipcodeField.setText(admin.getAddress().getZipcode());

                firstNameField.setEnabled(true);
                middleNameField.setEnabled(true);
                lastNameField.setEnabled(true);
                phoneNumberField.setEnabled(true);
                townField.setEnabled(true);
                cityField.setEnabled(true);
                countryField.setEnabled(true);
                zipcodeField.setEnabled(true);

            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administrator admin = (Administrator) AuthenticationManager.getLoggedInUser();

                try {
                    admin.updateInfo(firstNameField.getText(),
                            middleNameField.getText(),
                            lastNameField.getText(),
                            new Address(townField.getText(), cityField.getText(), countryField.getText(),
                                    zipcodeField.getText()), phoneNumberField.getText());
                } catch (SQLException sqlException) {
//                    sqlException.printStackTrace();
                }

                firstNameField.setEnabled(false);
                middleNameField.setEnabled(false);
                lastNameField.setEnabled(false);
                phoneNumberField.setEnabled(false);
                townField.setEnabled(false);
                cityField.setEnabled(false);
                countryField.setEnabled(false);
                zipcodeField.setEnabled(false);

                CardLayout c = (CardLayout) card.getLayout();
                c.show(card, LoginView.NAME);
            }
        });
    }
}


class Dashboard extends JPanel {
    public static final String NAME = "DASHBOARD";

    public Dashboard() {
        setLayout(new GridBagLayout());        

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 5, 5, 5);


        JPanel counterPanel = new JPanel(new GridBagLayout());

        counterPanel.setBorder(BorderFactory.createTitledBorder("Summary"));
        ((TitledBorder) counterPanel.getBorder()).setTitleFont(new Font("Tahoma", Font.PLAIN, 14));


        gbc.gridx = gbc.gridy = 0;
        add(counterPanel, gbc);

        gbc.gridy = 1;

        JLabel buses = new JLabel("Buses: 0");
        JLabel routes = new JLabel("Routes: 0");
        JLabel customers = new JLabel("Customers: 0");
        JLabel drivers = new JLabel("Drivers: 0");

        JButton refresh = new JButton("Refresh");

        gbc.gridx = gbc.gridy = 0;
        counterPanel.add(refresh, gbc);

        gbc.gridy++;
        counterPanel.add(buses, gbc);

        gbc.gridy++;
        counterPanel.add(routes, gbc);

        gbc.gridy++;
        counterPanel.add(customers, gbc);

        gbc.gridy++;
        counterPanel.add(drivers, gbc);

        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(1200, 1));

        JLabel trendingRoutesTitle = new JLabel("Trending Routes");

        gbc.gridy++;
        counterPanel.add(separator, gbc);

        gbc.gridy++;
        counterPanel.add(trendingRoutesTitle, gbc);

        try {
            buses.setText("Buses : " + Bus.getTotalNumber());
            drivers.setText("Drivers : " + Driver.getTotalNumber());
            routes.setText("Routes : " + Route.getTotalNumber());
            customers.setText("Customers : " + Customer.getTotalNumber());

            JLabel trending;
            for (String s: trendingRoutes()) {
                trending = new JLabel(s);

                gbc.gridy++;
                counterPanel.add(trending, gbc);
            }
        } catch (SQLException sqlException) {

        }

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counterPanel.removeAll();

                gbc.gridx = gbc.gridy = 0;
                counterPanel.add(refresh, gbc);

                gbc.gridy++;
                counterPanel.add(buses, gbc);

                gbc.gridy++;
                counterPanel.add(routes, gbc);

                gbc.gridy++;
                counterPanel.add(customers, gbc);

                gbc.gridy++;
                counterPanel.add(drivers, gbc);

                JSeparator separator = new JSeparator();
                separator.setOrientation(SwingConstants.HORIZONTAL);
                separator.setPreferredSize(new Dimension(1200, 1));

                JLabel trendingRoutesTitle = new JLabel("Trending Routes");

                gbc.gridy++;
                counterPanel.add(separator, gbc);

                gbc.gridy++;
                counterPanel.add(trendingRoutesTitle, gbc);

                try {
                    buses.setText("Buses : " + Bus.getTotalNumber());
                    drivers.setText("Drivers : " + Driver.getTotalNumber());
                    routes.setText("Routes : " + Route.getTotalNumber());
                    customers.setText("Customers : " + Customer.getTotalNumber());
                } catch (SQLException sqlException) {
//                    sqlException.printStackTrace();
                }

                try {
                    JLabel trending;
                    for (String s: trendingRoutes()) {
                        trending = new JLabel(s);

                        gbc.gridy++;
                        counterPanel.add(trending, gbc);
                    }

                } catch (SQLException exception) {
//                    exception.printStackTrace();
                }

                counterPanel.revalidate();
                counterPanel.repaint();

            }
        });

    }

    public String[] trendingRoutes() throws SQLException{
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select routeid, count(routeid) from reservation group by routeid;");

        int count = 0;
        String[] output = new String[3];
        while (count<3 && rs.next()) {
            output[count] = String.format("\t'%s' with '%s' sold tickets", rs.getString(1), rs.getString(2));
            count++;
        }
        stmt.close();
        return output;

    }
}


class SchedulePanel extends JPanel {
    public static final String NAME = "SCHEDULES";

    JTextField searchField;

    public SchedulePanel(JFrame frame) {
        setLayout(new GridBagLayout());
        
        setBorder(BorderFactory.createTitledBorder("Schedule"));    
        ((TitledBorder) getBorder()).setTitleFont(new Font("Tahoma", Font.PLAIN, 14));
        // ((TitledBorder) getBorder()).setTitleColor(Color.BLUE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTHWEST;        

        // Search
        JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT));        
        JLabel filter = new JLabel("Filter by");
        String[] list = {"Bus Id", "Route Id", "Driver Id"};
        JComboBox filterType = new JComboBox(list);
        searchField = new JTextField(25);
        JButton addSchedule = new JButton("Add New Schedule");

        gbc.gridx = gbc.gridy = 0;                
        gbc.weightx = gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 5;
        add(search, gbc);  
        search.add(filter);
        search.add(filterType);
        search.add(searchField);
        search.add(addSchedule);

        addSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScheduleDiag scheduleDiag = new ScheduleDiag(frame);
                scheduleDiag.setVisible(true);
            }
        });

        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                removeAll();

                gbc.gridx = gbc.gridy = 0;
                gbc.weightx = gbc.weighty = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridwidth = 5;
                add(search, gbc);
                search.add(filter);
                search.add(filterType);
                search.add(searchField);
                search.add(addSchedule);

                // Time-Table
                String[] columnNames = {"Id", "From", "To", "Departure Time", "Arrival Time", "Bus ID", "Driver"};

                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.weightx = 1;
                gbc.gridwidth = 1;
                JLabel label;
                for (int i=0; i<columnNames.length; i++) {
                    label = new JLabel(columnNames[i]);
                    label.setFont(new Font("Serif", Font.BOLD, 12));
                    add(label, gbc);
                    gbc.gridx += 1;
                }

                JSeparator separator = new JSeparator();
                separator.setOrientation(SwingConstants.HORIZONTAL);
                separator.setPreferredSize(new Dimension(1200, 1));

                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = columnNames.length;
                gbc.weighty = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                ArrayList<String[]> searchResult = new ArrayList<>();
                try {
                    String filteredBy = (String) filterType.getSelectedItem();
                    if (filteredBy.equals(list[0]))
                        searchResult = getSearchResult(searchField.getText(),"", "");
                    else if(filteredBy.equals(list[1]))
                        searchResult = getSearchResult("", searchField.getText(),"");
                    else if(filteredBy.equals(list[2]))
                        searchResult = getSearchResult("","", searchField.getText());
                    else
                        searchResult = getSearchResult("", "", "");
                } catch (SQLException e) {
//                    e.printStackTrace();
                }

                if (searchResult.size() != 0)
                    gbc.weighty = 0;

                add(separator, gbc);

                gbc.gridy = 2;
                gbc.weightx = 1;
                gbc.weighty = 0;
                gbc.gridwidth = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                JButton deleteButton;
                int i = 1;
                for (String[] row: searchResult) {
                    if (i == searchResult.size())
                        gbc.weighty = 1;
                    gbc.gridx = 0;
                    for (int j=0; j < row.length; j++) {
                        label = new JLabel(row[j]);
                        label.setFont(new Font("Serif", Font.BOLD, 12));
                        add(label, gbc);
                        gbc.gridx += 1;
                    }

                    deleteButton = new JButton("Remove");
                    deleteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                Connection con = Database.getConnection();
                                Statement stmt = con.createStatement();
                                ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM Reservation WHERE " +
                                        "routeId = '%s' AND busId = '%s';", row[0], row[5]));

                                if (rs.next())
                                {
                                    rs.close();
                                    stmt.close();
                                    JOptionPane.showMessageDialog(frame, "Can't remove reserved schedule!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                else {
                                    stmt.executeUpdate(String.format("DELETE FROM BusAssignment WHERE routeId = '%s' " +
                                            "AND busId = '%s' " +
                                            "AND driverId = '%s';", row[0], row[5], row[6]));

                                    for(ActionListener a: searchField.getActionListeners()) {
                                        a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
                                        });
                                    }
                                }

                            } catch (SQLException sqlException) {
//                                sqlException.printStackTrace();
                            }
                        }
                    });
                    add(deleteButton, gbc);
                    gbc.gridy += 1;
                    i++;
                }

                revalidate();
                repaint();
            }
        });
    }

    private ArrayList<String[]> getSearchResult(String bus_id, String route_id, String driver_id) throws SQLException {
        String byBusId = String.format("SELECT r.id, r.source, r.destination, r.departureTime, r.arrivalTime, ba.busid, ba.driverid " +
                        "FROM Busassignment ba " +
                "JOIN Bus b on ba.busid = b.id AND b.id = '%s' " +
                "JOIN Scheduledroute r on r.id = ba.routeId " +
                "JOIN Driver d on d.id = ba.driverId;", bus_id);

        String byRouteId = String.format("SELECT r.id, r.source, r.destination, r.departureTime, r.arrivalTime, ba.busid, ba.driverid " +
                        "FROM Busassignment ba " +
                "JOIN Bus b on ba.busid = b.id " +
                "JOIN Scheduledroute r on r.id = ba.routeId AND r.id = '%s' " +
                "JOIN Driver d on d.id = ba.driverId;", route_id);

        String byDriverId = String.format("SELECT r.id, r.source, r.destination, r.departureTime, r.arrivalTime, ba.busid, ba.driverid " +
                        "FROM Busassignment ba " +
                "JOIN Bus b on ba.busid = b.id " +
                "JOIN Scheduledroute r on r.id = ba.routeId " +
                "JOIN Driver d on d.id = ba.driverId AND d.id = '%s';", driver_id);

        String all = "SELECT r.id, r.source, r.destination, r.departureTime, r.arrivalTime, ba.busid, ba.driverid " +
                        "FROM Busassignment ba " +
                        "JOIN Bus b on ba.busid = b.id " +
                        "JOIN Scheduledroute r on r.id = ba.routeId " +
                        "JOIN Driver d on d.id = ba.driverId;";

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        if (!bus_id.isBlank()) {
            rs = stmt.executeQuery(byBusId);
        }
        else if (!route_id.isBlank()) {
            rs = stmt.executeQuery(byRouteId);
        }
        else if (!driver_id.isBlank()){
            rs = stmt.executeQuery(byDriverId);
        }
        else {
            rs = stmt.executeQuery(all);
        }

        ArrayList<String[]> results = new ArrayList<>();
        String[] row;

        while (rs.next()) {
            row = new String[7];
            row[0] = rs.getString(1);                                                   // route id
            row[1] = rs.getString(2);                                                   // from
            row[2] = rs.getString(3);                                                   // to
            row[3] = rs.getString(4);                                                   // dep time
            row[4] = rs.getString(5);                                                  // arrival time
            row[5] = rs.getString(6);                                                  // bus id
            row[6] = rs.getString(7);                                                  // driver id
            results.add(row);
        }
        stmt.close();

        return results;
    }
}


class DriverPanel extends JPanel {
    public static final String NAME = "DRIVERS";

    JTextField searchField;

    public DriverPanel(JFrame frame) {
        setLayout(new GridBagLayout());
        
        setBorder(BorderFactory.createTitledBorder("Drivers"));    
        ((TitledBorder) getBorder()).setTitleFont(new Font("Tahoma", Font.PLAIN, 14));
        // ((TitledBorder) getBorder()).setTitleColor(Color.BLUE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTHWEST;        

        // Search
        JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT));        
        JLabel filter = new JLabel("Filter by");
        String[] list = {"Driver Name", "Driver Id", "Address"};
        JComboBox filterType = new JComboBox(list);
        searchField = new JTextField(25);
        JButton addDriver = new JButton("Add New Driver");

        gbc.gridx = gbc.gridy = 0;                
        gbc.weightx = gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 5;
        add(search, gbc);  
        search.add(filter);
        search.add(filterType);
        search.add(searchField);
        search.add(addDriver);

        addDriver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DriverDiag driverDiag = new DriverDiag(frame);
                driverDiag.setVisible(true);
            }
        });

        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                removeAll();

                gbc.gridx = gbc.gridy = 0;
                gbc.weightx = gbc.weighty = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridwidth = 5;
                add(search, gbc);
                search.add(filter);
                search.add(filterType);
                search.add(searchField);
                search.add(addDriver);

                // Driver Table
                String[] columnNames = {"Id", "First Name", "Middle Name", "Last Name", "Address", "Phone Number"};

                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.weightx = 1;
                gbc.gridwidth = 1;
                JLabel label;
                for (int i=0; i<columnNames.length; i++) {
                    label = new JLabel(columnNames[i]);
                    label.setFont(new Font("Serif", Font.BOLD, 12));
                    add(label, gbc);
                    gbc.gridx += 1;
                }

                JSeparator separator = new JSeparator();
                separator.setOrientation(SwingConstants.HORIZONTAL);
                separator.setPreferredSize(new Dimension(1200, 1));

                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = columnNames.length;
                gbc.weighty = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                ArrayList<String[]> searchResult = new ArrayList<>();
                try {
                    String filteredBy = (String) filterType.getSelectedItem();
                    if (filteredBy.equals(list[0]))
                        searchResult = getSearchResult("", searchField.getText(),"");
                    else if(filteredBy.equals(list[1]))
                        searchResult = getSearchResult(searchField.getText(), "","");
                    else if(filteredBy.equals(list[2]))
                        searchResult = getSearchResult("","", searchField.getText());
                    else
                        searchResult = getSearchResult("", "", "");
                } catch (SQLException e) {
//                    e.printStackTrace();
                }

                if (searchResult.size() != 0)
                    gbc.weighty = 0;

                add(separator, gbc);

                gbc.gridy = 2;
                gbc.weightx = 1;
                gbc.weighty = 0;
                gbc.gridwidth = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                JButton deleteButton;
                int i = 1;
                for (String[] row: searchResult) {
                    if (i == searchResult.size())
                        gbc.weighty = 1;
                    gbc.gridx = 0;
                    for (int j=0; j < row.length; j++) {
                        label = new JLabel(row[j]);
                        label.setFont(new Font("Serif", Font.BOLD, 12));
                        add(label, gbc);
                        gbc.gridx += 1;
                    }

                    deleteButton = new JButton("Remove");
                    deleteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                Driver.getDriver(row[0]).remove();
                                for(ActionListener a: searchField.getActionListeners()) {
                                    a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
                                    });
                                }
                            } catch (SQLException sqlException) {
//                                sqlException.printStackTrace();
                            } catch (ReservationSystemException exception) {
                                JOptionPane.showMessageDialog(frame, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                    add(deleteButton, gbc);
                    gbc.gridy += 1;
                    i++;
                }

                revalidate();
                repaint();;
            }
        });
    }

    private ArrayList<String[]> getSearchResult(String id, String name, String address) throws SQLException {
        String byName = String.format("Select * from Driver WHERE firstname LIKE '%%%s%%' " +
                "or middlename LIKE '%%%s%%'" +
                "or lastname LIKE '%%%s%%'; ", name, name, name);
        String byAddress = String.format("Select * from Driver WHERE address LIKE '%%%s%%'; ", address);
        String byId = String.format("Select * from Driver WHERE id = '%s'; ", id);


        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        if (!name.isBlank()) {
            rs = stmt.executeQuery(byName);
        }
        else if (!address.isBlank()) {
            rs = stmt.executeQuery(byAddress);
        }
        else if (!id.isBlank()){
            rs = stmt.executeQuery(byId);
        }
        else {
            rs = stmt.executeQuery("Select * from Driver");
        }

        ArrayList<String[]> results = new ArrayList<>();
        String[] row;

        while (rs.next()) {
            row = new String[6];
            row[0] = rs.getString(1);                                                   // driver id
            row[1] = rs.getString(2);                                                   // first name
            row[2] = rs.getString(3);                                                   // middle name
            row[3] = rs.getString(4);                                                   // last name
            row[4] = rs.getString(5);                                                  // address
            row[5] = rs.getString(6);                                                  // phone number
            results.add(row);
        }
        stmt.close();

        return results;
    }
}


class BusPanel extends JPanel {
    public static final String NAME = "BUSES";

    JTextField searchField;

    public BusPanel(JFrame frame) {
        // BoxLayout blayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        // setLayout(blayout);
        setLayout(new GridBagLayout());

        setBorder(BorderFactory.createTitledBorder("Buses"));
        ((TitledBorder) getBorder()).setTitleFont(new Font("Tahoma", Font.PLAIN, 14));
        // ((TitledBorder) getBorder()).setTitleColor(Color.BLUE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTHWEST;        

        // Search
        JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT));        
        JLabel filter = new JLabel("Filter by");
        String[] list = {"Bus Id", "Capacity"};
        JComboBox filterType = new JComboBox(list);
        searchField = new JTextField(25);
        JButton addBus = new JButton("Add New Bus");

        gbc.gridx = gbc.gridy = 0;                
        gbc.weightx = gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 5;
        add(search, gbc);  
        search.add(filter);
        search.add(filterType);
        search.add(searchField);
        search.add(addBus);

        addBus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BusDiag busDiag = new BusDiag(frame);
                busDiag.setVisible(true);
            }
        });

        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                removeAll();

                gbc.gridx = gbc.gridy = 0;
                gbc.weightx = gbc.weighty = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridwidth = 5;
                add(search, gbc);
                search.add(filter);
                search.add(filterType);
                search.add(searchField);
                search.add(addBus);

                // Bus Table
                String[] columnNames = {"Bus Id", "Capacity"};

                gbc.anchor = GridBagConstraints.NORTHWEST;
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                JLabel label = new JLabel();
                for (int i=0; i<columnNames.length; i++) {
                    label = new JLabel(columnNames[i]);
                    label.setFont(new Font("Serif", Font.BOLD, 12));
                    add(label, gbc);
                    gbc.gridx += 1;
                }

                JSeparator separator = new JSeparator();
                separator.setOrientation(SwingConstants.HORIZONTAL);
                separator.setPreferredSize(new Dimension(1200, 1));

                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.weighty = 1;
                gbc.gridwidth = columnNames.length;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                ArrayList<String[]> searchResult = new ArrayList<>();
                try {
                    String filteredBy = (String) filterType.getSelectedItem();
                    if (filteredBy.equals(list[0]))
                        searchResult = getSearchResult(searchField.getText(),"");
                    else if(filteredBy.equals(list[1]))
                        searchResult = getSearchResult("", searchField.getText());
                } catch (SQLException e) {
//                    e.printStackTrace();
                }

                if (searchResult.size() != 0)
                    gbc.weighty = 0;

                add(separator, gbc);

                gbc.gridy = 2;
                gbc.weightx = 1;
                gbc.weighty = 0;
                gbc.gridwidth = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                JButton deleteButton;
                int i = 1;
                for (String[] row: searchResult) {
                    if (i == searchResult.size())
                        gbc.weighty = 1;
                    gbc.gridx = 0;
                    for (int j=0; j < row.length; j++) {
                        label = new JLabel(row[j]);
                        label.setFont(new Font("Serif", Font.BOLD, 12));
                        add(label, gbc);
                        gbc.gridx += 1;
                    }
                    deleteButton = new JButton("Remove");
                    deleteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                Bus.getBus(row[0]).remove();
                                for(ActionListener a: searchField.getActionListeners()) {
                                    a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
                                    });
                                }
                            } catch (SQLException sqlException) {
//                                sqlException.printStackTrace();
                            } catch (ReservationSystemException exception) {
                                JOptionPane.showMessageDialog(frame, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                    add(deleteButton, gbc);
                    gbc.gridy += 1;
                    i++;
                }

                revalidate();
                repaint();

            }
        });
    }

    private ArrayList<String[]> getSearchResult(String id, String capacity) throws SQLException {
        String byCapacity = String.format("Select * from Bus WHERE capacity = %s; ", capacity);
        String byId = String.format("Select * from Bus WHERE id = '%s'; ", id);


        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        if (!id.isBlank()) {
            rs = stmt.executeQuery(byId);
        }
        else if (!capacity.isBlank()) {
            rs = stmt.executeQuery(byCapacity);
        }
        else {
            rs = stmt.executeQuery("Select * from Bus");
        }

        ArrayList<String[]> results = new ArrayList<>();
        String[] row;

        while (rs.next()) {
            row = new String[2];
            row[0] = rs.getString(1);                                                   // bus id
            row[1] = rs.getString(2);                                                   // capacity
            results.add(row);
        }
        stmt.close();

        return results;
    }
}


class RoutePanel extends JPanel {
    public static final String NAME = "ROUTES";

    JTextField searchField;
    JLabel label;

    public RoutePanel(JFrame frame) {
        setLayout(new GridBagLayout());

        setBorder(BorderFactory.createTitledBorder("Routes"));
        ((TitledBorder) getBorder()).setTitleFont(new Font("Tahoma", Font.PLAIN, 14));
        // ((TitledBorder) getBorder()).setTitleColor(Color.BLUE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTHWEST;        

        // Search
        JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT));        
        JLabel filter = new JLabel("Filter by");
        String[] list = {"Route Id", "Source", "Destination"};
        JComboBox filterType = new JComboBox(list);
        searchField = new JTextField(30);

        JButton addRoute = new JButton("Add New Route");

        gbc.gridx = gbc.gridy = 0;                
        gbc.weightx = gbc.weighty = 0;        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 5;
        add(search, gbc);  
        search.add(filter);
        search.add(filterType);
        search.add(searchField);
        search.add(addRoute);

        addRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RouteDiag diag = new RouteDiag(frame);
                diag.setVisible(true);
            }
        });

        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                removeAll();

                gbc.gridx = gbc.gridy = 0;
                gbc.weightx = gbc.weighty = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridwidth = 5;
                add(search, gbc);
                search.add(filter);
                search.add(filterType);
                search.add(searchField);
                search.add(addRoute);

                // Route Table
                String[] columnNames = {"Route Id", "From", "To", "Departure Time", "Arrival Time"};

                gbc.anchor = GridBagConstraints.NORTHWEST;
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                for (int i=0; i<columnNames.length; i++) {
                    label = new JLabel(columnNames[i]);
                    label.setFont(new Font("Serif", Font.BOLD, 12));
                    add(label, gbc);
                    gbc.gridx += 1;
                }

                JSeparator separator = new JSeparator();
                separator.setOrientation(SwingConstants.HORIZONTAL);
                separator.setPreferredSize(new Dimension(1200, 1));

                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.weighty = 1;
                gbc.gridwidth = columnNames.length;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                ArrayList<String[]> searchResult = new ArrayList<>();
                try {
                    String filteredBy = (String) filterType.getSelectedItem();
                    if (filteredBy.equals(list[0]))
                        searchResult = getSearchResult("", "", searchField.getText());
                    else if(filteredBy.equals(list[1]))
                        searchResult = getSearchResult(searchField.getText(),"", "");
                    else if(filteredBy.equals(list[2]))
                        searchResult = getSearchResult("", searchField.getText(), "");
                } catch (SQLException e) {
//                    e.printStackTrace();
                }

                if (searchResult.size() != 0)
                    gbc.weighty = 0;

                add(separator, gbc);

                gbc.gridy = 2;
                gbc.weightx = 1;
                gbc.weighty = 0;
                gbc.gridwidth = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                JButton deleteButton;
                int i = 1;
                for (String[] row: searchResult) {
                    if (i == searchResult.size())
                        gbc.weighty = 1;
                    gbc.gridx = 0;
                    for (int j=0; j < row.length ; j++) {
                        label = new JLabel(row[j]);
                        label.setFont(new Font("Serif", Font.BOLD, 12));
                        add(label, gbc);
                        gbc.gridx += 1;
                    }

                    deleteButton = new JButton("Remove");
                    deleteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                Route.getRoute(row[0]).remove();
                                for(ActionListener a: searchField.getActionListeners()) {
                                    a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
                                    });
                                }
                            } catch (SQLException sqlException) {
//                                sqlException.printStackTrace();
                            } catch (ReservationSystemException exception) {
                                JOptionPane.showMessageDialog(frame, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                    add(deleteButton, gbc);
                    gbc.gridy += 1;
                    i++;
                }

                revalidate();
                repaint();
            }
        });

    }

    private ArrayList<String[]> getSearchResult(String source, String destination, String id) throws SQLException {
        String bySource = String.format("Select * from ScheduledRoute WHERE source LIKE '%%%s%%'; ", source);
        String byDestination = String.format("Select * from ScheduledRoute WHERE destination LIKE '%%%s%%'; ", destination);
        String byId = String.format("Select * from ScheduledRoute WHERE id = '%s'; ", id);


        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        if (!source.isBlank()) {
            rs = stmt.executeQuery(bySource);
        }
        else if (!destination.isBlank()) {
            rs = stmt.executeQuery(byDestination);
        }
        else if (!id.isBlank()){
            rs = stmt.executeQuery(byId);
        }
        else {
            rs = stmt.executeQuery("Select * from ScheduledRoute");
        }

        ArrayList<String[]> results = new ArrayList<>();
        String[] row;

        while (rs.next()) {
            row = new String[6];
            row[0] = rs.getString(1);                                                   // route id
            row[1] = rs.getString(2);                                                   // source
            row[2] = rs.getString(3);                                                   // destination
            row[3] = rs.getString(4);                                                   // departure time
            row[4] = rs.getString(5);                                                  // Arrival Time
            results.add(row);
        }
        stmt.close();

        return results;
    }
}

class RouteDiag extends JDialog {
    public RouteDiag(JFrame frame) {
        super(frame);
        setModal(true);
        setTitle("New Route");

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTHWEST;

        JTextField routeid = new JTextField(20);
        JTextField source = new JTextField(20);
        JTextField dest = new JTextField(20);
        JTextField depTime = new JTextField(20);
        JTextField arrTime = new JTextField(20);
        JButton submit = new JButton("Submit");

        gbc.gridx = gbc.gridy = 0;
        add(new JLabel("Route Id"), gbc);

        gbc.gridy++;
        add(new JLabel("Source"), gbc);

        gbc.gridy++;
        add(new JLabel("Destination"), gbc);

        gbc.gridy++;
        add(new JLabel("Departure Time[d-m-y h:m:s]"), gbc);

        gbc.gridy++;
        add(new JLabel("Arrival Time[d-m-y h:m:s]"), gbc);

        gbc.gridy++;

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(routeid, gbc);

        gbc.gridy++;
        add(source, gbc);

        gbc.gridy++;
        add(dest, gbc);

        gbc.gridy++;
        add(depTime, gbc);

        gbc.gridy++;
        add(arrTime, gbc);

        gbc.gridy++;
        add(submit, gbc);

        pack();

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (routeid.getText().isBlank() ||
                        source.getText().isBlank() ||
                        dest.getText().isBlank() ||
                        depTime.getText().isBlank() ||
                        arrTime.getText().isBlank()
                ) {
                    setTitle("All fields are required");
                    return;
                }

                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                try {
                    Route route = new Route(routeid.getText(), source.getText(),
                            dest.getText(), format.parse(depTime.getText()), format.parse(arrTime.getText()));
                    route.add();
                    dispose();
                } catch (ParseException parseException) {
                    depTime.setText("Invalid date format");
                    arrTime.setText("Invalid date format");
                } catch (SQLException sqlException) {
                    setTitle("Unable to add new route");
                }
            }
        });


    }
}

class BusDiag extends JDialog {
    public BusDiag(JFrame frame) {
        super(frame);
        setModal(true);
        setTitle("New Bus");

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTHWEST;

        JTextField busid = new JTextField(20);
        JTextField capacity = new JTextField(20);
        JButton submit = new JButton("Submit");

        gbc.gridx = gbc.gridy = 0;
        add(new JLabel("Bus Id"), gbc);

        gbc.gridy++;
        add(new JLabel("Capacity"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(busid, gbc);

        gbc.gridy++;
        add(capacity, gbc);

        gbc.gridy++;
        add(submit, gbc);

        pack();

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (busid.getText().isBlank() || capacity.getText().isBlank()
                ) {
                    setTitle("All fields are required");
                    return;
                }

                try {
                    Bus bus = new Bus(busid.getText(), Integer.parseInt(capacity.getText()));
                    bus.add();
                    dispose();
                } catch (NumberFormatException parseException) {
                    capacity.setToolTipText("Enter a number");
                } catch (SQLException sqlException) {
                    setTitle("Unable to add new bus");
                }
            }
        });
    }
}

class ScheduleDiag extends JDialog {
    public ScheduleDiag(JFrame frame) {
        super(frame);

        setTitle("New Schedule");

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTHWEST;

        JTextField busid = new JTextField(20);
        JTextField routeid = new JTextField(20);
        JTextField driverid = new JTextField(20);
        JButton submit = new JButton("Submit");

        gbc.gridx = gbc.gridy = 0;
        add(new JLabel("Bus Id"), gbc);

        gbc.gridy++;
        add(new JLabel("Route Id"), gbc);

        gbc.gridy++;
        add(new JLabel("Driver Id"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(busid, gbc);

        gbc.gridy++;
        add(routeid, gbc);

        gbc.gridy++;
        add(driverid, gbc);

        gbc.gridy++;
        add(submit, gbc);

        pack();

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (busid.getText().isBlank() || routeid.getText().isBlank() || driverid.getText().isBlank()
                ) {
                    setTitle("All fields are required");
                    return;
                }

                try {
                    Administrator admin = (Administrator) AuthenticationManager.getLoggedInUser();
                    admin.allocateDriverBusRoute(driverid.getText(), busid.getText(), routeid.getText());

                } catch(ReservationSystemException exception) {
                    JOptionPane.showMessageDialog(frame, exception.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException sqlException) {
//                    sqlException.printStackTrace();
                    setTitle(sqlException.getMessage());
                }
            }
        });
    }
}

class DriverDiag extends JDialog {
    public DriverDiag (JFrame frame) {
        super(frame);
        setModal(true);
        setTitle("New Driver");

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTHWEST;

        JTextField driverId = new JTextField(20);
        JTextField firstname = new JTextField(20);
        JTextField middlename = new JTextField(20);
        JTextField lastname = new JTextField(20);
        JTextField address = new JTextField(20);
        JTextField phonenumber = new JTextField(20);
        JButton submit = new JButton("Submit");

        gbc.gridx = gbc.gridy = 0;
        add(new JLabel("Driver Id"), gbc);

        gbc.gridy++;
        add(new JLabel("First name"), gbc);

        gbc.gridy++;
        add(new JLabel("Middle name"), gbc);

        gbc.gridy++;
        add(new JLabel("Last name"), gbc);

        gbc.gridy++;
        add(new JLabel("Address [town, city, country, zipcode]"), gbc);

        gbc.gridy++;
        add(new JLabel("Phone number"), gbc);

        gbc.gridy++;

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(driverId, gbc);

        gbc.gridy++;
        add(firstname, gbc);

        gbc.gridy++;
        add(middlename, gbc);

        gbc.gridy++;
        add(lastname, gbc);

        gbc.gridy++;
        add(address, gbc);

        gbc.gridy++;
        add(phonenumber, gbc);

        gbc.gridy++;
        add(submit, gbc);

        pack();

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (driverId.getText().isBlank() ||
                        firstname.getText().isBlank() ||
                        middlename.getText().isBlank() ||
                        lastname.getText().isBlank() ||
                        address.getText().isBlank() ||
                        phonenumber.getText().isBlank()
                ) {
                    setTitle("All fields are required");
                    return;
                }

                try {
                    Driver driver = new Driver(driverId.getText(), firstname.getText(),
                            middlename.getText(), lastname.getText(), new Address(address.getText()), phonenumber.getText());
                    driver.add();
                    dispose();
                } catch (Exception exception) {
                    setTitle("Unable to add new driver");
                }
            }
        });

    }
}

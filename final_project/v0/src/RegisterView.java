import BRS.Address;
import BRS.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegisterView extends JPanel {
    public static final String NAME = "REGISTER";

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
    private JPasswordField passwordField;

    private JTextField townField;
    private JTextField cityField;
    private JTextField countryField;
    private JTextField zipcodeField;

    private JButton registerBtn;
    private JButton goBackBtn;

    private GridBagConstraints gbc;

    private JPanel card;
    private JFrame frame;

    public RegisterView(JPanel card, JFrame frame) {
        this.card = card;
        this.frame = frame;

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JPanel personalInfo = new JPanel(new GridBagLayout());
        personalInfo.setBorder(BorderFactory.createTitledBorder("Personal Information"));

        JPanel address = new JPanel(new GridBagLayout());
        address.setBorder(BorderFactory.createTitledBorder("Address"));

        registerBtn = new JButton("Register");
        goBackBtn = new JButton("Back to Login");

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1;
        gbc.gridx = gbc.gridy = 0;
        add(personalInfo, gbc);

        gbc.gridx = 1;
        add(address, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(registerBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(goBackBtn, gbc);

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
        passwordField = new JPasswordField(20);
        firstNameField = new JTextField(20);
        middleNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        phoneNumberField = new JTextField(20);

        townField = new JTextField(20);
        cityField = new JTextField(20);
        countryField = new JTextField(20);
        zipcodeField = new JTextField(20);

        gbc.gridy++;
        personalInfo.add(username, gbc);
        gbc.gridy++;
        personalInfo.add(password, gbc);
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
        personalInfo.add(passwordField, gbc);
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

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (usernameField.getText().isBlank() || firstNameField.getText().isBlank() || middleNameField.getText().isBlank() ||
                        lastNameField.getText().isBlank() || townField.getText().isBlank() ||  cityField.getText().isBlank() ||
                        countryField.getText().isBlank() ||  zipcodeField.getText().isBlank() ||
                        phoneNumberField.getText().isBlank()) {

                    JOptionPane.showMessageDialog(frame, "All fields are required!",
                            "Alert", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Customer newCustomer = new Customer(usernameField.getText(), firstNameField.getText(), middleNameField.getText(),
                        lastNameField.getText(), new Address(townField.getText(), cityField.getText(), countryField.getText(), zipcodeField.getText()),
                        phoneNumberField.getText());

                try {
                    if (Customer.getCustomer(usernameField.getText()) != null) {
                        JOptionPane.showMessageDialog(frame, "User with the given username already exist!",
                                "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        newCustomer.add(String.copyValueOf(passwordField.getPassword()));
                        JOptionPane.showMessageDialog(frame, "Successfully Registered!");
                        CardLayout c = (CardLayout) card.getLayout();
                        c.show(card, LoginView.NAME);

                        usernameField.setText("");
                        passwordField.setText("");
                        firstNameField.setText("");
                        middleNameField.setText("");
                        lastNameField.setText("");
                        phoneNumberField.setText("");
                        townField.setText("");
                        cityField.setText("");
                        countryField.setText("");
                        zipcodeField.setText("");
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(frame, "Can't register user!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        goBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout c = (CardLayout) card.getLayout();
                c.show(card, LoginView.NAME);

                usernameField.setText("");
                passwordField.setText("");
                firstNameField.setText("");
                middleNameField.setText("");
                lastNameField.setText("");
                phoneNumberField.setText("");
                townField.setText("");
                cityField.setText("");
                countryField.setText("");
                zipcodeField.setText("");
            }
        });
    }
}

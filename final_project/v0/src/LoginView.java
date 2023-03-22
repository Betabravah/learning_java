import BRS.AuthenticationManager;
import BRS.User;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginView extends JPanel {
    public static final String NAME = "LOGIN";

    private JLabel username;
    private JLabel password;
    private JLabel register;
    private JLabel message;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn;
    private JButton registerBtn;
    private JSeparator separator;

    private GridBagConstraints gbc;

    private JPanel card;

    public LoginView (JPanel card, JFrame frame) {
        this.card = card;

        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        username = new JLabel("Username");
        password = new JLabel("Password");
        message = new JLabel();
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginBtn = new JButton("Login");
        registerBtn = new JButton("Register");
        register = new JLabel("Or");
        separator = new JSeparator();
        separator.setOrientation(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(3, 1));

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(username, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(password, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(usernameField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(loginBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(separator, gbc);

        gbc.fill = GridBagConstraints.NONE;
        add(register, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(registerBtn, gbc);

        loginBtn.addActionListener(new ActionListener() {
            CardLayout c;

            @Override
            public void actionPerformed(ActionEvent event) {

                try {
                    if (AuthenticationManager.authenticate(usernameField.getText(), String.copyValueOf(passwordField.getPassword()))) {

                        if (AuthenticationManager.getUserRole(usernameField.getText()).equals(User.CUSTOMER)) {
                            c = (CardLayout) (card.getLayout());
                            c.show(card, CustomerDashboard.NAME);
                            c.show(card, LoginView.NAME);
                            c.show(card, CustomerDashboard.NAME);
                        }
                        else {
                            c = (CardLayout) (card.getLayout());
                            c.show(card, AdminDashboard.NAME);
                        }
                    }
                    else {
                        usernameField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                        passwordField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout c = (CardLayout) (card.getLayout());
                c.show(card, RegisterView.NAME);
            }
        });

        this.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                passwordField.setText("");
                usernameField.setText("");
                usernameField.setBorder(null);
                passwordField.setBorder(null);
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
                ancestorAdded(event);
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }
        });
    }
}

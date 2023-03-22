import BRS.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException{

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.setTitle("Bus Reservation System");
        frame.setPreferredSize(new Dimension(820, 560));
        frame.setVisible(true);

        JPanel card = new JPanel(new CardLayout());
        card.add(new LoginView(card, frame), LoginView.NAME);
        card.add(new RegisterView(card, frame), RegisterView.NAME);
        card.add(new AdminDashboard(card, frame), AdminDashboard.NAME);
        card.add(new CustomerDashboard(card, frame), CustomerDashboard.NAME);

        frame.add(card);

        frame.pack();

        CardLayout c = (CardLayout) card.getLayout();

        c.show(card, LoginView.NAME);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                Database.closeConnection();
                frame.dispose();
                System.exit(0);
            }
        });
    }
}
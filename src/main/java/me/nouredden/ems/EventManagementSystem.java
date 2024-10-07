package me.nouredden.ems;

import com.formdev.flatlaf.FlatLightLaf;
import me.nouredden.ems.ui.CreateEventPanel;
import me.nouredden.ems.ui.ManageEventsPanel;

import javax.swing.*;
import java.awt.*;

public class EventManagementSystem {

    public static void main(String[] args) {
        FlatLightLaf.install();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Event Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane();

            ManageEventsPanel manageEventsPanel = new ManageEventsPanel();
            tabbedPane.addTab("Manage Events", manageEventsPanel);

            CreateEventPanel createEventPanel = new CreateEventPanel(manageEventsPanel);
            tabbedPane.addTab("Create Event", createEventPanel);

            frame.add(tabbedPane, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }

}
        /*MySQLContext mysql = new MySQLContext();
        mysql.connect();
        mysql.execute("CREATE TABLE IF NOT EXISTS Events(ID  varchar(100), Title varchar(100), CreatedAt DATE, EndsAt DATE, Location varchar(100))");
        mysql.disconnect();*/

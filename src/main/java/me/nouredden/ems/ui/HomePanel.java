package me.nouredden.ems.ui;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel
{
    public HomePanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Welcome to the Event Management System", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);
    }
}

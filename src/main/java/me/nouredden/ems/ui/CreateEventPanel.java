package me.nouredden.ems.ui;

import me.nouredden.ems.EventManagementSystem;
import me.nouredden.ems.entities.Event;
import me.nouredden.ems.utils.DateLabelFormatter;
import me.nouredden.ems.utils.SystemProvider;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.pushingpixels.trident.Timeline;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.Properties;

public class CreateEventPanel extends JPanel {
    private final JTextField txtTitle;
    private final JDatePickerImpl startDatePicker;
    private final JDatePickerImpl endDatePicker;
    private final JTextField txtLocation;

    public CreateEventPanel(ManageEventsPanel manageEventsPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("Title:");
        txtTitle = createStyledTextField();

        // Start Date Picker
        JLabel lblStartDate = new JLabel("Start Date:");
        SqlDateModel startDateModel = new SqlDateModel();
        Properties startDateProperties = new Properties();
        startDateProperties.put("text.today", "Today");
        startDateProperties.put("text.month", "Month");
        startDateProperties.put("text.year", "Year");
        JDatePanelImpl startDatePanel = new JDatePanelImpl(startDateModel, startDateProperties);
        startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());

        // End Date Picker
        JLabel lblEndDate = new JLabel("End Date:");
        SqlDateModel endDateModel = new SqlDateModel();
        Properties endDateProperties = new Properties();
        endDateProperties.put("text.today", "Today");
        endDateProperties.put("text.month", "Month");
        endDateProperties.put("text.year", "Year");
        JDatePanelImpl endDatePanel = new JDatePanelImpl(endDateModel, endDateProperties);
        endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());

        JLabel lblLocation = new JLabel("Location:");
        txtLocation = createStyledTextField();

        JButton btnCreateEvent = new JButton("Create Event");
        styleBootstrapButton(btnCreateEvent);
        btnCreateEvent.addActionListener(e -> {
            String title = txtTitle.getText();
            java.sql.Date startDateSql = (java.sql.Date) startDatePicker.getModel().getValue();
            java.sql.Date endDateSql = (java.sql.Date) endDatePicker.getModel().getValue();
            String location = txtLocation.getText();

            if (title.isEmpty() || startDateSql == null || endDateSql == null || location.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            LocalDate startDate = startDateSql.toLocalDate();
            LocalDate endDate = endDateSql.toLocalDate();

            Event event = new Event(title, location, startDate, endDate);
            SystemProvider.getEventManager().insert(event);
            JOptionPane.showMessageDialog(this, "Event Created Successfully!");

            clearFields();

            manageEventsPanel.refreshTable();
        });

        add(lblTitle);
        add(txtTitle);
        add(lblStartDate);
        add(startDatePicker);
        add(lblEndDate);
        add(endDatePicker);
        add(lblLocation);
        add(txtLocation);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(btnCreateEvent);
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                new EmptyBorder(5, 5, 5, 5)
        ));
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

    private void styleBootstrapButton(JButton button) {
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 123, 255)),
                new EmptyBorder(10, 20, 10, 20)
        ));
    }

    private void clearFields() {
        txtTitle.setText("");
        startDatePicker.getModel().setValue(null);
        endDatePicker.getModel().setValue(null);
        txtLocation.setText("");
    }
}

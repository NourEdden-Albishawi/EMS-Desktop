package me.nouredden.ems.ui;

import me.nouredden.ems.utils.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.Properties;

import me.nouredden.ems.entities.Event;
import org.jdatepicker.impl.SqlDateModel;

public class EditEventPanel extends JPanel
{
    private final JTextField txtTitle;
    private final JDatePickerImpl startDatePicker;
    private final JDatePickerImpl endDatePicker;
    private final JTextField txtLocation;
    private final Event event;

    public EditEventPanel(Event event) {
        this.event = event;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("Title:");
        txtTitle = new JTextField(event.getTitle());

        // Start Date Picker
        JLabel lblStartDate = new JLabel("Start Date:");
        startDatePicker = createDatePicker(event.getStartDate());

        // End Date Picker
        JLabel lblEndDate = new JLabel("End Date:");
        endDatePicker = createDatePicker(event.getEndDate());

        JLabel lblLocation = new JLabel("Location:");
        txtLocation = new JTextField(event.getLocation());

        JButton btnSaveChanges = new JButton("Save Changes");
        styleBootstrapButton(btnSaveChanges);
        btnSaveChanges.addActionListener(e -> {
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

            event.setTitle(title);
            event.setStartDate(startDate);
            event.setEndDate(endDate);
            event.setLocation(location);

            JOptionPane.showMessageDialog(this, "Changes saved successfully!");

            // Optionally, you can refresh the manage events panel or update the table here
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
        add(btnSaveChanges);
    }

    private JDatePickerImpl createDatePicker(LocalDate date) {
        SqlDateModel dateModel = new SqlDateModel();
        dateModel.setValue(new java.sql.Date(date.toEpochDay()));
        Properties dateProperties = new Properties();
        dateProperties.put("text.today", "Today");
        dateProperties.put("text.month", "Month");
        dateProperties.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, dateProperties);
        return new JDatePickerImpl(datePanel, new DateLabelFormatter());
    }

    private void styleBootstrapButton(JButton button) {
        button.setBackground(new Color(23, 162, 184)); // Bootstrap info color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(23, 162, 184)),
                new EmptyBorder(10, 20, 10, 20)
        ));
    }
}

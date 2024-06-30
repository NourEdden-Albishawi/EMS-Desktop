package me.nouredden.ems.ui;

import me.nouredden.ems.entities.Event;
import me.nouredden.ems.utils.SystemProvider;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.UUID;

public class ManageEventsPanel extends JPanel {
    private final JTable eventTable;
    private final String[] columnNames = {"Unique ID", "Title", "Creation Date", "Location", "Start Date", "End Date"};

    public ManageEventsPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Manage Events", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.NORTH);

        eventTable = new JTable(new DefaultTableModel(columnNames, 0)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable editing cells
            }
        };
        eventTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(eventTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnEditEvent = new JButton("Edit Event");
        styleBootstrapButton(btnEditEvent);
        btnEditEvent.addActionListener(e -> {
            int selectedRow = eventTable.getSelectedRow();
            if (selectedRow != -1) {
                UUID uniqueId = (UUID) eventTable.getValueAt(selectedRow, 0);
                Event event = SystemProvider.getEventManager().get(uniqueId);
                if (event != null) {
                    openEditEventPanel(event);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select an event to edit.");
            }
        });

        JButton btnDeleteEvent = new JButton("Delete Event");
        styleBootstrapButton(btnDeleteEvent);
        btnDeleteEvent.addActionListener(e -> {
            int selectedRow = eventTable.getSelectedRow();
            if (selectedRow != -1) {
                UUID uniqueId = (UUID) eventTable.getValueAt(selectedRow, 0);
                Event event = SystemProvider.getEventManager().get(uniqueId);
                if (event != null) {
                    int option = JOptionPane.showConfirmDialog(this,
                            "Are you sure you want to delete this event?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        SystemProvider.getEventManager().delete(event.getUniqueId());
                        refreshTable();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select an event to delete.");
            }
        });

        buttonPanel.add(btnEditEvent);
        buttonPanel.add(btnDeleteEvent);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshTable();
    }

    public void refreshTable() {
        List<Event> events = SystemProvider.getEventManager().getAll();
        DefaultTableModel model = (DefaultTableModel) eventTable.getModel();
        model.setRowCount(0); // Clear existing rows

        for (Event event : events) {
            Object[] rowData = {
                    event.getUniqueId(),
                    event.getTitle(),
                    event.getCreationDate(),
                    event.getLocation(),
                    event.getStartDate(),
                    event.getEndDate()

            };
            model.addRow(rowData);
        }
    }

    private void openEditEventPanel(Event event) {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        JDialog dialog = new JDialog(parentFrame, "Edit Event", true);
        dialog.setLayout(new BorderLayout());

        EditEventPanel editEventPanel = new EditEventPanel(event);
        dialog.add(editEventPanel, BorderLayout.CENTER);

        JButton btnCancel = new JButton("Cancel");
        styleBootstrapButton(btnCancel);
        btnCancel.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnCancel);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);

        // Optionally, you can refresh the manage events panel or update the table after editing
        refreshTable();
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


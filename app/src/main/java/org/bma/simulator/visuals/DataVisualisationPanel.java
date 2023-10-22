package org.bma.simulator.visuals;

import javax.swing.*;
import java.awt.*;

public class DataVisualisationPanel extends JFrame {

    public DataVisualisationPanel(Object[][] data, String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);

        // Create a table without a header row
        JTable table = new JTable(data, new String[]{"", ""});
        table.setTableHeader(null); // Hide the table header

        // Create a panel to center the table with a margin
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add margin

        JScrollPane scrollPane = new JScrollPane(table);
        centerPanel.add(scrollPane);

        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}

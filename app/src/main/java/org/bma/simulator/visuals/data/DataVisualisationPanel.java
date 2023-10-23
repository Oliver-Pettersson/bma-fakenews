package org.bma.simulator.visuals.data;

import javax.swing.*;
import java.awt.*;

public abstract class DataVisualisationPanel extends JFrame {
    protected JPanel panel;

    protected DataVisualisationPanel(Object[][] data, String title) {
        setTitle(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 600);

        // Create a table without a header row
        JTable table = new JTable(data, new String[]{"", ""});
        table.setTableHeader(null); // Hide the table header

        // Create a panel to center the table with a margin
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.gridheight = 3;
        c.gridx = 0;
        c.gridy = 0;
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add margin
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, c);

        add(panel, BorderLayout.CENTER);

    }
}

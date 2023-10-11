package org.bma.simulator.visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel {
    private JFrame frame;
    private JTextField nodesTextField, celebritiesTextField, refreshRateTextField;

    public ControlPanel() {
        frame = new JFrame("Control Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Apply basic styling to improve the appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Amount of Nodes:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        nodesTextField = new JTextField(10);
        panel.add(nodesTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Amount of Celebrities:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        celebritiesTextField = new JTextField(10);
        panel.add(celebritiesTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Refresh Rate (ms):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        refreshRateTextField = new JTextField(10);
        panel.add(refreshRateTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add code to handle the "Generate" button click here
            }
        });
        panel.add(generateButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add code to handle the "Reset" button click here
            }
        });
        panel.add(resetButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JButton injectButton = new JButton(" ");
        injectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add code to handle the "Inject" button click here
            }
        });
        panel.add(injectButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }


}

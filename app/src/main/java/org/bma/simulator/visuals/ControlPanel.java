package org.bma.simulator.visuals;

import org.bma.simulator.datamodel.ResultData;
import org.bma.simulator.utils.FakeNewsUtils;

import javax.swing.*;
import java.awt.*;
import org.bma.simulator.utils.GraphGenerator;
import org.bma.simulator.visuals.data.ResultDataVisualisationPanel;
import org.bma.simulator.visuals.profile.UserProfileControlPanel;

public class ControlPanel extends JFrame{
    private final JTextField nodesTextField, celebritiesTextField, refreshRateTextField, injectionSourceTextField, minFollowsTextField, maxFollowsTextField;

    public ControlPanel() {
        setTitle("Control Panel");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(750, 300);

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
        panel.add(new JLabel("Min amount of Follows:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        minFollowsTextField = new JTextField(10);
        minFollowsTextField.setText("1");
        panel.add(minFollowsTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Max amount of Follows:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        maxFollowsTextField = new JTextField(10);
        maxFollowsTextField.setText("3");
        panel.add(maxFollowsTextField, gbc);


        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(new JLabel("Refresh Rate (ms):"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        refreshRateTextField = new JTextField(10);
        panel.add(refreshRateTextField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(new JLabel("Injection Source:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        injectionSourceTextField = new JTextField(10);
        panel.add(injectionSourceTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(getGenerateButton(), gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        panel.add(getInjectionButton(), gbc);

        gbc.gridx = 4;
        gbc.gridy = 4;
        panel.add(getEditProfileButton(), gbc);

        // default Values
        nodesTextField.setText("100");
        celebritiesTextField.setText("1");
        refreshRateTextField.setText("1000");
        injectionSourceTextField.setText("0");

        add(panel);
        generateGraph();
        setVisible(true);
    }

    private JButton getInjectionButton() {
        JButton injectButton = new JButton("Inject Fake News");
        injectButton.addActionListener(e -> new Thread(() -> {
            FakeNewsUtils.injectFakeNews(
                    VisualisationGraph.getGraph().getNode(injectionSourceTextField.getText()),
                    Long.parseLong(refreshRateTextField.getText()));
            new ResultDataVisualisationPanel(new ResultData().getData(), "Result Panel");
        }).start());
        return injectButton;
    }

    private JButton getGenerateButton() {
        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(e -> {
            generateGraph();
        });
        return generateButton;
    }

    private JButton getEditProfileButton() {
        JButton editProfileButton = new JButton("Edit Profile");
        editProfileButton.addActionListener(e -> new UserProfileControlPanel());
        return editProfileButton;
    }

    private void generateGraph() {
        GraphGenerator.setMinFollowerAmount(Integer.parseInt(minFollowsTextField.getText()));
        GraphGenerator.setMaxFollowerAmount(Integer.parseInt(maxFollowsTextField.getText()));
        VisualisationGraph.generateNewGraph(Integer.parseInt(nodesTextField.getText()), Integer.parseInt(celebritiesTextField.getText()));
    }

}

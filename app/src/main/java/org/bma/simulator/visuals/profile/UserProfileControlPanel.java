package org.bma.simulator.visuals.profile;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.bma.simulator.datamodel.userprofile.*;
import org.bma.simulator.datamodel.userprofile.attributetype.AgeGroup;
import org.bma.simulator.datamodel.userprofile.attributetype.PoliticalType;
import org.bma.simulator.datamodel.userprofile.utils.UserProfileUtils;

public class UserProfileControlPanel extends JFrame {
    private JComboBox<String> profileDropdown;
    private JLabel selectedLabel;
    private JTextField botCredibilityTextField;
    private JLabel botCredibilityLabel;
    private JTextField occurrenceTextField;
    private JLabel occurrenceLabel;
    private ProfileOccurrence selectedProfile;
    private PoliticalType selectedPoliticalType;
    private AgeGroup selectedAgeGroup;
    private JPanel panel;
    private JButton createButton;
    private JButton saveButton;
    private JComboBox<String> politicalTypeDropdown;
    private JLabel politicalTypeLabel;
    private JComboBox<String> ageGroupDropdown;
    private JLabel ageGroupLabel;

    public UserProfileControlPanel() {
        // Set the title for the frame
        super("User Profile Control Panel");
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;

        // Set the default close operation
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Set the size of the frame
        setSize(400, 300);

        // Create a panel for components
        panel = new JPanel(new GridBagLayout());
        add(panel, BorderLayout.CENTER);
        loadCreateButton(c);
        loadProfileDropdown(c);
        loadOccurrenceComponents(c);

        loadBotComponents(c);
        loadHumanUserComponents(c);
        loadSaveButton(c);

        loadComponentsBasedOnProfile();

        setVisible(true);
    }

    private void showBotComponents() {
        botCredibilityLabel.setVisible(true);
        botCredibilityTextField.setVisible(true);
    }

    private void showHumanUserComponents() {
        politicalTypeLabel.setVisible(true);
        politicalTypeDropdown.setVisible(true);
        ageGroupLabel.setVisible(true);
        ageGroupDropdown.setVisible(true);
    }

    private void wipeInputFields() {
        botCredibilityLabel.setVisible(false);
        botCredibilityTextField.setVisible(false);
        politicalTypeLabel.setVisible(false);
        politicalTypeDropdown.setVisible(false);
        ageGroupLabel.setVisible(false);
        ageGroupDropdown.setVisible(false);
    }

    private void loadOccurrenceComponents(GridBagConstraints c) {
        occurrenceLabel = new JLabel("Amount of occurrences:");
        occurrenceTextField = new JTextField(20);

        occurrenceTextField.setText("0");

        c.gridx = 0;
        c.gridy = 4;
        panel.add(occurrenceLabel, c);
        c.gridx = 1;
        panel.add(occurrenceTextField, c);
    }

    private void loadProfileDropdown(GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 1;
        // Create a label to display the selected option
        selectedLabel = new JLabel("Selected Profile: ");
        panel.add(selectedLabel, c);

        // Create a JComboBox (dropdown)
        c.gridx = 1;
        String[] options = UserProfileUtils.getProfiles().keySet().toArray(String[]::new);
        selectedProfile = UserProfileUtils.getProfiles().get(options[0]);
        profileDropdown = new JComboBox<>(options);
        panel.add(profileDropdown, c);

        // Add an ActionListener to the dropdown
        profileDropdown.addActionListener(e -> {
            // Get the selected option and perform your action here
            String selectedOption = (String) profileDropdown.getSelectedItem();
            selectedProfile = UserProfileUtils.getProfiles().get(selectedOption);
            loadComponentsBasedOnProfile();
        });
    }

    private void loadComponentsBasedOnProfile() {
        if (selectedProfile != null) {
            wipeInputFields();
            occurrenceTextField.setText(Integer.toString(selectedProfile.getOccurrence()));
            if (selectedProfile.getProfile() instanceof BotUserProfile) {
                botCredibilityTextField.setText(Double.toString(selectedProfile.getProfile().getCredibility()));
                showBotComponents();
            }
            if (selectedProfile.getProfile() instanceof HumanUserProfile) {
                politicalTypeDropdown.setSelectedItem(((HumanUserProfile) selectedProfile.getProfile()).getPoliticalType().name());
                ageGroupDropdown.setSelectedItem(((HumanUserProfile) selectedProfile.getProfile()).getAgeGroup().name());
                showHumanUserComponents();
            }
            updateGUI();
        }
    }

    private void loadBotComponents(GridBagConstraints c) {
        botCredibilityLabel = new JLabel("Credibility:");
        botCredibilityTextField = new JTextField(20);

        botCredibilityTextField.setText("0.0");

        botCredibilityLabel.setVisible(false);
        botCredibilityTextField.setVisible(false);

        c.gridx = 0;
        c.gridy = 2;
        panel.add(botCredibilityLabel, c);
        c.gridx = 1;
        panel.add(botCredibilityTextField, c);
    }

    private void loadHumanUserComponents(GridBagConstraints c) {
        ageGroupLabel = new JLabel("Age Group:");
        ageGroupDropdown = new JComboBox<>(Arrays.stream(AgeGroup.values()).map(Enum::name).toArray(String[]::new));
        c.gridx = 0;
        c.gridy = 2;
        panel.add(ageGroupLabel, c);
        c.gridx = 1;
        panel.add(ageGroupDropdown, c);

        politicalTypeLabel = new JLabel("Political Type:");
        politicalTypeDropdown = new JComboBox<>(Arrays.stream(PoliticalType.values()).map(Enum::name).toArray(String[]::new));
        c.gridx = 0;
        c.gridy = 3;
        panel.add(politicalTypeLabel, c);
        c.gridx = 1;
        panel.add(politicalTypeDropdown, c);

        politicalTypeDropdown.addActionListener(e -> {
            String selectedOption = (String) politicalTypeDropdown.getSelectedItem();
            selectedPoliticalType = PoliticalType.valueOf(selectedOption);
        });

        ageGroupDropdown.addActionListener(e -> {
            String selectedOption = (String) ageGroupDropdown.getSelectedItem();
            selectedAgeGroup = AgeGroup.valueOf(selectedOption);
        });

        ageGroupLabel.setVisible(false);
        ageGroupDropdown.setVisible(false);
        politicalTypeLabel.setVisible(false);
        politicalTypeDropdown.setVisible(false);
    }

    private void loadSaveButton(GridBagConstraints c) {
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 5;
        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            selectedProfile.setOccurrence(Integer.parseInt(occurrenceTextField.getText()));
            if (selectedProfile.getProfile() instanceof HumanUserProfile) {
                ((HumanUserProfile) selectedProfile.getProfile()).setPoliticalType(selectedPoliticalType);
                ((HumanUserProfile) selectedProfile.getProfile()).setAgeGroup(selectedAgeGroup);
            }
            if (selectedProfile.getProfile() instanceof BotUserProfile) {
                selectedProfile.getProfile().setCredibility(Double.parseDouble(botCredibilityTextField.getText()));
            }
            JOptionPane.showMessageDialog(this, "Save was successful", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(saveButton, c);
        c.gridwidth = 1;
    }

    private void loadCreateButton(GridBagConstraints c) {
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        createButton = new JButton("Create New Profile");
        createButton.addActionListener(e -> {
            new CreateUserProfilePanel(this);
        });
        panel.add(createButton, c);
        c.gridwidth = 1;
    }

    public void updateGUI() {
        revalidate();
        repaint();
    }

    public JComboBox<String> getProfileDropdown() {
        return profileDropdown;
    }
}

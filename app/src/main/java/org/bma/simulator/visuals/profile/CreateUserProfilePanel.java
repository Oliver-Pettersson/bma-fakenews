package org.bma.simulator.visuals.profile;

import org.bma.simulator.datamodel.userprofile.*;
import org.bma.simulator.datamodel.userprofile.attributetype.AgeGroup;
import org.bma.simulator.datamodel.userprofile.attributetype.PoliticalType;
import org.bma.simulator.datamodel.userprofile.utils.UserProfileUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class CreateUserProfilePanel extends JFrame {
    private final JPanel panel;
    private JLabel profileTitleLabel;
    private JTextField profileTitleTextField;
    private JLabel occurrenceLabel;
    private JTextField occurrenceTextField;
    private JComboBox<String> politicalTypeDropdown;
    private JLabel politicalTypeLabel;
    private JComboBox<String> ageGroupDropdown;
    private JLabel ageGroupLabel;
    private PoliticalType selectedPoliticalType;
    private AgeGroup selectedAgeGroup;
    private JCheckBox isCelebrityProfileCheckbox;
    private JLabel isCelebrityProfileLabel;
    private JButton saveButton;
    private UserProfileControlPanel controlPanel;


    public CreateUserProfilePanel(UserProfileControlPanel controlPanel) {
        // Set the title for the frame
        super("Create User Profile");
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.controlPanel = controlPanel;

        // Set the default close operation
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Set the size of the frame
        setSize(400, 300);

        // Create a panel for components
        panel = new JPanel(new GridBagLayout());
        add(panel, BorderLayout.CENTER);

        loadProfileTitleComponents(c);
        loadCelebrityCheckComponents(c);
        loadHumanUserComponents(c);
        loadOccurrenceComponents(c);

        loadSaveButton(c);

        setVisible(true);
    }

    private void loadSaveButton(GridBagConstraints c) {
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 5;
        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            UserProfile profile;
            if (isCelebrityProfileCheckbox.isSelected()) {
                profile = new CelebrityUserProfile(profileTitleTextField.getText(), selectedPoliticalType, selectedAgeGroup);
            } else {
                profile = new HumanUserProfile(profileTitleTextField.getText(), selectedPoliticalType, selectedAgeGroup);
            }
            UserProfileUtils.getProfiles().put(profile.getType(), new ProfileOccurrence(profile, Integer.parseInt(occurrenceTextField.getText())));
            controlPanel.getProfileDropdown().addItem(profile.getType());
            controlPanel.updateGUI();
            JOptionPane.showMessageDialog(this, "Creation was successful", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(saveButton, c);
        c.gridwidth = 1;
    }

    private void loadProfileTitleComponents(GridBagConstraints c) {
        profileTitleLabel = new JLabel("Profile Title:");
        profileTitleTextField = new JTextField(20);

        c.gridx = 0;
        c.gridy = 0;
        panel.add(profileTitleLabel, c);
        c.gridx = 1;
        panel.add(profileTitleTextField, c);
    }

    private void  loadCelebrityCheckComponents(GridBagConstraints c) {
        isCelebrityProfileLabel = new JLabel("Is Celebrity Profile:");
        isCelebrityProfileCheckbox = new JCheckBox();

        c.gridx = 0;
        c.gridy = 1;
        panel.add(isCelebrityProfileLabel, c);
        c.gridx = 1;
        panel.add(isCelebrityProfileCheckbox, c);
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

    }
}

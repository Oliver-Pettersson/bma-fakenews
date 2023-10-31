package org.bma.simulator.visuals;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import org.bma.simulator.datamodel.userprofile.UserProfile;
import org.bma.simulator.datamodel.userprofile.UserProfileConstants;

public class UserProfileControlPanel extends JFrame {
  private JComboBox<String> profileDropdown;
  private JLabel selectedLabel;
  private JTextField botCredibilityTextField;
  private JLabel botCredibilityLabel;
  private UserProfile selectedProfile;
  private JPanel panel;
  private JButton createButton;
  private JButton saveButton;

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

    loadBotComponents(c);


    c.gridx = 0;
    c.gridy = 3;
    // enter here
    c.gridx = 1;
    // enter here

    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(e -> {
      String credibility = botCredibilityTextField.getText();

      JOptionPane.showMessageDialog(this, "Save was successful", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    });
    panel.add(saveButton, c);

    setVisible(true);
  }

  private void loadProfileDropdown(GridBagConstraints c) {
    c.gridx = 0;
    c.gridy = 1;
    // Create a label to display the selected option
    selectedLabel = new JLabel("Selected Profile: ");
    panel.add(selectedLabel, c);

    // Create a JComboBox (dropdown)
    c.gridx = 1;
    String[] options = {"", UserProfileConstants.AVERAGE, "Option 2", "Option 3"};
    profileDropdown = new JComboBox<>(options);
    panel.add(profileDropdown, c);

    // Add an ActionListener to the dropdown
    profileDropdown.addActionListener(e -> {
      // Get the selected option and perform your action here
      profileDropdown.removeItem("");
      String selectedOption = (String) profileDropdown.getSelectedItem();
      selectedProfile = UserProfileConstants.getProfileFromString(selectedOption);
      if (selectedProfile != null) {
        botCredibilityTextField.setText(Double.toString(selectedProfile.getCredibility()));
      }
    });
  }

  private void loadBotComponents(GridBagConstraints c) {
    botCredibilityLabel = new JLabel("Credibility:");
    botCredibilityTextField = new JTextField(20);

    botCredibilityTextField.setText("0.0");

    c.gridx = 0;
    c.gridy = 4;
    panel.add(botCredibilityLabel, c);
    c.gridx = 1;
    panel.add(botCredibilityTextField, c);
  }

  private void loadSaveButton(GridBagConstraints c) {
    c.gridwidth = 2;
    c.gridx = 0;
    c.gridy = 5;
    saveButton = new JButton("Save");
    saveButton.addActionListener(e -> {
      String credibility = botCredibilityTextField.getText();

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
      String credibility = botCredibilityTextField.getText();

      JOptionPane.showMessageDialog(this, "Save was successful", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    });
    panel.add(createButton, c);
    c.gridwidth = 1;
  }

  private void showBotComponents(boolean show) {
    botCredibilityLabel.setVisible(show);
    botCredibilityTextField.setVisible(show);
  }

  private void updateGUI() {
    revalidate();
    repaint();
  }
}

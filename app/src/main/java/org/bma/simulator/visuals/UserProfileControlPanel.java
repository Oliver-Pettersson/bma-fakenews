package org.bma.simulator.visuals;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import org.bma.simulator.datamodel.userprofile.AverageUserProfile;
import org.bma.simulator.datamodel.userprofile.UserProfile;
import org.bma.simulator.datamodel.userprofile.UserProfileConstants;

public class UserProfileControlPanel extends JFrame {
  private final JComboBox<String> dropdown;
  private final JLabel selectedLabel;
  private final JTextField scepticismTextField;
  private final JTextField credibilityTextField;
  private UserProfile selectedProfile;

  public UserProfileControlPanel() {
    // Set the title for the frame
    super("User Profile Control Panel");

    JLabel scepticismLabel = new JLabel("Scepticism:");
    JLabel credibilityLabel = new JLabel("Credibility:");
    scepticismTextField = new JTextField(20);
    credibilityTextField = new JTextField(20);

    scepticismTextField.setText("0.0");
    credibilityTextField.setText("0.0");

    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(10, 0, 0, 0);
    c.fill = GridBagConstraints.HORIZONTAL;

    // Set the default close operation
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // Set the size of the frame
    setSize(400, 300);

    // Create a panel for components
    JPanel panel = new JPanel(new GridBagLayout());
    add(panel, BorderLayout.CENTER);

    // Create a label to display the selected option
    selectedLabel = new JLabel("Selected Profile: ");
    panel.add(selectedLabel);

    // Create a JComboBox (dropdown)
    String[] options = {"", UserProfileConstants.AVERAGE, "Option 2", "Option 3"};
    dropdown = new JComboBox<>(options);
    panel.add(dropdown);

    // Add an ActionListener to the dropdown
    dropdown.addActionListener(e -> {
      // Get the selected option and perform your action here
      dropdown.removeItem("");
      String selectedOption = (String) dropdown.getSelectedItem();
      selectedProfile = UserProfileConstants.getProfileFromString(selectedOption);
      if (selectedProfile != null) {
        credibilityTextField.setText(Double.toString(selectedProfile.getCredibility()));
        scepticismTextField.setText(Double.toString(selectedProfile.getScepticism()));
      }
    });

    c.gridx = 0;
    c.gridy = 3;
    panel.add(scepticismLabel, c);
    c.gridx = 1;
    panel.add(scepticismTextField, c);
    c.gridx = 0;
    c.gridy = 4;
    panel.add(credibilityLabel, c);
    c.gridx = 1;
    panel.add(credibilityTextField, c);

    c.gridwidth = 2;
    c.gridx = 0;
    c.gridy = 5;

    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(e -> {
      String scepticism = scepticismTextField.getText();
      String credibility = credibilityTextField.getText();
      if (selectedProfile instanceof AverageUserProfile) {
        AverageUserProfile.setAverageUserScepticism(Double.parseDouble(scepticism));
        AverageUserProfile.setAverageUserCredibility(Double.parseDouble(credibility));
      }
      JOptionPane.showMessageDialog(this, "Save was successful", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    });
    panel.add(saveButton, c);

    setVisible(true);
  }
}

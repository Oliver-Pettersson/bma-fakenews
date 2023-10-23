package org.bma.simulator.visuals;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NodeDataVisualisationPanel extends DataVisualisationPanel {
  private JTextField scepticismTextField;
  private JTextField credibilityTextField;

  public NodeDataVisualisationPanel(Object[][] data, String title) {
    super(data, title);

    JLabel scepticismLabel = new JLabel("Scepticism:");
    JLabel credibilityLabel = new JLabel("Credibility:");
    scepticismTextField = new JTextField(20);
    credibilityTextField = new JTextField(20);

    GridBagConstraints c = new GridBagConstraints();
    c.insets= new Insets(10,0,0,0);

    c.fill = GridBagConstraints.CENTER;
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

    JButton submitButton = new JButton("Submit");
    submitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String scepticism = scepticismTextField.getText();
        String credibility = credibilityTextField.getText();
        // Do something with the entered data, e.g., validate or process it.
      }
    });
    panel.add(submitButton, c);

    setTitle(title + ": " + data[0][1]);
    setVisible(true);
  }
}

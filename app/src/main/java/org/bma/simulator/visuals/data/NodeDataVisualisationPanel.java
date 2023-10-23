package org.bma.simulator.visuals.data;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NodeDataVisualisationPanel extends DataVisualisationPanel {

  public NodeDataVisualisationPanel(Object[][] data, String title) {
    super(data, title);

    setTitle(title + ": " + data[0][1]);
    setVisible(true);
  }
}

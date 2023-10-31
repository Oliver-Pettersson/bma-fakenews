package org.bma.simulator.visuals.data;


public class NodeDataVisualisationPanel extends DataVisualisationPanel {

  public NodeDataVisualisationPanel(Object[][] data, String title) {
    super(data, title);

    setTitle(title + ": " + data[0][1]);
    setVisible(true);
  }
}

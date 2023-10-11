package org.bma.simulator;

import org.bma.simulator.utils.FakeNewsUtils;
import org.bma.simulator.utils.GraphGenerator;
import org.bma.simulator.visuals.ControlPanel;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class Main {
    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "swing");

        Graph graph = GraphGenerator.createRandomGraph(1000);

        ControlPanel controlPanel = new ControlPanel();
    }
}
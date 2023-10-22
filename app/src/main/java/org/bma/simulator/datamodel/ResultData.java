package org.bma.simulator.datamodel;

import org.bma.simulator.visuals.VisualisationGraph;
import org.graphstream.graph.Node;

import java.util.List;
import java.util.stream.Stream;

public class ResultData {
    private int totalNodes;
    private int infectedNodes;
    private int healthyNodes;

    public ResultData() {
        List<Node> nodes = VisualisationGraph.getGraph().nodes().toList();
        this.totalNodes = nodes.size();
        this.infectedNodes = (int) nodes.stream().filter(node -> node.hasAttribute("ui.class") && node.getAttribute("ui.class").equals("infected")).count();
        this.healthyNodes = this.totalNodes - this.infectedNodes;
    }

    public Object[][] getData() {
        return new Object[][]{
                {"total_user_amount", this.totalNodes},
                {"infected_user_amount", this.infectedNodes},
                {"healthy_user_amount", this.healthyNodes}
        };
    }

    public void setTotalNodes(int totalNodes) {
        this.totalNodes = totalNodes;
    }

    public void setInfectedNodes(int infectedNodes) {
        this.infectedNodes = infectedNodes;
    }

    public void setHealthyNodes(int healthyNodes) {
        this.healthyNodes = healthyNodes;
    }
}

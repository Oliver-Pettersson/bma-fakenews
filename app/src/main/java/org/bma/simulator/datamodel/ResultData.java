package org.bma.simulator.datamodel;

import org.bma.simulator.visuals.VisualisationGraph;
import org.graphstream.graph.Node;

import java.util.List;

public class ResultData {
    private final int totalNodes;
    private final int infectedNodes;
    private final int immuneNodes;
    private final int unaffectedNodes;

    public ResultData() {
        List<Node> nodes = VisualisationGraph.getGraph().nodes().toList();
        this.totalNodes = nodes.size();
        this.infectedNodes = (int) nodes.stream().filter(node -> node.hasAttribute("ui.class") && node.getAttribute("ui.class").equals("infected")).count();
        this.immuneNodes = (int) nodes.stream().filter(node -> node.hasAttribute("ui.class") && node.getAttribute("ui.class").equals("immune")).count();
        this.unaffectedNodes = this.totalNodes - this.infectedNodes - this.immuneNodes;
    }

    public Object[][] getData() {
        return new Object[][]{
                {"total_user_amount", this.totalNodes},
                {"infected_user_amount", this.infectedNodes},
                {"immune_user_amount", this.immuneNodes},
                {"unaffected_user_amount", this.unaffectedNodes}
        };
    }
}

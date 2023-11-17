package org.bma.simulator.visuals;

import org.bma.simulator.datamodel.UserNode;
import org.bma.simulator.datamodel.userprofile.utils.UserProfileUtils;
import org.bma.simulator.utils.GraphGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.view.Viewer;

public class VisualisationGraph {
    private static final Graph GRAPH = new MultiGraph("0");
    private static final Viewer graphViewer;
    private static final String STYLE_SHEET = """
            node {
            	size: 10px;
            	fill-color: #777;
            	text-mode: hidden;
            	z-index: 0;
            }
            edge {
            	shape: line;
            	fill-color: #222;
            	arrow-size: 3px, 2px;
            }
            node.infected {
                fill-color: red;
            }
            edge.infected {
                fill-color: red;
            }
            node.immune {
                fill-color: blue;
            }
            edge.immune {
                fill-color: blue;
            }
            """;

    static {
        graphViewer = GRAPH.display();
    }

    private VisualisationGraph() {}


    public static void generateNewGraph(int amountOfNodes, int amountOfCelebrities) {
        GRAPH.clear();
        GRAPH.setAttribute("ui.stylesheet", STYLE_SHEET);
        GRAPH.setAttribute("ui.quality");
        GRAPH.setAttribute("ui.antialias");
        GraphGenerator.createRandomGraphStructure(GRAPH, amountOfNodes);
        GraphGenerator.setCelebrities(amountOfCelebrities);
        updateData();
    }

    private static void updateData() {
        GRAPH.nodes().forEach(node -> {
            UserNode data = node.getAttribute("data", UserNode.class);
            data.setAmountOfFollowers(node.leavingEdges().toList().size());
            data.setAmountOfFollows(node.enteringEdges().toList().size());
        });
        UserProfileUtils.spreadUserProfiles(GRAPH.nodes().toList());
    }

    public static Graph getGraph() {
        return GRAPH;
    }

    public static Viewer getGraphViewer() {
        return graphViewer;
    }
}

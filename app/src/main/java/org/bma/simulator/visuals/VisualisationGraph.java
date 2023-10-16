package org.bma.simulator.visuals;

import org.bma.simulator.utils.GraphGenerator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.view.Viewer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class VisualisationGraph {
    private static final Graph GRAPH = new MultiGraph("0");
    private static Viewer graphViewer;
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
            """;

    static {
        graphViewer = GRAPH.display();
    }

    private VisualisationGraph() {}


    public static void generateNewGraph(int amountOfNodes) {
        GRAPH.clear();
        GRAPH.setAttribute("ui.stylesheet", STYLE_SHEET);
        GRAPH.setAttribute("ui.quality");
        GRAPH.setAttribute("ui.antialias");
        GraphGenerator.createRandomGraphStructure(GRAPH, amountOfNodes);
    }

    public static void setCelebrities(int amount) {
        HashMap<String, Boolean> celebrityRegister = new HashMap<>(amount, 1);
        int nodeCount = GRAPH.getNodeCount();
        for (int i = 0; i < amount; i++) {
            String celebrityId = GRAPH.getNode(ThreadLocalRandom.current().nextInt(0, nodeCount)).getId();
            while (celebrityRegister.get(celebrityId) != null) {
                celebrityId = GRAPH.getNode(ThreadLocalRandom.current().nextInt(0, nodeCount)).getId();
            }
            attachFollowersToCelebrity(celebrityId);
            celebrityRegister.put(celebrityId, true);
        }
    }

    private static void attachFollowersToCelebrity(String celebrityId) {
        Node celebrity = GRAPH.getNode(celebrityId);
        int nodeCount = GRAPH.getNodeCount();
        List<Edge> leavingEdges = celebrity.leavingEdges().toList();
        leavingEdges.forEach(GRAPH::removeEdge);
        List<Node> nodes = new ArrayList<>(GRAPH.nodes().toList());
        nodes.remove(celebrity);
        Collections.shuffle(nodes);
        nodes.subList(0, (int) (nodeCount * ThreadLocalRandom.current().nextDouble(0.4, 0.8)));
        for (Node follower :
                nodes) {
            String followerId = follower.getId();
            GRAPH.addEdge(celebrityId + "->" + followerId, celebrityId, followerId, true);
        }
    }

    public static Graph getGraph() {
        return GRAPH;
    }

    public static Viewer getGraphViewer() {
        return graphViewer;
    }
}

package org.bma.simulator.utils;


import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class FakeNewsUtils {


    private FakeNewsUtils() {
    }

    public static void injectFakeNews(Node sourceNode, long millis) {
        sourceNode.setAttribute("ui.class", "infected");
        sleep(millis);
        List<Node> nodes = new ArrayList<>(List.of(sourceNode));
        while (!nodes.isEmpty()) {
            List<Node> newNodes = new ArrayList<>();
            for (Node node : nodes) {
                for (Edge edge : node.leavingEdges().toList()) {
                    Node returnNode = edge.getTargetNode();
                    if (returnNode.getAttribute("ui.class") == null || !returnNode.getAttribute("ui.class").equals("infected")) {
                        returnNode.setAttribute("ui.class", "infected");
                        edge.setAttribute("ui.class", "infected");
                        newNodes.add(returnNode);
                    }
                }
            }
            nodes = newNodes;
            sleep(millis);

        }
        System.out.println("DONE");
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
        }
    }


}

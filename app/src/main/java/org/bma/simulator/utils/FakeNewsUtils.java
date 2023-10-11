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

    public static void injectFakeNews(Node sourceNode) {
        List<Node> nodes = new ArrayList<>(List.of(sourceNode));
//        while (!nodes.isEmpty()) {
//            nodes = nodes.stream().flatMap(node -> node.leavingEdges().map(edge -> {
//                Node returnNode = edge.getTargetNode();
//                returnNode.setAttribute("ui.class", "infected");
//                return returnNode;
//            })).distinct().toList();
//            sleep();
//        }
        while (!nodes.isEmpty()) {
            List<Node> newNodes = new ArrayList<>();
            for (Node node : nodes) {
                for (Edge edge : node.leavingEdges().toList()) {
                    Node returnNode = edge.getTargetNode();
                    if (returnNode.getAttribute("ui.class") == null || !returnNode.getAttribute("ui.class").equals("infected")) {
                        returnNode.setAttribute("ui.class", "infected");
                        newNodes.add(returnNode);
                    }
                }
            }
            nodes = newNodes;
            sleep();

        }
        System.out.println("DONE");
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }


}

package org.bma.simulator.utils;


import org.bma.simulator.datamodel.UserNode;
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
        int wave = 0;
        updateInfectionStatus(sourceNode, sourceNode, wave);
        sleep(millis);
        List<Node> nodes = new ArrayList<>(List.of(sourceNode));
        while (!nodes.isEmpty()) {
            wave++;
            List<Node> newNodes = new ArrayList<>();
            for (Node node : nodes) {
                for (Edge edge : node.leavingEdges().toList()) {
                    Node returnNode = edge.getTargetNode();
                    if (returnNode.getAttribute("ui.class") == null || !returnNode.getAttribute("ui.class").equals("infected")) {
                        updateInfectionStatus(returnNode, edge.getSourceNode(), wave);
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

    private static void updateInfectionStatus(Node infectedNode, Node culpritNode, int wave) {
        infectedNode.setAttribute("ui.class", "infected");
        UserNode data = infectedNode.getAttribute("data", UserNode.class);
        data.setInfected(true);
        data.setCulpritId(culpritNode.getId());
        data.setInfectionWave(Integer.toString(wave));
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
        }
    }


}

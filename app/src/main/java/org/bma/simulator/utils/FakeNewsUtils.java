package org.bma.simulator.utils;


import org.bma.simulator.datamodel.UserNode;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;

import java.util.ArrayList;
import java.util.List;

public class FakeNewsUtils {
    private static final int REFRESH_RATE = 1000;
    private static final String UI_CLASS = "ui.class";
    private static final String INFECTED = "infected";
    private static final String IMMUNE = "immune";


    private FakeNewsUtils() {
    }

    public static void injectFakeNews(Node originalSourceNode) {
        int wave = 0;
        updateInfectionStatus(originalSourceNode, originalSourceNode, wave, true);
        sleep();
        List<Node> nodes = new ArrayList<>(List.of(originalSourceNode));
        while (!nodes.isEmpty()) {
            wave++;
            List<Node> newNodes = new ArrayList<>();
            for (Node node : nodes) {
                for (Edge edge : node.leavingEdges().toList()) {
                    Node targetNode = edge.getTargetNode();
                    Node sourceNode = edge.getSourceNode();
                    String uiClass = targetNode.getAttribute(UI_CLASS, String.class);
                    if (uiClass == null || (!uiClass.equals(INFECTED) && !uiClass.equals(IMMUNE))) {
                        UserNode sourceNodeData = sourceNode.getAttribute("data", UserNode.class);
                        UserNode targetNodeData = targetNode.getAttribute("data", UserNode.class);
                        if (FakeNewsCalcUtils.calcIfIsInfected(sourceNodeData, targetNodeData)) {
                            updateInfectionStatus(targetNode, sourceNode, wave, true);
                            edge.setAttribute(UI_CLASS, INFECTED);
                            newNodes.add(targetNode);
                        } else {
                            updateInfectionStatus(targetNode, sourceNode, wave, false);
                            edge.setAttribute(UI_CLASS, IMMUNE);
                        }
                    }
                }
            }
            nodes = newNodes;
            sleep();
        }
    }

    private static void updateInfectionStatus(Node infectedNode, Node culpritNode, int wave, boolean infected) {
        UserNode data = infectedNode.getAttribute("data", UserNode.class);
        if (infected) {
            infectedNode.setAttribute(UI_CLASS, INFECTED);
            data.setInfected(true);
        } else {
            infectedNode.setAttribute(UI_CLASS, IMMUNE);
            data.setImmune(true);
        }
        data.setCulpritId(culpritNode.getId());
        data.setInfectionWave(Integer.toString(wave));
    }

    private static void sleep() {
        try {
            Thread.sleep(REFRESH_RATE);
        } catch (Exception e) {}
    }


}

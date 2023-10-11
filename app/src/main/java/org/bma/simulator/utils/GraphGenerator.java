package org.bma.simulator.utils;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.graphicGraph.stylesheet.StyleConstants;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GraphGenerator {
    private static final String styleSheet = """
            node {
                fill-color: black;
            }
            node.infected {
                fill-color: red;
            }
            """;
    private static int graphNumber = 0;

    private static List<String> nodeIds;

    private GraphGenerator() {}

    public static Graph createRandomGraph(int amountOfNodes) {
        nodeIds = new ArrayList<>(amountOfNodes);
        Graph graph = new MultiGraph(Integer.toString(graphNumber));
        graph.setAttribute("ui.stylesheet", styleSheet);

        attachNodes(graph, amountOfNodes);
        createEdges(graph, amountOfNodes);

        graphNumber++;
        nodeIds = null;

        return graph;
    }

    private static void attachNodes(Graph graph, int amountOfNodes) {
        for (int i = 0; i < amountOfNodes; i++) {
            String nodeId = Integer.toString(i);
            graph.addNode(nodeId);
            nodeIds.add(nodeId);
        }
    }
    
    private static void createEdges(Graph graph, int amountOfNodes) {
        for (int sourceNode = 0; sourceNode < amountOfNodes; sourceNode++) {
            int amountOfFollowers = ThreadLocalRandom.current().nextInt(1, 4);


            if (sourceNode == 4 || sourceNode == 167) {
                amountOfFollowers = 80;
            }


            String sourceNodeId = Integer.toString(sourceNode);
            List<String> randomFollowers = getRandomDifferentNodes(amountOfFollowers, nodeIds, sourceNodeId);
            for (String followerId :
                    randomFollowers) {
                graph.addEdge( sourceNodeId + followerId, sourceNodeId, followerId, true);
            }
        }
    }

    private static List<String> getRandomDifferentNodes(int amountOfRandomNodes, List<String> nodeIdList, String initialNode) {

        if (nodeIdList.size() / 3 < amountOfRandomNodes + 1) {
            List<String> copyList = new ArrayList<>(nodeIdList);
            copyList.remove(Integer.parseInt(initialNode));
            Collections.shuffle(copyList);
            return copyList.subList(0, amountOfRandomNodes);
        } else {
            HashMap<String, Boolean> chosenIds = new HashMap<>(amountOfRandomNodes + 1, 1);
            chosenIds.put(initialNode, true);
            int listSize = nodeIdList.size();
            for (int i = 0; i < amountOfRandomNodes; i++) {
                String chosenId = nodeIdList.get(ThreadLocalRandom.current().nextInt(listSize));
                while (chosenIds.containsKey(chosenId)) {
                    chosenId = nodeIdList.get(ThreadLocalRandom.current().nextInt(listSize));
                }
                chosenIds.put(chosenId, true);
            }
            chosenIds.remove(initialNode);
            return chosenIds.keySet().stream().toList();
        }
    }

}
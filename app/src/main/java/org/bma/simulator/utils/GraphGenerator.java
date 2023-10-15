package org.bma.simulator.utils;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GraphGenerator {


    private static List<String> nodeIds;

    private GraphGenerator() {}

    public static void createRandomGraphStructure(Graph graph, int amountOfNodes) {
        nodeIds = new ArrayList<>(amountOfNodes);


        attachNodes(graph, amountOfNodes);
        createEdges(graph, amountOfNodes);
        nodeIds = null;
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

            String sourceNodeId = Integer.toString(sourceNode);
            List<String> randomFollowers = getRandomDifferentNodes(amountOfFollowers, nodeIds, sourceNodeId);
            for (String followerId :
                    randomFollowers) {
                graph.addEdge( sourceNodeId + "->" + followerId, sourceNodeId, followerId, true);
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
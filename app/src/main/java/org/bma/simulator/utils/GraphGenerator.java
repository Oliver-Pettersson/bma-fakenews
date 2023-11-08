package org.bma.simulator.utils;

import org.bma.simulator.datamodel.UserConnectionEdge;
import org.bma.simulator.datamodel.UserNode;
import org.bma.simulator.visuals.VisualisationGraph;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GraphGenerator {
    private static int minFollowerAmount;
    private static int maxFollowerAmount;


    private static List<String> nodeIds;

    private GraphGenerator() {
    }

    public static void createRandomGraphStructure(Graph graph, int amountOfNodes) {
        nodeIds = new ArrayList<>(amountOfNodes);


        attachNodes(graph, amountOfNodes);
        createEdges(graph, amountOfNodes);
        nodeIds = null;
    }

    private static void attachNodes(Graph graph, int amountOfNodes) {
        for (int i = 0; i < amountOfNodes; i++) {
            String nodeId = Integer.toString(i);
            graph.addNode(nodeId).setAttribute("data", new UserNode(nodeId));
            nodeIds.add(nodeId);
        }
    }

    private static void createEdges(Graph graph, int amountOfNodes) {
        for (int sourceNode = 0; sourceNode < amountOfNodes; sourceNode++) {
            int amountOfFollowers = ThreadLocalRandom.current().nextInt(minFollowerAmount, maxFollowerAmount + 1);

            String sourceNodeId = Integer.toString(sourceNode);
            List<String> randomFollowers = getRandomDifferentNodes(amountOfFollowers, nodeIds, sourceNodeId);
            for (String followerId :
                    randomFollowers) {
                Edge e = graph.addEdge(sourceNodeId + "->" + followerId, sourceNodeId, followerId, true);
                e.setAttribute("data", new UserConnectionEdge());
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

    public static void setCelebrities(int amount) {
        HashMap<String, Boolean> celebrityRegister = new HashMap<>(amount, 1);
        int nodeCount = VisualisationGraph.getGraph().getNodeCount();
        for (int i = 0; i < amount; i++) {
            String celebrityId = VisualisationGraph.getGraph().getNode(ThreadLocalRandom.current().nextInt(0, nodeCount)).getId();
            while (celebrityRegister.get(celebrityId) != null) {
                celebrityId = VisualisationGraph.getGraph().getNode(ThreadLocalRandom.current().nextInt(0, nodeCount)).getId();
            }
            ((UserNode) VisualisationGraph.getGraph().getNode(celebrityId).getAttribute("data")).setCelebrity(true);
            attachFollowersToCelebrity(celebrityId);
            celebrityRegister.put(celebrityId, true);
        }
    }

    private static void attachFollowersToCelebrity(String celebrityId) {
        Node celebrity = VisualisationGraph.getGraph().getNode(celebrityId);
        int nodeCount = VisualisationGraph.getGraph().getNodeCount();
        List<Edge> leavingEdges = celebrity.leavingEdges().toList();
        leavingEdges.forEach(VisualisationGraph.getGraph()::removeEdge);
        List<Node> nodes = new ArrayList<>(VisualisationGraph.getGraph().nodes().toList());
        nodes.remove(celebrity);
        Collections.shuffle(nodes);
        nodes = nodes.subList(0, (int) (nodeCount * ThreadLocalRandom.current().nextDouble(0.4, 0.8)));
        for (Node follower :
                nodes) {
            String followerId = follower.getId();
            VisualisationGraph.getGraph().addEdge(celebrityId + "->" + followerId, celebrityId, followerId, true);
        }
    }

    public static void setMinFollowerAmount(int minFollowerAmount) {
        GraphGenerator.minFollowerAmount = minFollowerAmount;
    }

    public static void setMaxFollowerAmount(int maxFollowerAmount) {
        GraphGenerator.maxFollowerAmount = maxFollowerAmount;
    }
}
package com.epam.javacourses2016.task13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyGraphCreator extends AbstractGraphCreator {

    @Override
    public MyGraph createGraph(int numberNodes) {
        if (numberNodes <= 0) {
            return null;
        }
        return new MyGraph(numberNodes);
    }

    public class MyGraph extends AbstractGraph {

        Map<Integer, Node> nodes = new HashMap<>();

        public MyGraph(int numberOfNodes) {
            super(numberOfNodes);
        }

        @Override
        public void addEdge(int first, int second) {

            if (!(checkInputValue(first) && checkInputValue(second))) {
                throw new IllegalArgumentException();
            }

            if (nodes.size() >= NUMBER_NODES) {
                throw new IllegalArgumentException();
            }

            Node firstNode = nodes.get(first);
            Node secondNode = nodes.get(second);

            if (firstNode == null) {
                firstNode = new Node(first);
                nodes.put(first, firstNode);
            }

            if (secondNode == null) {
                secondNode = new Node(second);
                nodes.put(second, secondNode);
            }

            firstNode.addNode(secondNode);
            secondNode.addNode(firstNode);
        }

        @Override
        public void removeEdge(int first, int second) {
            if (!(checkInputValue(first) && checkInputValue(second))) {
                throw new IllegalArgumentException();
            }

            Node firstNode = nodes.get(first);
            Node secondNode = nodes.get(second);

            firstNode.getConnectedNodes().remove(secondNode);
            secondNode.getConnectedNodes().remove(firstNode);
        }

        @Override
        public boolean isExistEdge(int first, int second) {
            if (!(checkInputValue(first) && checkInputValue(second))) {
                return false;
            }

            Node firstNode = nodes.get(first);
            Node secondNode = nodes.get(second);

            if (firstNode == null) {
                return false;
            }

            if (secondNode == null) {
                return false;
            }

            return firstNode.getConnectedNodes().contains(secondNode) &&
                    secondNode.getConnectedNodes().contains(firstNode);
        }

        private boolean checkInputValue(int value) {
            if (value > NUMBER_NODES || value < 0) {
                return false;
            }

            return true;
        }

        class Node {
            int value;
            ArrayList<Node> connectedNodes = new ArrayList<>();

            public Node(int value) {
                this.value = value;
            }

            public boolean addNode(Node node) {
                if (!connectedNodes.contains(node)) {
                    connectedNodes.add(node);
                }

                return false;
            }

            public ArrayList<Node> getConnectedNodes() {
                return connectedNodes;
            }
        }
    }
}
package main.java.Graphs.Dijkstras;

import java.util.*;

/**
 * Created by Tanner on 6/4/2016.
 */
public class Dijkstras {

    List<Node> vertices;
    List<Edge> edges;
    Map<Node, Integer> distances;
    Map<Node, Node> predecessors;
    Set<Node> settled;
    Set<Node> unsettled;

    public Dijkstras(List<Node> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public void run(Node source) {
        distances = new HashMap<>();
        predecessors = new HashMap<>();
        settled = new HashSet<>();
        unsettled = new HashSet<>();

        distances.put(source, 0);
        unsettled.add(source);
        while (!unsettled.isEmpty()) {
            Node currentNode = getShortest(unsettled);
            settled.add(currentNode);
            unsettled.remove(currentNode);
            findShortestDistances(currentNode);
        }
    }

    public List<Node> getShortestPath(Node dest) {
        if (predecessors == null || predecessors.get(dest) == null) {
            return null;
        }
        List<Node> path = new ArrayList<>();
        path.add(dest);
        while (predecessors.get(dest) != null) {
            dest = predecessors.get(dest);
            path.add(dest);
        }
        Collections.reverse(path);
        return path;
    }

    private void findShortestDistances(Node node) {
        List<Node> neighbours = getNeighbours(node);
        for (Node neighbour : neighbours) {
            if (getDistance(neighbour) > getDistance(node) + getWeight(node, neighbour)) {
                distances.put(neighbour, getDistance(node) + getWeight(node, neighbour));
                unsettled.add(neighbour);
                predecessors.put(neighbour, node);
            }
        }
    }

    private List<Node> getNeighbours(Node node) {
        List<Node> neighbours = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && !settled.contains(edge.getDestination())) {
                neighbours.add(edge.getDestination());
            }
        }
        return neighbours;
    }

    private int getWeight(Node source, Node dest) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(source) && edge.getDestination().equals(dest)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Could not find edge with given source and destination");
    }

    private Node getShortest(Set<Node> nodes) {
        Node shortest = null;
        for (Node node : nodes) {
            if (shortest == null) {
                shortest = node;
            } else if (getDistance(node) < getDistance(shortest)) {
                shortest = node;
            }
        }
        return shortest;
    }

    private int getDistance(Node node) {
        return distances.get(node) == null ? Integer.MAX_VALUE : distances.get(node);
    }

    private class Node {

        String id;
        String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (id != null ? !id.equals(node.id) : node.id != null) return false;
            return name != null ? name.equals(node.name) : node.name == null;

        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }

    private class Edge {

        Node source;
        Node destination;
        int weight;

        public Node getSource() {
            return source;
        }

        public void setSource(Node source) {
            this.source = source;
        }

        public Node getDestination() {
            return destination;
        }

        public void setDestination(Node destination) {
            this.destination = destination;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}

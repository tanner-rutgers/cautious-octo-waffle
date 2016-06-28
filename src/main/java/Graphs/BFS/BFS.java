package main.java.Graphs.BFS;

import java.util.*;

/**
 * Created by Tanner on 6/4/2016.
 */
public class BFS {

    Queue<Node> queue;
    Map<Node, Node> parents;

    public int bfs(Node start, String target) {
        int distance = -1;
        queue = new LinkedList<>();
        parents = new HashMap<>();
        queue.add(start);
        Node current = queue.poll();
        boolean found = false;
        while (!found) {
            List<Node> neighbours = current.getNeighbours();
            for (Node neighbour : neighbours) {
                if (parents.get(neighbour) == null) {
                    parents.put(neighbour, current);
                    if (neighbour.getValue().equals(target)) {
                        distance = getDistance(start, neighbour);
                        found = true;
                        break;
                    }
                    queue.add(neighbour);
                }
            }
            current = queue.poll();
        }
        return distance;
    }

    private int getDistance(Node start, Node finish) {
        int distance = 0;
        Node step = finish;
        while (parents.get(step) != start) {
            step = parents.get(step);
            distance++;
        }
        return distance++;
    }

    private class Node {
        List<Node> neighbours;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (neighbours != null ? !neighbours.equals(node.neighbours) : node.neighbours != null) return false;
            return value != null ? value.equals(node.value) : node.value == null;

        }

        @Override
        public int hashCode() {
            int result = neighbours != null ? neighbours.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }

        String value;


        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<Node> getNeighbours() {
            return neighbours;
        }

        public void setNeighbours(List<Node> neighbours) {
            this.neighbours = neighbours;
        }
    }
}

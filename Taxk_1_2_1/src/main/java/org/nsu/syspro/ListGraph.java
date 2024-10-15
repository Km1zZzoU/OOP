package org.nsu.syspro;
import java.util.*;

/**
 * Реализация графа через список смежности.
 */
public class ListGraph implements Graph {
    private final Map<String, List<String>> List = new HashMap<>();

    @Override
    public void addVertex(String vertex) {
        List.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public void removeVertex(String vertex) {
        List.remove(vertex);
        List.values().forEach(e -> e.remove(vertex));
    }

    @Override
    public void addEdge(String source, String destination) {
        List.putIfAbsent(source, new ArrayList<>());
        List.putIfAbsent(destination, new ArrayList<>());
        List.get(source).add(destination);
    }

    @Override
    public void removeEdge(String source, String destination) {
        List<String> neighbors = List.get(source);
        if (neighbors != null) {
            neighbors.remove(destination);
        }
    }

    @Override
    public List<String> getNeighbors(String vertex) {
        return List.getOrDefault(vertex, new ArrayList<>());
    }

    @Override
    public void readFromFile(String filename) {
        // Реализация чтения из файла
    }

    @Override
    public List<String> topologicalSort() {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        for (String vertex : List.keySet()) {
            if (!visited.contains(vertex)) {
                topologicalSortUtil(vertex, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    private void topologicalSortUtil(String vertex, Set<String> visited, Stack<String> stack) {
        visited.add(vertex);
        for (String neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }
        stack.push(vertex);
    }

    @Override
    public String toString() {
        return List.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ListGraph)) return false;
        ListGraph other = (ListGraph) obj;
        return List.equals(other.List);
    }
}

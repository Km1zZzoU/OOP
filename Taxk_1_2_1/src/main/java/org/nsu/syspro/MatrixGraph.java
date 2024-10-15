package org.nsu.syspro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Реализация графа через матрицу смежности.
 */
public class MatrixGraph implements Graph {
    private final Map<String, Integer> vertexIndexMap = new HashMap<>();
    private final List<String> vertices = new ArrayList<>();
    private boolean[][] Matrix;

    @Override
    public void addVertex(String vertex) {
        vertexIndexMap.putIfAbsent(vertex, vertices.size());
        vertices.add(vertex);
        resizeMatrix();
    }

    @Override
    public void removeVertex(String vertex) {
        Integer index = vertexIndexMap.remove(vertex);
        if (index != null) {
            vertices.remove((int) index);
            resizeMatrix();
            for (String v : vertexIndexMap.keySet()) {
                if (vertexIndexMap.get(v) > index) {
                    vertexIndexMap.put(v, vertexIndexMap.get(v) - 1);
                }
            }
        }
    }

    @Override
    public void addEdge(String source, String destination) {
        int sourceIndex = vertexIndexMap.get(source);
        int destinationIndex = vertexIndexMap.get(destination);
        Matrix[sourceIndex][destinationIndex] = true;
    }

    @Override
    public void removeEdge(String source, String destination) {
        int sourceIndex = vertexIndexMap.get(source);
        int destinationIndex = vertexIndexMap.get(destination);
        Matrix[sourceIndex][destinationIndex] = false;
    }

    @Override
    public List<String> getNeighbors(String vertex) {
        List<String> neighbors = new ArrayList<>();
        int vertexIndex = vertexIndexMap.get(vertex);
        for (int i = 0; i < Matrix[vertexIndex].length; i++) {
            if (Matrix[vertexIndex][i]) {
                neighbors.add(vertices.get(i));
            }
        }
        return neighbors;
    }

    private void resizeMatrix() {
        int size = vertices.size();
        boolean[][] newMatrix = new boolean[size][size];
        if (Matrix != null) {
            for (int i = 0; i < Matrix.length; i++) {
                System.arraycopy(Matrix[i], 0, newMatrix[i], 0, Math.min(Matrix[i].length, size));
            }
        }
        Matrix = newMatrix;
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

        for (String vertex : vertices) {
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
        return Arrays.deepToString(Matrix);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MatrixGraph)) return false;
        MatrixGraph other = (MatrixGraph) obj;
        return Arrays.deepEquals(Matrix, other.Matrix);
    }
}

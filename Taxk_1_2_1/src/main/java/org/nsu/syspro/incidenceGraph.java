package org.nsu.syspro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Реализация графа через матрицу инцидентности.
 */
public class incidenceGraph implements Graph {
    private final List<String> vertices = new ArrayList<>();
    private final List<String[]> edges = new ArrayList<>();
    private boolean[][] incidenceMatrix;

    @Override
    public void addVertex(String vertex) {
        vertices.add(vertex);
        resizeMatrix();
    }

    @Override
    public void removeVertex(String vertex) {
        int index = vertices.indexOf(vertex);
        if (index != -1) {
            vertices.remove(index);
            edges.removeIf(edge -> edge[0].equals(vertex) || edge[1].equals(vertex));
            resizeMatrix();
        }
    }

    @Override
    public void addEdge(String source, String destination) {
        edges.add(new String[]{source, destination});
        resizeMatrix();
    }

    @Override
    public void removeEdge(String source, String destination) {
        edges.removeIf(edge -> edge[0].equals(source) && edge[1].equals(destination));
        resizeMatrix();
    }

    @Override
    public List<String> getNeighbors(String vertex) {
        List<String> neighbors = new ArrayList<>();
        for (String[] edge : edges) {
            if (edge[0].equals(vertex)) {
                neighbors.add(edge[1]);
            } else if (edge[1].equals(vertex)) {
                neighbors.add(edge[0]);
            }
        }
        return neighbors;
    }

    private void resizeMatrix() {
        int vertexCount = vertices.size();
        incidenceMatrix = new boolean[vertexCount][edges.size()];
        for (int j = 0; j < edges.size(); j++) {
            String[] edge = edges.get(j);
            int sourceIndex = vertices.indexOf(edge[0]);
            int destinationIndex = vertices.indexOf(edge[1]);
            incidenceMatrix[sourceIndex][j] = true;
            incidenceMatrix[destinationIndex][j] = true;
        }
    }

    @Override
    public void readFromFile(String filename) {
    }

    @Override
    public List<String> topologicalSort() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return Arrays.deepToString(incidenceMatrix);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof incidenceGraph)) return false;
        incidenceGraph other = (incidenceGraph) obj;
        return Arrays.deepEquals(incidenceMatrix, other.incidenceMatrix);
    }
}

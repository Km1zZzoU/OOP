package org.nsu.syspro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Реализация графа через матрицу смежности с вершинами типа Integer.
 */
public class MatrixGraph implements Graph {
    private final Map<Integer, Integer> vertexIndexMap = new HashMap<>();
    private int cap;
    private boolean[][] matrix;

    MatrixGraph() {
        this.cap = 4;
        this.matrix = new boolean[this.cap][this.cap];
    }

    /**
     * Добавляет вершину в граф.
     *
     * @param vertex Вершина, которую нужно добавить.
     */
    @Override
    public void addVertex(Integer vertex) {
        if (!vertexIndexMap.containsKey(vertex)) {
            if (vertexIndexMap.size() == cap) {
                sizeUpMatrix();
            }
            vertexIndexMap.put(vertex, vertexIndexMap.size());
        }
    }

    /**
     * Удаляет вершину из графа. Если ее нет, не делает ничего. ВАУ.
     *
     * @param vertex Вершина, которую нужно удалить.
     */
    @Override
    public void removeVertex(Integer vertex) {
        Integer index = vertexIndexMap.remove(vertex);
        if (index != null) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][index] = false;
            }
        }
    }

    /**
     * Добавляет ребро между двумя вершинами.
     * При отсутвие вершин, добавляет их.
     *
     * @param source Исходная вершина.
     * @param destination Целевая вершина.
     */
    @Override
    public void addEdge(Integer source, Integer destination) {
        addVertex(source);
        int sourceIndex = vertexIndexMap.get(source);
        addVertex(destination);
        int destinationIndex = vertexIndexMap.get(destination);
        matrix[sourceIndex][destinationIndex] = true;
    }

    /*
     * Удаляет ребро между двумя вершинами. При отсутствии не делает ничего.
     *
     * @param source Исходная вершина.
     * @param destination Целевая вершина.
     */
    @Override
    public void removeEdge(Integer source, Integer destination) {
        if (!vertexIndexMap.containsKey(source) || !vertexIndexMap.containsKey(destination)) {
            return;
        }
        int sourceIndex = vertexIndexMap.get(source);
        int destinationIndex = vertexIndexMap.get(destination);
        matrix[sourceIndex][destinationIndex] = false;
    }

    /**
     * Возвращает список соседей заданной вершины.
     *
     * @param vertex Вершина, для которой нужно получить соседей.
     * @return Список соседей. Если передать NULL вернет всех.
     */
    @Override
    public List<Integer> getNeighbors(Integer vertex) {
        if (vertex == null) {
            return new ArrayList<>(vertexIndexMap.keySet());
        }

        List<Integer> neighbors = new ArrayList<>();
        int vertexIndex = vertexIndexMap.get(vertex);
        for (int i = 0; i < matrix[vertexIndex].length; i++) {
            if (matrix[vertexIndex][i]) {
                neighbors.add(getVertexByIndex(i));
            }
        }
        return neighbors;
    }

    /**
     * Получает вершину по её индексу.
     *
     * @param index Индекс вершины.
     * @return Вершина, соответствующая данному индексу.
     */
    private Integer getVertexByIndex(int index) {
        for (Map.Entry<Integer, Integer> entry : vertexIndexMap.entrySet()) {
            if (entry.getValue() == index) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Изменяет размер матрицы смежности в зависимости от количества вершин.
     */
    private void sizeUpMatrix() {
        cap = cap << 1;
        boolean[][] newMatrix = new boolean[cap][cap];

        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix[i].length);
        }

        matrix = newMatrix;
    }
}

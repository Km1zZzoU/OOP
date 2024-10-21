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
    private boolean[][] Matrix;

    MatrixGraph() {

    }

    /**
     * Добавляет вершину в граф.
     *
     * @param vertex Вершина, которую нужно добавить.
     */
    @Override
    public void addVertex(Integer vertex) {
        if (!vertexIndexMap.containsKey(vertex)) {
            vertexIndexMap.put(vertex, vertexIndexMap.size());
            sizeUpMatrix();
        }
    }

    /**
     * Удаляет вершину из графа.
     *
     * @param vertex Вершина, которую нужно удалить.
     */
    @Override
    public void removeVertex(Integer vertex) {
        Integer index = vertexIndexMap.remove(vertex);
        if (index != null) {
            for (int i = 0; i < Matrix.length; i++) {
                Matrix[i][index] = false;
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
        Matrix[sourceIndex][destinationIndex] = true;
    }

    /**
     * Удаляет ребро между двумя вершинами.
     *
     * @param source Исходная вершина.
     * @param destination Целевая вершина.
     */
    @Override
    public void removeEdge(Integer source, Integer destination) {
        int sourceIndex = vertexIndexMap.get(source);
        int destinationIndex = vertexIndexMap.get(destination);
        Matrix[sourceIndex][destinationIndex] = false;
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
        for (int i = 0; i < Matrix[vertexIndex].length; i++) {
            if (Matrix[vertexIndex][i]) {
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
    public void sizeUpMatrix() {
        int size = vertexIndexMap.size();
        boolean[][] newMatrix = new boolean[size][size];

        // Копирование данных из старой матрицы в новую
        if (Matrix != null) {
            for (int i = 0; i < Matrix.length; i++) {
                for (int j = 0; j < Matrix[i].length; j++) {
                    newMatrix[i][j] = Matrix[i][j];
                }
            }
        }

        Matrix = newMatrix;
    }


}

package org.nsu.syspro;

import com.sun.jdi.IntegerValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Реализация ориентированного графа через матрицу инцидентности с использованием int.
 */
public class IncidenceGraph implements Graph {
    private Map<Integer, Integer> vertexIndexMap = new HashMap<>();
    private int[][] incidenceMatrix; // Матрица инцидентности
    private int vertexCount;           // Количество вершин
    private int edgeCount;             // Количество рёбер
    private int capEdge;
    private int capVertex;

    /**
     * Конструктор для инициализации графа с нулевым количеством вершин и рёбер.
     */
    public IncidenceGraph() {
        vertexCount = 0;
        edgeCount = 0;
        capEdge = 4;
        capVertex = 4;
        incidenceMatrix = new int[capEdge][capVertex];
    }

    /**
     * Добавляет вершину в граф.
     *
     * @param vertex Вершина, которую нужно добавить.
     */
    @Override
    public void addVertex(Integer vertex) {
        if (vertexIndexMap.containsKey(vertex)) {
            return;
        }
        vertexIndexMap.put(vertex, vertexCount++);
        resizeMatrix();
    }

    /**
     * Удаляет вершину из графа. Если ее нет, не делает ничего.
     *
     * @param vertex Вершина, которую нужно удалить.
     */
    @Override
    public void removeVertex(Integer vertex) {
        Integer vertexIndex = vertexIndexMap.remove(vertex);
        if (vertexIndex != null) {

            // Обнуляем строку в матрице инцидентности, связанную с удаляемой вершиной
            for (int j = 0; j < edgeCount; j++) {
                if (incidenceMatrix[vertexIndex][j] != 0){
                    if (incidenceMatrix[vertexIndex][j] != 2) {
                        for (int k = 0; k < vertexCount; k++) {
                            incidenceMatrix[k][j] = 0;
                        }
                    }
                    incidenceMatrix[vertexIndex][j] = 0;
                }
            }
            vertexCount--;
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
        addVertex(destination);

        int sourceIndex = vertexIndexMap.get(source);
        int destinationIndex = vertexIndexMap.get(destination);

        edgeCount++;
        resizeMatrix();
        if (source == destination) {
            incidenceMatrix[sourceIndex][edgeCount - 1] = 2;
            return;
        }


        // Устанавливаем инцидентность для ориентированного ребра
        incidenceMatrix[sourceIndex][edgeCount - 1] = -1; // Исходная вершина
        incidenceMatrix[destinationIndex][edgeCount - 1] = 1; // Целевая вершина
    }

    /**
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



        // Проходим по столбцам, чтобы найти ребро, соединяющее source и destination
        for (int j = 0; j < edgeCount; j++) {
            if (incidenceMatrix[sourceIndex][j] == -1 && incidenceMatrix[destinationIndex][j] == 1
                || incidenceMatrix[sourceIndex][j] == 2) {
                // Удаляем инцидентность
                for (int i = 0; i < vertexCount; i++) {
                    incidenceMatrix[i][j] = '\0'; // Обозначаем отсутствие инцидентности
                }
                break; // Найдено и удалено
            }
        }
    }

    /**
     * Возвращает список соседей заданной вершины.
     *
     * @param vertex Вершина, для которой нужно получить соседей.
     * @return Список соседей. Если передать NULL, вернет всех.
     */
    @Override
    public List<Integer> getNeighbors(Integer vertex) {
        List<Integer> neighbors = new ArrayList<>();

        if (vertex == null) {
            // Возвращаем все вершины, если vertex равно null
            for (Integer value : vertexIndexMap.values()) {
                neighbors.add(getVertexByIndex(value)); // Добавляем все вершины
            }
            return neighbors;
        }

        int vertexIndex = vertexIndexMap.get(vertex);
        for (int j = 0; j < edgeCount; j++) {
            if (incidenceMatrix[vertexIndex][j] == -1) {
                // Если вершина инцидентна ребру, добавляем целевую вершину
                for (int i = 0; i < capVertex; i++) {
                    if (incidenceMatrix[i][j] == 1) {
                        neighbors.add(getVertexByIndex(i));
                    }
                }
            } else if (incidenceMatrix[vertexIndex][j] == 2) {
                neighbors.add(getVertexByIndex(vertexIndex));
            }
        }

        return neighbors;
    }

    /**
     * Изменяет размер матрицы инцидентности в зависимости от количества вершин и рёбер.
     */
    private void resizeMatrix() {
        if (edgeCount < capEdge && vertexCount < capVertex) {
            return;
        }
        capEdge = edgeCount >= capEdge ? capEdge << 1: capEdge;
        capVertex = vertexCount >= capVertex ? capVertex << 1 : capVertex;
        int[][] newMatrix = new int[capVertex][capEdge];
        for (int i = 0; i < Math.min(incidenceMatrix.length, vertexCount); i++) {
            for (int j = 0; j < Math.min(incidenceMatrix[i].length, edgeCount); j++) {
                newMatrix[i][j] = incidenceMatrix[i][j];
            }
        }
        incidenceMatrix = newMatrix;
    }

    /**
     * Получает вершину по индексу.
     *
     * @param index Индекс вершины.
     * @return Вершина.
     */
    private Integer getVertexByIndex(int index) {
        for (Map.Entry<Integer, Integer> entry : vertexIndexMap.entrySet()) {
            if (entry.getValue() == index) {
                return entry.getKey();
            }
        }
        return null;
    }
}

package org.nsu.syspro;

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

    /**
     * Конструктор для инициализации графа с нулевым количеством вершин и рёбер.
     */
    public IncidenceGraph() {
        vertexCount = 0;
        edgeCount = 0;
        incidenceMatrix = new int[0][0];
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
     * Удаляет вершину из графа.
     *
     * @param vertex Вершина, которую нужно удалить.
     */
    @Override
    public void removeVertex(Integer vertex) {
        Integer vertexIndex = vertexIndexMap.remove(vertex); // Удаляем вершину из мапы и получаем ее индекс
        if (vertexIndex != null) {
            vertexCount--;

            // Создаем новую матрицу инцидентности с уменьшенным количеством вершин
            int[][] newMatrix = new int[vertexCount][edgeCount];
            int newRowIndex = 0;

            // Копируем данные в новую матрицу, пропуская удаляемую вершину
            for (int i = 0; i < incidenceMatrix.length; i++) {
                if (i != vertexIndex) {
                    for (int j = 0; j < edgeCount; j++) {
                        newMatrix[newRowIndex][j] = incidenceMatrix[i][j];
                    }
                    newRowIndex++;
                }
            }

            incidenceMatrix = newMatrix;

            // Обновляем vertexIndexMap
            Map<Integer, Integer> newVertexIndexMap = new HashMap<>();
            int newIndex = 0;
            for (Map.Entry<Integer, Integer> entry : vertexIndexMap.entrySet()) {
                if (entry.getValue() > vertexIndex) {
                    newVertexIndexMap.put(entry.getKey(), entry.getValue() - 1); // Уменьшаем индекс для вершин, которые идут после удаляемой
                } else {
                    newVertexIndexMap.put(entry.getKey(), entry.getValue());
                }
            }
            vertexIndexMap = newVertexIndexMap;
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
     * Удаляет ребро между двумя вершинами.
     *
     * @param source Исходная вершина.
     * @param destination Целевая вершина.
     */
    @Override
    public void removeEdge(Integer source, Integer destination) {
        int sourceIndex = vertexIndexMap.get(source);
        int destinationIndex = vertexIndexMap.get(destination);



        // Проходим по столбцам, чтобы найти ребро, соединяющее source и destination
        for (int j = 0; j < edgeCount; j++) {
            if (incidenceMatrix[sourceIndex][j] == -1 && incidenceMatrix[destinationIndex][j] == 1 ||
            incidenceMatrix[sourceIndex][j] == 2) {
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
            for (int i = 0; i < vertexCount; i++) {
                neighbors.add(getVertexByIndex(i)); // Добавляем все вершины
            }
            return neighbors;
        }

        int vertexIndex = vertexIndexMap.get(vertex);
        for (int j = 0; j < edgeCount; j++) {
            if (incidenceMatrix[vertexIndex][j] == -1) {
                // Если вершина инцидентна ребру, добавляем целевую вершину
                for (int i = 0; i < vertexCount; i++) {
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
        int[][] newMatrix = new int[vertexCount][edgeCount];
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

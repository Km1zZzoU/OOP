package org.nsu.syspro;

import java.util.List;

/**
 * Интерфейс для работы с графом.
 */
public interface Graph {
    /**
     * Добавляет вершину в граф.
     *
     * @param vertex Вершина, которую нужно добавить.
     */
    void addVertex(Integer vertex);

    /**
     * Удаляет вершину из графа.
     *
     * @param vertex Вершина, которую нужно удалить.
     */
    void removeVertex(Integer vertex);

    /**
     * Добавляет ребро между двумя вершинами.
     *
     * @param source Исходная вершина.
     * @param destination Конечная вершина.
     */
    void addEdge(Integer source, Integer destination);

    /**
     * Удаляет ребро между двумя вершинами.
     *
     * @param source Исходная вершина.
     * @param destination Конечная вершина.
     */
    void removeEdge(Integer source, Integer destination);

    /**
     * Получает список соседей для заданной вершины.
     *
     * @param vertex Вершина, для которой нужно получить соседей.
     * @return Список соседей.
     */
    List<Integer> getNeighbors(Integer vertex);
}

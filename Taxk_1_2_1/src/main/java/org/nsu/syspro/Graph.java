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
    void addVertex(String vertex);

    /**
     * Удаляет вершину из графа.
     *
     * @param vertex Вершина, которую нужно удалить.
     */
    void removeVertex(String vertex);

    /**
     * Добавляет ребро между двумя вершинами.
     *
     * @param source Исходная вершина.
     * @param destination Конечная вершина.
     */
    void addEdge(String source, String destination);

    /**
     * Удаляет ребро между двумя вершинами.
     *
     * @param source Исходная вершина.
     * @param destination Конечная вершина.
     */
    void removeEdge(String source, String destination);

    /**
     * Получает список соседей для заданной вершины.
     *
     * @param vertex Вершина, для которой нужно получить соседей.
     * @return Список соседей.
     */
    List<String> getNeighbors(String vertex);

    /**
     * Читает граф из файла.
     *
     * @param filename Имя файла.
     */
    void readFromFile(String filename);

    /**
     * Выполняет топологическую сортировку графа.
     *
     * @return Список вершин в топологическом порядке.
     */
    List<String> topologicalSort();
}

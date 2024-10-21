package org.nsu.syspro;

import java.util.*;

/**
 * Реализация графа через список смежности с вершинами типа Integer.
 */
public class ListGraph implements Graph {
    private final Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

    ListGraph() {

    }

    /**
     * Добавляет вершину в граф.
     *
     * @param vertex Вершина, которую нужно добавить.
     */
    @Override
    public void addVertex(Integer vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    /**
     * Удаляет вершину из графа.
     *
     * @param vertex Вершина, которую нужно удалить.
     */
    @Override
    public void removeVertex(Integer vertex) {
        adjacencyList.remove(vertex);
        adjacencyList.values().forEach(e -> e.remove(vertex));
        // Удаляем вершину из списков смежности других вершин
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
        adjacencyList.putIfAbsent(source, new ArrayList<>());
        adjacencyList.putIfAbsent(destination, new ArrayList<>());
        if (!adjacencyList.get(source).contains(destination)) { // Проверяем на дубликаты
            adjacencyList.get(source).add(destination);
        }
    }

    /**
     * Удаляет ребро между двумя вершинами.
     *
     * @param source Исходная вершина.
     * @param destination Целевая вершина.
     */
    @Override
    public void removeEdge(Integer source, Integer destination) {
        List<Integer> neighbors = adjacencyList.get(source);
        if (neighbors != null) {
            neighbors.remove(destination); // Удаляем связь (ребро) между вершинами
        }
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
            return new ArrayList<>(adjacencyList.keySet());
        }
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }
}

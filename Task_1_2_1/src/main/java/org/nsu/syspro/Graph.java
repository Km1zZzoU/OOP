package org.nsu.syspro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * При отсутвие вершин, добавляет их.
     *
     * @param source Исходная вершина.
     * @param destination Целевая вершина.
     */
    void addEdge(Integer source, Integer destination);

    /**
     * Удаляет ребро между двумя вершинами.
     * Когда ребра нет, не делает ничего.
     *
     * @param source Исходная вершина.
     * @param destination Конечная вершина.
     */
    void removeEdge(Integer source, Integer destination);

    /**
     * Получает список соседей для заданной вершины.
     * если передвть NULL, вернет все вершины.
     *
     * @param vertex Вершина, для которой нужно получить соседей.
     * @return Список соседей.
     */
    List<Integer> getNeighbors(Integer vertex);

    /**
     * Восстанавливает граф из файла.
     *
     * @param filePath Путь к файлу, содержащему строковое представление графа.
     * @param graph Граф, в который будет восстановлено представление.
     * @param <G> Тип графа.
     * @return Восстановленный граф.
     * @throws IOException Если возникла ошибка при чтении файла.
     */
    static <G extends Graph> G fromFile(String filePath, G graph) throws IOException {
        StringBuilder graphString = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                graphString.append(line).append("\n");
            }
        }
        return fromString(graphString.toString(), graph);
    }

    /**
     * Topsort.
     *
     * @param graph graph for sort.
     * @return sorted graph or throw new IllegalArgumentException("Граф содержит цикл").
     */
    static List<Integer> topologicalSort(Graph graph) {
        List<Integer> sorted = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> tempMarked = new HashSet<>();

        for (Integer vertex : getAllVertices(graph)) {
            if (!visited.contains(vertex)) {
                if (!dfsTopologicalSort(graph, vertex, visited, tempMarked, sorted)) {
                    throw new IllegalArgumentException("Граф содержит цикл");
                }
            }
        }

        Collections.reverse(sorted); // Так как при DFS вершины добавляются в обратном порядке
        return sorted;
    }

    /**
     * Рекурсивная функция для выполнения DFS при топологической сортировке.
     */
    private static boolean dfsTopologicalSort(Graph graph, Integer vertex,
        Set<Integer> visited, Set<Integer> tempMarked, List<Integer> sorted) {
        if (tempMarked.contains(vertex)) {
            return false; // Найден цикл
        }
        if (!visited.contains(vertex)) {
            tempMarked.add(vertex);
            for (Integer neighbor : graph.getNeighbors(vertex)) {
                if (!dfsTopologicalSort(graph, neighbor, visited, tempMarked, sorted)) {
                    return false;
                }
            }
            tempMarked.remove(vertex);
            visited.add(vertex);
            sorted.add(vertex);
        }
        return true;
    }

    /**
     * Преобразование графа в строковое представление в формате:
     * Вершина-Соседи, упорядоченные по возрастанию.
     *
     * @param graph Граф, реализующий интерфейс Graph.
     * @return Строковое представление графа.
     */
    static String graphToString(Graph graph) {
        String result = "";

        List<Integer> vertices = new ArrayList<>(getAllVertices(graph));
        Collections.sort(vertices);

        for (Integer vertex : vertices) {
            result += vertex + "->";

            List<Integer> neighbors = graph.getNeighbors(vertex);
            Collections.sort(neighbors);

            result += neighbors.toString()
                .replace("[", "").replace("]", "").replace(", ", ";") + "\n";
        }

        return result;
    }


    /**
     * Получение всех вершин графа.
     *
     * @param graph Граф, реализующий интерфейс Graph.
     * @return Набор всех вершин.
     */
    private static List<Integer> getAllVertices(Graph graph) {
        List<Integer> vertices = new ArrayList<>();

        for (Integer vertex : graph.getNeighbors(null)) {
            vertices.add(vertex);
        }

        return vertices;
    }

    /**
     * Восстанавливает граф из строкового представления.
     *
     * @param graphString Строковое представление графа.
     * @param graph Граф, в который будет восстановлено представление.
     * @param <G> Тип графа.
     * @return Восстановленный граф.
     */
    static <G extends Graph> G fromString(String graphString, G graph) {
        String[] lines = graphString.split("\n");

        for (String line : lines) {
            String[] parts = line.split("->");
            Integer vertex = Integer.parseInt(parts[0]);
            graph.addVertex(vertex);

            if (parts.length > 1 && !parts[1].isEmpty()) {
                String[] neighbors = parts[1].split(";");
                for (String neighbor : neighbors) {
                    graph.addEdge(vertex, Integer.parseInt(neighbor));
                }
            }
        }

        return graph;
    }

    /**
     * Сравнивает на равенство два графа.
     *
     * @param gr1 граф1.
     * @param gr2 граф2.
     * @return равны или не равны (bool).
     * @param <G> Тип графа.
     */
    static <G extends Graph> boolean equals(G gr1, G gr2) {
        return graphToString(gr1).equals(graphToString(gr2));
    }
}

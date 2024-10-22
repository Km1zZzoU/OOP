package org.nsu.syspro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Класс для тестирования графов.
 */
public class Tests {

    private static final String TEST_FILE_PATH = "test.txt";

    private void assertEqualsWithToString(Object expected, Object actual) {
        assertEquals(expected.toString(), actual.toString(),
            "Строковое представление объектов не совпадает");
    }

    @Nested
    class ListGraphTests {

        @Test
        void testListGraphFromFile() throws IOException {
            ListGraph graph = new ListGraph();
            Graph.fromFile(TEST_FILE_PATH, graph);
            ListGraph expected = new ListGraph();
            assertTrue(Graph.equals(graph, Graph.fromString(
                "0->0;1;2\n1->2\n2->3;5\n3->0;3;5\n5->1;2\n", expected)));
        }

        @Test
        void testListGraphAddVertex() throws IOException {
            ListGraph graph = new ListGraph();
            Graph.fromFile(TEST_FILE_PATH, graph);
            graph.addVertex(6);
            ListGraph expected = new ListGraph();
            assertTrue(Graph.equals(graph, Graph.fromString(
                "0->0;1;2\n1->2\n2->3;5\n3->0;3;5\n5->1;2\n6->\n", expected)));
        }

        @Test
        void testListGraphRemoveVertex() throws IOException {
            ListGraph graph = new ListGraph();
            Graph.fromFile(TEST_FILE_PATH, graph);
            graph.removeVertex(1);
            graph.removeVertex(6);

            ListGraph expected = new ListGraph();
            assertTrue(Graph.equals(graph, Graph.fromString(
                "0->0;2\n2->3;5\n3->0;3;5\n5->2\n", expected)));
        }

        @Test
        void testListGraphAddEdge() throws IOException {
            ListGraph graph = new ListGraph();
            Graph.fromFile(TEST_FILE_PATH, graph);
            graph.addEdge(1, 3);
            graph.addEdge(2, 0);
            graph.addEdge(7, 8);
            graph.addEdge(8, 2);
            ListGraph expected = new ListGraph();
            assertTrue(Graph.equals(graph, Graph.fromString(
                "0->0;1;2\n1->2;3\n2->0;3;5\n3->0;3;5\n5->1;2\n7->8\n8->2\n", expected)));
        }

        @Test
        void testListGraphRemoveEdge() throws IOException {
            ListGraph graph = new ListGraph();
            Graph.fromFile(TEST_FILE_PATH, graph);
            graph.removeEdge(2, 5);
            graph.removeEdge(3, 3);
            graph.removeEdge(8, 9);
            ListGraph expected = new ListGraph();
            assertTrue(Graph.equals(graph, Graph.fromString(
                "0->0;1;2\n1->2\n2->3\n3->0;5\n5->1;2\n", expected)));
        }

        @Test
        void testListTopSort() {
            ListGraph graph = new ListGraph();
            //2->5->7->1->6->3->4
            graph.addEdge(2, 7);
            graph.addEdge(5, 3);
            graph.addEdge(1, 4);
            graph.addEdge(1, 6);
            graph.addEdge(6, 3);
            graph.addEdge(3, 4);
            graph.addEdge(5, 7);
            graph.addEdge(7, 1);
            graph.addEdge(2, 5);
            assertEqualsWithToString("[2, 5, 7, 1, 6, 3, 4]",
                Graph.topologicalSort(graph));
            graph.addEdge(4, 2);
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Graph.topologicalSort(graph));
            assertEquals("Граф содержит цикл", exception.getMessage());
        }
    }

    @Nested
    class IncidenceGraphTests {

        @Test
        void testIncidenceGraphFromFile() throws IOException {
            IncidenceGraph graph = new IncidenceGraph();
            Graph.fromFile(TEST_FILE_PATH, graph);
            IncidenceGraph expected = new IncidenceGraph();
            assertTrue(Graph.equals(graph, Graph.fromString(
                "0->0;1;2\n1->2\n2->3;5\n3->0;3;5\n5->1;2\n", expected)));
        }

        @Test
        void testIncidenceGraphAddVertex() throws IOException {
            IncidenceGraph graph = new IncidenceGraph();
            Graph.fromFile(TEST_FILE_PATH, graph);
            graph.addVertex(6);
            IncidenceGraph expected = new IncidenceGraph();
            assertTrue(Graph.equals(graph, Graph.fromString(
                "0->0;1;2\n1->2\n2->3;5\n3->0;3;5\n5->1;2\n6->\n", expected)));
        }

        @Test
        void testIncidenceGraphRemoveVertex() throws IOException {
            IncidenceGraph graph = new IncidenceGraph();
            Graph.fromFile(TEST_FILE_PATH, graph);
            graph.removeVertex(1);
            graph.removeVertex(6);
            IncidenceGraph expected = new IncidenceGraph();
            assertTrue(Graph.equals(graph, Graph.fromString(
                "0->0;2\n2->3;5\n3->0;3;5\n5->2\n", expected)));
        }

        @Test
        void testIncidenceGraphAddEdge() throws IOException {
            IncidenceGraph graph = new IncidenceGraph();
            Graph.fromFile(TEST_FILE_PATH, graph);
            graph.addEdge(1, 3);
            graph.addEdge(2, 0);
            graph.addEdge(7, 8);
            graph.addEdge(8, 2);
            IncidenceGraph expected = new IncidenceGraph();
            assertTrue(Graph.equals(graph, Graph.fromString(
                "0->0;1;2\n1->2;3\n2->0;3;5\n3->0;3;5\n5->1;2\n7->8\n8->2\n", expected)));
        }

        @Test
        void testIncidenceGraphRemoveEdge() throws IOException {
            IncidenceGraph graph = new IncidenceGraph();
            Graph.fromFile(TEST_FILE_PATH, graph);
            graph.removeEdge(2, 5);
            graph.removeEdge(3, 3);
            graph.removeEdge(8, 9);
            IncidenceGraph expected = new IncidenceGraph();
            assertTrue(Graph.equals(graph, Graph.fromString(
                "0->0;1;2\n1->2\n2->3\n3->0;5\n5->1;2\n", expected)));
        }

        @Test
        void testIncidenceTopSort() {
            IncidenceGraph graph = new IncidenceGraph();
            //2->5->7->1->6->3->4
            graph.addEdge(2, 7);
            graph.addEdge(5, 3);
            graph.addEdge(1, 4);
            graph.addEdge(1, 6);
            graph.addEdge(6, 3);
            graph.addEdge(3, 4);
            graph.addEdge(5, 7);
            graph.addEdge(7, 1);
            graph.addEdge(2, 5);
            assertEqualsWithToString("[2, 5, 7, 1, 6, 3, 4]",
                Graph.topologicalSort(graph));
            graph.addEdge(4, 2);
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Graph.topologicalSort(graph));
            assertEquals("Граф содержит цикл", exception.getMessage());
        }
    }


    @Nested
    class MatrixGraphTests {

        @Test
        void testMatrixGraphFromFile() throws IOException {
            MatrixGraph graph = new MatrixGraph();
            Graph.fromFile(TEST_FILE_PATH, graph);
            MatrixGraph expected = new MatrixGraph();
            assertTrue(Graph.equals(graph, Graph.fromString(
                "0->0;1;2\n1->2\n2->3;5\n3->0;3;5\n5->1;2\n", expected)));
        }

        @Test
        void testMatrixGraphAddVertex() throws IOException {
            MatrixGraph graph = new MatrixGraph();
            Graph.fromFile(TEST_FILE_PATH, graph);
            graph.addVertex(6);
            MatrixGraph expected = new MatrixGraph();
            assertTrue(Graph.equals(graph, Graph.fromString(
                "0->0;1;2\n1->2\n2->3;5\n3->0;3;5\n5->1;2\n6->\n", expected)));
        }

        @Test
        void testMatrixGraphRemoveVertex() throws IOException {
            MatrixGraph graph = new MatrixGraph();
            Graph.fromFile(TEST_FILE_PATH, graph);
            graph.removeVertex(1);
            graph.removeVertex(6);
            MatrixGraph expected = new MatrixGraph();
            assertTrue(Graph.equals(graph, Graph.fromString(
                "0->0;2\n2->3;5\n3->0;3;5\n5->2\n", expected)));
        }

        @Test
        void testMatrixGraphRemoveVertex2() throws IOException {
            MatrixGraph graph = new MatrixGraph();
            Graph.fromFile(TEST_FILE_PATH, graph);
            graph.removeVertex(0);
            graph.removeVertex(1);
            graph.removeVertex(2);
            graph.removeVertex(3);
            graph.removeVertex(4);
            graph.removeVertex(5);
            graph.addEdge(1,2);
            MatrixGraph expected = new MatrixGraph();
            assertTrue(Graph.equals(graph, Graph.fromString("1->2\n2->\n", expected)));
        }

        @Test
        void testMatrixGraphAddEdge() throws IOException {
            MatrixGraph graph = new MatrixGraph();
            Graph.fromFile(TEST_FILE_PATH, graph);
            graph.addEdge(1, 3);
            graph.addEdge(2, 0);
            graph.addEdge(7, 8);
            graph.addEdge(8, 2);

            MatrixGraph expected = new MatrixGraph();
            assertTrue(Graph.equals(graph, Graph.fromString(
                "0->0;1;2\n1->2;3\n2->0;3;5\n3->0;3;5\n5->1;2\n7->8\n8->2\n", expected)));
        }

        @Test
        void testMatrixGraphRemoveEdge() throws IOException {
            MatrixGraph graph = new MatrixGraph();
            Graph.fromFile(TEST_FILE_PATH, graph);
            graph.removeEdge(2, 5);
            graph.removeEdge(3, 3);
            graph.removeEdge(8, 9);
            MatrixGraph expected = new MatrixGraph();
            assertTrue(Graph.equals(graph, Graph.fromString(
                "0->0;1;2\n1->2\n2->3\n3->0;5\n5->1;2\n", expected)));
        }

        @Test
        void testMatrixTopSort() {
            MatrixGraph graph = new MatrixGraph();
            //2->5->7->1->6->3->4
            graph.addEdge(2, 7);
            graph.addEdge(5, 3);
            graph.addEdge(1, 4);
            graph.addEdge(1, 6);
            graph.addEdge(6, 3);
            graph.addEdge(3, 4);
            graph.addEdge(5, 7);
            graph.addEdge(7, 1);
            graph.addEdge(2, 5);
            assertEqualsWithToString("[2, 5, 7, 1, 6, 3, 4]", Graph.topologicalSort(graph));
            graph.addEdge(4, 2);
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Graph.topologicalSort(graph));
            assertEquals("Граф содержит цикл", exception.getMessage());
        }
    }
}

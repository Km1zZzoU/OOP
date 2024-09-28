package org.nsu.syspro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Класс для тестирования математических выражений.
 */
public class Tests {

    /**
     * Функция для сравнения объектов по их строковому представлению.
     */
    private void assertEqualsWithToString(Object expected, Object actual) {
        assertEquals(expected.toString(), actual.toString(),
            "Строковое представление объектов не совпадает");
    }

    @Nested
    class NumberTests {
        @Test
        void testNumber() {
            Number num = new Number(5);
            assertEquals(5, num.solve());
            assertEqualsWithToString("5", num);
        }

        @Test
        void testNumberDerivative() {
            Number num = new Number(5);
            assertEqualsWithToString(new Number(0), num.derivative("x"));
        }
    }

    @Nested
    class VariableTests {
        @Test
        void testVariable() {
            Variable x = new Variable("x");
            x.setValue(10);
            assertEquals(10, x.solve());
        }

        @Test
        void testVariableDerivative() {
            Variable x = new Variable("x");
            assertEqualsWithToString(new Number(1), x.derivative("x"));
            assertEqualsWithToString(new Number(0), x.derivative("y"));
        }

        @Test
        void testUninitializedVariable() {
            Variable y = new Variable("y");
            assertThrows(IllegalStateException.class, y::solve);
        }
    }

    @Nested
    class AddTests {
        @Test
        void testAddition() {
            Expression expr = new Add(new Number(3), new Number(5));
            assertEquals(8, expr.solve());
        }

        @Test
        void testAdditionDerivative() {
            Expression expr = new Add(new Number(3), new Variable("x"));
            assertEqualsWithToString(new Add(new Number(0), new Number(1)), expr.derivative("x"));
        }
    }

    @Nested
    class SubTests {
        @Test
        void testSubtraction() {
            Expression expr = new Sub(new Number(10), new Number(4));
            assertEquals(6, expr.solve());
        }

        @Test
        void testSubtractionDerivative() {
            Expression expr = new Sub(new Variable("x"), new Number(2));
            assertEqualsWithToString(new Sub(new Number(1), new Number(0)), expr.derivative("x"));
        }
    }

    @Nested
    class MulTests {
        @Test
        void testMultiplication() {
            Expression expr = new Mul(new Number(7), new Number(6));
            assertEquals(42, expr.solve());
        }

        @Test
        void testMultiplicationDerivative() {
            Expression expr = new Mul(new Number(3), new Variable("x"));
            assertEqualsWithToString(new Add(new Mul(new Number(0), new Variable("x")),
                new Mul(new Number(3), new Number(1))), expr.derivative("x"));
        }
    }

    @Nested
    class DivTests {
        @Test
        void testDivision() {
            Expression expr = new Div(new Number(12), new Number(4));
            assertEquals(3, expr.solve());
        }

        @Test
        void testDivisionByZero() {
            try {
                Expression expr = new Div(new Number(5), new Number(0));
                Assertions.assertNull(expr);
            } catch (AssertionError e) {}
        }
    }

    @Nested
    class ComplexExpressionTests {
        @Test
        void testComplexExpression() {
            Expression expr = new Mul(
                new Add(new Number(3), new Number(2)),
                new Sub(new Number(4), new Number(1))
            );
            assertEquals(15, expr.solve());
        }

        @Test
        void testToString() {
            Expression expr = new Mul(new Number(3), new Variable("x"));
            assertEqualsWithToString("(3*x)", expr);
        }
    }
}

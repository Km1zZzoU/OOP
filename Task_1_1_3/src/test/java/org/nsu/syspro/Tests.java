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
    class ParserTests {
        /**
         * Функция для сравнения объектов по их строковому представлению.
         */
        private void assertEqualsWithToString(Object expected, Object actual) {
            assertEquals(expected.toString(), actual.toString(),
                "Строковое представление объектов не совпадает");
        }

        @Nested
        class SimpleExpressionTests {
            @Test
            void testSingleNumber() {
                Expression expr = Expression.parser("5");
                assertEqualsWithToString(new Number(5), expr);
            }

            @Test
            void testSingleVariable() {
                Expression expr = Expression.parser("x");
                assertEqualsWithToString(new Variable("x"), expr);
            }
        }

        @Nested
        class AdditionTests {
            @Test
            void testSimpleAddition() {
                Expression expr = Expression.parser("(1+2)");
                assertEqualsWithToString(new Add(new Number(1), new Number(2)), expr);
            }

            @Test
            void testAdditionWithVariable() {
                Expression expr = Expression.parser("(x+3)");
                assertEqualsWithToString(new Add(new Variable("x"), new Number(3)), expr);
            }
        }

        @Nested
        class SubtractionTests {
            @Test
            void testSimpleSubtraction() {
                Expression expr = Expression.parser("(10-5)");
                assertEqualsWithToString(new Sub(new Number(10), new Number(5)), expr);
            }

            @Test
            void testSubtractionWithVariable() {
                Expression expr = Expression.parser("(y-7)");
                assertEqualsWithToString(new Sub(new Variable("y"), new Number(7)), expr);
            }
        }

        @Nested
        class MultiplicationTests {
            @Test
            void testSimpleMultiplication() {
                Expression expr = Expression.parser("(2*3)");
                assertEqualsWithToString(new Mul(new Number(2), new Number(3)), expr);
            }

            @Test
            void testMultiplicationWithVariable() {
                Expression expr = Expression.parser("(x*4)");
                assertEqualsWithToString(new Mul(new Variable("x"), new Number(4)), expr);
            }
        }

        @Nested
        class DivisionTests {
            @Test
            void testSimpleDivision() {
                Expression expr = Expression.parser("(8/2)");
                assertEqualsWithToString(new Div(new Number(8), new Number(2)), expr);
            }

            @Test
            void testDivisionWithVariable() {
                Expression expr = Expression.parser("(z/3)");
                assertEqualsWithToString(new Div(new Variable("z"), new Number(3)), expr);
            }
        }

        @Nested
        class ComplexExpressionTests {
            @Test
            void testComplexExpression() {
                Expression expr = Expression.parser("(1+(2*3))");
                assertEqualsWithToString(new Add(new Number(1), new Mul(new Number(2), new Number(3))), expr);
            }

            @Test
            void testNestedExpression() {
                Expression expr = Expression.parser("((x+4)*(y-2))");
                assertEqualsWithToString(new Mul(new Add(new Variable("x"), new Number(4)), new Sub(new Variable("y"), new Number(2))), expr);
            }
        }
    }


    @Nested
    class NumberTests {
        @Test
        void testNumber() {
            Number num = new Number(5);
            assertEquals(5, num.eval(""));
            assertEqualsWithToString("5", num);
        }

        @Test
        void testNumberDerivative() {
            Number num = new Number(5);
            assertEqualsWithToString(new Number(0), num.derivative("x"));
        }

        @Test
        void testToString() {
            Number num = new Number(3.14);
            assertEquals("3.14", num.toString());
        }
    }

    @Nested
    class VariableTests {

        @Test
        void testVariableDerivative() {
            Variable x = new Variable("x");
            assertEqualsWithToString(new Number(1), x.derivative("x"));
            assertEqualsWithToString(new Number(0), x.derivative("y"));
        }

        @Test
        void testEval() {
            Variable var = new Variable("x");
            assertEquals(4, var.eval("y = 3; x = 4; z = 5"));
        }

        @Test
        void testThrowEval() {
            Variable var = new Variable("w");
            assertThrows(IllegalStateException.class, () -> var.eval("y = 3; x = 4; z = 5"));
        }
    }

    @Nested
    class AddTests {
        @Test
        void testAddition() {
            Expression expr = new Add(new Number(3), new Number(5));
            assertEquals(8, expr.eval(""));
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
            assertEquals(6, expr.eval(""));
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
            assertEquals(42, expr.eval(""));
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
            assertEquals(3, expr.eval(""));
        }

        @Test
        void testDivisionByZero() {
            Expression expr = new Div(new Number(5), new Number(0));
            assertThrows(ArithmeticException.class, () -> expr.eval("x = 5"));
        }


        @Test
        void testComplexDivisionDerivative() {
            Expression expr = new Div(new Number(3), new Variable("x"));
            assertEqualsWithToString(expr.derivative("x").toString(), "(((0*x)-(3*1))/(x*x))");
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
            assertEquals(15, expr.eval(""));
        }

        @Test
        void testToString() {
            Expression expr = new Mul(new Number(3), new Variable("x"));
            assertEqualsWithToString("(3*x)", expr);
        }
    }
}

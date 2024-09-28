package org.nsu.syspro;

/**
 * Класс для представления числового значения в выражении.
 */
public class Number extends Expression {
    private final double value;

    /**
     * Создает числовое выражение с заданным значением.
     *
     * @param value Число, представляющее значение выражения.
     */
    public Number(double value) {
        this.value = value;
    }

    /**
     * Возвращает производную числа, которая всегда равна 0.
     *
     * @param s Имя переменной (игнорируется).
     * @return Новое выражение, представляющее число 0.
     */
    @Override
    public Expression derivative(String s) {
        return new Number(0);
    }

    /**
     * Возвращает числовое значение выражения.
     *
     * @param s Строка с переменными (игнорируется).
     * @return Значение числа.
     */
    @Override
    public Double eval(String s) {
        return value;
    }

    /**
     * Возвращает строковое представление числа.
     * Если число целое, оно выводится без десятичных знаков.
     *
     * @return Строка с числом.
     */
    @Override
    public String toString() {
        if (value == (long) value) {
            return String.format("%d", (long) value);
        } else {
            return Double.toString(value);
        }
    }

    /**
     * Возвращает значение числа.
     *
     * @return Числовое значение.
     */
    @Override
    public Double solve() {
        return value;
    }
}

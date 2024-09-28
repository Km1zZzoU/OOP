package org.nsu.syspro;

import java.util.Objects;

/**
 * Класс для представления переменной в выражении.
 */
public class Variable extends Expression {
    private final String name;
    private Double value;

    /**
     * Создает переменную с заданным именем.
     *
     * @param name Имя переменной.
     */
    public Variable(String name) {
        this.name = name;
        this.value = null;
    }

    /**
     * Устанавливает значение переменной.
     *
     * @param value Значение переменной.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Вычисляет значение переменной, подставляя ее значение из строки.
     *
     * @param s Строка с переменными и их значениями в формате "x = 10; y = 5".
     * @return Значение переменной или null, если значение не найдено.
     */
    @Override
    public Double eval(String s) {
        String[] equals = s.split("; ");
        for (String equal : equals) {
            String[] morfems = equal.split(" = ");
            if (Objects.equals(morfems[0], name)) {
                setValue(Double.parseDouble(morfems[1]));
                return value;
            }
        }
        return null;
    }

    /**
     * Возвращает производную переменной.
     * Если имя переменной совпадает с заданным, возвращает 1, иначе — 0.
     *
     * @param s Имя переменной.
     * @return Производная переменной.
     */
    @Override
    public Expression derivative(String s) {
        if (Objects.equals(name, s)) {
            return new Number(1);
        }
        return new Number(0);
    }

    /**
     * Возвращает имя переменной.
     *
     * @return Строковое представление переменной.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Возвращает значение переменной.
     *
     * @return Значение переменной.
     * @throws IllegalStateException если значение переменной не установлено.
     */
    @Override
    public Double solve() {
        if (value == null) {
            throw new IllegalStateException("Variable '" + name + "' is not initialized.");
        }
        return value;
    }
}

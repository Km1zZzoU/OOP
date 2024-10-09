package org.nsu.syspro;

/**
 * Класс для представления операции сложения двух выражений.
 */
public class Add extends Expression {
    private final Expression arg1;
    private final Expression arg2;

    /**
     * Создает выражение сложения двух аргументов.
     *
     * @param arg1 Первое выражение.
     * @param arg2 Второе выражение.
     */
    public Add(Expression arg1, Expression arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    /**
     * Вычисляет значение выражения, подставляя значения переменных.
     *
     * @param s Строка с переменными и их значениями.
     * @return Результат сложения.
     */
    @Override
    public Double eval(String s) {
        return arg1.eval(s) + arg2.eval(s);
    }

    /**
     * Вычисляет производную выражения по заданной переменной.
     *
     * @param s Имя переменной.
     * @return Производная выражения.
     */
    @Override
    public Expression derivative(String s) {
        return new Add(arg1.derivative(s), arg2.derivative(s));
    }

    /**
     * Возвращает строковое представление сложения.
     *
     * @return Строка вида "(arg1 + arg2)".
     */
    @Override
    public String toString() {
        return "(" + arg1.toString() + "+" + arg2.toString() + ")";
    }
}

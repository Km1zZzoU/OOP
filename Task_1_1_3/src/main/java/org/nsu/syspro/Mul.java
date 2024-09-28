package org.nsu.syspro;

/** Класс для представления операции умножения двух выражений.
 */
public class Mul extends Expression {
    private final Expression arg1;
    private final Expression arg2;

    /**
     * Создает выражение умножения двух аргументов.
     *
     * @param arg1 Первое выражение.
     * @param arg2 Второе выражение.
     */
    public Mul(Expression arg1, Expression arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    /**
     * Вычисляет значение выражения, подставляя значения переменных.
     *
     * @param s Строка с переменными и их значениями.
     * @return Результат умножения.
     */
    @Override
    public Double eval(String s) {
        return arg1.eval(s) * arg2.eval(s);
    }

    /**
     * Вычисляет производную выражения по заданной переменной.
     *
     * @param s Имя переменной.
     * @return Производная произведения (по правилу произведения).
     */
    @Override
    public Expression derivative(String s) {
        return new Add(new Mul(arg1.derivative(s), arg2), new Mul(arg1, arg2.derivative(s)));
    }

    /**
     * Возвращает строковое представление умножения.
     *
     * @return Строка вида "(arg1 * arg2)".
     */
    @Override
    public String toString() {
        return "(" + arg1.toString() + "*" + arg2.toString() + ")";
    }

    /**
     * Вычисляет результат умножения без переменных.
     *
     * @return Результат умножения.
     */
    @Override
    public Double solve() {
        return arg1.solve() * arg2.solve();
    }
}

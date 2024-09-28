package org.nsu.syspro;

/** Класс для представления операции деления двух выражений.
 */
public class Div extends Expression {
    private final Expression arg1;
    private final Expression arg2;

    /**
     * Создает выражение деления двух аргументов.
     *
     * @param arg1 Первое выражение (делимое).
     * @param arg2 Второе выражение (делитель).
     */
    public Div(Expression arg1, Expression arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    /**
     * Вычисляет значение выражения, подставляя значения переменных.
     *
     * @param s Строка с переменными и их значениями.
     * @return Результат деления.
     */
    @Override
    public Double eval(String s) {
        return arg1.eval(s) / arg2.eval(s);
    }

    /**
     * Вычисляет производную выражения по заданной переменной.
     * Применяется правило производной для частного.
     *
     * @param s Имя переменной.
     * @return Производная выражения.
     */
    @Override
    public Expression derivative(String s) {
        return new Div((new Mul(arg1, arg2)).derivative(s), new Mul(arg2, arg2));
    }

    /**
     * Возвращает строковое представление деления.
     *
     * @return Строка вида "(arg1 / arg2)".
     */
    @Override
    public String toString() {
        return "(" + arg1.toString() + "/" + arg2.toString() + ")";
    }

    /**
     * Вычисляет результат деления без переменных.
     *
     * @return Результат деления.
     */
    @Override
    public Double solve() {
        return arg1.solve() / arg2.solve();
    }
}

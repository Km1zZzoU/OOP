package org.nsu.syspro;
/**
 * Абстрактный класс для представления математических выражений.
 * Этот класс определяет базовые методы, которые должны быть реализованы
 * в конкретных производных классах для работы с выражениями.
 */
public abstract class Expression {

    public static Expression parser(String str) {
        boolean isNumber = false;
        String operators = "+-*/";
        int depth = 0;
        for (int i = 0; i < str.length(); i++) {
            if (!isNumber && 48 <= str.charAt(i) && str.charAt(i) < 58) {
                isNumber = true;
            }
            if (str.charAt(i) == '(') {
                depth++;
            }
            if (str.charAt(i) == ')') {
                depth--;
            }
            if (depth == 1 && operators.contains(Character.toString(str.charAt(i)))) {
                Expression e1 = parser(str.substring(1, i));
                Expression e2 = parser(str.substring(i + 1, str.length()-1));
                switch (str.charAt(i)) {
                    case '+':
                        return new Add(e1, e2);
                    case '-':
                        return new Sub(e1, e2);
                    case '*':
                        return new Mul(e1, e2);
                    case '/':
                        return new Div(e1, e2);
                }
            }
        }
        if (isNumber) {
            return new Number(Double.parseDouble(str));
        }
        return new Variable(str);
    }

    /**
     * Возвращает строковое представление выражения.
     * Используется для печати выражения в читаемом виде.
     * @return Строковое представление выражения.
     */
    @Override
    public abstract String toString();

    /**
     * Вычисляет производную выражения по заданной переменной.
     * @param s Имя переменной, по которой нужно вычислить производную.
     * @return Новое выражение, представляющее производную исходного.
     */
    public abstract Expression derivative(String s);

    /**
     * Вычисляет значение выражения, подставляя значение переменной.
     * @param s Строка, содержащая переменные и их значения в формате "x = 10", "y = 5".
     * @return Числовое значение выражения после подстановки значений переменных.
     */
    public abstract Double eval(String s);

    /**
     * Выводит строковое представление выражения в консоль.
     * Использует метод toString() для преобразования выражения в строку.
     */
    public void print() {
        System.out.println(this);
    }
}

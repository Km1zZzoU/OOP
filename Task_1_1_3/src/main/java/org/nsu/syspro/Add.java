package org.nsu.syspro;

public class Add extends Expression {
    private final Expression arg1;
    private final Expression arg2;

    public Add(Expression arg1, Expression arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Override
    public Double eval(String s) {
        return arg1.eval(s) + arg2.eval(s);
    }

    @Override
    public Expression derivative(String s) {
        return new Add(arg1.derivative(s), arg2.derivative(s));
    }

    @Override
    public String toString() {
        return "(" + arg1.toString() + "+" + arg2.toString() + ")";
    }

    @Override
    public Double solve() {
        return arg1.solve() + arg2.solve();
    }
}

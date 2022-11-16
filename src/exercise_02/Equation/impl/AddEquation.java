package exercise_02.Equation.impl;

import exercise_02.Equation.Equation;

public class AddEquation extends Equation {
    public AddEquation() {
        generateEquation('+');
    }

    @Override
    protected int calculate() {
        // TODO Auto-generated method stub
        return this.getLeft() + this.getRight();
    }
    @Override
    public int getOpIndex(String s) {
        return s.indexOf("+");
    }
}

package exercise_02.Equation.impl;

import exercise_02.Equation.Equation;

public class SubEquation extends Equation {
     public SubEquation() {
		 generateEquation('-');
	 }
	@Override
	protected int calculate() {
		return this.getLeft()-this.getRight();
	}
	@Override
	public int getOpIndex(String s) {
		return s.indexOf("-");
	}
}

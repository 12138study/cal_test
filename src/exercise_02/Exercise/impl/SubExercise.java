package exercise_02.Exercise.impl;


import exercise_02.Equation.Equation;
import exercise_02.Exercise.Exercise;
import exercise_02.Equation.impl.SubEquation;

public class SubExercise extends Exercise {
    public SubExercise() {
    }

    public SubExercise(int count) {
        super(count);
    }

    @Override
    public Equation getEquation(String op) throws Exception {
        return new SubEquation();
    }
}

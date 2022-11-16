package exercise_02.Exercise.impl;

import exercise_02.Equation.Equation;
import exercise_02.Equation.impl.AddEquation;
import exercise_02.Exercise.Exercise;

public class AddExercise extends Exercise {
    public AddExercise() {

    }
    public AddExercise(int count) {
        super(count);
    }
    @Override
    public Equation getEquation(String op) throws Exception {
        return new AddEquation();
    }
}

package exercise_02.Exercise;

import exercise_02.Equation.Equation;
import exercise_02.EquationFactory;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExerciseFileDAO {
    char[] op = new char[]{'+','-','*','/'};
    public void writeExerciseToFile(String fileName, Exercise exercise) {
        int index = exercise.getIndex();
        exercise.reset();
         BufferedWriter br = null;
        try{
             br = new BufferedWriter(new FileWriter(fileName));
             while(exercise.hasNext()) {
                 br.write(exercise.next().toString()+"\n");
             }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        exercise.setIndex(index);
    }
    public Exercise readExerciseFromFile(String fileName,EquationFactory equationFactory) {
        Pattern pattern = Pattern.compile("[\\+\\-\\*/]");

        BufferedReader br = null;
        Exercise exercise = Exercise.newIntense();
        try {
            String str = null;
            br = new BufferedReader(new FileReader(fileName));
            while ((str = br.readLine())!=null) {
                Matcher matcher = pattern.matcher(str);
                if(matcher.find()) {
                    char op = matcher.group(0).charAt(0);
                    Equation object = equationFactory.getObject(op).createClone();
                    Equation equation = object.getEquation(str, op);
                    exercise.add(equation);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return exercise;
    }
}

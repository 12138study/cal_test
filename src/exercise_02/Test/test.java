package exercise_02.Test;

import exercise_02.Check;
import exercise_02.Answer;
import exercise_02.Equation.impl.AddEquation;
import exercise_02.Equation.impl.SubEquation;
import exercise_02.EquationFactory;
import exercise_02.Exercise.Exercise;
import exercise_02.Exercise.ExerciseFileDAO;
import exercise_02.Exercise.impl.SubExercise;
import org.junit.Test;

public class test {
    @Test
    public void FileReadAndWriteTest() {
        Exercise ex = Exercise.newIntense();
        ex.generateExercise();
//        exercise_02.Answer answer = new AnswerImpl(ex);
//        answer.writeAnswerToFile("input.txt");
    }

    @Test
    public void ReadFromFileTest() {
        ExerciseFileDAO exerciseFileDAO = new ExerciseFileDAO();
        EquationFactory equationFactory = new EquationFactory();
        equationFactory.register('-', new SubEquation());
        equationFactory.register('+', new AddEquation());
        Exercise exercise = exerciseFileDAO.readExerciseFromFile("input.txt",equationFactory);
        exercise.getExercise().forEach(System.out::println);
    }

    @Test
    public void AnswerTest() {
        Answer answer = new Answer();
        Exercise exercise = Exercise.newIntense();
        exercise.generateExercise();
        while(exercise.hasNext()) {
            String str = exercise.next().toString();
            str = str.substring(0, str.indexOf('=')+1);
            System.out.println( str);
            answer.scanAndWriteFromKeyboard();
        }
    }

    public static void main(String[] args) {
        Answer answer = new Answer();
        Exercise exercise = Exercise.newIntense(3);
        exercise.generateExercise();
        while (exercise.hasNext()) {
            String str = exercise.next().toString();
            str = str.substring(0, str.indexOf('=') + 1);
            System.out.println(str);
            answer.scanAndWriteFromKeyboard();
        }
        ExerciseFileDAO exerciseFileDAO = new ExerciseFileDAO();
        exerciseFileDAO.writeExerciseToFile("input.txt", exercise);
        exercise.reset();
        Check check = new Check();
        try {
            check.check(exercise,answer);
            check.writeCheckToFile("input.txt");
            check.printCheck();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void getNameTest() {
        Exercise subExercise = new SubExercise();
        String simpleName = subExercise.getClass().getSimpleName();
        System.out.println("simpleName = " + simpleName);
    }
    @Test
    public void generateExerciseTest(){
        Exercise exercise0 = Exercise.newIntense();
        try {
            exercise0.generateExercise("-");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("hhhhhhhh");
    }
}

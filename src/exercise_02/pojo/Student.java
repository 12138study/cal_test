package exercise_02.pojo;

import java.util.ArrayList;

import exercise_02.Equation.Equation;
import exercise_02.Exercise.Exercise;
import org.junit.Test;

public class Student {
private static Exercise exercise = null;
	public static void main(String[] args) {
		exercise = Exercise.newIntense();
		exercise.generateExercise();
		exercise.printExercise();
		
	}
	public static void printExercise() {
		int i = 0;
		ArrayList<Equation> es = exercise.getExercise();
		for(Equation e:es) {
			i++;
			String str = "("+i+")"+e.toString();
			str = String.format("%-16s", str);
			System.out.print(str);
			if(i%5 == 0) {
				System.out.println();
			}
		}
	}
	@Test
	public void test() {
		exercise = Exercise.newIntense();
		exercise.printExercise();
	}
	
}

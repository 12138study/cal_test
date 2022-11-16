package exercise_02.Exercise;
import exercise_02.Equation.Equation;
import exercise_02.Equation.impl.AddEquation;
import exercise_02.Equation.impl.SubEquation;
import exercise_02.ExerciseFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
public abstract class Exercise implements Cloneable{
	private int count = 50;
	private int index = 0;
	private ArrayList<Equation> exercise = null;
	public static Exercise newIntense() {
		return new Exercise() {
			@Override
			public Equation getEquation(String op) throws Exception {
				throw new RuntimeException("当前对象无法生成指定算式习题");
			}
		};
	}
	public static Exercise newIntense(int count) {
		return new Exercise(count) {
			@Override
			public Equation getEquation(String op) throws Exception {
				throw new RuntimeException("��ǰ�����޷�����������ʽ");
			}
		};
	}
	public Exercise() {
	   exercise = new ArrayList<Equation>();	
	}
	//�������ɵ���ʽ����
	public Exercise(int count) {
		this.setCount(count);
		exercise = new ArrayList<Equation>();
	}
	public int getCount() {
		return count;
	}
	public void setCount(int conut) {
		this.count = conut;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public ArrayList<Equation> getExercise() {
		return exercise;
	}
	public void setExercise(ArrayList<Equation> exercise) {
		this.exercise = exercise;
	}

	//�ж���ʽ�ڵ�ǰϰ�����Ƿ����
	public boolean occursIn(Equation e) {
		if(e == null) {
			throw new RuntimeException("��ʱ��ʽΪ��");
		}
		for(Equation es : exercise) {
			if(es.isEqual(e)) {
				return true;
			}
		}
		return false;
	}
	//����ϰ��
	public void generateExercise() {
		Random r = new Random();
		int i = 0;
		while(i<count) {
			Equation e;
			if(r.nextInt(2)==1) {
				e = new AddEquation();
				e.setOp('+');
			}else {
				e = new SubEquation();
				e.setOp('-');
			}
			if(!occursIn(e)) {
				exercise.add(e);
				i++;
			}
		}
	}
	//����ָ�����͵�ϰ��
	public void generateExercise(String op) throws Exception{
		int i = 0;
		while( i<count ) {
			Equation e = null;
			try {
				e = getEquation(op);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
			if( !occursIn(e) ) {
				exercise.add(e);
				i++;
			}
		}
	}
	//�õ���Ҫ��ָ������������ʽ
	public abstract Equation getEquation(String op) throws Exception;
//��ӡϰ��
	public void printExercise() {
		int i = 0;
		for(Equation e:exercise) {
			i++;
			String str = "("+i+")"+e.toString1();
			str = String.format("%-16s", str);
			System.out.print(str);
			if(i%5 == 0) {
				System.out.println();
			}
		}
	}

	//�ж���һ����ʽ�Ƿ����
	public boolean hasNext() {
		return index<exercise.size();
	}
	//��ȡ��һ����ʽ
	public Equation next() {
		if(hasNext()) {
			return exercise.get(index++);
		}
		return null;
	}
	//��ϰ���������ʽ
	public boolean add(Equation e) {
		if(index<count) {
			exercise.add(e);
			index++;
			return true;
		}
		return false;
		
	}

	public void reset() {
		index = 0;
	}

	public Exercise createClone() {
		 Exercise e = null;
		try {
			e = (Exercise) clone();
		} catch (CloneNotSupportedException ex) {
			throw new RuntimeException(ex);
		}
		return e;
	}
	public void clear() {
		exercise.clear();
	}
}

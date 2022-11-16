package exercise_02.Equation;

import java.util.Random;

public abstract class Equation implements Cloneable{
	private int min=0,max=100;
	private int left;
	private int right;
	private char op;
	private int result;

	public Equation() {
	}

	public Equation(int left, int right, char op, int result) {
		super();
		this.left = left;
		this.right = right;
		this.op = op;
		this.result = result;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public char getOp() {
		return op;
	}

	public void setOp(char op) {
		this.op = op;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "" + left + op + right + "=" + result;
	}
    public String toString1() {
		return "" + left + op + right + "=";
	}
	// ���������
	private int generateRandom() {
		return new Random().nextInt(max - min + 1) + min;
	}

	// �ж����Ƿ���ָ����Χ��
	private boolean isBetween(int min,int max,int value) {
		return value < max && value > min;
	}

	// ��������㷽��
	protected abstract int calculate();

	// �����ʽ�Ƿ����
	public boolean isEqual(Equation e) {
		if (e == null) {
			throw new RuntimeException("�Ƚϵ���ʽΪ��");
		}
		if (String.valueOf((e.getOp())).equals(String.valueOf(this.getOp()))) {
			if ((e.getLeft() == this.getLeft() && e.getRight() == this.getRight())
					|| (e.getLeft() == this.getRight() && e.getRight() == this.getLeft())) {
				return true;
			}
		}
		return false;
	}
//	public exercise_02.Equation StringToEquation(String str) {
//		int index = getOpIndex(str);
//		
//	}
	protected abstract int getOpIndex(String str);

	//������ʽ
	public Equation generateEquation(char op) {
		// TODO Auto-generated method stub
		do {
			left = generateRandom();
			right = generateRandom();
			result = calculate();
	   if(isBetween(min,max,result)) {
		   setResult(result);
		   setOp(op);
		return this;
	   }
		}while(true);
	}
	//Ϊ���ļ��ж�ȡ��ʽ��ת��
	public Equation getEquation(String eq, char op) {
		int index = eq.indexOf(op);
		int length = eq.length();
		this.setLeft(Integer.parseInt(eq.substring(0, index)));
		this.setOp(op);
		this.setRight(Integer.parseInt(eq.substring(index+1,eq.indexOf('='))));
		this.setResult(calculate());
		return this;
	}
	//�ṩ��¡�ķ���
	public Equation createClone() {
		 Equation e = null;
		try {
			e = (Equation) clone();
		} catch (CloneNotSupportedException ex) {
			throw new RuntimeException(ex);
		}
		return e;
	}
}

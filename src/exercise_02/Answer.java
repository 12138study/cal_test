package exercise_02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class Answer {
    private ArrayList<Integer> answer = new ArrayList<>();
    private int index = 0;
    //写答案到文件中
    public void writeAnswerToFile(String fileName) {
        File exFile=new File(fileName);
        Writer out=null;
        try {
            out=new FileWriter(exFile);
            for(Integer i:answer) {
                out.write(i+",");
            }
            out.flush();
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //从文件中读取答案
    public void readAnswerFromFile(String fileName) {
        File exFile=new File(fileName);
        String anS;
        Scanner in=null;
        answer.clear();
        try {
            in=new Scanner(exFile);
            in.useDelimiter(",");
            while(in.hasNext()) {
                anS=in.next().replaceAll("\\s", "");
                answer.add(Integer.parseInt(anS));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            in.close();
        }
    }
    //键盘输入答案并存储
    public void scanAndWriteFromKeyboard() {
        Scanner scan = new Scanner(System.in);
       while (!scan.hasNextInt()){
           System.out.println("非法输入，请重新输入");
           scan.nextLine();
        }
         int i = scan.nextInt();
            answer.add(i);
        scan.nextLine();
    }
    //是否存在下一个答案
    public boolean hasNext() {
        return index < answer.size();
    }
    //获取答案
    public Integer next() throws Exception {
        if(hasNext()) {
            return answer.get(index++);
        }
        throw new Exception("没有下一个答案了");
    }
    //重置index
    public void reset() {
        index = 0;
    }
    //向answer中添加元素
    public void add(Integer element) {
        answer.add(element);
    }

    public void setAnswer(ArrayList<Integer> answer) {
        this.answer = answer;
    }
    public ArrayList<Integer> getAnswer() {
        return answer;
    }

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}

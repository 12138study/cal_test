package exercise_02.Test;

import exercise_02.*;
import exercise_02.Equation.impl.AddEquation;
import exercise_02.Equation.impl.SubEquation;
import exercise_02.Exercise.Exercise;
import exercise_02.Exercise.ExerciseFileDAO;
import exercise_02.Exercise.impl.AddExercise;
import exercise_02.Exercise.impl.SubExercise;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static Exercise exercise = null;
    private ArrayList<String> menu1 = new ArrayList<>();
    private String[][] menu2 = new String[][]{
            {"产生减法习题", "产生加法习题", "产生混合习题", "返回上层"},//批量与随机产生习题二级菜单均相同
            {"加载减法习题", "加载加法习题", "加载混合习题", "返回上层"}
    };
    private static ExerciseFactory exerciseFactory = null;
    private static MenuFactory menuFactory = null;
    private static HashMap<Integer, String> map = new HashMap<>();
    private static EquationFactory equationFactory = null;
    //表驱动编程,表的初始化
    static {
        equationFactory = new EquationFactory();
        equationFactory.register('-', new SubEquation());
        equationFactory.register('+', new AddEquation());
        exerciseFactory = new ExerciseFactory();
        exerciseFactory.register("-", new SubExercise());
        exerciseFactory.register("+", new AddExercise());
        exerciseFactory.register("", Exercise.newIntense());
        menuFactory = new MenuFactory();
        for (int i = 0; i < 3; i++)
            menuFactory.register(i, exerciseFactory.getMap());
        map.put(0, "-");
        map.put(1, "+");
        map.put(2, "");
    }

    public Menu() {
        exercise = Exercise.newIntense();
        menu1.add("批量产生习题");
        menu1.add("加载习题");
        menu1.add("在线批改习题");
        menu1.add("退出程序");
    }

    public void Menu() {
        int option = -1;
        while (true) {
            System.out.println("欢迎使用100以内的口算练习程序");
            System.out.println("请选择想要的功能");
            border();
            for (String s : menu1) {
                System.out.println(menu1.indexOf(s) + ":" + s);
            }
            border();
            option = getOption();
            switch (option) {
                case 0:
                    action0();
                    break;
                case 1:
                    action1();
                    break;
                case 2:
                    action2();
                    break;
                case 3:
                    return;
            }
            border();
        }

    }

    private void action0() {
        while (true) {
            border();
            for (int i = 0; i < menu2[0].length; i++) {
                System.out.println(i + ":" + menu2[0][i]);
            }
            System.out.println("请输入对应的序号");
            int option = getOption();
            if (option == 3) return;
            HashMap<String, Exercise> obj = menuFactory.getObject(option);
            Exercise exercise = obj.get(map.get(option));
            common0(exercise, map.get(option));
            exercise.clear();
        }
    }
    private void action1() {
        while (true) {
            border();
            for (int i = 0; i < menu2[1].length; i++) {
                System.out.println(i + ":" + menu2[1][i]);
            }
           System.out.println("请输入对应的序号");
            int option = getOption();
            if (option == 3) return;
            HashMap<String, Exercise> obj = menuFactory.getObject(option);
            Exercise exercise = obj.get(map.get(option));
            common1(exercise, map.get(option));
        }
    }
    //批量在线习题操作
    private void action2() {
        while (true) {
            for (int i = 0; i < menu2[0].length; i++) {
                System.out.println(i + ":" + menu2[0][i]);
            }
            border();
            System.out.println("请输入要生成的习题类型");
            int option = getOption();
            if (option == 3) return;
            HashMap<String, Exercise> obj = menuFactory.getObject(option);
            Exercise exercise = obj.get(map.get(option));
            common2(exercise, map.get(option));
            exercise.clear();
        }
    }
    //针对于批量产生习题的实际操作
    private void common0(Exercise exercise, String op) {
        ExerciseFileDAO file = new ExerciseFileDAO();
        Answer answer = new Answer();
        System.out.println("请输入要生成的习题个数");
        int num = getOption();
        String newfilename = getFileName(exercise, op, num);
        file.writeExerciseToFile(newfilename, exercise);
    }

    //加载存在的习题以供练习的实际操作
    private void common1(Exercise exercise, String s) {
        ExerciseFileDAO fileDAO = new ExerciseFileDAO();
        List<String> fileNameList = getFileName(s);
        System.out.println("请输入要加载的习题名:");
        System.out.println("若不想进行下去可以按3,退出当前加载环境");
        for(int i=0;i<fileNameList.size();i++){
            Scanner scanner = new Scanner(System.in);
            String s1 = scanner.next();
            if(s1.equals("3")) return;
            if(fileNameList.get(i).equals(s1)){
                load(s1);
                break;
            }else {
                System.out.println("输入的文件名未找到，请重新输入");
            }
        }

    }
    private void load(String filename) {
        ExerciseFileDAO fileDAO = new ExerciseFileDAO();
        Exercise exercise1 = fileDAO.readExerciseFromFile(filename,equationFactory);
        exercise1.printExercise();
         System.out.println("请依次输入答案");
        Answer answer = new Answer();
        for (int i = 0; i < exercise1.getExercise().size(); i++) {
            answer.scanAndWriteFromKeyboard();
        }
        filename = filename.substring(0,filename.indexOf(".")) + "Answer.txt";
        answer.writeAnswerToFile(filename);
        Check check = new Check();
        try {
            check.check(exercise, answer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        check.writeCheckToFile(filename);
        check.printCheck();
    }
    private List<String> getFileName(String op) {
        File file = new File("D:/Project/softwareStructure/exercise_02");
        String[] list = file.list();
        List<String> nameList = new ArrayList<>();
        for(String s : list) {
            if(s.endsWith(".txt")&&!op.equals("")){
                if(s.startsWith(exerciseFactory.getObject(op).getClass().getSimpleName().substring(0,3))) {
                    System.out.println(s);
                    nameList.add(s);
                }
            }else if(s.endsWith(".txt")&&s.startsWith("Exe")){
                System.out.println(s);
                nameList.add(s);
            }
        }
        return nameList;
    }

    //在线批量批改习题的实际操作
    private void common2(Exercise exercise, String op) {
        ExerciseFileDAO file = new ExerciseFileDAO();
        System.out.println("请输入要生成的习题个数");
        int num = getOption();
        String newfilename = getFileName(exercise, op, num);
        exercise.printExercise();
        file.writeExerciseToFile(newfilename, exercise);
        System.out.println("请依次输入答案");
        Answer answer = new Answer();
        for (int i = 0; i < num; i++) {
            answer.scanAndWriteFromKeyboard();
        }
        String fileName = newfilename.substring(0,newfilename.indexOf("."))+"Answer.txt";
        answer.writeAnswerToFile(fileName);
        Check check = new Check();
        try {
            check.check(exercise, answer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        check.writeCheckToFile(newfilename);
        check.printCheck();
    }

    public String getFileName(Exercise exercise, String op, int num) {
        String oldfilename = null;
        String newfilename = null;
        exercise.setCount(num);
        if (op != "") {
            try {
                exercise.generateExercise(op);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            oldfilename = exercise.getClass().getSimpleName() + ".txt";
        } else {
            exercise.generateExercise();
            oldfilename = "Exercise.txt";
        }
        File file1 = new File(oldfilename);
        newfilename = oldfilename;
        int i = 0;
        while (file1.exists()) {
            int pointindex = oldfilename.indexOf(".");
            newfilename = oldfilename.substring(0, pointindex) + (i++) + ".txt";
            file1 = new File(newfilename);
        }
        return newfilename;
    }

    private void border() {
        System.out.println("*******************************");
    }

    private int getOption() {
        Scanner scan = new Scanner(System.in);
        int option = 0;
        while (true) {
            if (scan.hasNextInt()) {
                if((option = scan.nextInt())<menu1.size())
                return option;
                else {
                    System.out.println("该序号无对应的操作，请重新选择");
                    scan.nextLine();
                }
            } else {
                System.out.println("输入了非法字符，请重新输入");
                scan.nextLine();
            }
        }
    }
}

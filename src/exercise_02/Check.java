package exercise_02;

import exercise_02.Exercise.Exercise;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Check {
    private int error = 0;
    private int success = 0;

    public void check(Exercise ex, Answer an) throws Exception {
        int exIndex = ex.getIndex();
        int anIndex = an.getIndex();
        ex.reset();
        an.reset();
        for(int i = 0; i<ex.getCount(); i++) {
            if(ex.hasNext()&&an.hasNext()){
                if(ex.next().getResult() == an.next()) {
                    success++;
                }else {
                    error++;
                }
            }
        }
        ex.setIndex(exIndex);
        an.setIndex(exIndex);
    }
    public void writeCheckToFile(String fileName) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(fileName,true));
            bw.write("total:"+(error+success)+"\n");
            bw.write("error:"+error+"\n");
            bw.write("success:"+success+"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void printCheck() {
        System.out.println("total:"+(error+success));
        System.out.println("error:"+error);
        System.out.println("success:"+success);
    }
}

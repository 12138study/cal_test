package exercise_02;

import exercise_02.Equation.Equation;

import java.util.HashMap;
import java.util.Map;

public class EquationFactory implements Factory<Character, Equation>{
    private static Map map = new HashMap();
    @Override
    public void register(Character key, Equation value){
        map.put(key,value);
    }
    @Override
    public Equation getObject(Character key){
        return (Equation) map.get(key);
    }
}

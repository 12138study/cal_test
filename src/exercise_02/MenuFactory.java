package exercise_02;

import exercise_02.Exercise.Exercise;

import java.util.HashMap;

public class MenuFactory implements Factory<Integer, HashMap<String, Exercise>>{
    private HashMap<Integer,HashMap<String ,Exercise>> map = new HashMap<>();
    @Override
    public void register(Integer key, HashMap<String, Exercise> value) {
        map.put(key,value);
    }

    @Override
    public HashMap<String, Exercise> getObject(Integer key) {
        return map.get(key);
    }
}

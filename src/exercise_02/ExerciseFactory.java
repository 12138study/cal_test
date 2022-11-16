package exercise_02;

import exercise_02.Exercise.Exercise;

import java.util.HashMap;

public class ExerciseFactory implements Factory<String, Exercise>{
    private HashMap<String ,Exercise> map = new HashMap<>();
    @Override
    public void register(String key, Exercise value) {
        map.put(key,value);
    }

    @Override
    public Exercise getObject(String key) {
        return map.get(key);
    }
    public HashMap<String,Exercise> getMap() {
        return map;
    }
}

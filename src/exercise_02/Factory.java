package exercise_02;

public interface Factory<E,T> {
    void register(E key, T value);
    T getObject(E key);
}

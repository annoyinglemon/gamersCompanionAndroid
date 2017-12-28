package lemond.annoying.gamerscompanion.repository.service;



public class DataState<T> {

    public enum State {
        LOADING,
        ERROR,
        EMPTY,
        NO_INTERNET,
        CONTENT
    }

    public T data;
    public State state;
    public int size;

}

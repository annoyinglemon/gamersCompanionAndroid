package lemond.annoying.gamerscompanion.repository.service;



public class DataWrapper<T> {

    public enum State {
        LOADING,
        ERROR,
        EMPTY,
        NO_INTERNET,
        CONTENT
    }

    public T data;
    public State state;

}

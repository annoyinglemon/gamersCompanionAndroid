package lemond.annoying.gamerscompanion.core.model;


import io.reactivex.Single;

public interface DataRepository<T> {

    Single<T> fetchData();

}

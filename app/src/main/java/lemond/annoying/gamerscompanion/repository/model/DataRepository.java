package lemond.annoying.gamerscompanion.repository.model;


import io.reactivex.Single;

public interface DataRepository<T> {

    Single<T> fetchData();

}

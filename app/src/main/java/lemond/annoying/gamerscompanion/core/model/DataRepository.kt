package lemond.annoying.gamerscompanion.core.model


import io.reactivex.Single

interface DataRepository<T> {

    fun fetchData(): Single<T>

}

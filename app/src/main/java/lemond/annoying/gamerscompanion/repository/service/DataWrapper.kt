package lemond.annoying.gamerscompanion.repository.service


class DataWrapper<T> {

    var data: T? = null
    var state: State? = null

    enum class State {
        LOADING,
        ERROR,
        EMPTY,
        NO_INTERNET,
        CONTENT
    }

}

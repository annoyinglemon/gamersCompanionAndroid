package lemond.annoying.gamerscompanion.repository.objects


class PEGI {

    var rating: Rating? = null
    var sypnosis: String? = null

    enum class Rating private constructor(val value: Int) {
        //minimum age
        MIN3(1),
        MIN7(2),
        MIN12(3),
        MIN16(4),
        MIN18(5)
    }
}

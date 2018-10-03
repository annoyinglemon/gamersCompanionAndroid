package lemond.annoying.gamerscompanion.repository.objects


class ESRB {

    var rating: Rating? = null
    var synopsis: String? = null

    enum class Rating private constructor(val value: Int) {
        RP(1),
        EC(2),
        E(3),
        E10PLUS(4),
        T(5),
        M(6),
        AO(7)
    }
}

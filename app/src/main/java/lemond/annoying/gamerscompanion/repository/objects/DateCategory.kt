package lemond.annoying.gamerscompanion.repository.objects


enum class DateCategory private constructor(val value: Int) {
    // change (_) to (-) on conversion
    YYYY_MMM_DD(0),
    YYYY_MMM(1),
    YYYY(2),
    YYYY_Q1(3),
    YYYY_Q2(4),
    YYYY_Q3(5),
    YYYY_Q4(6),
    TBD(7)
}
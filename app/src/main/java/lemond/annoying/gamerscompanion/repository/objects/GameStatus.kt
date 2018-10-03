package lemond.annoying.gamerscompanion.repository.objects


enum class GameStatus private constructor(val value: Int) {
    RELEASED(0),
    ALPHA(1),
    BETA(2),
    EARLY_ACCESS(3),
    OFFLINE(4),
    CANCELLED(5)
}
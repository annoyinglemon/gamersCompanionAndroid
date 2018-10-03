package lemond.annoying.gamerscompanion.repository.objects


class ReleaseDate {
    var id: Long = 0
    var game: Long = 0
    var category: DateCategory? = null
    var platform: Long = 0
    var human: String? = null
    // TODO: 2017-12-07 check if category is necessary
    var date: Long = 0
    var region: Region? = null
    var y: Int = 0
    var m: Int = 0

}

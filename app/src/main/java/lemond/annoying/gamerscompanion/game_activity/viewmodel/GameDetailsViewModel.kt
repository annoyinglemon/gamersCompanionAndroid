package lemond.annoying.gamerscompanion.game_activity.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.text.TextUtils
import android.view.View
import lemond.annoying.gamerscompanion.BR
import lemond.annoying.gamerscompanion.repository.objects.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class GameDetailsViewModel(private var game: Game) : BaseObservable() {

    init {
        notifyPropertyChanged(BR.name)
    }

    fun setGame(game: Game) {
        this.game = game
        notifyPropertyChanged(BR.userRatingAverage)
        notifyPropertyChanged(BR.userRatingCount)
        notifyPropertyChanged(BR.criticRatingAverage)
        notifyPropertyChanged(BR.criticRatingCount)
        notifyPropertyChanged(BR.totalRatingAverage)
        notifyPropertyChanged(BR.summary)
        notifyPropertyChanged(BR.summaryVisibility)
        notifyPropertyChanged(BR.storyline)
        notifyPropertyChanged(BR.storylineVisibility)
        notifyPropertyChanged(BR.releaseDate)
        notifyPropertyChanged(BR.releaseDateVisibility)
        notifyPropertyChanged(BR.platforms)
        notifyPropertyChanged(BR.platformsVisibility)
        notifyPropertyChanged(BR.tags)
        notifyPropertyChanged(BR.tagsVisibility)
    }

    @Bindable
    fun getName() : String {
        return this.game.name ?: ""
    }

    @Bindable
    fun getUserRatingAverage() : Int? {
        return this.game.userRatingAverage?.toInt()
    }

    @Bindable
    fun getUserRatingCount() : Int {
        return this.game.userRatingCount ?: 0
    }

    @Bindable
    fun getCriticRatingAverage() : Int? {
        return this.game.criticRatingAverage?.toInt()
    }

    @Bindable
    fun getCriticRatingCount() : Int {
        return this.game.criticRatingCount ?: 0
    }

    @Bindable
    fun getTotalRatingAverage() : Int? {
        return this.game.totalRatingAverage?.toInt()
    }

    @Bindable
    fun getSummary() : String {
        return this.game.summary ?: ""
    }

    @Bindable
    fun getSummaryVisibility() : Int {
        return if (TextUtils.isEmpty(getSummary())) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    @Bindable
    fun getStoryline() : String {
        return this.game.storyLine ?: ""
    }

    @Bindable
    fun getStorylineVisibility() : Int {
        return if (TextUtils.isEmpty(getStoryline())) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    @Bindable
    fun getReleaseDate() : String {
        return if (this.game.first_release_date == null || this.game.first_release_date == 0.0.toLong()) {
            ""
        } else {
            val date = Date(this.game.first_release_date!!)
            val simpleDateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
            simpleDateFormat.format(date)
        }
    }

    @Bindable
    fun getReleaseDateVisibility() : Int {
        return if (TextUtils.isEmpty(getReleaseDate())) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    @Bindable
    fun getPlatforms() : List<Platform>? {
        return if (this.game.platforms != null && this.game.platforms!!.isNotEmpty()) {
            this.game.platforms
        } else {
            return null
        }
    }

    @Bindable
    fun getPlatformsVisibility() : Int {
        return if (getPlatforms() == null) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    @Bindable
    fun getTags() : List<Any>? {
        return if (this.game.themes != null && this.game.themes!!.isNotEmpty()
                || this.game.keywordTags != null && this.game.keywordTags!!.isNotEmpty()
                || this.game.genreTags != null && this.game.genreTags!!.isNotEmpty()) {

            val tags = ArrayList<Any>()
            if (this.game.themes != null && this.game.themes!!.isNotEmpty()) {
                val themeIterator = this.game.themes!!.iterator()
                themeIterator.forEach {
                    tags.add(it)
                }
            }
            if (this.game.keywordTags != null && this.game.keywordTags!!.isNotEmpty()) {
                val keywordIterator = this.game.keywordTags!!.iterator()
                keywordIterator.forEach {
                    tags.add(it)
                }
            }
            if (this.game.genreTags != null && this.game.genreTags!!.isNotEmpty()) {
                val genreIterator = this.game.genreTags!!.iterator()
                genreIterator.forEach {
                    tags.add(it)
                }
            }
            return tags

        } else {
            null
        }
    }

    @Bindable
    fun getTagsVisibility() : Int {
        return if (getTags() == null) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

}

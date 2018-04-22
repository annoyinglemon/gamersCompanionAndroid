package lemond.annoying.gamerscompanion.game_activity

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.view.ViewTreeObserver
import lemond.annoying.gamerscompanion.R
import lemond.annoying.gamerscompanion.core.util.ViewUtils
import lemond.annoying.gamerscompanion.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener, ViewTreeObserver.OnGlobalLayoutListener {

    private var binding: ActivityGameBinding? = null

    private var gameImageCollapsedWidthPercentage: Float? = null
    private var gameImageCollapsedHeightPercentage: Float? = null

    private var gameNameThemeTop : Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game)

        binding?.imageViewGameActivityGameCover?.viewTreeObserver?.addOnGlobalLayoutListener(this)

    }

    override fun onGlobalLayout() {
        var gameImageExpandedWidth = binding?.imageViewGameActivityGameCover?.width
        var gameImageExpandedHeight = binding?.imageViewGameActivityGameCover?.height
        var gameImageCollapsedWidth = ViewUtils.Companion.convertDpToPixel(45, this)
        var gameImageCollapsedHeight = ViewUtils.Companion.convertDpToPixel(60, this)

        gameImageCollapsedWidthPercentage = 1f - gameImageCollapsedWidth.toFloat().div(gameImageExpandedWidth!!.toFloat())
        gameImageCollapsedHeightPercentage = 1f - gameImageCollapsedHeight.toFloat().div(gameImageExpandedHeight!!.toFloat())

        binding?.imageViewGameActivityGameCover?.pivotY = binding?.imageViewGameActivityGameCover?.height!!.times(0.08f)
        binding?.imageViewGameActivityGameCover?.pivotX = binding?.imageViewGameActivityGameCover?.width?.toFloat()!!

        binding?.appBarLayoutActivityGame?.addOnOffsetChangedListener(this)

        gameNameThemeTop = binding?.containerGameActivityGameNameTheme?.top?.toFloat()
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val scrollTravelInPx = appBarLayout?.height?.minus(binding?.toolbarActivityGame?.height!!)
        val scrollPercentage = Math.abs(verticalOffset.toFloat().times(-1f).div(scrollTravelInPx?.toFloat()!!))

        binding?.imageViewGameActivityGameCover?.scaleX = 1f - gameImageCollapsedWidthPercentage?.times(scrollPercentage)!!
        binding?.imageViewGameActivityGameCover?.scaleY = 1f - gameImageCollapsedHeightPercentage?.times(scrollPercentage)!!

        binding?.textViewGameActivityGameNameDark?.alpha = scrollPercentage
        binding?.textViewGameActivityGameThemeDark?.alpha = scrollPercentage

        binding?.textViewGameActivityGameName?.alpha = 1f - scrollPercentage
        binding?.textViewGameActivityGameTheme?.alpha = 1f - scrollPercentage

        binding?.containerGameActivityGameNameTheme?.top = gameNameThemeTop?.times(1f.minus(scrollPercentage))?.toInt()!!
    }
}


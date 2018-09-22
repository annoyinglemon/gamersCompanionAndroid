package lemond.annoying.gamerscompanion.game_activity.view

import android.animation.ArgbEvaluator
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.NavUtils
import android.support.v4.content.ContextCompat
import android.transition.Transition
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.android.AndroidInjection
import lemond.annoying.gamerscompanion.R
import lemond.annoying.gamerscompanion.app.GlideApp
import lemond.annoying.gamerscompanion.core.util.ImageUtil
import lemond.annoying.gamerscompanion.core.util.ViewUtils
import lemond.annoying.gamerscompanion.databinding.ActivityGameDetailsBinding
import lemond.annoying.gamerscompanion.game_activity.model.GameDetailsActivityRepository
import lemond.annoying.gamerscompanion.game_activity.viewmodel.GameDetailsActivityViewModel
import lemond.annoying.gamerscompanion.game_activity.viewmodel.GameDetailsViewModel
import lemond.annoying.gamerscompanion.game_activity.viewmodel.GameDetailsViewModelFactory
import lemond.annoying.gamerscompanion.repository.objects.Game
import lemond.annoying.gamerscompanion.repository.service.GameService
import javax.inject.Inject

open class GameDetailsActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener, ViewTreeObserver.OnGlobalLayoutListener {

    companion object {
        const val EXTRA_GAME: String = "extra_game"
    }

    @Inject
    lateinit var gameService: GameService
    lateinit var gameDetailsViewModel: GameDetailsActivityViewModel
    lateinit var binding: ActivityGameDetailsBinding

    private var gameImageCollapsedWidthPercentage: Float? = null
    private var gameImageCollapsedHeightPercentage: Float? = null
    private var gameNameThemeTop: Float? = null

    lateinit var gameViewModel: GameDetailsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_game_details)

        setSupportActionBar(binding.toolbarActivityGame)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setUpViewModelAndBindingValues()

        setUpEnterTransition()

        binding.cardViewGameCoverContainer.viewTreeObserver.addOnGlobalLayoutListener(this)

        //todo load game details
        gameDetailsViewModel.liveData.observe(this, Observer { dataWrapper ->
            if (dataWrapper!!.data != null) {
                gameViewModel.setGame(dataWrapper.data)
            }
            binding.containerView.setDisplayState(dataWrapper.state)
        })
        gameDetailsViewModel.fetchData(false)
    }

    private fun setUpViewModelAndBindingValues() {
        val game: Game = intent.getParcelableExtra(EXTRA_GAME)
        val gameDetailsRepo = GameDetailsActivityRepository(gameService)
        gameDetailsRepo.setGameId(game.id.toString())
        val gameDetailsViewModelFactory = GameDetailsViewModelFactory(gameDetailsRepo)
        gameDetailsViewModel = ViewModelProviders.of(this, gameDetailsViewModelFactory).get(GameDetailsActivityViewModel::class.java)

        gameViewModel = GameDetailsViewModel(game)
        binding.setViewModel(gameViewModel)

        GlideApp.with(this)
                .load(ImageUtil.getImageUrl(game.cover!!.cloudinary_id, ImageUtil.ImageSize.COVER_BIG, true))
                .error(R.drawable.ic_error_image)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(binding.imageViewGameActivityGameCover)
    }

    private fun setUpEnterTransition() {
        val sharedElementEnterTransition = window.sharedElementEnterTransition

        val transitionEnterListener = object: Transition.TransitionListener {
            override fun onTransitionResume(transition: Transition) {}
            override fun onTransitionEnd(transition: Transition) {
                binding.cardViewGameCoverContainer.visibility = View.VISIBLE
            }

            override fun onTransitionStart(transition: Transition) {
                binding.cardViewGameCoverContainer.visibility = View.INVISIBLE
            }

            override fun onTransitionPause(transition: Transition) {
            }

            override fun onTransitionCancel(transition: Transition) {
            }
        }
        sharedElementEnterTransition.addListener(transitionEnterListener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Respond to the action bar's Up/Home button. However, using navigateUpFromSameTask() is suitable only when your app is the owner of the current task
                // (that is, the user began this task from your app).
//                https@ //developer.android.com/training/implementing-navigation/ancestral
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onGlobalLayout() {
        val gameImageExpandedWidth = binding.cardViewGameCoverContainer.width
        val gameImageExpandedHeight = binding.cardViewGameCoverContainer.height
        val gameImageCollapsedWidth = ViewUtils.convertDpToPixel(45, this)
        val gameImageCollapsedHeight = ViewUtils.convertDpToPixel(60, this)

        gameImageCollapsedWidthPercentage = 1f - gameImageCollapsedWidth.toFloat().div(gameImageExpandedWidth.toFloat())
        gameImageCollapsedHeightPercentage = 1f - gameImageCollapsedHeight.toFloat().div(gameImageExpandedHeight.toFloat())

        binding.cardViewGameCoverContainer.pivotY = binding.cardViewGameCoverContainer.height.times(0.08f)
        binding.cardViewGameCoverContainer.pivotX = binding.cardViewGameCoverContainer.width.toFloat()

        binding.appBarLayoutActivityGame.addOnOffsetChangedListener(this)

        gameNameThemeTop = binding.containerGameActivityGameNameTheme.top.toFloat()
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val scrollTravelInPx = appBarLayout?.height?.minus(binding.toolbarActivityGame.height)
        val scrollPercentage = Math.abs(verticalOffset.toFloat().times(-1f).div(scrollTravelInPx?.toFloat()!!))

        binding.cardViewGameCoverContainer.scaleX = 1f - gameImageCollapsedWidthPercentage?.times(scrollPercentage)!!
        binding.cardViewGameCoverContainer.scaleY = 1f - gameImageCollapsedHeightPercentage?.times(scrollPercentage)!!

        binding.containerGameActivityGameNameTheme.top = gameNameThemeTop?.times(1f.minus(scrollPercentage))?.toInt()!!

        val textColor: Int = ArgbEvaluator().evaluate(scrollPercentage, ContextCompat.getColor(this, R.color.white), ContextCompat.getColor(this, R.color.colorPrimary)) as Int

        binding.textViewGameActivityGameName.setTextColor(textColor)
    }
}


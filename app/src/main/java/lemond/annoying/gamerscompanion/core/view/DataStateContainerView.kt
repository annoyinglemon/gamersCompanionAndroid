package lemond.annoying.gamerscompanion.core.view

import android.annotation.TargetApi
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.support.graphics.drawable.Animatable2Compat
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView

import lemond.annoying.gamerscompanion.R
import lemond.annoying.gamerscompanion.repository.service.DataWrapper

import lemond.annoying.gamerscompanion.repository.service.DataWrapper.State.LOADING


class DataStateContainerView(context: Context, attributeSet: AttributeSet?) : FrameLayout(context, attributeSet) {

    private val contentView: ViewGroup
    private val loadingView: View
    private val emptyView: View
    private val errorView: View
    private val networkErrorView: View
    private val animatedLoadingView: ImageView

    init {

        val a = context.theme.obtainStyledAttributes(attributeSet, R.styleable.DataStateContainerView, 0, 0)
        val loadingViewResId = a.getResourceId(R.styleable.DataStateContainerView_loadingView, R.layout.core_data_loading_view)
        val emptyViewResId = a.getInteger(R.styleable.DataStateContainerView_emptyView, R.layout.core_data_empty_view)
        val errorViewResId = a.getInteger(R.styleable.DataStateContainerView_errorView, R.layout.core_data_error_view)
        val networkErrorViewResId = a.getInteger(R.styleable.DataStateContainerView_networkErrorView, R.layout.core_data_network_error_view)
        a.recycle()

        val inflater = LayoutInflater.from(context)
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)

        loadingView = inflater.inflate(loadingViewResId, this, false)
        animatedLoadingView = loadingView.findViewById(R.id.loadingView)
        super.addView(loadingView, -1, params)
        emptyView = inflater.inflate(emptyViewResId, this, false)
        super.addView(emptyView, -1, params)
        errorView = inflater.inflate(errorViewResId, this, false)
        super.addView(errorView, -1, params)
        networkErrorView = inflater.inflate(networkErrorViewResId, this, false)
        super.addView(networkErrorView, -1, params)
        contentView = FrameLayout(context)
        super.addView(contentView, -1, params)

        initSettingsButton()

        setDisplayState(LOADING)
    }

    private fun initSettingsButton() {
        val button = networkErrorView.findViewById<Button>(R.id.button_errorAction)
        button.setOnClickListener { view ->
            val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
            val componentName = intent.resolveActivity(context.packageManager)
            if (componentName != null) {
                context.startActivity(intent)
            }
        }
    }

    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {
        contentView.addView(child, index, params)
    }

    fun setDisplayState(displayState: DataWrapper.State?) {
        when (displayState) {
            LOADING -> {
                loadingView.visibility = View.VISIBLE
                startLoadingAnim()
                contentView.visibility = View.GONE
                errorView.visibility = View.GONE
                emptyView.visibility = View.GONE
                networkErrorView.visibility = View.GONE
            }
            DataWrapper.State.EMPTY -> {
                loadingView.visibility = View.GONE
                stopLoadingView()
                contentView.visibility = View.GONE
                errorView.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
                networkErrorView.visibility = View.GONE
            }
            DataWrapper.State.ERROR -> {
                loadingView.visibility = View.GONE
                stopLoadingView()
                contentView.visibility = View.GONE
                errorView.visibility = View.VISIBLE
                emptyView.visibility = View.GONE
                networkErrorView.visibility = View.GONE
            }
            DataWrapper.State.NO_INTERNET -> {
                loadingView.visibility = View.GONE
                stopLoadingView()
                contentView.visibility = View.GONE
                errorView.visibility = View.GONE
                emptyView.visibility = View.GONE
                networkErrorView.visibility = View.VISIBLE
            }
            DataWrapper.State.CONTENT -> {
                loadingView.visibility = View.GONE
                stopLoadingView()
                contentView.visibility = View.VISIBLE
                errorView.visibility = View.GONE
                emptyView.visibility = View.GONE
                networkErrorView.visibility = View.GONE
            }
            else -> {
                loadingView.visibility = View.GONE
                stopLoadingView()
                contentView.visibility = View.VISIBLE
                errorView.visibility = View.GONE
                emptyView.visibility = View.GONE
                networkErrorView.visibility = View.GONE
            }
        }
    }

    private fun startLoadingAnim() {
        val drawable = animatedLoadingView.drawable
        if (drawable != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val avd = drawable as AnimatedVectorDrawable
                avd.registerAnimationCallback(object : Animatable2.AnimationCallback() {

                    private val fHandler = Handler(Looper.getMainLooper())

                    @TargetApi(Build.VERSION_CODES.M)
                    override fun onAnimationEnd(drawable: Drawable) {
                        val animatable2 = drawable as Animatable2
                        fHandler.post { animatable2.start() }
                    }
                })
                avd.start()

            } else {
                val avd = drawable as AnimatedVectorDrawableCompat
                avd.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {

                    private val fHandler = Handler(Looper.getMainLooper())

                    override fun onAnimationEnd(drawable: Drawable) {
                        val animatable2Compat = drawable as Animatable2Compat
                        fHandler.post { animatable2Compat.start() }
                    }
                })
                avd.start()
            }
        }
    }

    private fun stopLoadingView() {
        val drawable = animatedLoadingView.drawable
        if (drawable != null) {
            if (drawable is AnimatedVectorDrawable) {
                drawable.stop()
            } else if (drawable is AnimatedVectorDrawableCompat) {
                drawable.stop()
            }
        }
    }
}

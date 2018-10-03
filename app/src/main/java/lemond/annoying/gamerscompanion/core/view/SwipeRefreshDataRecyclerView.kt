package lemond.annoying.gamerscompanion.core.view

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Animatable
import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.support.graphics.drawable.Animatable2Compat
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
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


class SwipeRefreshDataRecyclerView(context: Context, attributeSet: AttributeSet) : SwipeRefreshLayout(context, attributeSet) {

    private val container: FrameLayout
    private val dataRecyclerView: RecyclerView
    private val loadingView: View
    private val emptyView: View
    private val errorView: View
    private val networkErrorView: View
    private val animatedLoadingView: ImageView

    var layoutManager: RecyclerView.LayoutManager?
        get() = this.dataRecyclerView.layoutManager
        set(layoutManager) {
            this.dataRecyclerView.layoutManager = layoutManager
        }


    init {

        val a = context.theme.obtainStyledAttributes(attributeSet, R.styleable.SwipeRefreshDataRecyclerView, 0, 0)
        val loadingViewResId = a.getResourceId(R.styleable.SwipeRefreshDataRecyclerView_loadingView, R.layout.core_data_loading_view)
        val emptyViewResId = a.getInteger(R.styleable.SwipeRefreshDataRecyclerView_emptyView, R.layout.core_data_empty_view)
        val errorViewResId = a.getInteger(R.styleable.SwipeRefreshDataRecyclerView_errorView, R.layout.core_data_error_view)
        val networkErrorViewResId = a.getInteger(R.styleable.SwipeRefreshDataRecyclerView_networkErrorView, R.layout.core_data_network_error_view)

        a.recycle()

        View.inflate(getContext(), R.layout.core_swipe_refresh_data_view, this)

        this.container = findViewById(R.id.frameLayout_swipeRefresh)
        this.dataRecyclerView = findViewById(R.id.recyclerView_swipeRefresh)
        this.dataRecyclerView.setHasFixedSize(true)

        val inflater = LayoutInflater.from(context)

        loadingView = inflater.inflate(loadingViewResId, this, false)
        animatedLoadingView = loadingView.findViewById(R.id.loadingView)
        emptyView = inflater.inflate(emptyViewResId, this, false)
        errorView = inflater.inflate(errorViewResId, this, false)
        networkErrorView = inflater.inflate(networkErrorViewResId, this, false)

        val params = ViewGroup.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)

        container.addView(loadingView, -1, params)
        container.addView(emptyView, -1, params)
        container.addView(errorView, -1, params)
        container.addView(networkErrorView, -1, params)

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

    fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        this.dataRecyclerView.adapter = adapter
    }

    fun scrollToTop(animate: Boolean) {
        if (this.dataRecyclerView.adapter != null && this.dataRecyclerView.layoutManager != null) {
            if (!animate) {
                this.dataRecyclerView.scrollToPosition(0)
            } else {
                this.dataRecyclerView.smoothScrollToPosition(0)
            }
        }
    }

    fun setDisplayState(displayState: DataWrapper.State?) {
        when (displayState) {
            LOADING -> {
                loadingView.visibility = View.VISIBLE
                startLoadingAnim()
                dataRecyclerView.visibility = View.GONE
                errorView.visibility = View.GONE
                emptyView.visibility = View.GONE
                networkErrorView.visibility = View.GONE
            }
            DataWrapper.State.EMPTY -> {
                loadingView.visibility = View.GONE
                stopLoadingView()
                dataRecyclerView.visibility = View.GONE
                errorView.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
                networkErrorView.visibility = View.GONE
            }
            DataWrapper.State.ERROR -> {
                loadingView.visibility = View.GONE
                stopLoadingView()
                dataRecyclerView.visibility = View.GONE
                errorView.visibility = View.VISIBLE
                emptyView.visibility = View.GONE
                networkErrorView.visibility = View.GONE
            }
            DataWrapper.State.NO_INTERNET -> {
                loadingView.visibility = View.GONE
                stopLoadingView()
                dataRecyclerView.visibility = View.GONE
                errorView.visibility = View.GONE
                emptyView.visibility = View.GONE
                networkErrorView.visibility = View.VISIBLE
            }
            DataWrapper.State.CONTENT -> {
                loadingView.visibility = View.GONE
                stopLoadingView()
                dataRecyclerView.visibility = View.VISIBLE
                errorView.visibility = View.GONE
                emptyView.visibility = View.GONE
                networkErrorView.visibility = View.GONE
            }
            else -> {
                loadingView.visibility = View.GONE
                stopLoadingView()
                dataRecyclerView.visibility = View.VISIBLE
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

                    override fun onAnimationEnd(drawable: Drawable) {
                        val animatable = drawable as Animatable
                        fHandler.post { animatable.start() }
                    }
                })
                avd.start()

            } else {
                val avd = drawable as AnimatedVectorDrawableCompat
                avd.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {

                    private val fHandler = Handler(Looper.getMainLooper())

                    override fun onAnimationEnd(drawable: Drawable) {
                        val animatable = drawable as Animatable2Compat
                        fHandler.post { animatable.start() }
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

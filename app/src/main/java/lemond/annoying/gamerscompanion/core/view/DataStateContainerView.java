package lemond.annoying.gamerscompanion.core.view;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.Animatable2Compat;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.repository.service.DataWrapper;

import static lemond.annoying.gamerscompanion.repository.service.DataWrapper.State.LOADING;


public class DataStateContainerView extends FrameLayout {

    protected final ViewGroup contentView;
    protected final View loadingView;
    protected final View emptyView;
    protected final View errorView;
    protected final View networkErrorView;
    protected final ImageView animatedLoadingView;


    public DataStateContainerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);

        TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.DataStateContainerView, 0, 0);
        int loadingViewResId = a.getResourceId(R.styleable.DataStateContainerView_loadingView, R.layout.core_data_loading_view);
        int emptyViewResId = a.getInteger(R.styleable.DataStateContainerView_emptyView, R.layout.core_data_empty_view);
        int errorViewResId = a.getInteger(R.styleable.DataStateContainerView_errorView, R.layout.core_data_error_view);
        int networkErrorViewResId = a.getInteger(R.styleable.DataStateContainerView_networkErrorView, R.layout.core_data_network_error_view);
        a.recycle();

        final LayoutInflater inflater = LayoutInflater.from(context);
        final LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        loadingView = inflater.inflate(loadingViewResId, this, false);
        animatedLoadingView = loadingView.findViewById(R.id.loadingView);
        super.addView(loadingView, -1, params);
        emptyView = inflater.inflate(emptyViewResId, this, false);
        super.addView(emptyView, -1, params);
        errorView = inflater.inflate(errorViewResId, this, false);
        super.addView(errorView, -1, params);
        networkErrorView = inflater.inflate(networkErrorViewResId, this, false);
        super.addView(networkErrorView, -1, params);
        contentView = new FrameLayout(context);
        super.addView(contentView, -1, params);

        initSettingsButton();

        setDisplayState(LOADING);
    }

    private void initSettingsButton() {
        Button button = networkErrorView.findViewById(R.id.button_errorAction);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            ComponentName componentName = intent.resolveActivity(getContext().getPackageManager());
            if (componentName != null) {
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void addView(View child, final int index, ViewGroup.LayoutParams params) {
        contentView.addView(child, index, params);
    }

    public void setDisplayState(DataWrapper.State displayState) {
        switch (displayState) {
            case LOADING:
                loadingView.setVisibility(VISIBLE);
                startLoadingAnim();
                contentView.setVisibility(GONE);
                errorView.setVisibility(GONE);
                emptyView.setVisibility(GONE);
                networkErrorView.setVisibility(GONE);
                break;
            case EMPTY:
                loadingView.setVisibility(GONE);
                stopLoadingView();
                contentView.setVisibility(GONE);
                errorView.setVisibility(GONE);
                emptyView.setVisibility(VISIBLE);
                networkErrorView.setVisibility(GONE);
                break;
            case ERROR:
                loadingView.setVisibility(GONE);
                stopLoadingView();
                contentView.setVisibility(GONE);
                errorView.setVisibility(VISIBLE);
                emptyView.setVisibility(GONE);
                networkErrorView.setVisibility(GONE);
                break;
            case NO_INTERNET:
                loadingView.setVisibility(GONE);
                stopLoadingView();
                contentView.setVisibility(GONE);
                errorView.setVisibility(GONE);
                emptyView.setVisibility(GONE);
                networkErrorView.setVisibility(VISIBLE);
                break;
            default:
            case CONTENT:
                loadingView.setVisibility(GONE);
                stopLoadingView();
                contentView.setVisibility(VISIBLE);
                errorView.setVisibility(GONE);
                emptyView.setVisibility(GONE);
                networkErrorView.setVisibility(GONE);
                break;
        }
    }

    private void startLoadingAnim() {
        Drawable drawable = animatedLoadingView.getDrawable();
        if (drawable != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                AnimatedVectorDrawable avd = (AnimatedVectorDrawable) drawable;
                    avd.registerAnimationCallback(new Animatable2.AnimationCallback() {

                        @NonNull
                        private final Handler fHandler = new Handler(Looper.getMainLooper());

                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public void onAnimationEnd(Drawable drawable) {
                            Animatable2 animatable2 = (Animatable2) drawable;
                            fHandler.post(animatable2::start);
                        }
                    });
                avd.start();

            } else {
                AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) drawable;
                avd.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {

                    @NonNull
                    private final Handler fHandler = new Handler(Looper.getMainLooper());

                    @Override
                    public void onAnimationEnd(Drawable drawable) {
                        Animatable2Compat animatable2Compat = (Animatable2Compat) drawable;
                        fHandler.post(animatable2Compat::start);
                    }
                });
                avd.start();
            }
        }
    }

    private void stopLoadingView() {
        Drawable drawable = animatedLoadingView.getDrawable();
        if (drawable != null) {
            if (drawable instanceof AnimatedVectorDrawable) {
                AnimatedVectorDrawable avd = (AnimatedVectorDrawable) drawable;
                avd.stop();
            } else if (drawable instanceof AnimatedVectorDrawableCompat) {
                AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) drawable;
                avd.stop();
            }
        }
    }
}

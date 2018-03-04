package lemond.annoying.gamerscompanion.core.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.repository.service.DataWrapper;

import static lemond.annoying.gamerscompanion.repository.service.DataWrapper.State.LOADING;


public class SwipeRefreshDataRecyclerView extends SwipeRefreshLayout {

    protected final FrameLayout container;
    protected final RecyclerView dataRecyclerView;
    protected final View loadingView;
    protected final View emptyView;
    protected final View errorView;
    protected final View networkErrorView;


    public SwipeRefreshDataRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.SwipeRefreshDataRecyclerView, 0, 0);
        int loadingViewResId = a.getResourceId(R.styleable.SwipeRefreshDataRecyclerView_loadingView, R.layout.core_data_loading_view);
        int emptyViewResId = a.getInteger(R.styleable.SwipeRefreshDataRecyclerView_emptyView, R.layout.core_data_empty_view);
        int errorViewResId = a.getInteger(R.styleable.SwipeRefreshDataRecyclerView_errorView, R.layout.core_data_error_view);
        int networkErrorViewResId = a.getInteger(R.styleable.SwipeRefreshDataRecyclerView_networkErrorView, R.layout.core_data_network_error_view);

        a.recycle();

        inflate(getContext(), R.layout.core_swipe_refresh_data_view, this);

        this.container = findViewById(R.id.frameLayout_swipeRefresh);
        this.dataRecyclerView = findViewById(R.id.recyclerView_swipeRefresh);
        this.dataRecyclerView.setHasFixedSize(true);

        final LayoutInflater inflater = LayoutInflater.from(context);

        loadingView = inflater.inflate(loadingViewResId, this, false);
        emptyView = inflater.inflate(emptyViewResId, this, false);
        errorView = inflater.inflate(errorViewResId, this, false);
        networkErrorView = inflater.inflate(networkErrorViewResId, this, false);

        final LayoutParams params = new LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);

        container.addView(loadingView, -1, params);
        container.addView(emptyView, -1, params);
        container.addView(errorView, -1, params);
        container.addView(networkErrorView, -1, params);

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

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.dataRecyclerView.setLayoutManager(layoutManager);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.dataRecyclerView.setAdapter(adapter);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return this.dataRecyclerView.getLayoutManager();
    }

    public void scrollToTop(boolean animate) {
        if (this.dataRecyclerView.getAdapter() != null && this.dataRecyclerView.getLayoutManager() != null) {
            if (!animate) {
                this.dataRecyclerView.scrollToPosition(0);
            } else {
                this.dataRecyclerView.smoothScrollToPosition(0);
            }
        }
    }

    public void setDisplayState(DataWrapper.State displayState) {
        switch (displayState) {
            case LOADING:
                loadingView.setVisibility(VISIBLE);
                dataRecyclerView.setVisibility(GONE);
                errorView.setVisibility(GONE);
                emptyView.setVisibility(GONE);
                networkErrorView.setVisibility(GONE);
                break;
            case EMPTY:
                loadingView.setVisibility(GONE);
                dataRecyclerView.setVisibility(GONE);
                errorView.setVisibility(GONE);
                emptyView.setVisibility(VISIBLE);
                networkErrorView.setVisibility(GONE);
                break;
            case ERROR:
                loadingView.setVisibility(GONE);
                dataRecyclerView.setVisibility(GONE);
                errorView.setVisibility(VISIBLE);
                emptyView.setVisibility(GONE);
                networkErrorView.setVisibility(GONE);
                break;
            case NO_INTERNET:
                loadingView.setVisibility(GONE);
                dataRecyclerView.setVisibility(GONE);
                errorView.setVisibility(GONE);
                emptyView.setVisibility(GONE);
                networkErrorView.setVisibility(VISIBLE);
                break;
            default:
            case CONTENT:
                loadingView.setVisibility(GONE);
                dataRecyclerView.setVisibility(VISIBLE);
                errorView.setVisibility(GONE);
                emptyView.setVisibility(GONE);
                networkErrorView.setVisibility(GONE);
                break;
        }
    }

}

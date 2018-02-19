package lemond.annoying.gamerscompanion.core.viewmodel;


import android.view.View;
import android.widget.Toast;

public class ViewMoreViewModel {

    public enum ViewMoreType {
        NEWS,
        HYPED_GAMES,
        POPULAR_GAMES,
        TRENDING_GAMES
    }

    private final ViewMoreType type;
    private final String viewMoreText;

    public ViewMoreViewModel(ViewMoreType type, String viewMoreText) {
        this.type = type;
        this.viewMoreText = viewMoreText;
    }

    public String getViewMoreText() {
        return viewMoreText;
    }

    public void onViewMoreClick(View view) {
        switch (type) {
            case HYPED_GAMES:
                break;
            case POPULAR_GAMES:
                break;
            case TRENDING_GAMES:
                break;
            case NEWS:
                // TODO: 2018-02-16 launch view more news activity here
                Toast.makeText(view.getContext(), "to be implemented", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

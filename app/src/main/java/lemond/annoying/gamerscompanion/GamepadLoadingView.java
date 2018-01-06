package lemond.annoying.gamerscompanion;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;


public class GamepadLoadingView extends FrameLayout {


    public GamepadLoadingView(@NonNull Context context) {
        super(context);
    }

    public GamepadLoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GamepadLoadingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        AnimationDrawable anim = (AnimationDrawable) getBackground();

        if (anim != null) {
            anim.setEnterFadeDuration(175);
            anim.setExitFadeDuration(175);
            anim.start();
        }

    }
}

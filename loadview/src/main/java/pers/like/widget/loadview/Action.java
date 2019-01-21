package pers.like.widget.loadview;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * @author Like
 */
public class Action {

    private String title;
    private View.OnClickListener action;

    public Action(@NonNull String title, @NonNull View.OnClickListener action) {
        this.title = title;
        this.action = action;
    }

    @NonNull
    public String title() {
        return title;
    }

    @NonNull
    public View.OnClickListener action() {
        return action;
    }


}

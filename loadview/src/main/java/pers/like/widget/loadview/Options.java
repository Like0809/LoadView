package pers.like.widget.loadview;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * @author Like
 */
public class Options {

    private int icon;
    private String message;
    private Action action;

    public Options() {
    }

    public Options(@DrawableRes int icon, String message) {
        this.icon = icon;
        this.message = message;
    }

    public Options(@DrawableRes int icon, String message, Action action) {
        this.icon = icon;
        this.message = message;
        this.action = action;
    }

    public Options(String message) {
        this.message = message;
    }

    public Options(String message, Action action) {
        this.message = message;
        this.action = action;
    }

    public int icon() {
        return icon;
    }

    public Options icon(@DrawableRes int icon) {
        this.icon = icon;
        return this;
    }

    public String message() {
        return message;
    }

    public Options message(String message) {
        this.message = message;
        return this;
    }

    public Action action() {
        return action;
    }

    public Options action(@NonNull Action action) {
        this.action = action;
        return this;
    }

}

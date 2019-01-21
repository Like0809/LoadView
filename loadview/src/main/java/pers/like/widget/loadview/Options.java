package pers.like.widget.loadview;

import android.support.annotation.IdRes;

/**
 * @author Like
 */
public class Options {

    private int icon = R.mipmap.base_empty;
    private String message = "暂无数据";
    private Action action;

    public Options() {
    }

    public Options(int icon, String message) {
        this.icon = icon;
        this.message = message;
    }

    public Options(int icon, String message, Action action) {
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

    public Options icon(int icon) {
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

    public Options action(Action action) {
        this.action = action;
        return this;
    }

}

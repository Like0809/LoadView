package pers.like.widget.loadview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Like
 */
public class LoadView extends FrameLayout {

    private final Options DEFAULT_EMPTY_OPTIONS = new Options();
    private final Options DEFAULT_ERROR_OPTIONS = new Options();
    private int mEmptyResId = NO_ID, mLoadingResId = NO_ID, mErrorResId = NO_ID;
    private int mContentId = NO_ID;
    private LayoutInflater mInflater;
    private SparseArray<View> mLayouts = new SparseArray<>();
    private int currentId = NO_ID;

    public LoadView(@NonNull Context context) {
        this(context, null);
    }

    public LoadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadView, defStyleAttr, R.style.LoadStyleDefault);

        int emptyIcon = typedArray.getResourceId(R.styleable.LoadView_lvEmptyIcon, NO_ID);
        String emptyText = typedArray.getString(R.styleable.LoadView_lvEmptyText);
        DEFAULT_EMPTY_OPTIONS.icon(emptyIcon).message(emptyText);

        int errorIcon = typedArray.getResourceId(R.styleable.LoadView_lvErrorIcon, NO_ID);
        String errorMessage = typedArray.getString(R.styleable.LoadView_lvErrorText);
        DEFAULT_ERROR_OPTIONS.icon(errorIcon).message(errorMessage);

        mEmptyResId = typedArray.getResourceId(R.styleable.LoadView_lvEmptyResId, R.layout.base_empty);
        mLoadingResId = typedArray.getResourceId(R.styleable.LoadView_lvLoadingResId, R.layout.base_loading);
        mErrorResId = typedArray.getResourceId(R.styleable.LoadView_lvErrorResId, R.layout.base_error);
        typedArray.recycle();
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() == 0) {
            return;
        }
        if (getChildCount() > 1) {
            removeViews(1, getChildCount() - 1);
        }
        View view = getChildAt(0);
        setContentView(view);
        loading();
    }

    private void setContentView(View view) {
        mContentId = view.getId();
        mLayouts.put(mContentId, view);
    }

    public void loading() {
        show(mLoadingResId, null);
    }

    public void content() {
        show(mContentId, null);
    }

    public void empty() {
        empty(DEFAULT_EMPTY_OPTIONS);
    }

    public void emptyOptions(Options options) {
        DEFAULT_EMPTY_OPTIONS.message(options.message()).action(options.action()).icon(options.icon());
    }

    public void empty(Options options) {
        show(mEmptyResId, options);
    }

    public void error() {
        error(DEFAULT_ERROR_OPTIONS);
    }

    public void errorOptions(Options options) {
        DEFAULT_ERROR_OPTIONS.message(options.message()).action(options.action()).icon(options.icon());
    }

    public void error(Options options) {
        show(mErrorResId, options);
    }

    private void show(int layoutId, Options options) {
        View current = mLayouts.get(currentId);
        currentId = layoutId;
        if (current != null) {
            current.setVisibility(GONE);
        }
        layout(layoutId, options).setVisibility(VISIBLE);
    }

    private View layout(int layoutId, Options options) {
        View layout = mLayouts.get(layoutId);
        if (layout == null) {
            layout = mInflater.inflate(layoutId, this, false);
            layout.setVisibility(GONE);
            addView(layout);
            mLayouts.put(layoutId, layout);
        }
        if (layoutId == mEmptyResId) {
            ImageView icon = layout.findViewById(R.id.image_empty_icon);
            if (icon != null) {
                icon.setImageResource(options.icon());
            }
            TextView message = layout.findViewById(R.id.text_empty_message);
            if (message != null) {
                message.setText(options.message());
            }
            TextView action = layout.findViewById(R.id.text_empty_action);
            if (action != null) {
                if (options.action() != null) {
                    action.setText(options.action().title());
                    action.setOnClickListener(options.action().action());
                    action.setVisibility(VISIBLE);
                } else {
                    action.setVisibility(GONE);
                }
            }
        } else if (layoutId == mErrorResId) {
            ImageView icon = layout.findViewById(R.id.image_error_icon);
            if (icon != null) {
                icon.setImageResource(options.icon());
            }
            TextView message = layout.findViewById(R.id.text_error_message);
            if (message != null) {
                message.setText(options.message());
            }
            TextView action = layout.findViewById(R.id.text_error_action);
            if (action != null) {
                if (options.action() != null) {
                    action.setText(options.action().title());
                    action.setOnClickListener(options.action().action());
                    action.setVisibility(VISIBLE);
                } else {
                    action.setVisibility(GONE);
                }
            }
        }
        return layout;
    }

}

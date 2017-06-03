package com.mylibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.SeekBar;

public class SeekBarColorPicker
        extends SeekBar
        implements SeekBar.OnSeekBarChangeListener {

    private int color;
    private OnColorChangedListener listener;

    /**
     * A callback that notifies clients when the color has changed
     */
    public interface OnColorChangedListener {

        /**
         * Notify that the progress color level has changed.
         *
         * @param color the current color level
         */
        public void onProgressColorChanged(int color);

        /**
         * Notify the start progress level color.
         *
         * @param color the current color level
         */
        public void onStartColorChanged(int color);

        /**
         * Notify the stop progress level color.
         *
         * @param color the current color level
         */
        public void onStopColorChanged(int color);
    }

    public SeekBarColorPicker(Context context) {
        super(context);
        init();
    }

    public SeekBarColorPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SeekBarColorPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setMax(256 * 6 - 1);
        setOnSeekBarChangeListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * Sets a listener to receiver notifications about color changes to the Seekbar's progress level
     *
     * @param listener The seek bar notification listener
     */
    public void setOnSeekBarColorChangedListener(OnColorChangedListener listener) {
        this.listener = listener;
    }

    public int getCurrentColor() {
        return color;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            color = changeColor(progress);
            if (listener != null) {
                listener.onProgressColorChanged(color);
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        color = changeColor(seekBar.getProgress());
        if (listener != null) {
            listener.onStartColorChanged(color);
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        color = changeColor(seekBar.getProgress());
        if (listener != null) {
            listener.onStopColorChanged(color);
        }
    }

    private void createSeekBar(int width, int height) {
    	int[] mcolor=new int[]{0xFF000000, 0xFF0000FF, 0xFF00FF00, 0xFF00FFFF, 0xFFFF0000, 0xFFFF00FF, 0xFFFFFF00, 0xFFFFFFFF};
    	GradientDrawable m=new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,mcolor);
    	m.setGradientType(GradientDrawable.LINEAR_GRADIENT);
    	m.setCornerRadius(15);
    	this.setProgressDrawable(m);
    }

    /**
     * 改变颜色
     * @param progress  值
     * @return
     */
    private int changeColor(int progress) {

        int r = 0;
        int g = 0;
        int b = 0;

        Log.d("progress", "progress: " + progress);
        if (progress < 256) {
            r = 255 ;//
            g=progress % 256;
        } else if (progress < 256 * 2) {
        	g=255;
        	r=255 - progress % 256;
        } else if (progress < 256 * 3) {
        	r=0;
        	g=255;
        	b=progress % 256;
        } else if (progress < 256 * 4) {
        	b=255;
        	g = 256 - progress % 256;
        } else if (progress < 256 * 5) {
        	b=255;
        	r=progress % 256;
        } else if (progress < 256 * 6) {
        	r=255;
        	b=256 - progress % 256;
        }

        return Color.argb(255, r, g, b);

    }
}

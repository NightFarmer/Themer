package com.nightfarmer.themer.sample;

import android.support.annotation.ColorRes;
import android.support.annotation.StyleRes;

/**
 * Created by zhangfan on 16-9-8.
 */
public enum Themes {

    Red("红色", R.style.Theme_Red, R.color.primary_Red),
    Blue("蓝色", R.style.Theme_Blue, R.color.primary_Blue)

    ;

    private final String title;
    private final int colorRes;
    private final int theme;

    Themes(String title, @StyleRes int theme, @ColorRes int colorRes) {
        this.title = title;
        this.theme = theme;
        this.colorRes = colorRes;
    }

    public String getTitle() {
        return title;
    }

    public int getColorRes() {
        return colorRes;
    }

    public int getTheme() {
        return theme;
    }
}

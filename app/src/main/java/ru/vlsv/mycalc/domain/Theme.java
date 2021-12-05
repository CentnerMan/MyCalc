package ru.vlsv.mycalc.domain;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import ru.vlsv.mycalc.R;

public enum Theme {

    DEFAULT(R.style.Theme_MyCalc, R.string.theme_one, "default"),
    BLACK(R.style.Theme_MyCalc_Black, R.string.theme_two, "black"),
    WHITE(R.style.Theme_MyCalc_White, R.string.theme_three, "white");

    @StyleRes
    private final int theme;

    @StringRes
    private final int name;

    private String key;

    Theme(int theme, int name, String key) {
        this.theme = theme;
        this.name = name;
        this.key = key;
    }

    Theme(int theme, int name) {
        this.theme = theme;
        this.name = name;
    }

    public int getTheme() {
        return theme;
    }

    public int getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

}

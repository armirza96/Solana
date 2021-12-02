package com.winners.solana.ui.home;

import android.graphics.Color;

import androidx.annotation.ColorInt;

public class CellItem {
    private String header;
    private String value;
    private int bgColor;
    private int textColor;

    public CellItem(String header, String value,@ColorInt int bgColor, @ColorInt int textColor) {
        this.header = header;
        this.value = value;
        this.bgColor = bgColor;
        this.textColor = textColor;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}

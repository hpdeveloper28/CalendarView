package com.calendarview.helper;

import android.view.View;

import java.util.Date;

public abstract class CaldroidListener {
    public abstract void onSelectDate(Date date, View view);

    public void onLongClickDate(Date date, View view) {
    }
    public void onChangeMonth(int month, int year) {
    }
    public void onCaldroidViewCreated() {
    }
}

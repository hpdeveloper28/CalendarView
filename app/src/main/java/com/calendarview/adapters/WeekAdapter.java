package com.calendarview.adapters;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.calendarview.R;

import java.util.List;

/**
 * Customize the weekday gridview
 */
public class WeekAdapter extends ArrayAdapter<String> {
    LayoutInflater localInflater;

    public WeekAdapter(Context context, int textViewResourceId,
                       List<String> objects, int themeResource) {
        super(context, textViewResourceId, objects);
        localInflater = getLayoutInflater(getContext(), themeResource);
    }

    // To prevent cell highlighted when clicked
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // To customize text size and color
        TextView textView = (TextView) localInflater.inflate(R.layout.week_textview, null);

        // Set content
        String item = getItem(position);
        textView.setText(item);

        return textView;
    }

    private LayoutInflater getLayoutInflater(Context context, int themeResource) {
        Context wrapped = new ContextThemeWrapper(context, themeResource);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.cloneInContext(wrapped);
    }

}

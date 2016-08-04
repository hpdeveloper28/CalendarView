package com.calendarview;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.calendarview.fragments.CalendarFragment;
import com.calendarview.helper.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class HomeActivity extends AppCompatActivity {
    private CalendarFragment calendarFragment;

    private void setCustomResourceForDates() {
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DATE, -7);
        Date blueDate = cal.getTime();

        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 14);
        Date greenDate = cal.getTime();

        cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 21);
        Date cyanDate = cal.getTime();

        if (calendarFragment != null) {

            ColorDrawable blue = new ColorDrawable(getResources().getColor(R.color.event_color));
            ColorDrawable green = new ColorDrawable(getResources().getColor(R.color.event_color));
            ColorDrawable cyan = new ColorDrawable(getResources().getColor(R.color.event_color));

            calendarFragment.setBackgroundDrawableForDate(blue, blueDate);
            calendarFragment.setBackgroundDrawableForDate(green, greenDate);
            calendarFragment.setBackgroundDrawableForDate(cyan, cyanDate);

            calendarFragment.setTextColorForDate(R.color.white, blueDate);
            calendarFragment.setTextColorForDate(R.color.white, greenDate);
            calendarFragment.setTextColorForDate(R.color.white, cyanDate);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        // Setup caldroid fragment
        calendarFragment = new CalendarFragment();

        // If Activity is created after rotation
        if (savedInstanceState != null) {
            calendarFragment.restoreStatesFromKey(savedInstanceState,
                    "Calendar_On_State");
        }
        // If activity is created from fresh
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CalendarFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CalendarFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CalendarFragment.ENABLE_SWIPE, true);
            args.putBoolean(CalendarFragment.SIX_WEEKS_IN_CALENDAR, true);

            calendarFragment.setArguments(args);
        }

        setCustomResourceForDates();

        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, calendarFragment);
        t.commit();

        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                Toast.makeText(getApplicationContext(), formatter.format(date),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChangeMonth(int month, int year) {

            }

            @Override
            public void onLongClickDate(Date date, View view) {
                String dateLongClick = formatter.format(date);
            }

            @Override
            public void onCaldroidViewCreated() {
                if (calendarFragment.getLeftArrowButton() != null) {

                }
            }

        };

        // Setup Caldroid
        calendarFragment.setCaldroidListener(listener);


    }

    /**
     * Save current states of the Caldroid here
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (calendarFragment != null) {
            calendarFragment.saveStatesToKey(outState, "Calendar_On_State");
        }
    }

}

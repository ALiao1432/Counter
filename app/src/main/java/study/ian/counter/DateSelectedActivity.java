package study.ian.counter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import study.ian.counter.util.PrefManager;

public class DateSelectedActivity extends AppCompatActivity {

    private final String TAG = "DateSelectedActivity";

    private PrefManager prefManager;
    private ViewPager viewPagerDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager = new PrefManager(this);
        if (prefManager.isFirstTimeLaunch()) {
            setContentView(R.layout.activity_date_selected);
            findViews();
            setViews();
        } else {
            launchMainActivity();
        }
    }

    private void findViews() {
        viewPagerDate = findViewById(R.id.viewPager_select_date);
    }

    private void setViews() {
        viewPagerDate.setAdapter(new SelectedDateAdapter(this));
    }

    private void launchMainActivity() {
        prefManager.setFirstTimeLaunch(false);
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    class SelectedDateAdapter extends PagerAdapter {

        private final Context context;

        SelectedDateAdapter(Context context) {
            this.context = context;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = null;

            switch (position) {
                case 0:
                    view = LayoutInflater.from(context).inflate(R.layout.pager_start_date, container, false);
                    break;
                case 1:
                    view = LayoutInflater.from(context).inflate(R.layout.pager_end_date, container, false);
                    break;
                default:
                    break;
            }
            setView(view, position);
            container.addView(view);
            return view;
        }

        private void setView(View sourceView, int position) {
            CalendarView calendarView;
            Button button;
            switch (position) {
                case 0:
                    calendarView = sourceView.findViewById(R.id.calendar_start);
                    calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) ->
                            prefManager.setStartDate(year, month, dayOfMonth));
                    break;
                case 1:
                    button = sourceView.findViewById(R.id.button_end);
                    button.setOnClickListener(v -> launchMainActivity());
                    calendarView = sourceView.findViewById(R.id.calendar_end);
                    calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) ->
                            prefManager.setEndDate(year, month, dayOfMonth));
                    break;
                default:
                    break;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }
    }
}

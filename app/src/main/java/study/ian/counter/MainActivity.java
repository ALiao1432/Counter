package study.ian.counter;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import study.ian.counter.viewpager.MainAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager mainViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setViews();
    }

    private void findViews() {
        mainViewPager = findViewById(R.id.viewPager_main);
    }

    private void setViews() {
        mainViewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        mainViewPager.setCurrentItem(1);
    }
}

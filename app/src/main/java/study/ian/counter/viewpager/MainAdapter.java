package study.ian.counter.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import study.ian.counter.fragment.DayFragment;
import study.ian.counter.fragment.HourFragment;
import study.ian.counter.fragment.MealFragment;

public class MainAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();

    public MainAdapter(FragmentManager fm) {
        super(fm);

        fragmentList.add(new DayFragment());
        fragmentList.add(new HourFragment());
        fragmentList.add(new MealFragment());
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}

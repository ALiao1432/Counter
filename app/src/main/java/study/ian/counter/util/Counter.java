package study.ian.counter.util;

import android.content.Context;

import java.util.Calendar;

public class Counter {

    private final String TAG = "Counter";

    private Calendar startC = Calendar.getInstance();
    private final Calendar endC = Calendar.getInstance();

    public Counter(Context context) {
        PrefManager prefManager = new PrefManager(context);
        int startYear = prefManager.getStartYear();
        int startMonth = prefManager.getStartMonth();
        int startDay = prefManager.getStartDay();
        int endYear = prefManager.getEndYear();
        int endMonth = prefManager.getEndMonth();
        int endDay = prefManager.getEndDay();

        setStartCalendar(startYear, startMonth, startDay);
        setEndCalendar(endYear, endMonth, endDay);
    }

    private void setStartCalendar(int year, int month, int date) {
        startC.set(year, month, date, 0, 0);

    }

    private void setEndCalendar(int year, int month, int date) {
        endC.set(year, month, date, 12, 0);
    }

    public int getRemainingDay() {
        long dayInMilliSec = 24 * 60 * 60 * 1000;
        return Math.toIntExact((endC.getTimeInMillis() - Calendar.getInstance().getTime().getTime()) / dayInMilliSec);
    }

    public int getRemainingHour() {
        long timeDiff = endC.getTimeInMillis() - Calendar.getInstance().getTime().getTime();
        return (int) (timeDiff / (60 * 60 * 1000));
    }

    public int getRemainingMin() {
        long timeDiff = endC.getTimeInMillis() - Calendar.getInstance().getTime().getTime();
        return (int) (timeDiff / (60 * 1000)) % 60;
    }

    public int getRemainingSec() {
        long timeDiff = endC.getTimeInMillis() - Calendar.getInstance().getTime().getTime();
        return (int) (timeDiff / 1000) % 60;
    }

    public int getRemainingMeal() {
        int meal;
        int hour = getRemainingHour();

        meal = (hour / 24) * 3;
        if (hasBreakfast(hour % 24)) {
            meal++;
        }
        if (hasLaunch(hour % 24)) {
            meal++;
        }
        if (hasDinner(hour % 24)) {
            meal++;
        }
        return meal;
    }

    public float getFinishPercent() {
        float remainTimeInMilli = (float) endC.getTimeInMillis() - Calendar.getInstance().getTime().getTime();
        float totalTimeInMilli = (float) endC.getTimeInMillis() - startC.getTimeInMillis();
        float finishPercent = 1 - remainTimeInMilli / totalTimeInMilli;
        return finishPercent * 100;
    }

    private boolean hasBreakfast(int hour) {
        return hour > 8;
    }

    private boolean hasLaunch(int hour) {
        return hour > 12;
    }

    private boolean hasDinner(int hour) {
        return hour > 17;
    }
}

package study.ian.counter.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    private static final String DATE_SETTING = "date_setting";
    private static final String START_DATE_YEAR = "start_date_year";
    private static final String START_DATE_MONTH = "start_date_month";
    private static final String START_DATE_DAY = "start_date_day";
    private static final String END_DATE_YEAR = "end_date_year";
    private static final String END_DATE_MONTH = "end_date_month";
    private static final String END_DATE_DAY = "end_date_day";
    private static final String IS_FIRST_TIME_LAUNCH = "isFirstTimeLaunch";
    private final SharedPreferences sharedPreferences;

    public PrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(DATE_SETTING, 0);
    }

    public boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.apply();
    }

    public void setStartDate(int year, int month, int day) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(START_DATE_YEAR, year);
        editor.putInt(START_DATE_MONTH, month);
        editor.putInt(START_DATE_DAY, day);
        editor.apply();
    }

    public void setEndDate(int year, int month, int day) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(END_DATE_YEAR, year);
        editor.putInt(END_DATE_MONTH, month);
        editor.putInt(END_DATE_DAY, day);
        editor.apply();
    }

    public int getStartYear() {
        return sharedPreferences.getInt(START_DATE_YEAR, 1970);
    }

    public int getStartMonth() {
        return sharedPreferences.getInt(START_DATE_MONTH, 1);
    }

    public int getStartDay() {
        return sharedPreferences.getInt(START_DATE_DAY, 1);
    }

    public int getEndYear() {
        return sharedPreferences.getInt(END_DATE_YEAR, 1970);
    }

    public int getEndMonth() {
        return sharedPreferences.getInt(END_DATE_MONTH, 1);
    }

    public int getEndDay() {
        return sharedPreferences.getInt(END_DATE_DAY, 1);
    }
}

package study.ian.counter.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.MessageFormat;

import study.ian.morphview.MorphView;
import study.ian.counter.R;
import study.ian.counter.util.Counter;
import study.ian.counter.util.VdConstants;

public class MealFragment extends Fragment {

    private final String TAG = "MealFragment";

    private MorphView morphMealH;
    private MorphView morphMealT;
    private MorphView morphMealU;
    private TextView txtPercent;
    private Counter counter;
    private Handler handler;

    @SuppressLint("HandlerLeak")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_meals, container, false);

        counter = new Counter(getContext());
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 9487:
                        int meal = counter.getRemainingMeal();
                        morphMealH.performAnimation(VdConstants.vdList.get(meal / 100));
                        morphMealT.performAnimation(VdConstants.vdList.get((meal % 100) / 10));
                        morphMealU.performAnimation(VdConstants.vdList.get(meal % 10));
                        txtPercent.setText(MessageFormat.format("{0} %", String.valueOf(counter.getFinishPercent())));
                        break;
                }
                super.handleMessage(msg);
            }
        };

        findViews(view);
        initViews();
        setViews();
        return view;
    }

    private void findViews(View sourceView) {
        morphMealH = sourceView.findViewById(R.id.morphView_meal_h);
        morphMealT = sourceView.findViewById(R.id.morphView_meal_t);
        morphMealU = sourceView.findViewById(R.id.morphView_meal_u);
        txtPercent = sourceView.findViewById(R.id.textView_meal_percent);
    }

    private void initViews() {
        morphMealH.setCurrentId(R.drawable.vd_0);
        morphMealT.setCurrentId(R.drawable.vd_0);
        morphMealU.setCurrentId(R.drawable.vd_0);
    }

    private void setViews() {
        new Thread(() -> {
            while (true) {
                Message clockMessage = new Message();
                clockMessage.what = 9487;
                handler.sendMessage(clockMessage);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }
}

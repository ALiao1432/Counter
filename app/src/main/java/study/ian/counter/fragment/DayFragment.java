package study.ian.counter.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.MessageFormat;

import study.ian.morphview.MorphView;
import study.ian.counter.R;
import study.ian.counter.util.Counter;
import study.ian.counter.util.VdConstants;

public class DayFragment extends Fragment {

    private final String TAG = "DayFragment";

    private MorphView morphDayH;
    private MorphView morphDayT;
    private MorphView morphDayU;
    private TextView txtPercent;
    private Counter counter;
    private Handler handler;

    @SuppressLint("HandlerLeak")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_day, container, false);

        counter = new Counter(getContext());
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 9487:
                        int day = counter.getRemainingDay();
                        Log.d(TAG, "handleMessage: day : " + day);
                        morphDayH.performAnimation(VdConstants.vdList.get(day / 100));
                        morphDayT.performAnimation(VdConstants.vdList.get((day % 100) / 10));
                        morphDayU.performAnimation(VdConstants.vdList.get(day % 10));
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
        morphDayH = sourceView.findViewById(R.id.morphView_day_h);
        morphDayT = sourceView.findViewById(R.id.morphView_day_t);
        morphDayU = sourceView.findViewById(R.id.morphView_day_u);
        txtPercent = sourceView.findViewById(R.id.textView_day_percent);
    }

    private void initViews() {
        morphDayH.setCurrentId(R.drawable.vd_0);
        morphDayT.setCurrentId(R.drawable.vd_0);
        morphDayU.setCurrentId(R.drawable.vd_0);
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

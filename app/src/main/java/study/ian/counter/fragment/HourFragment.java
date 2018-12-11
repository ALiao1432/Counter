package study.ian.counter.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
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

public class HourFragment extends Fragment {

    private final String TAG = "HourFragment";

    private MorphView morphHourH;
    private MorphView morphHourT;
    private MorphView morphHourU;
    private MorphView morphMinT;
    private MorphView morphMinU;
    private MorphView morphSecT;
    private MorphView morphSecU;
    private TextView txtPercent;
    private TextView txtHour;
    private TextView txtMin;
    private TextView txtSec;
    private Counter counter;
    private Handler handler;

    @SuppressLint("HandlerLeak")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_hour, container, false);

        counter = new Counter(getContext());
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 9487:
                        int hour = counter.getRemainingHour();
                        int min = counter.getRemainingMin();
                        int sec = counter.getRemainingSec();

                        morphHourH.performAnimation(VdConstants.vdList.get(hour / 100));
                        morphHourT.performAnimation(VdConstants.vdList.get((hour % 100) / 10));
                        morphHourU.performAnimation(VdConstants.vdList.get(hour % 10));
                        morphMinT.performAnimation(VdConstants.vdList.get(min / 10));
                        morphMinU.performAnimation(VdConstants.vdList.get(min % 10));
                        morphSecT.performAnimation(VdConstants.vdList.get(sec / 10));
                        morphSecU.performAnimation(VdConstants.vdList.get(sec % 10));
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
        morphHourH = sourceView.findViewById(R.id.morphView_hour_h);
        morphHourT = sourceView.findViewById(R.id.morphView_hour_t);
        morphHourU = sourceView.findViewById(R.id.morphView_hour_u);
        morphMinT = sourceView.findViewById(R.id.morphView_minute_t);
        morphMinU = sourceView.findViewById(R.id.morphView_minute_u);
        morphSecT = sourceView.findViewById(R.id.morphView_sec_t);
        morphSecU = sourceView.findViewById(R.id.morphView_sec_u);
        txtPercent = sourceView.findViewById(R.id.textView_hour_percent);
        txtHour = sourceView.findViewById(R.id.textView_hour);
        txtMin = sourceView.findViewById(R.id.textView_minute);
        txtSec = sourceView.findViewById(R.id.textView_sec);
    }

    private void initViews() {
        Palette.Swatch swatch =
                new Palette.Swatch(getResources().getColor(R.color.appMainColor, null), 100);

        morphHourH.setPaintColor(swatch.getBodyTextColor());
        morphHourT.setPaintColor(swatch.getBodyTextColor());
        morphHourU.setPaintColor(swatch.getBodyTextColor());
        morphMinT.setPaintColor(swatch.getBodyTextColor());
        morphMinU.setPaintColor(swatch.getBodyTextColor());
        morphSecT.setPaintColor(swatch.getBodyTextColor());
        morphSecU.setPaintColor(swatch.getBodyTextColor());
        txtPercent.setTextColor(swatch.getTitleTextColor());
        txtHour.setTextColor(swatch.getTitleTextColor());
        txtMin.setTextColor(swatch.getTitleTextColor());
        txtSec.setTextColor(swatch.getTitleTextColor());

        morphHourH.setCurrentId(R.drawable.vd_0);
        morphHourT.setCurrentId(R.drawable.vd_0);
        morphHourU.setCurrentId(R.drawable.vd_0);
        morphMinT.setCurrentId(R.drawable.vd_0);
        morphMinU.setCurrentId(R.drawable.vd_0);
        morphSecT.setCurrentId(R.drawable.vd_0);
        morphSecU.setCurrentId(R.drawable.vd_0);
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

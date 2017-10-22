package com.muddzdev.styleabletoastlibrary;

import android.os.CountDownTimer;

/**
 * Created by Muddz on 26-01-2017.
 */


public class ToastDurationTracker {
    private static final int EXTRA_DELAY = 500;
    private OnToastFinishedListener onToastFinishedListener;
    private int duration;

    public ToastDurationTracker(int duration, OnToastFinishedListener onToastFinishedListener) {
        this.duration = duration;
        this.onToastFinishedListener = onToastFinishedListener;
    }

    /**
     * Starts a {@link CountDownTimer} which counts down from the duration of the StyleableToastListener
     * from the moment {@link StyleableToast#show()} is called to cancel and reset the animation at end of the {@link StyleableToast}'s duration.
     */
    public void trackToastDuration() {
        CountDownTimer countDownTimer = new CountDownTimer(duration + EXTRA_DELAY, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                if (onToastFinishedListener != null) {
                    onToastFinishedListener.onToastFinished();
                }
            }
        };
        countDownTimer.start();
    }
}

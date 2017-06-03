package com.muddzdev.styleabletoastlibrary;

import android.os.CountDownTimer;

/**
 * Created by Muddz on 26-01-2017.
 */

/**
 * This class is used for counting down from either {@link android.widget.Toast#LENGTH_LONG} or {@link android.widget.Toast#LENGTH_SHORT} as soon as {@link StyleableToast#show()} is called<br>
 * So we know exactly when to cancel and reset any animation set on the StyleableToast's icon.
 */

public class DurationTracker {
    private static final int IN_SECONDS = 1000;
    private static final int EXTRA_DELAY = 500;
    private static final int LENGTH_LONG = 3500;
    private static final int LENGTH_SHORT = 2000;
    private OnToastFinished onToastFinished;
    private CountDownTimer countDownTimer;
    private int duration;

    public DurationTracker(int duration, OnToastFinished onToastFinished) {
        this.duration = duration;
        this.onToastFinished = onToastFinished;
    }

    /**
     * Starts a {@link CountDownTimer} which counts down from the duration set in StyleableToast when {@link StyleableToast#show()} is called.
     */
    public void trackToastDuration() {
        countDownTimer = new CountDownTimer(duration + EXTRA_DELAY, IN_SECONDS) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                if (onToastFinished != null) {
                    onToastFinished.onToastFinished();
                }
            }
        };
        countDownTimer.start();
    }

}

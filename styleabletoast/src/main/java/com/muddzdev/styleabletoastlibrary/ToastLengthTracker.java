package com.muddzdev.styleabletoastlibrary;

import android.os.CountDownTimer;

/**
 * Created by Muddz on 26-01-2017.
 */


public class ToastLengthTracker {
    private static final int EXTRA_DELAY = 500;
    private OnToastFinishedListener onToastFinishedListener;
    private int length;

    public ToastLengthTracker(int length, OnToastFinishedListener onToastFinishedListener) {
        this.length = length;
        this.onToastFinishedListener = onToastFinishedListener;
    }

    /**
     * Starts a {@link CountDownTimer} which counts down from the length of the StyleableToastListener
     * from the moment {@link StyleableToast#show()} is called to cancel and reset the animation at end of the {@link StyleableToast}'s length.
     */
    public void trackToastLength() {
        CountDownTimer countDownTimer = new CountDownTimer(length + EXTRA_DELAY, 1000) {
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

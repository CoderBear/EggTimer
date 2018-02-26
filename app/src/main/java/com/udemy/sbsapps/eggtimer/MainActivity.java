package com.udemy.sbsapps.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    MediaPlayer mediaPlayer;
    boolean counterIsActive = false;
    Button goButton;
    CountDownTimer countDownTimer;

    public void buttonClicked(View view) {
        String message;

        if (counterIsActive) {
            message = "0:30";
            textView.setText(message);
            seekBar.setProgress(30);
            seekBar.setEnabled(true);
            countDownTimer.cancel();
            goButton.setText(R.string.timer_button_text);
        }
        else{
            message = "STOP";
            counterIsActive = true;
            seekBar.setEnabled(false);
            goButton.setText(message);

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished/1000);
                }

                @Override
                public void onFinish() {
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }

    public void updateTimer(int i) {
        int minutes = i / 60;
        int seconds = i - (minutes * 60);

        String message;

        if(seconds < 10) {
            message = Integer.toString(minutes) + ":0" + Integer.toString(seconds);
            textView.setText(message);
        } else {
            message = Integer.toString(minutes) + ":" + Integer.toString(seconds);
            textView.setText(message);
        }
    }

    public void resetTimer() {
        String time = "0:30";
        textView.setText(time);
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText(R.string.timer_button_text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int initialTime = 30;

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        goButton = findViewById(R.id.button);
        mediaPlayer = MediaPlayer.create(this, R.raw.air_horn);

        InitializeTimer(initialTime);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    void InitializeTimer(int i) {
        int minutes = i / 60;
        int seconds = i - (minutes * 60);
        String message;

        if(seconds < 10) {
            message = Integer.toString(minutes) + ":0" + Integer.toString(seconds);
            textView.setText(message);
        } else {
            message = Integer.toString(minutes) + ":" + Integer.toString(seconds);
            textView.setText(message);
        }
    }
}

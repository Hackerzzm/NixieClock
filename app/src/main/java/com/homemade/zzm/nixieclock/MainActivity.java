package com.homemade.zzm.nixieclock;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.homemade.zzm.nixieclock.utils.CommonUtils;

public class MainActivity extends AppCompatActivity {
    private static final int STEP_1_START = 1;
    private static final int STEP_4_PIC1OK = 2;
    private static final int STEP_3_PIC2OK = 3;
    private static final int STEP_2_PIC3OK = 4;
    private static final int STEP_5_FINISH = 5;
    private ImageView onearmbandit1, onearmbandit2, onearmbandit3;
    private int success_time = 0, total_time = 0;
    private TextView statistic_tv;
    private Button luckybtn;
    private boolean result_out1 = false, result_out2 = false, result_out3 = false;
    private int num1, num2, num3;
    private boolean blurflag1 = false, blurflag2 = false, blurflag3 = false;
    private Handler blurHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    onearmbandit1.setImageResource(CommonUtils.getPictureResource((char) (48 + getARandomNum0_9())));
                    break;
                case 2:
                    onearmbandit2.setImageResource(CommonUtils.getPictureResource((char) (48 + getARandomNum0_9())));
                    break;
                case 3:
                    onearmbandit3.setImageResource(CommonUtils.getPictureResource((char) (48 + getARandomNum0_9())));
                    break;
                default:
                    break;
            }
        }
    };
    private Handler gameHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case STEP_1_START:
                    luckybtn.setClickable(false);
                    result_out1 = false;
                    result_out2 = false;
                    result_out3 = false;
                    startBlur(1);
                    startBlur(2);
                    startBlur(3);
                    gameHandler.sendEmptyMessageDelayed(STEP_2_PIC3OK, (int) (Math.random() * 4000));
                    gameHandler.sendEmptyMessageDelayed(STEP_3_PIC2OK, (int) (Math.random() * 4000));
                    gameHandler.sendEmptyMessageDelayed(STEP_4_PIC1OK, (int) (Math.random() * 4000));
                    break;
                case STEP_2_PIC3OK:
                    stopBlur(3);
                    onearmbandit3.setImageResource(CommonUtils.getPictureResource((char) (48 + num3)));
                    result_out1 = true;
                    check();
                    break;
                case STEP_3_PIC2OK:
                    stopBlur(2);
                    onearmbandit2.setImageResource(CommonUtils.getPictureResource((char) (48 + num2)));
                    result_out2 = true;
                    check();
                    break;
                case STEP_4_PIC1OK:
                    stopBlur(1);
                    onearmbandit1.setImageResource(CommonUtils.getPictureResource((char) (48 + num1)));
                    result_out3 = true;
                    check();
                    break;
                case STEP_5_FINISH:
                    if (num1 == num2 && num2 == num3) {
                        success_time++;
                        Toast.makeText(getApplicationContext(), "You Win!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "try again!", Toast.LENGTH_SHORT).show();
                    }
                    statistic_tv.setText(success_time + "/" + total_time);
                    luckybtn.setClickable(true);
                    break;
                default:
                    break;
            }
        }
    };

    private void startBlur(int witch) {
        switch (witch) {
            case 1:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        blurflag1 = true;
                        while (blurflag1) {
                            blurHandler.sendEmptyMessage(1);
                            try {
                                sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
                break;
            case 2:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        blurflag2 = true;
                        while (blurflag2) {
                            blurHandler.sendEmptyMessage(2);
                            try {
                                sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
                break;
            case 3:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        blurflag3 = true;
                        while (blurflag3) {
                            blurHandler.sendEmptyMessage(3);
                            try {
                                sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
                break;
            default:
                break;
        }
    }

    private void check() {
        if (result_out3 && result_out2 && result_out1) {
            gameHandler.sendEmptyMessage(STEP_5_FINISH);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        statistic_tv = (TextView) findViewById(R.id.statistics);
        success_time = getSharedPreferences("nixieclock_game_statistic", MODE_PRIVATE).getInt("success_time", 0);
        total_time = getSharedPreferences("nixieclock_game_statistic", MODE_PRIVATE).getInt("total_time", 0);
        statistic_tv.setText(success_time + "/" + total_time);
        onearmbandit1 = (ImageView) findViewById(R.id.onearmbandit_iv1);
        onearmbandit2 = (ImageView) findViewById(R.id.onearmbandit_iv2);
        onearmbandit3 = (ImageView) findViewById(R.id.onearmbandit_iv3);
        luckybtn = (Button) findViewById(R.id.lucky_btn);
        luckybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViews();
            }
        });
    }

    private void updateViews() {
        num1 = getARandomNum0_9();
        num2 = getARandomNum0_9();
        num3 = getARandomNum0_9();
        total_time++;
        gameHandler.sendEmptyMessage(STEP_1_START);
    }

    private int getARandomNum0_9() {
        return (int) (Math.random() * 10);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = getSharedPreferences("nixieclock_game_statistic", MODE_PRIVATE).edit();
        editor.putInt("success_time", success_time);
        editor.putInt("total_time", total_time);
        editor.apply();
    }

    private void stopBlur(int witch) {
        switch (witch) {
            case 1:
                blurflag1 = false;
                break;
            case 2:
                blurflag2 = false;
                break;
            case 3:
                blurflag3 = false;
                break;
            default:
                break;
        }
    }
}

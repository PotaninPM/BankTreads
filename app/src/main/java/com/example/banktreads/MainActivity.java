package com.example.banktreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView text1;
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = findViewById(R.id.textView);
        GiveMoneyThread giveMoney = new GiveMoneyThread();
        SpendMoneyThread spendMoney = new SpendMoneyThread();
        giveMoney.start();
        spendMoney.start();
    }

    class GiveMoneyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                Random rnd = new Random();
                int number = rnd.nextInt(1000);
                count += number;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class SpendMoneyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                Random rnd = new Random();
                int number = rnd.nextInt(1000);
                if (number > count) {
                    runOnUiThread(() -> text1.setText("Не хватает денег"));
                } else {
                    count -= number;
                    runOnUiThread(() -> text1.setText(String.valueOf(count)));
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

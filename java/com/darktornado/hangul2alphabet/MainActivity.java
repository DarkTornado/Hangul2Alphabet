package com.darktornado.hangul2alphabet;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(1);

        TextView txt1 = new TextView(this);
        txt1.setText("입력 : ");
        txt1.setTextSize(17);
        txt1.setTextColor(Color.BLACK);
        layout.addView(txt1);
        EditText txt2 = new EditText(this);
        txt2.setHint("내용 입력...");
        layout.addView(txt2);

        LinearLayout lay2 = new LinearLayout(this);
        lay2.setWeightSum(2);
        Button btn1 = new Button(this);
        btn1.setText("한글로 변환");
        btn1.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1));
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lay2.addView(btn1);
        Button btn2 = new Button(this);
        btn2.setText("알파벳으로 변환");
        btn2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1));
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lay2.addView(btn2);
        layout.addView(lay2);

        TextView txt3 = new TextView(this);
        txt3.setText("\n출력 : ");
        txt3.setTextSize(17);
        txt3.setTextColor(Color.BLACK);
        layout.addView(txt3);
        EditText txt4 = new EditText(this);
        txt4.setHint("결과물...");
        layout.addView(txt4);

        int pad = dip2px(16);
        layout.setPadding(pad, pad, pad, pad);
        ScrollView scroll = new ScrollView(this);
        scroll.addView(layout);
        setContentView(scroll);
    }

    private int dip2px(int dips) {
        return (int) Math.ceil(dips * getResources().getDisplayMetrics().density);
    }
}
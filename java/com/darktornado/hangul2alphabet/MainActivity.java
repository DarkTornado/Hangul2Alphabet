package com.darktornado.hangul2alphabet;

import android.app.Activity;
import android.os.Bundle;
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
        layout.addView(txt1);
        EditText txt2 = new EditText(this);
        txt2.setHint("내용 입력...");
        layout.addView(txt2);
        TextView txt3 = new TextView(this);
        txt3.setText("\n출력 : ");
        txt3.setTextSize(17);
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
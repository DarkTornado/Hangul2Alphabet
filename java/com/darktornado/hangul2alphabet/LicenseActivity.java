package com.darktornado.hangul2alphabet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.darktornado.library.LicenseView;

public class LicenseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(1);

        layout.addView(new LicenseView(this)
                .setTitle("Hangul.js")
                .setSubtitle("by Jaemin Jo, MIT License")
                .setLicense("MIT License", "Hangul.js.txt"));
        layout.addView(new LicenseView(this)
                .setTitle("LicenseView")
                .setSubtitle("by DarkTornado, BSD 3-Clause License")
                .setLicense("BSD 3-Clause License", "LicenseView.txt"));

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
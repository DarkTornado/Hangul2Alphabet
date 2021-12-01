package com.darktornado.hangul2alphabet;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText output;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Uri uri = Uri.parse("https://github.com/DarkTornado/Hangul2Alphabet");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
                break;
            case 1:
                startActivity(new Intent(this, LicenseActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "깃허브로 이동");
        menu.add(0, 1, 0, "오픈 소스 라이선스");
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(1);

        final WebView web = new WebView(this);
        web.getSettings().setJavaScriptEnabled(true);
        web.addJavascriptInterface(new JSLinker(), "android");
        web.loadUrl("file:///android_asset/index.html");

        TextView txt1 = new TextView(this);
        final EditText txt2 = new EditText(this);
        TextView txt3 = new TextView(this);
        final EditText txt4 = new EditText(this);

        txt1.setText("입력 : ");
        txt1.setTextSize(17);
        txt1.setTextColor(Color.BLACK);
        layout.addView(txt1);
        txt2.setHint("내용 입력...");
        layout.addView(txt2);

        LinearLayout lay2 = new LinearLayout(this);
        lay2.setWeightSum(2);
        Button btn1 = new Button(this);
        btn1.setText("한글로 변환");
        btn1.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1));
        btn1.setOnClickListener(v -> {
            String input = txt2.getText().toString();
            web.loadUrl("javascript:doProcess('" + toHangul(input) + "', false);");
        });
        lay2.addView(btn1);
        Button btn2 = new Button(this);
        btn2.setText("알파벳으로 변환");
        btn2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1));
        btn2.setOnClickListener(v -> {
            String input = txt2.getText().toString();
            web.loadUrl("javascript:doProcess('" + input + "', true);");
        });
        lay2.addView(btn2);
        layout.addView(lay2);

        txt3.setText("\n출력 : ");
        txt3.setTextSize(17);
        txt3.setTextColor(Color.BLACK);
        layout.addView(txt3);
        txt4.setHint("결과물...");
        layout.addView(txt4);

        Button copy = new Button(this);
        copy.setText("클립보드로 복사");
        copy.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1));
        copy.setOnClickListener(v -> {
            ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            cm.setPrimaryClip(ClipData.newPlainText("label", output.getText().toString()));
            Toast.makeText(this, "복사 완료", Toast.LENGTH_SHORT).show();
        });
        layout.addView(copy);

        output = txt4;
        TextView maker = new TextView(this);
        maker.setText("\n© 2021 Dark Tornado, All rights reserved.\n");
        maker.setTextSize(13);
        maker.setTextColor(Color.BLACK);
        maker.setGravity(Gravity.CENTER);
        layout.addView(maker);

        int pad = dip2px(16);
        layout.setPadding(pad, pad, pad, pad);
        ScrollView scroll = new ScrollView(this);
        scroll.addView(layout);
        setContentView(scroll);
    }

    private String toAlphabet(String str) {
        char[] han = "ㅂㅈㄷㄱㅅㅛㅕㅑㅐㅔㅁㄴㅇㄹㅎㅗㅓㅏㅣㅋㅌㅊㅍㅠㅜㅡㅃㅉㄸㄲㅆㅒㅖ".toCharArray();
        char[] alpha = "qwertyuiopasdfghjklzxcvbnmQWERTOP".toCharArray();
        for (int n = 0; n < alpha.length; n++) {
            str = str.replace(han[n], alpha[n]);
        }
        return str;
    }

    private String toHangul(String str) {
        char[] alpha = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
        char[] han = "ㅂㅈㄷㄱㅅㅛㅕㅑㅐㅔㅁㄴㅇㄹㅎㅗㅓㅏㅣㅋㅌㅊㅍㅠㅜㅡㅃㅉㄸㄲㅆㅛㅕㅑㅒㅖㅁㄴㅇㄹㅎㅗㅓㅏㅣㅋㅌㅊㅍㅠㅜㅡ".toCharArray();
        for (int n = 0; n < alpha.length; n++) {
            str = str.replace(alpha[n], han[n]);
        }
        return str;
    }

    public void onResultReceived(String data, boolean flag) {
        if (flag) data = toAlphabet(data);
        output.setText(data);
        Toast.makeText(this, "변환 완료", Toast.LENGTH_SHORT).show();
    }

    private int dip2px(int dips) {
        return (int) Math.ceil(dips * getResources().getDisplayMetrics().density);
    }

    private class JSLinker {
        @JavascriptInterface
        public void sendData(final String data, boolean flag) {
            new Handler().post(() -> onResultReceived(data, flag));
        }
    }

}
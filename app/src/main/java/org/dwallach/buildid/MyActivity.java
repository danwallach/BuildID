package org.dwallach.buildid;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

public class MyActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                buffer = new StringBuffer();
                prepareText();
                mTextView.setText(Html.fromHtml(buffer.toString()));
                mTextView.setMovementMethod(new ScrollingMovementMethod());
            }
        });
    }

    private StringBuffer buffer;

    protected void prepareText() {
        addItem("BOARD", Build.BOARD);
        addItem("BRAND", Build.BRAND);
        addItem("DEVICE", Build.DEVICE);
        addItem("HARDWARE", Build.HARDWARE);
        addItem("ID", Build.ID);
        addItem("MANUFACTURER", Build.MANUFACTURER);
        addItem("MODEL", Build.MODEL);
        addItem("PRODUCT", Build.PRODUCT);
        addItem("SERIAL", Build.SERIAL);
        addItem("TYPE", Build.TYPE);
    }

    private void addItem(String name, String value) {
        Log.v("BuildID", name + ": " + value);
        buffer.append("<b><i>" + name + "</i></b><br/>" + value + "<hr/>");
    }
}

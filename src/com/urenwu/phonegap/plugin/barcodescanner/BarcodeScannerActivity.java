package com.urenwu.phonegap.plugin.barcodescanner;

import android.os.Bundle;
import com.phonegap.*;

public class BarcodeScannerActivity extends DroidGap {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	BridgeR.init(R.class.getPackage().getName());
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/index.html");
    }
}
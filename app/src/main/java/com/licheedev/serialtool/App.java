package com.licheedev.serialtool;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.widget.Toast;

import com.licheedev.serialtool.util.PrefHelper;
import com.licheedev.serialtool.utils.AidlUtil;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class App extends Application {

    private WifiManager wifiManager;

    private Handler mUiHandler;
    private static App sInstance;

    private boolean isAidl;

    public boolean isAidl() {
        return isAidl;
    }

    public void setAidl(boolean aidl) {
        isAidl = aidl;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        mUiHandler = new Handler();
        initUtils();
        isAidl = true;
        AidlUtil.getInstance().connectPrinterService(this);

    }

    private void initUtils() {
        PrefHelper.initDefault(this);
    }

    public static App instance() {
        return sInstance;
    }

    public static Handler getUiHandler() {
        return instance().mUiHandler;
    }

}

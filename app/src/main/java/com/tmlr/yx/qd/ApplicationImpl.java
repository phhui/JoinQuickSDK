package com.tmlr.yx.qd;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.quicksdk.QuickSdkApplication;
import io.dcloud.application.DCloudApplication;
public class ApplicationImpl extends Application implements ApplicationListener {
    private String TAG = "log>>>";
    private PlusApplicationListener plusApplicationListener = null;
    private QuickApplicationListener quickApplicationListener= null;
    private MultiDexApplicationListener multiDexApplicationListener= null;
    @Override
    public void appOnCreate() {
        Log.d(TAG, "onCreate");
        if (plusApplicationListener != null){
            plusApplicationListener.appOnCreate();
        }
        if (quickApplicationListener != null){
            quickApplicationListener.appOnCreate();
        }else Log.d(TAG,"quickApp is null");
        if(multiDexApplicationListener!=null){
            multiDexApplicationListener.appOnCreate();
        }
    }
    @Override
    public void appAttachBaseContext(Context context) {
        Log.d(TAG, "attachBaseContext");
        plusApplicationListener = getPlusApplication();
        quickApplicationListener = getQuickApplication();
        multiDexApplicationListener=getMultiDexApplication();
        if (plusApplicationListener != null){
            plusApplicationListener.appAttachBaseContext(context);
        }
        if (quickApplicationListener != null){
            quickApplicationListener.appAttachBaseContext(context);
        }
        if(multiDexApplicationListener!=null)multiDexApplicationListener.appAttachBaseContext(context);
    }

    @Override
    public void appOnConfigurationChanged(Configuration configuration) {
        Log.d(TAG, "onConfigurationChanged");
        if (plusApplicationListener != null){
            plusApplicationListener.appOnConfigurationChanged(configuration);
        }
        if (quickApplicationListener != null){
            quickApplicationListener.appOnConfigurationChanged(configuration);
        }
        if(multiDexApplicationListener!=null)multiDexApplicationListener.appOnConfigurationChanged(configuration);
    }
    private PlusApplicationListener getPlusApplication() {
        Class clazz = null;
        try {
            clazz = Class.forName("com.tmlr.yx.qd.PlusApplicationImpl");
            return (PlusApplicationListener) clazz.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    private QuickApplicationListener getQuickApplication(){
        Class clazz = null;
        try {
            clazz = Class.forName("com.tmlr.yx.qd.QuickApplicationImpl");
            return (QuickApplicationListener) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
    private MultiDexApplicationListener getMultiDexApplication(){
        Class clazz = null;
        try {
            clazz = Class.forName("com.tmlr.yx.qd.MultiDexApplicationImpl");
            return (MultiDexApplicationListener) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
class PlusApplicationImpl extends DCloudApplication implements PlusApplicationListener {
    String TAG ="log>>>Plus >>";
    @Override
    public void appOnCreate() {
        Log.d(TAG,"X->onCreate");
        super.onCreate();
    }
    @Override
    public void appAttachBaseContext(Context context) {
        Log.d(TAG,"X->attachBaseContext");
        super.attachBaseContext(context);
    }

    @Override
    public void appOnConfigurationChanged(Configuration configuration) {
        Log.d(TAG,"X->onConfigurationChanged");
    }
}

class QuickApplicationImpl extends QuickSdkApplication implements QuickApplicationListener {
    String TAG = "log>>> Quick >>";
    @Override
    public void appOnCreate() {
        Log.d(TAG, "Y->onCreate");
        super.onCreate();
    }

    @Override
    public void appAttachBaseContext(Context context) {
        Log.d(TAG, "Y->attachBaseContext");
        super.attachBaseContext(context);
    }

    @Override
    public void appOnConfigurationChanged(Configuration configuration) {
        Log.d(TAG, "Y->onConfigurationChanged");
    }
}
class MultiDexApplicationImpl extends MultiDexApplication implements MultiDexApplicationListener {
    String TAG = "log>>> MultiDex >>";
    @Override
    public void appOnCreate() {
        Log.d(TAG, "Z->onCreate");
        super.onCreate();
    }

    @Override
    public void appAttachBaseContext(Context context) {
        Log.d(TAG, "Z->attachBaseContext");
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    @Override
    public void appOnConfigurationChanged(Configuration configuration) {
        Log.d(TAG, "Z->onConfigurationChanged");
    }
}
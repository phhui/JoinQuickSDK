package com.tmlr.yx.qd;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.dcloud.android.annotation.NonNull;
import com.dcloud.android.annotation.Nullable;
import com.quicksdk.apiadapter.IAdapterFactory;
public class MainApplication extends ProxyApplication implements Application.ActivityLifecycleCallbacks {
    private IAdapterFactory a = null;
    private boolean isBackRun =false;
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        if(!isBackRun)return;
        try {
            isBackRun =false;
            NPandoraEntryActivity.changeStatus(1);
        }catch(Exception E){

        }
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        if(isBackRun)return;
        //比如我的应用主页面是ActMain   ActMain进入后台就认定应用进入后台
        if (activity instanceof NPandoraEntryActivity){
            //在这里处理后台的操作
            isBackRun =true;
            NPandoraEntryActivity.changeStatus(0);
        }
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}

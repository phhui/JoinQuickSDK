package com.tmlr.yx.qd;
import android.content.Context;
import android.content.res.Configuration;
public interface ApplicationListener {
    void appOnCreate();
    void appAttachBaseContext(Context context);
    void appOnConfigurationChanged(Configuration configuration);
}
interface PlusApplicationListener {
    void appOnCreate();
    void appAttachBaseContext(Context context);
    void appOnConfigurationChanged(Configuration configuration);
}
interface QuickApplicationListener {
    void appOnCreate();
    void appAttachBaseContext(Context context);
    void appOnConfigurationChanged(Configuration configuration);
}
/**DEX超过65K，启用该插件*/
interface MultiDexApplicationListener {
    void appOnCreate();
    void appAttachBaseContext(Context context);
    void appOnConfigurationChanged(Configuration configuration);
}

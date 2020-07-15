package com.tmlr.yx.qd;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import io.dcloud.PandoraEntry;
import io.dcloud.WebAppActivity;

public class NPandoraEntry extends PandoraEntry {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        boolean isSteamApp = false;

        try {
            isSteamApp = intent.getBooleanExtra("is_stream_app", isSteamApp);
        } catch (Exception var5) {
            var5.printStackTrace();
            this.finish();
            return;
        }

        if (isSteamApp) {
            intent.setClass(this, WebAppActivity.class);
            intent.putExtra("is_stream_app", true);
        } else {
            intent.putExtra("short_cut_class_name", PandoraEntry.class.getName());
            intent.setClass(this, NPandoraEntryActivity.class);
        }

        this.startActivity(intent);
        (new Handler()).postDelayed(new Runnable() {
            public void run() {
                NPandoraEntry.this.finish();
            }
        }, 20L);

    }
    @Override
    public void startActivity(Intent intent) {
        ComponentName componentName = intent.getComponent();
        String packageName = componentName.getPackageName();
        String className = componentName.getClassName();
        if(className.equals("io.dcloud.PandoraEntryActivity")){
            intent.setComponent(new ComponentName(packageName, "com.tmlr.yx.qd.NPandoraEntry"));
        }
        super.startActivity(intent);
    }
}

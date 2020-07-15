package com.tmlr.yx.qd;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.quicksdk.entity.UserInfo;

import java.util.ArrayList;
import io.dcloud.PandoraEntryActivity;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.feature.internal.sdk.SDK;
public class NPandoraEntryActivity extends PandoraEntryActivity {
    private boolean isLogin=false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initQuickSDK();
    }
    @Override
    public void onResume() {
        super.onResume();
        com.quicksdk.Sdk.getInstance().onResume(this);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(!isLogin){
                signIn("");
                return true;
            }
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public static void changeStatus(int statu){
        getWebview().evalJS("javascript:appPlugin.pause('"+statu+"')");
    }
    public void signIn(String msg) {
        showLog("登录");
        QuickUtil.signIn();
    }
    public void signOut(){
        showLog("登出");
        QuickUtil.signOut();
    }
    public void reportCreateRole(String jsonStr){
        showLog("创角上报："+jsonStr);
        QuickUtil.reportUserinfo(jsonStr,true);
    }
    public void reportUserInfo(String jsonStr){
        showLog("升级上报："+jsonStr);
        QuickUtil.reportUserinfo(jsonStr,false);
    }
    public void toPay(String jsonStr){
        showLog("支付："+jsonStr);
        IWebview wb=getWebview();
        QuickUtil.toPay(jsonStr);
    }
    public void loginResult(JsonObject jobj){
        String res=jobj.toString();
        showLog("登录结果："+res);
        callJs("appPlugin.loginResult('"+res+"')");
    }
    public void payResult(JsonObject jobj){
        showLog("支付结果："+jobj.toString());
        callJs("appPlugin.payResult('"+jobj.toString()+"')");
    }
    public void callJs(String msg){
        IWebview wb = getWebview();
        wb.evalJS("javascript:" + msg);
    }
    public void showLog(String msg){
        Log.d("AppLog>>> ",msg);
    }
    public void showTip(String msg){
        Toast.makeText(NPandoraEntryActivity.this, msg, Toast.LENGTH_SHORT).show();
        showLog(msg);
    }
    public static IWebview getWebview(){
        ArrayList<IWebview> weblist=SDK.obtainAllIWebview();
        for(int i=0;i<weblist.size();i++){
            IWebview wb=weblist.get(i);
            if(wb.getOriginalUrl().contains("index.html") ){
                return wb;
            }
        }
        return null;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            QuickUtil.init();
        } else {
            System.exit(0);
            finish();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        com.quicksdk.Sdk.getInstance().onStart(this);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        com.quicksdk.Sdk.getInstance().onRestart(this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        com.quicksdk.Sdk.getInstance().onStop(this);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        com.quicksdk.Sdk.getInstance().onActivityResult(this, requestCode, resultCode, data);
    }
    private void initQuickSDK(){
        QuickUtil.create(this, new IQuickListener() {
            @Override
            public void loginSuccess(UserInfo info){
                JsonObject jobj=new JsonObject();
                try {
                    jobj.addProperty("code",1);
                    jobj.addProperty("msg","success");
                    jobj.addProperty("uid", info.getUID().toString());
                    jobj.addProperty("token",info.getToken());
                }catch(Exception err){
                    showLog("loginResult coverTo json error"+err.getMessage());
                }
                loginResult(jobj);
            }
            @Override
            public void loginFailed(String message, String trace) {
                showTip("登录失败("+trace+")");
            }

            @Override
            public void paySuccess(String sdkOrderID, String cpOrderID, String extrasParams) {
                JsonObject jobj=new JsonObject();
                jobj.addProperty("code",1);
                jobj.addProperty("pOderId",sdkOrderID);
                jobj.addProperty("orderId",cpOrderID);
                jobj.addProperty("msg","success");
                payResult(jobj);
            }

            @Override
            public void payFailed(String cpOrderID, String message, String trace) {
                showLog(">>>"+message+","+trace);
                JsonObject jobj=new JsonObject();
                jobj.addProperty("code",3);
                jobj.addProperty("orderId",cpOrderID);
                jobj.addProperty("msg",trace);
                payResult(jobj);
            }

            @Override
            public void reportFailed(String msg) {
                showLog("上报结果："+msg);
            }
        });
    }
}
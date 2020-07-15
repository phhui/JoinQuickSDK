var appPlugin={};
(function(appPlugin){
    appPlugin.app=null;
    appPlugin.signType={};
    appPlugin.packName="com.tmlr.yx.qd.NPandoraEntryActivity";
    appPlugin.isInit=false;
    appPlugin.delayList=[];
    appPlugin.reportParam=[];
    appPlugin.shareService=null;
    appPlugin.init=function(){
        appPlugin.app = plus.android.runtimeMainActivity();
        var Intent = plus.android.importClass("android.content.Intent");
        var intent = new Intent();
        intent.setClassName(appPlugin.app, appPlugin.packName);
        if(!appPlugin.app)alert("APP init failed");
        Log.appLogFunc=appPlugin.appLog;
        console.log=appPlugin.appLog;
        appPlugin.hideStatuAndMenu();
	    plus.screen.lockOrientation("portrait");
	    appPlugin.signType[AppCmd.LOGIN]="cj";
	    appPlugin.signType[AppCmd.LOGIN_BY_GOOGLE]="google";
	    appPlugin.signType[AppCmd.LOGIN_BY_FACEBOOK]="facebook";
        appPlugin.isInit=true;
        while(appPlugin.delayList.length>0)EventHelper.call(appPlugin.delayList.shift());
        Log.log("plugin inited");
    }
    appPlugin.signIn=function(e){
        if(!appPlugin.isInit)appPlugin.delayList.push(e);
        else{
            Log.log("plus.login:"+e);
            appPlugin.app.signIn(appPlugin.signType[e]);
        }
    }
    appPlugin.signOut=function(){
        if(!appPlugin.isInit){
            appPlugin.delayList.push(event);
            return;
        }
        appPlugin.app.signOut();
    }
    appPlugin.loginResult=function(msg){
        Log.log("plugin.loginResult:"+msg);
        EventHelper.call(AppCmd.LOGIN_RESULT,msg);
    }
    appPlugin.toPay=function(event,jsonStr){
        if(!appPlugin.isInit)return;
        Log.log("appPay："+jsonStr);
        appPlugin.app.toPay(jsonStr);
    }
    appPlugin.payResult=function(msg){
        EventHelper.call(AppCmd.PAY_RESULT,msg);
    }
    appPlugin.reportCreateRole=function(event,jsonStr){
        if(!appPlugin.isInit)return;
        Log.log("userinfoReport:"+jsonStr);
        appPlugin.reportParam=jsonStr;
        appPlugin.app.reportCreateRole(jsonStr);
    }
    appPlugin.reportInfo=function(event,jsonStr){
        if(!appPlugin.isInit)return;
        Log.log("userinfoReport:"+jsonStr);
        appPlugin.reportParam=jsonStr;
        appPlugin.app.reportUserInfo(jsonStr);
    }
    appPlugin.reportInfoResult=function(msg){
        if(msg==1)appPlugin.app.showLog("report success");
        else {
            appPlugin.app.showLog("report failed");
            appPlugin.app.reportUserInfo.apply(appPlugin.app,appPlugin.reportParam);
        }
    }
    appPlugin.pause=function(val){
        EventHelper.call(parseInt(val)==1?AppCmd.ONRESUME:AppCmd.PAUSE);
    }
    appPlugin.appLog=function(msg){
        if(!appPlugin.isInit)return;
        appPlugin.app.showLog(msg);
    }
    appPlugin.hideStatuAndMenu=function(){
        if(!appPlugin.isInit)return;
        plus.navigator.hideSystemNavigation();
        plus.ui.setFullscreen(true);
        setTimeout(appPlugin.hideStatuAndMenu,2000);
    }
    appPlugin.initShare=function(){
        if(!appPlugin.isInit)return;
       plus.share.getServices(function(ss){
           for(var i in ss){
               var s=ss[i];
               if(s.id=="weixin"){
                   shareService=s;
                   break;
               }
           }
       },function(err){
           alert("get wxshare failed："+e.message);
       });
    }
    appPlugin.share=function(){
        if(!appPlugin.isInit)return;
        var s=shareService;
        var msg={content:"我的甜蜜恋人",extra:{scene:null},pictures:""};
        s.send( msg, function(){
            //alert( "分享到\""+s.description+"\"成功！ " );
        }, function(e){
            //alert( "分享到\""+s.description+"\"失败: "+e.code+" - "+e.message );
        } );
    }
}(appPlugin));

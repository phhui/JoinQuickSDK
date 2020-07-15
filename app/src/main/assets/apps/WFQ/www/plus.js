document.addEventListener("plusready", onPlusReady, false );
var isApp=false;
var isRegEvent=false;
function onPlusReady() {
    appPlugin.init();
	isApp=true;
}
function regAppListener(){
    if(!EventHelper){
        setTimeout(regAppListener,500);
        return
    }
    EventHelper.addListener(AppCmd.LOGIN,appPlugin.signIn,appPlugin);
    EventHelper.addListener(AppCmd.LOGIN_BY_GOOGLE,appPlugin.signIn,appPlugin);
    EventHelper.addListener(AppCmd.LOGIN_BY_FACEBOOK,appPlugin.signIn,appPlugin);
    EventHelper.addListener(AppCmd.LOGIN_OUT,appPlugin.signOut,appPlugin);
    EventHelper.addListener(AppCmd.PAY,appPlugin.toPay,appPlugin);
    EventHelper.addListener(AppCmd.PAY_QUICKSDK,appPlugin.toPay,appPlugin);
    EventHelper.addListener(AppCmd.PAY_GOOGLE,appPlugin.toPay,appPlugin);
    EventHelper.addListener(AppCmd.REPORT_USERINFO,appPlugin.reportInfo,appPlugin);
    EventHelper.addListener(AppCmd.REPORT_CREATE_ROLE_QUICK,appPlugin.reportCreateRole,appPlugin);
    EventHelper.addListener(AppCmd.REPORT_USERINFO_QUICK,appPlugin.reportInfo,appPlugin);
}
regAppListener();
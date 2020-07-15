package com.tmlr.yx.qd;
import com.quicksdk.entity.UserInfo;
interface IQuickListener {
    void loginSuccess(UserInfo info);
    void loginFailed(String message, String trace);
    void paySuccess(String sdkOrderID, String cpOrderID,String extrasParams);
    void payFailed(String cpOrderID, String message, String trace);
    void reportFailed(String msg);
}

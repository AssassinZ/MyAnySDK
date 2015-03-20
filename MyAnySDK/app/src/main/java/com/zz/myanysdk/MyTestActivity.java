package com.zz.myanysdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.anysdk.framework.PluginWrapper;
import com.anysdk.framework.UserWrapper;
import com.anysdk.framework.java.AnySDK;
import com.anysdk.framework.java.AnySDKListener;
import com.anysdk.framework.java.AnySDKUser;


public class MyTestActivity extends Activity {

    private static boolean isInited = false;
    private Button loginButton;
    private Button platformButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initLayout();
        setBtnListener();
        initSDK();
        setListener();

    }

    private void initLayout() {
        loginButton = (Button) findViewById(R.id.button);
        platformButton = (Button) findViewById(R.id.button3);
    }

    private void setBtnListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInited) {
                    AnySDKUser.getInstance().login();
                    Log.i("!!!!!!", "login");
                }
            }
        });
        platformButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AnySDKUser.getInstance().isLogined()) {
                    //调用用户系统进入平台中心功能
                    if (AnySDKUser.getInstance().isFunctionSupported("enterPlatform")) {
                        AnySDKUser.getInstance().callFunction("enterPlatform");
                        Log.i("!!!!!!", "platform");
                    }
                }
            }
        });
    }

    private void initSDK() {
        String appKey = "6B5360E0-2EAF-F231-1516-37C1CFA48057";
        String appSecret = "d87569b8d930928f152704caf87754f7";
        String privateKey = "0A73DF39F13F5270FB9980ED18E3C2D7";
        String oauthLoginServer = "http://oauth.anysdk.com/api/OauthLoginDemo/Login.php";
        AnySDK.getInstance().initPluginSystem(this, appKey, appSecret, privateKey, oauthLoginServer);
        Log.i("!!!!!!", "initSDK");

    }

    private void setListener() {
        AnySDKUser.getInstance().setListener(new AnySDKListener() {
            @Override
            public void onCallBack(int arg0, String arg1) {
                switch (arg0) {
                    case UserWrapper.ACTION_RET_INIT_SUCCESS://初始化SDK成功回调
                        isInited = true;
                        Log.i("!!!!!!", "INIT_SUCCESS");
                        break;
                    case UserWrapper.ACTION_RET_INIT_FAIL://初始化SDK失败回调
                        isInited = false;
                        Log.i("!!!!!!", "INIT_FAIL");
                        break;
                    case UserWrapper.ACTION_RET_LOGIN_SUCCESS://登陆成功回调
                        Log.i("!!!!!!", "LOGIN_SUCCESS");
                        Log.i("!!!!!!", "UserId: " + AnySDKUser.getInstance().getUserID());
                        break;
                    case UserWrapper.ACTION_RET_LOGIN_TIMEOUT://登陆超时回调
                        break;
                    case UserWrapper.ACTION_RET_LOGIN_NO_NEED://无需登陆回调
                        Log.i("!!!!!!", "LOGIN_NO_NEED");
                        break;
                    case UserWrapper.ACTION_RET_LOGIN_CANCEL://登陆取消回调
                        Log.i("!!!!!!", "LOGIN_CANCEL");
                        break;
                    case UserWrapper.ACTION_RET_LOGIN_FAIL://登陆失败回调
                        Log.i("!!!!!!", "LOGIN_FAIL");
                        break;
                    case UserWrapper.ACTION_RET_LOGOUT_SUCCESS://登出成功回调
                        break;
                    case UserWrapper.ACTION_RET_LOGOUT_FAIL://登出失败回调
                        break;
                    case UserWrapper.ACTION_RET_PLATFORM_ENTER://平台中心进入回调
                        Log.i("!!!!!!", "PLATFORM_ENTER");
                        break;
                    case UserWrapper.ACTION_RET_PLATFORM_BACK://平台中心退出回调
                        Log.i("!!!!!!", "PLATFORM_BACK");
                        break;
                    case UserWrapper.ACTION_RET_PAUSE_PAGE://暂停界面回调
                        break;
                    case UserWrapper.ACTION_RET_EXIT_PAGE://退出游戏回调
                        break;
                    case UserWrapper.ACTION_RET_ANTIADDICTIONQUERY://防沉迷查询回调
                        break;
                    case UserWrapper.ACTION_RET_REALNAMEREGISTER://实名注册回调
                        break;
                    case UserWrapper.ACTION_RET_ACCOUNTSWITCH_SUCCESS://切换账号成功回调
                        break;
                    case UserWrapper.ACTION_RET_ACCOUNTSWITCH_FAIL://切换账号失败回调
                        break;
                    case UserWrapper.ACTION_RET_OPENSHOP://应用汇特有回调，接受到该回调调出游戏商店界面
                        break;
                    default:
                        break;
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        PluginWrapper.onDestroy();
        AnySDK.getInstance().release();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        PluginWrapper.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        PluginWrapper.onResume();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        PluginWrapper.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        PluginWrapper.onNewIntent(intent);
        super.onNewIntent(intent);
    }

    @Override
    protected void onStop() {
        PluginWrapper.onStop();
        super.onStop();
    }

    @Override
    protected void onRestart() {
        PluginWrapper.onRestart();
        super.onRestart();
    }
}

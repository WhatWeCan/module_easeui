package com.hyphenate.easeui.manager;

import android.content.Context;
import android.text.TextUtils;

import com.hdl.elog.ELog;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.EasyUiApplication;
import com.hyphenate.easeui.bean_cus.HXUser;
import com.hyphenate.easeui.cache.UserCacheManager;
import com.hyphenate.exceptions.HyphenateException;
import com.tjstudy.common.utils.APPUtils;

import java.util.Map;

/**
 * 环信管理，功能封装界面 外部直接调用这个进行基本的操作
 * Created by tjstudy on 2018/1/17.
 */

public class HXManager {
    private static HXManager ins;

    private HXManager() {
    }

    public static HXManager getIns() {
        if (ins == null) {
            synchronized (HXManager.class) {
                if (ins == null) {
                    ins = new HXManager();
                }
            }
        }
        return ins;
    }

    /**
     * 环信初始化(默认自动登录)
     *
     * @param appContext  appcontext
     * @param packageName 应用包名，避免多次初始化
     */
    public void init(Context appContext, String packageName) {
        init(appContext, packageName, true);
    }

    /**
     * 环信初始化
     *
     * @param appContext  appcontext
     * @param packageName 应用包名，避免多次初始化
     * @param isAutoLogin 不需要自动登录 设置为false
     */
    public void init(Context appContext, String packageName, boolean isAutoLogin) {
        //环信相关
        int pid = android.os.Process.myPid();
        String processAppName = APPUtils.getProcessName(pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回
        if (processAppName == null || !processAppName.equalsIgnoreCase(packageName)) {
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
        EMOptions options = new EMOptions();
        options.setAutoLogin(isAutoLogin);
        //初始化
        EaseUI.getInstance().init(appContext, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EasyUiApplication.setAppContext(appContext);
    }

    /**
     * 环信登录:先注册在登录，如果已经注册，会抛出一个指定的异常 但是不会影响到最后结果
     *
     * @param user          环信账号;密码;昵称;用户头像
     * @param loginCallback 登录结果
     */
    public void login(final HXUser user, final HXCallback loginCallback) {
        if (TextUtils.isEmpty(user.getName())) {
            loginCallback.onFailed(MyHxErrorCode.EM_NAME_EMPTY, "用户名为空");
            return;
        }
        //将这个token注册到环信  环信用户的ID 是 token  密码是用户的密码
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //注册失败会抛出HyphenateException
                    EMClient.getInstance().createAccount(user.getName(), user.getPwd());//同步方法
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    ELog.e("注册失败");
                }
                //注册成功了，进行登录,登录成功，将用户昵称和头像缓存到数据库
                EMClient.getInstance().login(user.getName(), user.getPwd(), new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        EMClient.getInstance().chatManager().loadAllConversations();
                        ELog.e("main", "登录聊天服务器成功！");
                        // 登录成功，将用户的环信ID、昵称和头像缓存在本地
                        UserCacheManager.save(user.getName(), user.getNick(), user.getHeadPic());
                        loginCallback.onSuccess();
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                        loginCallback.onFailed(code, message);
                        ELog.e("main", "登录聊天服务器失败！code=" + code + ";msg=" + message);
                    }
                });
            }
        }).start();
    }

    /**
     * 退出登录
     *
     * @param callback 操作结果反馈
     */
    public void logout(final HXCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().logout(true, new EMCallBack() {

                    @Override
                    public void onSuccess() {
                        ELog.e("退出登录成功");
                        callback.onSuccess();
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                        ELog.e("退出登录失败");
                        callback.onFailed(code, message);
                    }
                });
            }
        }).start();
    }

    /**
     * 判断用户是否登录环信
     *
     * @return 是否登录
     */
    public boolean isUserLogin() {
        return TextUtils.isEmpty(EMClient.getInstance().getCurrentUser());
    }

    /**
     * 获取所有的未读消息
     *
     * @return
     */
    public int getAllUnreadMsgSize() {
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        if (conversations == null || conversations.size() == 0) {
            return 0;
        }
        int size = 0;
        for (String key : conversations.keySet()) {
            EMConversation conversation = EMClient.getInstance().chatManager().getConversation(key);
            if (conversation != null) {
                size += conversation.getUnreadMsgCount();
            }
        }
        return size;
    }

    /**
     * 将用户缓存到本地
     *
     * @param hxUser 环信用户
     */
    public void save(HXUser hxUser) {
        UserCacheManager.save(hxUser.getName(), hxUser.getNick(), hxUser.getHeadPic());
    }
}

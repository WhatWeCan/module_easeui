package com.hyphenate.easeui.manager;

/**
 * 环信登录 结果反馈
 * Created by tjstudy on 2018/1/17.
 */

public interface HXCallback {
    void onSuccess();

    /**
     * 返回登录错误消息
     * @param code code值的定义查看EMError，code=-表示自定义错误码，用户名为空
     * @param msg 对应环信官网http://docs.easemob.com/start/450errorcode/20androiderrorcode
     */
    void onFailed(int code, String msg);
}

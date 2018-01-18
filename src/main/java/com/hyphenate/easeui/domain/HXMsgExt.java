package com.hyphenate.easeui.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * 环信拓展消息
 * Created by tjstudy on 2018/1/17.
 */

public class HXMsgExt implements Serializable {
    private Map<String, String> msgExt;

    public HXMsgExt(Map<String, String> msgExt) {
        this.msgExt = msgExt;
    }

    public Map<String, String> getMsgExt() {
        return msgExt;
    }

    public void setMsgExt(Map<String, String> msgExt) {
        this.msgExt = msgExt;
    }
}

package com.hyphenate.easeui;

import android.content.Context;

/**
 * Created by tjstudy on 2017/9/21.
 */

public class EasyUiApplication {

    private static Context appContext;

    public static void setAppContext(Context appContext) {
        EasyUiApplication.appContext = appContext;
    }

    public static Context getAppContext() {
        return appContext;
    }
}

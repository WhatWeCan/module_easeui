package com.hyphenate.easeui.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.bean_cus.HXUser;

/**
 * 聊天界面
 */
public class ChatActivity extends EaseBaseActivity {

    private static HXUser curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        EaseChatFragment chatFragment = new EaseChatFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EaseConstant.EXTRA_USER, curUser);
        chatFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_chat, chatFragment, "chat")
                .commit();
    }

    public static void startActivity(Context context, HXUser hxUser) {
        Intent intent = new Intent(context, ChatActivity.class);
        curUser = hxUser;
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        curUser = null;
    }
}

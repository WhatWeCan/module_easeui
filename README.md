## 环信EaseUI module快速接入

###  将module导入到主项目中  

1. 清单文件中的配置信息  
	
		<!-- Required -->
	    <uses-permission android:name="android.permission.VIBRATE" />
	    <uses-permission android:name="android.permission.INTERNET" />
	    <uses-permission android:name="android.permission.RECORD_AUDIO" />
	    <uses-permission android:name="android.permission.CAMERA" />
	    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
	    <uses-permission android:name="android.permission.ACCESS_MOCK_LOCATION" />
	    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
	    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>  
	    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
	    <uses-permission android:name="android.permission.GET_TASKS" />
	    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
	    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
	    <uses-permission android:name="android.permission.WAKE_LOCK" />
	    <uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" />
	    <uses-permission android:name="android.permission.READ_PHONE_STATE" />   

		<!-- 设置环信应用的AppKey -->
    	<meta-data android:name="EASEMOB_APPKEY"  android:value="Your AppKey" />

		<activity android:name="com.hyphenate.easeui.ui.ChatActivity" />
        <!--查看地图的View-->
        <activity android:name="com.hyphenate.easeui.ui.EaseBaiduMapActivity" />
        <!--查看大图的View-->
        <activity android:name="com.hyphenate.easeui.ui.EaseShowBigImageActivity" />

2. 打包混淆  

	    -keep class com.easemob.** {*;}
    	-keep class org.jivesoftware.** {*;}
    	-keep class org.apache.** {*;}
    	-dontwarn  com.easemob.**
    	#2.0.9后的不需要加下面这个keep
    	#-keep class org.xbill.DNS.** {*;}
    	#另外，demo中发送表情的时候使用到反射，需要keep SmileUtils
    	-keep class com.easemob.chatuidemo.utils.SmileUtils {*;}
    	#注意前面的包名，如果把这个类复制到自己的项目底下，比如放在com.example.utils底下，应该这么写（实际要去掉#）
    	#-keep class com.example.utils.SmileUtils {*;}
    	#如果使用EaseUI库，需要这么写
    	-keep class com.easemob.easeui.utils.EaseSmileUtils {*;}
    	
    	#2.0.9后加入语音通话功能，如需使用此功能的API，加入以下keep
    	-dontwarn ch.imvs.**
    	-dontwarn org.slf4j.**
    	-keep class org.ice4j.** {*;}
    	-keep class net.java.sip.** {*;}
    	-keep class org.webrtc.voiceengine.** {*;}
    	-keep class org.bitlet.** {*;}
    	-keep class org.slf4j.** {*;}
    	-keep class ch.imvs.** {*;}

### 功能代码块

1. Application环信初始化
	
		HXManager.getIns().init(this, getPackageName());//默认自动登录 
		HXManager.getIns().init(this, getPackageName(),false);//设置为不自动登录 
2. 登录  
	HXManager内部处理：登录的时候先注册，然后进行登录，注册失败会直接跑出异常捕捉这个异常，可以不进行其他处理。这里的token和password由第一次登录上服务器，登录成功之后返回的数据（数据安全方面的考虑）。在登录聊天服务器成功之后，最好直接加载对话列表（环信文档推荐）
		HXManager.getIns().login(new HXUser(name, pwd), new HXCallback() {
                    @Override
                    public void onSuccess() {
                        ELog.e("登录成功");
                    }

                    @Override
                    public void onFailed(int code, String msg) {
                        //对应于环信后台的错误码和错误消息，code=-1为自定义错误，用户名为空
                    }
                });  
3. 退出登录
	
		HXManager.getIns().logout(new HXCallback() {
                    @Override
                    public void onSuccess() {
                        
                    }

                    @Override
                    public void onFailed(int code, String msg) {

                    }
                });
4. 获取所有的未读消息
	
	 	HXManager.getIns().getAllUnreadMsgSize()
5. 判断当前是否有用户登录 
	
		HXManager.getIns().isUserLogin()

6. 聊天  
	
	- fragment  
	
			EaseChatFragment chatFragment = new EaseChatFragment();
	        Bundle bundle = new Bundle();
	        bundle.putSerializable(EaseConstant.EXTRA_USER, curUser);
	        chatFragment.setArguments(bundle);
	        getSupportFragmentManager().beginTransaction()
	                .add(R.id.fl_chat, chatFragment, "chat")
	                .commit();
	- activity
		
			ChatActivity.startActivity(this,new HXUser(name,pwd));  
7. 对话列表

	对话列表本身是一个Fragment，可以将这个fragment touch到activity中显示，对话列表的长按事件处理如下  
	
		EaseConversationListFragment msgFrag = new EaseConversationListFragment();
        //对话列表 长按事件
        msgFrag.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {

            @Override
            public void onListItemClicked(EMConversation conversation) {
            }

            @Override
            public void onListItemLongClicked(final EMConversation conversation) {
                //删除和某个user的整个的聊天记录（包括本地）
            }
        });
8. 用户在别处登录，当前用户被踢除会接收到一个反馈信息  
	
		EMClient.getInstance().addConnectionListener(new EMConnectionListener() {
	            @Override
	            public void onConnected() {
	
	            }
	
	            @Override
	            public void onDisconnected(int error) {
	                if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
	                    if (!isOtherLogin) {
	                        isOtherLogin = true;
	                        showToast("您的账号在别处登录了");
	                        LoginActivity.startActivity(mContext);
	                        finish();
	                    }
	                }
	            }
	        });
9. 用户接收到聊天消息  

		EMMessageListener msgListener = new EMMessageListener() {
	
	            @Override
	            public void onMessageReceived(final List<EMMessage> messages) {
	                runOnUiThread(new Runnable() {
	                    @Override
	                    public void run() {
						   // 刷新未读消息数
	                       // msgFrag.refresh();
	                       // if (currentTabIndex != 3) {
	                       //     showUnReadMsg();
	                       // }
	                    }
	                });
	            }
	
	            @Override
	            public void onCmdMessageReceived(List<EMMessage> messages) {
	                //收到透传消息
	            }
	
	            @Override
	            public void onMessageRead(List<EMMessage> messages) {
	                //收到已读回执
	            }
	
	            @Override
	            public void onMessageDelivered(List<EMMessage> message) {
	                //收到已送达回执
	            }
	
	            @Override
	            public void onMessageRecalled(List<EMMessage> messages) {
	                //消息被撤回
	            }
	
	            @Override
	            public void onMessageChanged(EMMessage message, Object change) {
	                //消息状态变动
	            }
	        };
	        EMClient.getInstance().chatManager().addMessageListener(msgListener); 
10. 将用户信息缓存到本地(在用户修改头像，昵称时需要更新缓存)   
	使用环信一般都会涉及到用户昵称和头像的携带，这里的处理方式是通过ormlite将相关的数据都缓存到本地。缓存方式：
  
		HXManager.getIns().save(HxUser user);   
11. 拓展消息  
	
	- 拓展消息字段  
	
		当前已加入拓展消息处理，当前扩展消息包括（当前用户的userId,nickName和头像），可能需要重新定义拓展消息的字段，这里开放了字段的设置，可以在Application中将默认的字段设置为所需字段，字段位置Contants。最好只设置一次  
		默认字段分别为  
	
				String EXT_CHAT_NAME = "ChatUserId";// 环信ID
				String EXT_CHAT_NICK = "ChatUserNick";// 昵称
				String EXT_CHAT_PIC = "ChatUserPic";// 头像Url 
	- 自定义扩展消息  
	  
		发送消息时添加的拓展消息：定位 UserCacheManager.java  _setMsgExt  
		接收消息时的拓展消息：定位UserCacheManager.java		__public static void save(Map<String, Object> ext)
	
12. 本地缓存失败
		
	本地数据库缓存失败，从服务器获取用户昵称头像数据，将数据改为从服务器获取。定位UserCacheManager.java,查找get方法，根据注释进行设置。  
		
		UserCacheManager.java
		/**
	     * 获取用户信息
	     *
	     * @param userId 用户环信ID
	     * @return
	     */
	    public static UserCacheInfo get(final String userId) {
	        UserCacheInfo info = null;
	
	        // 如果本地缓存不存在或者过期，则从存储服务器获取
	        if (notExistedOrExpired(userId)) {
	            // TODO: 2017/9/21 显示昵称修改，在主项目中进行，主项目中获取到要交易的用户信息，然后保存到本地数据库
	            Map<String, Object> params = new HashMap<>();//构造请求的参数
	            params.put("v", "1.0.1");
	            params.put("token", userId);
	            MyHttpUtils.build()
	                    .url(EaseConstant.userInfo_url)
	                    .addParams(params)
	                    .setJavaBean(User.class)
	                    .onExecuteByPost(new CommCallback<User>() {
	                        @Override
	                        public void onSucceed(User user) {
	                            String nickName = user.getNickname();
	                            String pic = user.getHead_pic();
	                            save(userId, nickName, pic);
	                        }
	
	                        @Override
	                        public void onFailed(Throwable throwable) {
	                        }
	                    });
	        }
	
	        // 从本地缓存中获取用户数据
	        info = getFromCache(userId);
	        if (info != null) {
	            String nickName = info.getNickName();
	            info.setNickName(TextUtils.isEmpty(nickName) ? "对方没有设置昵称" : nickName);
	        }
	
	        return info;
	    }  

### 环信的个性化修改
1. TitleBar的统一
	
	环信中封装有一个EaseTitleBar,可以在EaseBaseFragment.java 中查看title_bar，可以进行的操作(设置标题内容，左右图片)

		<com.hyphenate.easeui.widget.EaseTitleBar
		    android:id="@+id/title_bar"
		    android:layout_width="match_parent"
		    android:layout_height="wrap_content"
		    app:titleBarLeftImage="@drawable/ease_mm_title_back" /> 
	
		titleBar = (EaseTitleBar) getView().findViewById(R.id.title_bar);
		titleBar.setTitle("张建国");
		titleBar.setRightImageResource(R.drawable.ease_mm_title_remove);  
	当前只有聊天界面涉及到了标题栏，聊天界面默认显示的标题为聊天对象的用户昵称，没有昵称时-“对方没有设置昵称”，如果要修改没有昵称时的显示快速定位至：EaseChatFragment.java______ctrl F:对方没有设置昵称
2. 处理聊天消息中的指定消息（这里处理商品链接，点击跳转到APP对应的商品详情）  
	定位：EaseChatFragment.java ______ctrl f:第一个onMessageBubbleClick  
	处理示例：  
	
				//判断是否是链接
                //是链接就跳转到商品详情界面
                if (message.getType() == TXT) {
                    String content = ((EMTextMessageBody) message.getBody()).getMessage();
                    if (content.contains("details/goods_id/") &&
                            content.contains("/token/") &&
                            content.contains("/v/")) {
                        Log.e("tjs", "是链接");
                        //返回主页进行处理
                        goodsOper.onClickGoodsUrl(content);
                    } else {
                        Log.e("tjs", "不是链接");
                    }
                } 
		
	TIPS：goodsOper 是开启EaseChatFragment界面时传入的接口回调，由于是在单独的一个module中，这个module不能进行一些特定的处理（可以使用EventBus，广播等进行处理，这里的处理方式是接口回调）
3. 在聊天界面顶部加入商品View  
	定位：EaseChatFragment.java________ctrl F:onCreateView  
	点进去这个聊天界面的Layout,添加上所需要的View 
		
	示例： 
			
		<include layout="@layout/wubai_chat_goods" />  

		wubai_chat_goods.xml
		<?xml version="1.0" encoding="utf-8"?>
		<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
		    android:layout_width="match_parent"
		    android:layout_height="wrap_content"
		    android:background="@color/color_9ccc"
		    android:orientation="vertical">
		
		    <LinearLayout
		        android:id="@+id/ll_goods"
		        android:layout_width="match_parent"
		        android:layout_height="wrap_content"
		        android:layout_marginTop="1dp"
		        android:background="#fff"
		        android:gravity="center"
		        android:orientation="horizontal"
		        android:padding="10dp">
		
		        <ImageView
		            android:id="@+id/iv_goods_pic"
		            android:layout_width="100dp"
		            android:layout_height="100dp"
		            android:scaleType="centerCrop" />
		
		        <LinearLayout
		            android:layout_width="match_parent"
		            android:layout_height="wrap_content"
		            android:layout_marginLeft="20dp"
		            android:orientation="vertical">
		
		            <TextView
		                android:id="@+id/tv_goods_title"
		                android:layout_width="match_parent"
		                android:layout_height="wrap_content"
		                android:layout_weight="1"
		                android:ellipsize="end"
		                android:lineSpacingExtra="6dp"
		                android:maxLines="2"
		                android:textSize="14sp" />
		
		            <LinearLayout
		                android:layout_width="match_parent"
		                android:layout_height="wrap_content"
		                android:layout_marginTop="2dp"
		                android:orientation="horizontal">
		
		                <TextView
		                    android:id="@+id/tv_goods_store"
		                    android:layout_width="wrap_content"
		                    android:layout_height="wrap_content"
		                    android:layout_weight="1"
		                    android:text="库存7"
		                    android:textColor="#6000"
		                    android:textSize="14sp" />
		
		                <TextView
		                    android:id="@+id/tv_goods_rent"
		                    android:layout_width="wrap_content"
		                    android:layout_height="wrap_content"
		                    android:layout_weight="1"
		                    android:text="已租178"
		                    android:textColor="#6000"
		                    android:textSize="14sp" />
		
		                <TextView
		                    android:id="@+id/tv_goods_talk"
		                    android:layout_width="wrap_content"
		                    android:layout_height="wrap_content"
		                    android:layout_weight="1"
		                    android:text="评价132"
		                    android:textColor="#6000"
		                    android:textSize="14sp" />
		
		                <ImageView
		                    android:layout_width="wrap_content"
		                    android:layout_height="match_parent"
		                    android:src="@drawable/ic_gray_address" />
		
		                <TextView
		                    android:id="@+id/tv_des_distance"
		                    android:layout_width="wrap_content"
		                    android:layout_height="wrap_content"
		                    android:gravity="center|left"
		                    android:textColor="#6000"
		                    android:textSize="14sp" />
		            </LinearLayout>
		
		            <LinearLayout
		                android:layout_width="match_parent"
		                android:layout_height="wrap_content"
		                android:layout_marginTop="10dp"
		                android:orientation="horizontal">
		
		                <TextView
		                    android:id="@+id/tv_goods_price"
		                    android:layout_width="wrap_content"
		                    android:layout_height="wrap_content"
		                    android:layout_weight="1"
		                    android:gravity="center|left"
		                    android:text="￥66.89/天"
		                    android:textColor="#d12325"
		                    android:textSize="14sp" />
		
		                <TextView
		                    android:id="@+id/tv_goods_desp"
		                    android:layout_width="wrap_content"
		                    android:layout_height="wrap_content"
		                    android:layout_weight="1"
		                    android:text="押金132"
		                    android:textColor="#6000"
		                    android:textSize="14sp"
		                    android:visibility="gone" />
		
		                <TextView
		                    android:id="@+id/btn_goods_urge"
		                    android:layout_width="wrap_content"
		                    android:layout_height="wrap_content"
		                    android:layout_margin="2dp"
		                    android:background="@drawable/shape_ret_yel_white"
		                    android:gravity="center"
		                    android:paddingBottom="5dp"
		                    android:paddingLeft="10dp"
		                    android:paddingRight="10dp"
		                    android:paddingTop="5dp"
		                    android:text="催他"
		                    android:textColor="#d12325" />
		
		                <TextView
		                    android:id="@+id/btn_goods_sub"
		                    android:layout_width="wrap_content"
		                    android:layout_height="wrap_content"
		                    android:layout_margin="2dp"
		                    android:background="@drawable/shape_ret_yel_white"
		                    android:gravity="center"
		                    android:paddingBottom="5dp"
		                    android:paddingLeft="10dp"
		                    android:paddingRight="10dp"
		                    android:paddingTop="5dp"
		                    android:text="发送链接"
		                    android:textColor="#d12325" />
		            </LinearLayout>
		        </LinearLayout>
		    </LinearLayout>
		
		</LinearLayout>

	在onActivityCreated末尾设置商品信息，商品信息，由开启这个界面时带入。
	
		/**
	     * 显示商品信息
	     *
	     * @param goods 商品实体
	     */
	    private void showGoods(final Goods goods) {
	        if (goods == null) {
	            mViewGoods.setVisibility(View.GONE);
	            return;
	        }
	
	        mActivity.runOnUiThread(new Runnable() {
	            @Override
	            public void run() {
	                Log.e("tjs", "当前显示=" + goods.toString());
	                Glide.with(mActivity)
	                        .load(goods.getOriginal_img())
	                        .into(mIvGoodsPic);
	                mTvGoodsTitle.setText(goods.getGoods_name());
	                mTvGoodsStore.setText("库存" + goods.getStore_count());
	                mTvGoodsRent.setText("已租" + goods.getSales_sum());
	                mTvGoodsTalk.setText("评价" + goods.getComment_count());
	                mTvGoodsDistance.setText(goods.getDistance() + "km");
	                mTvGoodsPrice.setText(goods.getRent_price() + GlobleFiled.getRentType(goods.getRent_way()));
	
	                mTvGoodsSub.setOnClickListener(new OnClickListener() {
	                    @Override
	                    public void onClick(View v) {
	                        sendTextMessage(goodsUrl);
	                    }
	                });
	                mTvGoodsUrge.setOnClickListener(new OnClickListener() {
	                    @Override
	                    public void onClick(View v) {
	                        goodsOper.onGoodsUrge(goods.getGoods_id() + "");
	                    }
	                });
	                mViewGoods.setOnClickListener(new OnClickListener() {
	                    @Override
	                    public void onClick(View v) {
	                        goodsOper.onGoodsClick(new Gson().toJson(goods));
	                    }
	                });
	            }
	        });
	    }
		
	商品事件回调处理接口：
	
		/**
		 * 商品接口处理类
		 * Created by tjstudy on 2017/11/3.
		 */
		
		public interface OnGoodsOperate {
		
		    /**
		     * 商品点击
		     *
		     * @param goodsInfo
		     */
		    void onGoodsClick(String goodsInfo);
		
		    /**
		     * 商品 提交订单
		     *
		     * @param goodsInfo
		     */
		    void onClickGoodsUrl(String goodsInfo);
		
		    void onGoodsUrge(String goodsId);
		}
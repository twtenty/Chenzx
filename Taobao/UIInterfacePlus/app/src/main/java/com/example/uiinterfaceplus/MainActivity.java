package com.example.uiinterfaceplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.uiinterfaceplus.fragment.FirstPageFragment;
import com.example.uiinterfaceplus.fragment.MessageFragment;
import com.example.uiinterfaceplus.fragment.MyFragment;
import com.example.uiinterfaceplus.fragment.ShopFragment;
import com.example.uiinterfaceplus.fragment.WeiFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    //底部五个Fragment
//    LinearLayout microTaoLay;
//    LinearLayout messageLay;
//    LinearLayout buycarLay;
//    LinearLayout myfileLay;
//    LinearLayout firstPageLay;
//
//    ImageView microTaoIcon;
//    ImageView messageIcon;
//    ImageView buycarIcon;
//    ImageView myfileIcon;
//    ImageView firstPageIcon;
//    ImageView titleImage;
//
//    TextView microTaoText;
//    TextView messageText;
//    TextView buycarText;
//    TextView myfileText;
//
//
//    FragmentManager manager;
//    FragmentTransaction transaction;
//    FirstPageFragment firFragment;
//    WeiFragment microFragment;
//    MessageFragment messageFragment;
//    ShopFragment buycarFragment;
//    MyFragment myfileFragment;

//    static public FragmentState fragmentState = new FragmentState();

    //底部导航栏开始

    //底部五个Fragment
    private FirstPageFragment homeFragment;
    private WeiFragment weiFragment;
    private MessageFragment messageFragment;
    private ShopFragment shopFragment;
    private MyFragment myFragment;

    //底部五个Linearlayout

    private LinearLayout    ll_home;
    private LinearLayout    ll_wei;
    private LinearLayout    ll_message;
    private LinearLayout    ll_shop;
    private LinearLayout    ll_my;

    //底部五个ImageView

    private ImageView       iv_home;
    private ImageView       iv_wei;
    private ImageView       iv_message;
    private ImageView       iv_shop;
    private ImageView       iv_my;

    //底部五个标题

    private TextView        tv_home;
    private TextView        tv_wei;
    private TextView        tv_message;
    private TextView        tv_shop;
    private TextView        tv_my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        //底部
//        manager = getSupportFragmentManager();
//        transaction = manager.beginTransaction();
//        firFragment = new FirstPageFragment();
//        transaction.add(R.id.fragment_container, firFragment);
//        transaction.commit();
//        initUI();
//
//        //初始化底部按钮事件

        //初始化控件

        initView();

        //初始化底部按钮事件

        initEvent();

        initFragment(1);

    }

    private void initView(){
        ll_home=(LinearLayout)findViewById(R.id.home);
        ll_wei=(LinearLayout)findViewById(R.id.weiTao);
        ll_message=(LinearLayout)findViewById(R.id.message);
        ll_shop=(LinearLayout)findViewById(R.id.shop);
        ll_my=(LinearLayout)findViewById(R.id.me);

        iv_home=(ImageView)findViewById(R.id.imageButton_home);
        iv_wei=(ImageView)findViewById((R.id.imageButton_weiTao));
        iv_message=(ImageView)findViewById(R.id.imageButton_message);
        iv_shop=(ImageView)findViewById(R.id.imageButton_shop);
        iv_my=(ImageView)findViewById(R.id.imageButton_me);

        tv_home=(TextView)findViewById(R.id.textView_1);
        tv_wei=(TextView)findViewById(R.id.textView_2);
        tv_message=(TextView)findViewById(R.id.textView_3);
        tv_shop=(TextView)findViewById(R.id.textView_4);
        tv_my=(TextView)findViewById(R.id.textView_5);
    }

    private void initEvent(){
        ll_home.setOnClickListener(this);
        ll_wei.setOnClickListener(this);
        ll_message.setOnClickListener(this);
        ll_shop.setOnClickListener(this);
        ll_my.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        //刷新底部图标和标题的颜色
        refreshBottom();

        if(view==ll_home){
            iv_home.setImageResource(R.drawable.taobaoclick);
            tv_home.setTextColor(Color.rgb(216, 30, 6));
            initFragment(1);
        }else if(view==ll_wei){
            iv_wei.setImageResource(R.drawable.weitaoclick);
            tv_wei.setTextColor(Color.rgb(216, 30, 6));
            initFragment(2);
        }else if (view==ll_message){
            iv_message.setImageResource(R.drawable.xiaoxiclick);
            tv_message.setTextColor(Color.rgb(216, 30, 6));
            initFragment(3);
        }else if (view==ll_shop){
            iv_shop.setImageResource(R.drawable.shopclick);
            tv_shop.setTextColor(Color.rgb(216, 30, 6));
            initFragment(4);
        }else if (view==ll_my){
            iv_my.setImageResource(R.drawable.meclick);
            tv_my.setTextColor(Color.rgb(216, 30, 6));
            initFragment(5);
        }
    }

    private void refreshBottom(){
        iv_home.setImageResource(R.drawable.taobao);
        iv_wei.setImageResource(R.drawable.weitao);
        iv_message.setImageResource(R.drawable.xiaoxi);
        iv_shop.setImageResource(R.drawable.shop);
        iv_my.setImageResource(R.drawable.me);

        tv_home.setTextColor(Color.rgb(0, 0, 0));
        tv_wei.setTextColor(Color.rgb(0, 0, 0));
        tv_message.setTextColor(Color.rgb(0, 0, 0));
        tv_shop.setTextColor(Color.rgb(0, 0, 0));
        tv_my.setTextColor(Color.rgb(0, 0, 0));

    }

    private void initFragment(int i){
        //开始事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //隐藏所有的fragment
        hideFragment(transaction);

        switch (i){
            case 1:
                if(homeFragment == null){
                    homeFragment = new FirstPageFragment();
                    transaction.add(R.id.tb_fragment,homeFragment);
                }else{
                    iv_home.setImageResource(R.drawable.taobaoclick);
                    tv_home.setTextColor(Color.rgb(216, 30, 6));
                    transaction.show(homeFragment);
                    // Toast.makeText(this,"home",Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if(weiFragment == null){
                    weiFragment = new WeiFragment();
                    transaction.add(R.id.tb_fragment,weiFragment);
                }else{
                    transaction.show(weiFragment);
                    //Toast.makeText(this,"wei",Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                if(messageFragment == null){
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.tb_fragment,messageFragment);
                }else{
                    transaction.show(messageFragment);
                    //Toast.makeText(this,"mesage",Toast.LENGTH_SHORT).show();
                }
                break;
            case 4:
                if(shopFragment == null){
                    shopFragment = new ShopFragment();
                    transaction.add(R.id.tb_fragment,shopFragment);
                }else{
                    transaction.remove(shopFragment);
                    shopFragment = new ShopFragment();
                    transaction.add(R.id.tb_fragment,shopFragment);
                    // Toast.makeText(this,"shop",Toast.LENGTH_SHORT).show();
                }
                break;
            case 5:
                if(myFragment == null){
                    myFragment = new MyFragment();
                    transaction.add(R.id.tb_fragment,myFragment);
                }else{
                    transaction.show(myFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction){
        if(homeFragment!= null){
            transaction.hide(homeFragment);
        }
        if(weiFragment!= null){
            transaction.hide(weiFragment);
        }
        if(messageFragment!= null){
            transaction.hide(messageFragment);
        }
        if(shopFragment!= null){
            transaction.hide(shopFragment);
        }
        if(myFragment!=null){
            transaction.hide(myFragment);
        }
    }


//    //底部导航
//
//    private void initUI() {
//        microTaoLay = findViewById(R.id.micro_tao_layout);
//        messageLay = findViewById(R.id.message_layout);
//        buycarLay = findViewById(R.id.buycar_layout);
//        myfileLay = findViewById(R.id.myfile_layout);
//        firstPageLay = findViewById(R.id.first_page_layout);
//        firstPageLay.setVisibility(View.INVISIBLE);
//
//        microTaoIcon = findViewById(R.id.microtao_icon);
//        messageIcon = findViewById(R.id.message_icon);
//        buycarIcon = findViewById(R.id.buycar_icon);
//        myfileIcon = findViewById(R.id.myfile_icon);
//        firstPageIcon = findViewById(R.id.first_page_icon);
//        titleImage = findViewById(R.id.title_image);
//
//        microTaoText = findViewById(R.id.microtao_text);
//        messageText = findViewById(R.id.message_text);
//        buycarText = findViewById(R.id.buycar_text);
//        myfileText = findViewById(R.id.myfile_text);
//
//        microTaoLay.setOnClickListener(this);
//        messageLay.setOnClickListener(this);
//        buycarLay.setOnClickListener(this);
//        myfileLay.setOnClickListener(this);
//        firstPageLay.setOnClickListener(this);
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        transaction = manager.beginTransaction();
//        hideFragment(transaction);//隐藏之前的Fragment
//        switch (v.getId()) {
//            case R.id.micro_tao_layout:
//                microFragment = new WeiFragment();
//                transaction.add(R.id.fragment_container, microFragment);
//                transaction.commit();
//                microTaoIcon.setImageDrawable(getResources().getDrawable(R.drawable.weitaoclick));
//                microTaoText.setTextColor(Color.RED);
//
//                //显示首页布局，隐藏标题淘宝图片
//                if (firstPageLay.getVisibility() != View.VISIBLE) {
//                    firstPageLay.setVisibility(View.VISIBLE);
//                    titleImage.setVisibility(View.INVISIBLE);
//                }
//
//                break;
//            case R.id.message_layout:
//                messageFragment = new MessageFragment();
//                transaction.add(R.id.fragment_container, messageFragment);
//                transaction.commit();
//                messageIcon.setImageDrawable(getResources().getDrawable(R.drawable.xiaoxiclick));
//                messageText.setTextColor(Color.RED);
//
//                //显示首页布局，隐藏标题淘宝图片
//                if (firstPageLay.getVisibility() != View.VISIBLE) {
//                    firstPageLay.setVisibility(View.VISIBLE);
//                    titleImage.setVisibility(View.INVISIBLE);
//                }
//                break;
//            case R.id.buycar_layout:
//                buycarFragment = new ShopFragment();
//                transaction.add(R.id.fragment_container, buycarFragment);
//                transaction.commit();
//                buycarIcon.setImageDrawable(getResources().getDrawable(R.drawable.shopclick));
//                buycarText.setTextColor(Color.RED);
//
//                //显示首页布局，隐藏标题淘宝图片
//                if (firstPageLay.getVisibility() != View.VISIBLE) {
//                    firstPageLay.setVisibility(View.VISIBLE);
//                    titleImage.setVisibility(View.INVISIBLE);
//                }
//                break;
//            case R.id.myfile_layout:
//                myfileFragment = new MyFragment();
//                transaction.add(R.id.fragment_container, myfileFragment);
//                transaction.commit();
//                myfileIcon.setImageDrawable(getResources().getDrawable(R.drawable.meclick));
//                myfileText.setTextColor(Color.RED);
//
//                //显示首页布局，隐藏标题淘宝图片
//                if (firstPageLay.getVisibility() != View.VISIBLE) {
//                    firstPageLay.setVisibility(View.VISIBLE);
//                    titleImage.setVisibility(View.INVISIBLE);
//                }
//                break;
//
//            case R.id.first_page_layout:
//                firFragment = new FirstPageFragment();
//                transaction.add(R.id.fragment_container, firFragment);
//                transaction.commit();
//                firstPageLay.setVisibility(View.INVISIBLE);
//                titleImage.setVisibility(View.VISIBLE);
//
//            default:
//                break;
//        }
//    }
//
//    private void hideFragment(FragmentTransaction transaction) {
//        if (firFragment != null) {
//            transaction.remove(firFragment);
//
//        }
//        if (microFragment != null) {
//            transaction.remove(microFragment);
//            microTaoIcon.setImageDrawable(getResources().getDrawable(R.drawable.weitao));
//            microTaoText.setTextColor(Color.BLACK);
//
//        }
//        if (messageFragment != null) {
//            transaction.remove(messageFragment);
//            messageIcon.setImageDrawable(getResources().getDrawable(R.drawable.xiaoxi));
//            messageText.setTextColor(Color.BLACK);
//        }
//        if (buycarFragment != null) {
//            transaction.remove(buycarFragment);
//            buycarIcon.setImageDrawable(getResources().getDrawable(R.drawable.shop));
//            buycarText.setTextColor(Color.BLACK);
//        }
//        if (myfileFragment != null) {
//            transaction.remove(myfileFragment);
//            myfileIcon.setImageDrawable(getResources().getDrawable(R.drawable.me));
//            myfileText.setTextColor(Color.BLACK);
//        }
//    }

}
package com.example.uiinterfaceplus.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.uiinterfaceplus.activity.MainActivity2;
import com.example.uiinterfaceplus.R;
import com.example.uiinterfaceplus.adapter.RecyclerGridAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * author:why
 * created on: 2018/10/3 12:11
 * description:
 */
public class FirstPageFragment extends Fragment {

    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;

    //计算倒计时
    TextView mainTv;

    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id，可以根据自己的需求进行修改
    private int[] imageIds = new int[]{
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four
    };
    //存放图片的标题
    private String[] titles = new String[]{
            "轮播1",
            "轮播2",
            "轮播3",
            "轮播4"
    };
    private TextView title;
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;
//    //图片轮播
//    private static final long ONECE_TIME = 0;
//    private static final long TOTAL_TIME = 60;
//    private ViewPager viewPager;  //轮播图模块
//    private int[] mImg;
//    private int[] mImg_id;
//    private String[] mDec;
//    private ArrayList<ImageView> mImgList;
//    private LinearLayout ll_dots_container;
//    private TextView loop_dec;
//    private int previousSelectedPosition = 0;
//    boolean isRunning = false;

    //文字轮播
    private ViewFlipper mvF;
    private View view;

    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        //图片轮播
//        initLoopView(view);  //实现轮播图

        setView(view);

//        recycler
        layoutManager = new GridLayoutManager(getActivity(),2);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerGridAdapter(getActivity().getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        倒计时
        mainTv = view.findViewById(R.id.time);
        new TimeThread().start();//启动线程

        //文字轮播
        mvF = (ViewFlipper) view.findViewById(R.id.vf);
        mvF.startFlipping();

        intent = new Intent(getActivity(), MainActivity2.class);

        //搜索跳转
        Button btn_main = (Button) view.findViewById(R.id.search);
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        return view;
    }


    //图片轮播
    private void setView(View view){
        mViewPaper = (ViewPager)view.findViewById(R.id.vp);
        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i = 0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的小点
        dots = new ArrayList<View>();
        dots.add(view.findViewById(R.id.dot_0));
        dots.add(view.findViewById(R.id.dot_1));
        dots.add(view.findViewById(R.id.dot_2));
        dots.add(view.findViewById(R.id.dot_3));

        title = (TextView) view.findViewById(R.id.title);
        title.setText(titles[0]);
        adapter = new ViewPagerAdapter();

        mViewPaper.setAdapter(adapter);
        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.drawable.white_dot);
                dots.get(oldPosition).setBackgroundResource(R.drawable.gray_dot);
                oldPosition = position;
                currentItem = position;
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }
            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }
    /*定义的适配器*/
    public class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return images.size();
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
//          TODO Auto-generated method stub
//          super.destroyItem(container, position, object);
//          view.removeView(view.getChildAt(position));
//          view.removeViewAt(position);
            view.removeView(images.get(position));
        }
        @Override
        public Object instantiateItem(ViewGroup view, int position) {
// TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }
    }

    /**
     * 利用线程池定时执行动画轮播
     */

    @Override
    public void onStart() {
// TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
    }

    private class ViewPageTask implements Runnable{
        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }
//此处可以修改数字，改变轮播定时时间

    /**
     * 图片轮播任务
     * @author liuyazhuang
     *
     *  /
    /**
     * 接收子线程传递过来的数据
     */

    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        };
    };
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(scheduledExecutorService != null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }


    //倒计时，线程
    public class TimeThread extends Thread{
        @Override
        public void run() {
            super.run();
            do{
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (true);

        }
    }
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try
                    {
                        //定义特定时间
                        Date d1 = df.parse("2023-11-11 00:00:00");
                        //获取当前时间
                        Date d2 = new Date(System.currentTimeMillis());
                        long diff = d1.getTime() - d2.getTime();//这样得到的差值是毫秒级别
                        //计算天数
                        long days = diff / (1000 * 60 * 60 * 24);
                        //计算小时数
                        long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
                        //计算分钟
                        long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
                        //计算秒
                        long s = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60)-minutes*(1000*60))/(1000);
                        //显示小时：分钟：秒
                        mainTv.setText(""+hours+":"+minutes+":"+s);
                        //设置格式，两位补0
                        mainTv.setText(String.format("%02d:%02d:%02d",hours,minutes,s));
                    }catch (Exception e)
                    {
                    }

                    break;
            }
            return false;
        }
    });


//    private void initLoopView(View view) {
//
//        viewPager = (ViewPager)view.findViewById(R.id.loopviewpager);
//        ll_dots_container = (LinearLayout)view.findViewById(R.id.ll_dots_loop);
//        loop_dec = (TextView)view.findViewById(R.id.loop_dec);
//
//        // 图片资源id数组
//        mImg = new int[]{
//                R.drawable.one,
//                R.drawable.two,
//                R.drawable.three,
//                R.drawable.four
//        };
//
//        // 文本描述
//        mDec = new String[]{
//                "Test1",
//                "Test2",
//                "Test3",
//                "Test4",
//        };
//
//        mImg_id = new int[]{
//                R.id.pager_img1,
//                R.id.pager_img2,
//                R.id.pager_img3,
//                R.id.pager_img4
//        };
//
//        // 初始化要展示的5个ImageView
//        mImgList = new ArrayList<ImageView>();
//        ImageView imageView;
//        View dotView;
//        LinearLayout.LayoutParams layoutParams;
//        for(int i=0;i<mImg.length;i++){
//            //初始化要显示的图片对象
//            imageView = new ImageView(getActivity());
//            imageView.setBackgroundResource(mImg[i]);
//            imageView.setId(mImg_id[i]);
//            imageView.setOnClickListener(new PagerOnClickListener(getActivity().getApplicationContext()));
//            mImgList.add(imageView);
//            //加引导点
//            dotView = new View(getActivity());
//            dotView.setBackgroundResource(R.drawable.dot);
//            layoutParams = new LinearLayout.LayoutParams(10,10);
//            if(i!=0){
//                layoutParams.leftMargin=10;
//            }
//            //设置默认所有都不可用
//            dotView.setEnabled(false);
//            ll_dots_container.addView(dotView,layoutParams);
//        }
//
//        ll_dots_container.getChildAt(0).setEnabled(true);
//        loop_dec.setText(mDec[0]);
//        previousSelectedPosition=0;
//        //设置适配器
//        viewPager.setAdapter(new LoopViewAdapter(mImgList));
//        // 把ViewPager设置为默认选中Integer.MAX_VALUE / t2，从十几亿次开始轮播图片，达到无限循环目的;
//        int m = (Integer.MAX_VALUE / 2) %mImgList.size();
//        int currentPosition = Integer.MAX_VALUE / 2 - m;
//        viewPager.setCurrentItem(currentPosition);
//
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//                int newPosition = i % mImgList.size();
//                loop_dec.setText(mDec[newPosition]);
//                ll_dots_container.getChildAt(previousSelectedPosition).setEnabled(false);
//                ll_dots_container.getChildAt(newPosition).setEnabled(true);
//                previousSelectedPosition = newPosition;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });
//
//        // 开启轮询
//        new Thread(){
//            public void run(){
//                isRunning = true;
//                while(isRunning){
//                    try{
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
////                    getActivity().runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////                            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
////                        }
////                    });
//                }//下一条
//
//            }
//        }.start();
//
//    }


}

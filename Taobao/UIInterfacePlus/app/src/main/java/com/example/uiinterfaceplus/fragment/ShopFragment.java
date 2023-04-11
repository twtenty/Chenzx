package com.example.uiinterfaceplus.fragment;

//import static com.example.uiinterfaceplus.MainActivity.fragmentState;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uiinterfaceplus.R;
import com.example.uiinterfaceplus.adapter.RecyclerShopAdapter;
import com.example.uiinterfaceplus.tools.TwoDecimal;
import com.example.uiinterfaceplus.bean.Account;
import com.example.uiinterfaceplus.dao.AccountDao;
import java.util.List;


/**
 * author:why
 * created on: 2018/10/3 12:11
 * description:
 */
public class ShopFragment extends Fragment {

//    private View mView = fragmentState.getBuycar_view();
    // 需要适配的数据集合
    private List<Account> list;

    // 数据库增删改查操作类
    private AccountDao dao;
//    private Account pre = new Account();
//    private HashMap<String,Boolean> flag_dianjia = new HashMap<String,Boolean>();
    private View view;
    // 适配器
    private RecyclerShopAdapter adapter;
    // RecyclerView
    private RecyclerView recyclerView;
    //全选按钮
    private CheckBox all_checked ;
    //总金额文本
    private TextView tv_sum;
    //购物车文本监听
    private TextView shop_shop;
    //管理文本
    private TextView shop_manager;
    //结算按钮
    private Button shop_jiesuan;
    //标记是否点击删除
    private Boolean del_flag = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        fragmentState.setBuycar_view(inflater.inflate(R.layout.fragment_shop, container, false));
//        view = fragmentState.getBuycar_view();
        view = inflater.inflate(R.layout.fragment_shop, container, false);
        //购物车管理监听
        shop_manager = (TextView) view.findViewById(R.id.shop_manage);
        //购物车总金额监听
        tv_sum = (TextView) view.findViewById(R.id.shop_sum);
        //全选按钮监听
        all_checked = (CheckBox) view.findViewById(R.id.shop_all);
        //结算按钮监听
        shop_jiesuan = (Button) view.findViewById(R.id.shop_jiesuan);


        //购物车文本监听
        shop_shop = (TextView) view.findViewById(R.id.shop_shop);

        dao = new AccountDao(view.getContext());
        // 从数据库查询出所有数据
        list = dao.queryAll();
        //初始化控件
        initView();
        //        recycler
        recyclerView = (RecyclerView) view.findViewById(R.id.accountRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        adapter = new RecyclerShopAdapter(getActivity().getApplicationContext(),list,shop_shop,shop_jiesuan,tv_sum,all_checked);
        adapter = new RecyclerShopAdapter(list,shop_shop,shop_jiesuan,tv_sum,all_checked);
        recyclerView.setAdapter(adapter);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());


        //购物车文本监听
        shop_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shop_jiesuan.setText("结算");
                shop_jiesuan.setTextScaleX(1);
                shop_jiesuan.setBackground(getResources().getDrawable(R.drawable.border_sousuo));
                ViewGroup.LayoutParams lp_tv1=(ViewGroup.LayoutParams)shop_jiesuan.getLayoutParams();
                lp_tv1.height=180;
                lp_tv1.width=350;
                shop_jiesuan.setLayoutParams(lp_tv1);
            }
        });


        //管理监听
        shop_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shop_jiesuan.setText("删除");
                shop_jiesuan.setTextScaleX(1);
                shop_jiesuan.setBackground(getResources().getDrawable(R.drawable.border_jiesuan));
                ViewGroup.LayoutParams lp_tv1=(ViewGroup.LayoutParams)shop_jiesuan.getLayoutParams();
                lp_tv1.height=130;
                lp_tv1.width=300;
                shop_jiesuan.setLayoutParams(lp_tv1);
            }
        });

        //全选按钮监听
        all_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("全选", "onClick: 全选", null);
                if(all_checked.isChecked())
                {
                    for (int i=0;i<list.size();i++){
                        list.get(i).setFlag(1);
                        dao.update(list.get(i));
                    }
                    adapter.notifyDataSetChanged();// 刷新界面
                    tv_sum.setText(""+new TwoDecimal().solve(dao.querySum()));
//                    Toast.makeText(getActivity(), "quanxuan", Toast.LENGTH_SHORT).show();
                } else {
                    for (int i=0;i<list.size();i++){
                        list.get(i).setFlag(0);
                        dao.update(list.get(i));
                    }
//                    tv_sum.setText(""+new TwoDecimal().solve(dao.querySum()));
                    tv_sum.setText("0");
                    adapter.notifyDataSetChanged();// 刷新界面
//                    Toast.makeText(getActivity(), "buxuan", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    //fragment重新刷新的方法
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CART_BROADCAST");
        BroadcastReceiver mItemViewListClickReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent){
                String msg = intent.getStringExtra("data");
                if("refresh".equals(msg)){
                    refresh();
                }
            }
        };
        broadcastManager.registerReceiver(mItemViewListClickReceiver, intentFilter);
    }

    private void refresh() {
        initView();
    }


    // 初始化控件
    private void initView() {
//        Double p = tv_sum.getText().toString().equals("") ? 0 : Double.parseDouble(tv_sum.getText().toString());
        Double p =0.0;
        int num=0;
        for (int i=0;i<list.size();i++){

            if (list.get(i).getFlag()==1){
                p += list.get(i).getSum();
                num++;
            }
//                        Log.e(""+a.getSum(), ""+a.getSum()+" "+a.getName(),null );
        }
        if(num==list.size()&&num!=0) all_checked.setChecked(true);
        tv_sum.setText("" + new TwoDecimal().solve(p));
//        flag_dianjia = new HashMap<String,Boolean>();
//        pre = null;
//        accountLV = (ListView) view.findViewById(R.id.accountLV);
        // 添加监听器, 监听条目点击事件
//        accountLV.setOnItemClickListener(new MyOnItemClickListener());
    }

    // 自定义一个适配器(把数据装到ListView的工具)
//    private class MyAdapter extends BaseAdapter {
//        public int getCount() {                   // 获取条目总数
//            return list.size();
//        }
//
//        public Object getItem(int position) { // 根据位置获取对象
//            return list.get(position);
//        }
//
//        public long getItemId(int position) { // 根据位置获取id
//            return position;
//        }
//
//
//        // 获取一个条目视图
//        public View getView(int position, View convertView, ViewGroup parent) {
//            // 重用convertView
//            View item = convertView != null ? convertView : View.inflate(
//                    view.getContext().getApplicationContext(), R.layout.shop_item, null);
//            // 获取该视图中的TextView
//            TextView dianTv = (TextView) item.findViewById(R.id.shop_dianpu);
//            ImageView iconTv = (ImageView) item.findViewById(R.id.shop_gecimage);
//            TextView nameTV = (TextView) item.findViewById(R.id.shop_gwcname);
//            TextView priceTV = (TextView) item.findViewById(R.id.shop_gwcprice);
//            TextView numberTV = (TextView) item.findViewById(R.id.shop_gwcnum);
//            // 根据当前位置获取Account对象
//            Account a = list.get(position);
//            // 把Account对象中的数据放到TextView中
////            if (flag_dianjia.containsKey(a.getDianjia())==false){
////                dianTv.setText(a.getDianjia());
////                flag_dianjia.put(a.getDianjia(),true);
////
////            } else {
////                dianTv.setText("");
////            }
////            if (position==0) pre=null;
////            if(pre==null)
////                dianTv.setText(a.getDianjia()+position);
////            else
////            {
////                dianTv.setText(a.getDianjia().equals(pre.getDianjia())==true?"":a.getDianjia()+position);
////            }
//
//            if(position==0)
//                dianTv.setText(a.getDianjia()+">");
//            else {
//                if(list.get(position-1).getDianjia().equals(a.getDianjia())==true)
//                    dianTv.setText("");
//                else
//                    dianTv.setText(a.getDianjia()+">");
//            }
//            nameTV.setText(a.getName());
//            priceTV.setText(a.getSum()+"");
//            numberTV.setText(a.getNumber()+"");
//            iconTv.setImageResource(a.getIcon());
////            pre=a;
//
//            ImageView upIV = (ImageView) item.findViewById(R.id.shop_gwcadd);
//            ImageView downIV = (ImageView) item.findViewById(R.id.shop_gwcsub);
//            ImageView deleteIV = (ImageView) item.findViewById(R.id.shop_gwcdel);
//            CheckBox checkTv = (CheckBox) item.findViewById(R.id.shop_checked);
////            ImageView checkTv = (ImageView) item.findViewById(R.id.shop_checked);
////            if (list.get(position).getFlag()==1)
////                checkTv.setChecked(true);
////            else
////                checkTv.setChecked(false);
//            //判断是否要删除
//            //结算监听,目前只实现删除
//            shop_jiesuan.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(shop_jiesuan.getText().toString().equals("删除")&&checkTv.isChecked()){
//                        list.remove(a);          // 从集合中删除
//                        dao.delete(a.getId()); // 从数据库中删除
//                        notifyDataSetChanged();// 刷新界面
//                    }
//                }
//            });
//
//            //向上箭头的点击事件触发的方法
//            upIV.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    a.setNumber(a.getNumber() + 1); // 修改值
////                    Log.e("a.getSum", ""+a.getSum(),null );
////                    Log.e("a.getPrice", ""+a.getPrice(),null );
//                    a.setSum(new TwoDecimal().solve(a.getSum() + a.getPrice()));
////                    flag_dianjia = new HashMap<String,Boolean>();
////                    pre = null;
//                    if (checkTv.isChecked()) {
//                        Double p = tv_sum.getText().toString().equals("") ? 0 : Double.parseDouble(tv_sum.getText().toString());
//                        p += a.getPrice();
//                        tv_sum.setText("" + new TwoDecimal().solve(p));
//                    }
//                    notifyDataSetChanged(); // 刷新界面
//                    dao.update(a); // 更新数据库
//                }
//            });
//            //向下箭头的点击事件触发的方法
//            downIV.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    if(a.getNumber()<2)
//                    {
//                        DialogInterface.OnClickListener listener =
//                                new DialogInterface.
//                                        OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        list.remove(a);          // 从集合中删除
//                                        dao.delete(a.getId()); // 从数据库中删除
////                                        flag_dianjia = new HashMap<String,Boolean>();
////                                        pre = null;
//                                        if(checkTv.isChecked()){
//                                            Double p = tv_sum.getText().toString().equals("")?0:Double.parseDouble(tv_sum.getText().toString());
//                                            p-=a.getPrice();
//                                            tv_sum.setText(""+new TwoDecimal().solve(p));
//                                        }
//                                        notifyDataSetChanged();// 刷新界面
//                                    }
//                                };
//                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext()); // 创建对话框
//                        builder.setTitle("确定要删除吗?");                    // 设置标题
//                        // 设置确定按钮的文本以及监听器
//                        builder.setPositiveButton("确定", listener);
//                        builder.setNegativeButton("取消", null);         // 设置取消按钮
//                        builder.show(); // 显示对话框
//                    } else {
//                        if(checkTv.isChecked()){
//                            Double p = tv_sum.getText().toString().equals("")?0:Double.parseDouble(tv_sum.getText().toString());
//                            p-=a.getPrice();
//                            tv_sum.setText(""+new TwoDecimal().solve(p));
//                        }
//                        a.setNumber(a.getNumber() - 1);
//                        a.setSum(new TwoDecimal().solve(a.getSum()-a.getPrice()));
////                        flag_dianjia = new HashMap<String,Boolean>();
////                        pre = null;
//                        notifyDataSetChanged();
//                        dao.update(a);
//                    }
//
//                }
//            });
//            //删除图片的点击事件触发的方法
//            deleteIV.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    //删除数据之前首先弹出一个对话框
//                    DialogInterface.OnClickListener listener =
//                            new DialogInterface.
//                                    OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    Double p = tv_sum.getText().toString().equals("")?0:Double.parseDouble(tv_sum.getText().toString());
//                                    p-=a.getSum();
//                                    tv_sum.setText(""+new TwoDecimal().solve(p));
//                                    list.remove(a);          // 从集合中删除
//                                    dao.delete(a.getId()); // 从数据库中删除
////                                    flag_dianjia = new HashMap<String,Boolean>();
////                                    pre = null;
//                                    notifyDataSetChanged();// 刷新界面
//                                }
//                            };
//                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext()); // 创建对话框
//                    builder.setTitle("确定要删除吗?");                    // 设置标题
//                    // 设置确定按钮的文本以及监听器
//                    builder.setPositiveButton("确定", listener);
//                    builder.setNegativeButton("取消", null);         // 设置取消按钮
//                    builder.show(); // 显示对话框
//                }
//            });
//
//
//
//           // 全选
//            if (all_checked.isChecked()){
//                if(!checkTv.isChecked()){
//                    checkTv.setChecked(true);
//                    a.setFlag(1);
//                    dao.update(a);
////                    notifyDataSetChanged();
//                }
//            }
//
//
//
//            //选择商品监听事件
//            checkTv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if(isChecked){
//                        a.setFlag(1);
//                        dao.update(a);
////                        Toast.makeText(getActivity(), tv_sum.getText(), Toast.LENGTH_SHORT).show();
//                        Double p = tv_sum.getText().toString().equals("")?0:Double.parseDouble(tv_sum.getText().toString());
//                        if (!all_checked.isChecked())p+=a.getSum();
////                        Log.e(""+a.getSum(), ""+a.getSum()+" "+a.getName(),null );
//                        tv_sum.setText(""+new TwoDecimal().solve(p));
//                        int cnt=0;
//                        for (int i=0;i<list.size();i++){
//                            if(list.get(i).getFlag()==1)
//                                cnt++;
//                        }
//                        if(cnt==list.size())
//                            all_checked.setChecked(true);
////                        notifyDataSetChanged();
//                    } else {
//                        a.setFlag(0);
//                        dao.update(a);
//                        Double p = tv_sum.getText().toString().equals("")?0:Double.parseDouble(tv_sum.getText().toString());
//                        p-=a.getSum();
//                        tv_sum.setText(""+new TwoDecimal().solve(p));
//                        all_checked.setChecked(false);
////                        notifyDataSetChanged();
//                    }
//
//                }
//            });
//
////            checkTv.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    checkTv.setImageResource(R.drawable.shop_checked);
////                }
////            });
//            return item;
//        }
//    }
//    //ListView的Item点击事件
//    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {
//        public void onItemClick(AdapterView<?> parent, View view, int position,
//                                long id) {
//            // 获取点击位置上的数据
//            Account a = (Account) parent.getItemAtPosition(position);
//            Toast.makeText(view.getContext().getApplicationContext(), a.toString(),
//                    Toast.LENGTH_SHORT).show();
//        }
//    }



}



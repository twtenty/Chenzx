package com.example.uiinterfaceplus.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uiinterfaceplus.R;
import com.example.uiinterfaceplus.activity.MainActivity2;
import com.example.uiinterfaceplus.activity.ProductdetailActivity;
import com.example.uiinterfaceplus.bean.Account;
import com.example.uiinterfaceplus.dao.AccountDao;
import com.example.uiinterfaceplus.info.SnacksInfo;
import com.example.uiinterfaceplus.tools.TwoDecimal;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private View itemView;
    private AccountDao dao;
    public List<SnacksInfo> list;

    public SnacksInfo getItem(int position) {
        return list == null ? null : list.get(position);
    }

    public void setData(List<SnacksInfo> data) {
        this.list = data;
        notifyDataSetChanged();
    }

    public int[] icons = {
            R.drawable.fruit,
            R.drawable.nut,
            R.drawable.qiaqia,
            R.drawable.irvins
    };

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item2, parent, false);
        HomeAdapter.MyViewHolder holder = new HomeAdapter.MyViewHolder(itemView);
        dao = new AccountDao(parent.getContext());
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
//            System.out.println("我进来了");
//            System.out.println("info="+getItem(position));
        SnacksInfo info = getItem(position);
        holder.name2.setText(info.getNames());

        Glide.with(itemView)
                .load(info.getImg())
                .into(holder.iv2);
        Glide.with(itemView)
                .load(info.getGwc())
                .into(holder.gwc);
        holder.introduce2.setText(info .getIntroduce());
        holder.fans.setText(info.getFans());
        holder.ju.setText(info.getJu());
        holder.price.setText("¥"+info.getPrice());
        holder.payed.setText(info.getPayed());
        holder.bk_1.setText(info.getBk_1());
        holder.bk_2.setText(info.getBk_2());
        holder.bk_3.setText(info.getBk_3());
        holder.bk_4.setText(info.getBk_4());
        holder.dp.setText(info.getDp());

        int icon = icons[position];
        String dianjia = info.getDp();
        String name = info.getIntroduce();
        String pri = info.getPrice();
        String sum = info.getPrice();
        Double p=pri.equals("") ? 0: Double.parseDouble(pri);
        Double s=sum.equals("") ? 0 : Double.parseDouble(sum);
        p = new TwoDecimal().solve(p);
        s = new TwoDecimal().solve(s);
        //三目运算 balance.equals(“”) 则等于0
        //如果balance 不是空字符串 则进行类型转换
        Account a = new Account(icon,dianjia,name,p,s, 1,0);
        Account aa = dao.queryone(name);

        holder.iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductdetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("iv2",icon);
                bundle.putString("price",pri);
                bundle.putString("dianjia",dianjia);
                bundle.putString("bk1",info.getBk_1());
                bundle.putString("bk2",info.getBk_2());
                bundle.putString("bk3",info.getBk_3());
                bundle.putString("bk4",info.getBk_4());
                bundle.putString("name",info.getNames());
                bundle.putString("introduce",name);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });

        holder.gwc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aa==null){
                    dao.insert(a);                      // 插入数据库
                    Toast.makeText(v.getContext(), "成功加入购物车", Toast.LENGTH_SHORT).show();
                    dao.update(a); // 更新数据库
                } else {
                    aa.setNumber(aa.getNumber() + 1); // 修改值
                    aa.setSum(new TwoDecimal().solve(aa.getSum()+aa.getPrice()));
                    Toast.makeText(v.getContext(), "购物车已有，数量增加一", Toast.LENGTH_SHORT).show();
                    dao.update(aa); // 更新数据库
                }
                notifyDataSetChanged(); // 刷新界面

            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv2;
        TextView name2;
        TextView introduce2,fans,price;
        TextView ju,payed;
        TextView bk_1,bk_2,bk_3,bk_4;
        TextView dp;
        ImageView gwc;
        public MyViewHolder(View view){
            super(view);
            iv2 = (ImageView) view.findViewById(R.id.iv2);
            name2 = (TextView) view.findViewById(R.id.name2);
            introduce2 = (TextView) view.findViewById(R.id.introduce2);
            fans = (TextView) view.findViewById(R.id.fans);
            ju = (TextView) view.findViewById(R.id.ju);
            price = (TextView) view.findViewById(R.id.price);
            payed = (TextView) view.findViewById(R.id.payed);
            bk_1 = (TextView) view.findViewById(R.id.bk_1);
            bk_2 = (TextView) view.findViewById(R.id.bk_2);
            bk_3 = (TextView) view.findViewById(R.id.bk_3);
            bk_4 = (TextView) view.findViewById(R.id.bk_4);
            dp = (TextView) view.findViewById(R.id.dp);
            gwc = (ImageView) view.findViewById(R.id.gwc);

        }
    }
}



package com.example.uiinterfaceplus.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.uiinterfaceplus.activity.EndActivity;
import com.example.uiinterfaceplus.R;

public class RecyclerGridAdapter extends RecyclerView.Adapter<RecyclerGridAdapter.MyViewHolder> {

    private int [] icons = {R.drawable.tianmaoxinpin, R.drawable.tiantiantemai, R.drawable.feizhulvxing, R.drawable.babanongchang, R.drawable.tianmaochaoshi,
            R.drawable.tianmaoguoji, R.drawable.chihuo, R.drawable.taopiaopiao, R.drawable.tuhuoxianshi, R.drawable.zizhiguize,
            R.drawable.chongzhizhongxin, R.drawable.haixianda, R.drawable.lingtaojinbi, R.drawable.alipaimai, R.drawable.feilei,
            R.drawable.eleme, R.drawable.xianyu, R.drawable.tianmaohaoyao, R.drawable.tianmaoqiche, R.drawable.gengduopindao};
    private String [] names = {
            "天猫新品","天天特卖","飞猪旅行","芭芭农场","天猫超市",
            "天猫国际","吃货","淘票票","土货鲜食","资质规则",
            "充值中心","淘鲜达","领淘金币","阿里拍卖","分类",
            "饿了么","咸鱼","天猫好药","天猫汽车","更多频道"
    };

    public RecyclerGridAdapter(Context context) {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //加载图片
//        holder.img.setImageResource(imgList.get(position));
        holder.img.setImageResource(icons[position]);
        holder.tv.setText(names[position]);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EndActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.name);
        }
    }

    @Override
    public int getItemCount() {
        return icons.length;
    }
}

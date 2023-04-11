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

public class RecyclerContactsAdapter extends RecyclerView.Adapter<RecyclerContactsAdapter.MyViewHolder> {

    private int [] icons = {R.drawable.messagestouxiangone,R.drawable.messagestouxiangtwo,R.drawable.messagestouxiangthree,R.drawable.messagestouxiangfour};
    private String [] names = {
            "tbNick_4e","辰星科技商品店","summer hot","星航设计"
    };
    private String[] contents = {
            "个偶的撒娇还是多久啊实打实",
            "asdhja阿斯加德哈市",
            "萨迪假设的卡温顿",
            "isad驾驶机动皮卡商品店铺洼地"
    };

    public RecyclerContactsAdapter(Context context) {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_message, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //加载图片
//        holder.img.setImageResource(imgList.get(position));
        holder.img.setImageResource(icons[position]);
        holder.name.setText(names[position]);
        holder.content.setText(contents[position]);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EndActivity.class);

                v.getContext().startActivity(intent);
            }
        });
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EndActivity.class);

                v.getContext().startActivity(intent);
            }
        });
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EndActivity.class);

                v.getContext().startActivity(intent);
            }
        });
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView name;
        public TextView content;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.message_icon);
            name = itemView.findViewById(R.id.message_name);
            content = itemView.findViewById(R.id.message_content);
        }
    }

    @Override
    public int getItemCount() {
        return icons.length;
    }
}
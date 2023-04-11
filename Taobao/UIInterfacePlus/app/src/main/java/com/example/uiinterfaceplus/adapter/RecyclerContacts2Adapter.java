package com.example.uiinterfaceplus.adapter;


//import static com.example.uiinterfaceplus.MainActivity.fragmentState;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uiinterfaceplus.R;

import java.util.List;

public class RecyclerContacts2Adapter extends RecyclerView.Adapter<RecyclerContacts2Adapter.MyViewHolder> {

    //    private View mview = fragmentState.getBuycar_view();
    private View itemView;
    //    private Context context;
//    private LayoutInflater layoutInflater;
    // 需要适配的数据集合
    private List<String> names;
    private List<List<String>> details;

    public RecyclerContacts2Adapter(List<String> names, List<List<String>> details) {
        this.names=names;
        this.details=details;
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.recycle_shop, parent, false);
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_contacts2, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);
//        shop_shop = (TextView) mview.findViewById(R.id.shop_shop);
//        shop_jiesuan = (Button) mview.findViewById(R.id.shop_jiesuan);
//        tv_sum = (TextView) mview.findViewById(R.id.shop_sum);
//        all_checked = (CheckBox) mview.findViewById(R.id.shop_all);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(position%2==0) holder.touxiang.setImageResource(R.drawable.contacts_touxiang);
        else holder.touxiang.setImageResource(R.drawable.contacts_touxiang2);
        holder.name.setText(names.get(position));
        Log.i("name", names.get(position));
        String p = new String();
        for(int i=0;i<details.get(position).size();i++)
        {
            p+=details.get(position).get(i);
            if(i==0) p+=" ";
            else p+=" ; ";
        }
        holder.phone.setText(p);
        holder.tijiao.setText("添加");
        holder.tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "添加成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,phone;
        ImageView touxiang;
        Button tijiao;
        public MyViewHolder(View view){
            super(view);
            touxiang = (ImageView) view.findViewById(R.id.contacts2_touxiang);
            name = (TextView) view.findViewById(R.id.contacts2_name);
            phone = (TextView) view.findViewById(R.id.contacts2_phone);
            tijiao = (Button) view.findViewById(R.id.contacts2_contactsadd);
        }
    }

}


package com.example.uiinterfaceplus.adapter;

//import static com.example.uiinterfaceplus.MainActivity.fragmentState;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uiinterfaceplus.R;
import com.example.uiinterfaceplus.tools.TwoDecimal;
import com.example.uiinterfaceplus.bean.Account;
import com.example.uiinterfaceplus.dao.AccountDao;

import java.util.List;

public class RecyclerOrderAdapter extends RecyclerView.Adapter<RecyclerOrderAdapter.MyViewHolder> {

    //    private View mview = fragmentState.getBuycar_view();
    private View itemView;
    //    private Context context;
//    private LayoutInflater layoutInflater;
    // 需要适配的数据集合
    private List<Account> list;

    public RecyclerOrderAdapter( List<Account> PreList) {
        this.list = PreList;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.recycle_shop, parent, false);
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_order, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);
//        shop_shop = (TextView) mview.findViewById(R.id.shop_shop);
//        shop_jiesuan = (Button) mview.findViewById(R.id.shop_jiesuan);
//        tv_sum = (TextView) mview.findViewById(R.id.shop_sum);
//        all_checked = (CheckBox) mview.findViewById(R.id.shop_all);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AccountDao dao = new AccountDao(itemView.getContext());
        Account a = list.get(position);
        if (position == 0)
            holder.dianTv.setText(a.getDianjia() + ">");
        else {
            if (list.get(position - 1).getDianjia().equals(a.getDianjia()) == true)
                holder.dianTv.setText("");
            else
                holder.dianTv.setText(a.getDianjia() + ">");
        }
        holder.nameTv.setText(a.getName());
        holder.priceTv.setText(a.getSum() + "");
        holder.numberTv.setText("x"+a.getNumber()  );
        holder.iconTv.setImageResource(a.getIcon());

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dianTv,nameTv,priceTv,numberTv;
        ImageView iconTv,upTv,downTv,deleteTv;
        CheckBox checkTv;
        public MyViewHolder(View view){
            super(view);
            dianTv = (TextView) view.findViewById(R.id.dianpu_order);
            iconTv = (ImageView) view.findViewById(R.id.iv_order);
            nameTv = (TextView) view.findViewById(R.id.name_order);
            priceTv = (TextView) view.findViewById(R.id.price_order);
            numberTv = (TextView) view.findViewById(R.id.num_order);
        }
    }

}

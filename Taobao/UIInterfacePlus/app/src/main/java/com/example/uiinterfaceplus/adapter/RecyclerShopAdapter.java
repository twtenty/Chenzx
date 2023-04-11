package com.example.uiinterfaceplus.adapter;

//import static com.example.uiinterfaceplus.MainActivity.fragmentState;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uiinterfaceplus.activity.OrderActivity;
import com.example.uiinterfaceplus.R;
import com.example.uiinterfaceplus.tools.TwoDecimal;
import com.example.uiinterfaceplus.bean.Account;
import com.example.uiinterfaceplus.dao.AccountDao;

import java.util.List;

public class RecyclerShopAdapter extends RecyclerView.Adapter<RecyclerShopAdapter.MyViewHolder> {

//    private View mview = fragmentState.getBuycar_view();
    private View itemView;
//    private Context context;
//    private LayoutInflater layoutInflater;
    // 需要适配的数据集合
    private List<Account> list;
    //购物车文本监听
    private TextView shop_shop;
    //结算按钮
    private Button shop_jiesuan;
    //总金额文本
    private TextView tv_sum;
    //全选按钮
    private CheckBox all_checked ;

    public RecyclerShopAdapter( List<Account> PreList,TextView shop_shop,Button shop_jiesuan,TextView tv_sum,CheckBox all_checked) {
        this.list = PreList;
//        this.context = Precontext;
//        layoutInflater = LayoutInflater.from(Precontext);
        this.shop_shop = shop_shop;
        this.shop_jiesuan = shop_jiesuan;
        this.tv_sum = tv_sum;
        this.all_checked = all_checked;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.recycle_shop, parent, false);
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_shop, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);
//        shop_shop = (TextView) mview.findViewById(R.id.shop_shop);
//        shop_jiesuan = (Button) itemView.findViewById(R.id.shop_jiesuan);
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
        holder.numberTv.setText(a.getNumber() + "");
        holder.iconTv.setImageResource(a.getIcon());
        System.out.println("getIcon"+a.getIcon()+"    icon:"+R.drawable.fruit+" "+R.drawable.nut+" "+R.drawable.qiaqia+" "+R.drawable.irvins);

        shop_jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(shop_jiesuan.getText().toString());
                if (shop_jiesuan.getText().toString().equals("删除")==true ) {
                    if(holder.checkTv.isChecked()){
                        list.remove(a);          // 从集合中删除
                        dao.delete(a.getId()); // 从数据库中删除
                        notifyDataSetChanged();// 刷新界面
                    }

                } else {
                    Intent intent = new Intent(itemView.getContext(), OrderActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            }
        });

        //向上箭头的点击事件触发的方法
        holder.upTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                a.setNumber(a.getNumber() + 1); // 修改值
//                    Log.e("a.getSum", ""+a.getSum(),null );
//                    Log.e("a.getPrice", ""+a.getPrice(),null );
                a.setSum(new TwoDecimal().solve(a.getSum() + a.getPrice()));
//                    flag_dianjia = new HashMap<String,Boolean>();
//                    pre = null;
                if (holder.checkTv.isChecked()) {
                    Double p = tv_sum.getText().toString().equals("") ? 0 : Double.parseDouble(tv_sum.getText().toString());
                    p += a.getPrice();
                    tv_sum.setText("" + new TwoDecimal().solve(p));
                }
                notifyDataSetChanged(); // 刷新界面
                dao.update(a); // 更新数据库
            }
        });
        //向下箭头的点击事件触发的方法
        holder.downTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (a.getNumber() < 2) {
                    DialogInterface.OnClickListener listener =
                            new DialogInterface.
                                    OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    list.remove(a);          // 从集合中删除
                                    dao.delete(a.getId()); // 从数据库中删除
//                                        flag_dianjia = new HashMap<String,Boolean>();
//                                        pre = null;
                                    if (holder.checkTv.isChecked()) {
                                        Double p = tv_sum.getText().toString().equals("") ? 0 : Double.parseDouble(tv_sum.getText().toString());
                                        p -= a.getPrice();
                                        tv_sum.setText("" + new TwoDecimal().solve(p));
                                    }
                                    notifyDataSetChanged();// 刷新界面
                                }
                            };
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext()); // 创建对话框
                    builder.setTitle("确定要删除吗?");                    // 设置标题
                    // 设置确定按钮的文本以及监听器
                    builder.setPositiveButton("确定", listener);
                    builder.setNegativeButton("取消", null);         // 设置取消按钮
                    builder.show(); // 显示对话框
                } else {
                    if (holder.checkTv.isChecked()) {
                        Double p = tv_sum.getText().toString().equals("") ? 0 : Double.parseDouble(tv_sum.getText().toString());
                        p -= a.getPrice();
                        tv_sum.setText("" + new TwoDecimal().solve(p));
                    }
                    a.setNumber(a.getNumber() - 1);
                    a.setSum(new TwoDecimal().solve(a.getSum() - a.getPrice()));
//                        flag_dianjia = new HashMap<String,Boolean>();
//                        pre = null;
                    notifyDataSetChanged();
                    dao.update(a);
                }

            }
        });
        //删除图片的点击事件触发的方法
        holder.deleteTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //删除数据之前首先弹出一个对话框
                DialogInterface.OnClickListener listener =
                        new DialogInterface.
                                OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Double p = tv_sum.getText().toString().equals("") ? 0 : Double.parseDouble(tv_sum.getText().toString());
                                p -= a.getSum();
                                tv_sum.setText("" + new TwoDecimal().solve(p));
                                list.remove(a);          // 从集合中删除
                                dao.delete(a.getId()); // 从数据库中删除
//                                    flag_dianjia = new HashMap<String,Boolean>();
//                                    pre = null;
                                notifyDataSetChanged();// 刷新界面
                            }
                        };
                AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext()); // 创建对话框
                builder.setTitle("确定要删除吗?");                    // 设置标题
                // 设置确定按钮的文本以及监听器
                builder.setPositiveButton("确定", listener);
                builder.setNegativeButton("取消", null);         // 设置取消按钮
                builder.show(); // 显示对话框
            }
        });


        if (a.getFlag() == 1)
            holder.checkTv.setChecked(true);
        else
            holder.checkTv.setChecked(false);

        //选择商品监听事件
        holder.checkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkTv.isChecked())
                {
                    a.setFlag(1);
                    dao.update(a);
//                        Toast.makeText(getActivity(), tv_sum.getText(), Toast.LENGTH_SHORT).show();
                    Double p = tv_sum.getText().toString().equals("") ? 0 : Double.parseDouble(tv_sum.getText().toString());
                    if (!all_checked.isChecked()) p += a.getSum();
//                        Log.e(""+a.getSum(), ""+a.getSum()+" "+a.getName(),null );
                    tv_sum.setText("" + new TwoDecimal().solve(p));
                    int cnt = 0;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getFlag() == 1)
                            cnt++;
                    }
                    if (cnt == list.size())
                        all_checked.setChecked(true);
//                        notifyDataSetChanged();
                } else

                {
                    a.setFlag(0);
                    dao.update(a);
                    Double p = tv_sum.getText().toString().equals("") ? 0 : Double.parseDouble(tv_sum.getText().toString());
                    p -= a.getSum();
                    tv_sum.setText("" + new TwoDecimal().solve(p));
                    all_checked.setChecked(false);
//                        notifyDataSetChanged();
                }
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dianTv,nameTv,priceTv,numberTv;
        ImageView iconTv,upTv,downTv,deleteTv;
        CheckBox checkTv;
        public MyViewHolder(View view){
            super(view);
            dianTv = (TextView) view.findViewById(R.id.shop_dianpu);
            iconTv = (ImageView) view.findViewById(R.id.shop_gecimage);
            nameTv = (TextView) view.findViewById(R.id.shop_gwcname);
            priceTv = (TextView) view.findViewById(R.id.shop_gwcprice);
            numberTv = (TextView) view.findViewById(R.id.shop_gwcnum);
            upTv = (ImageView) view.findViewById(R.id.shop_gwcadd);
            downTv = (ImageView) view.findViewById(R.id.shop_gwcsub);
            deleteTv = (ImageView) view.findViewById(R.id.shop_gwcdel);
            checkTv = (CheckBox) view.findViewById(R.id.shop_checked);
        }
    }

}

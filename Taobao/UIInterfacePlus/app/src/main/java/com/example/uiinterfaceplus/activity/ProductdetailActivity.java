package com.example.uiinterfaceplus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uiinterfaceplus.R;
import com.example.uiinterfaceplus.bean.Account;
import com.example.uiinterfaceplus.dao.AccountDao;
import com.example.uiinterfaceplus.tools.TwoDecimal;

public class ProductdetailActivity extends AppCompatActivity {

    private ImageView iv2;
    private TextView name2;
    private TextView introduce2,price;
    private TextView bk_1,bk_2,bk_3,bk_4;
    private Button gwc;

    private AccountDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);

        //添加监听
        iv2 = (ImageView) findViewById(R.id.iv2_shopdetail);
        price = (TextView) findViewById(R.id.price_shopdetail);
        bk_1 = (TextView) findViewById(R.id.bk_1_shopdetail);
        bk_2 = (TextView) findViewById(R.id.bk_2_shopdetail);
        bk_3 = (TextView) findViewById(R.id.bk_3_shopdetail);
        bk_4 = (TextView) findViewById(R.id.bk_4_shopdetail);
        name2 = (TextView) findViewById(R.id.name2_shopdetail);
        introduce2 = (TextView) findViewById(R.id.introduce2_shopdetail);
        gwc = (Button) findViewById(R.id.gwc_shopdetail);

//        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        //取出数据
        int iv= bundle.getInt("iv2");
        String dianjia = bundle.getString("dianjia");
        String name = bundle.getString("name");
        String introduce = bundle.getString("introduce");
        String pri = bundle.getString("price");
        Double p=pri.equals("") ? 0: Double.parseDouble(pri);
        Double s=pri.equals("") ? 0 : Double.parseDouble(pri);
        iv2.setImageResource(iv);
        price.setText("¥"+pri);
        bk_1.setText(bundle.getString("bk1"));
        bk_2.setText(bundle.getString("bk2"));
        bk_3.setText(bundle.getString("bk3"));
        bk_4.setText(bundle.getString("bk4"));
        name2.setText(name);
        introduce2.setText(introduce);

        dao = new AccountDao(ProductdetailActivity.this);
        Account a = new Account(iv,dianjia,introduce,p,s, 1,0);
        Account aa = dao.queryone(introduce);

        gwc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aa==null){
                    dao.insert(a);                      // 插入数据库
                    Toast.makeText(ProductdetailActivity.this, "成功加入购物车", Toast.LENGTH_SHORT).show();
                    dao.update(a); // 更新数据库
                } else {
                    aa.setNumber(aa.getNumber() + 1); // 修改值
                    aa.setSum(new TwoDecimal().solve(aa.getSum()+aa.getPrice()));
                    Toast.makeText(ProductdetailActivity.this, "购物车已有，数量增加一", Toast.LENGTH_SHORT).show();
                    dao.update(aa); // 更新数据库
                }
            }
        });
    }
}
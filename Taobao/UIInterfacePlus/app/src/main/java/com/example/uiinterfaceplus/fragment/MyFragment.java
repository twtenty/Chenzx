package com.example.uiinterfaceplus.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uiinterfaceplus.activity.EndActivity;
import com.example.uiinterfaceplus.activity.LoginActivity;
import com.example.uiinterfaceplus.R;


/**
 * author:why
 * created on: 2018/10/3 12:11
 * description:
 */
public class MyFragment extends Fragment {

    private TextView login_id1,login_id2;
    private ImageView one,two,three;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        login_id1 = (TextView) view.findViewById(R.id.me_name);
        login_id2 = (TextView) view.findViewById(R.id.me_id);
        ImageButton touxiang = (ImageButton) view.findViewById(R.id.me_touxiang);
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("touxiang");
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent,1);

            }
        });

        one = (ImageView) view.findViewById(R.id.me_one);
        two = (ImageView) view.findViewById(R.id.me_two);
        three = (ImageView) view.findViewById(R.id.me_three);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EndActivity.class);

                v.getContext().startActivity(intent);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EndActivity.class);

                v.getContext().startActivity(intent);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EndActivity.class);

                v.getContext().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==2){
            String name = data.getStringExtra("name");
            String id = data.getStringExtra("id");
            String pwd = data.getStringExtra("password");
            login_id1.setText(name);
            login_id2.setText(id);
        }
    }
}

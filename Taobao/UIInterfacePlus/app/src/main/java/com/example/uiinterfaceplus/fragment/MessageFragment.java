package com.example.uiinterfaceplus.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uiinterfaceplus.activity.ContactsActivity1;
import com.example.uiinterfaceplus.R;
import com.example.uiinterfaceplus.adapter.RecyclerContactsAdapter;


/**
 * author:why
 * created on: 2018/10/3 12:11
 * description:
 */
public class MessageFragment extends Fragment {

    // 适配器
    private RecyclerContactsAdapter adapter;
    // RecyclerView
    private RecyclerView recyclerView;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);

        //        recycler
        recyclerView = (RecyclerView) view.findViewById(R.id.contacts2_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerContactsAdapter(getContext());
        recyclerView.setAdapter(adapter);

        //点击联系人事件
        ImageView message_contacts = (ImageView) view.findViewById(R.id.message_contacts);
        message_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContactsActivity1.class);
                startActivity(intent);
            }
        });

        return view;
    }



}

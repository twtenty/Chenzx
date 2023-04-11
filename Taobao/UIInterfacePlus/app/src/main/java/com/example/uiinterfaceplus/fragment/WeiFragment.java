package com.example.uiinterfaceplus.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uiinterfaceplus.R;


/**
 * author:why
 * created on: 2018/10/3 12:11
 * description:
 */
public class WeiFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wei, container, false);
        return view;
    }
}

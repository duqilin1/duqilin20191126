package com.bawie.duqilin20191126.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawie.duqilin20191126.R;
import com.bawie.duqilin20191126.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Frag_02 extends BaseFragment {

    private TextView name;
    private String key;

    @Override
    protected void initView(View inflate) {
        name = inflate.findViewById(R.id.name);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_02;
    }

    @Override
    protected void initData() {
        Intent intent = getActivity().getIntent();
        key = intent.getStringExtra("key");
        name.setText(key);
    }

    public static Frag_02 getInstance(String values) {
        Frag_02 frag_02 = new Frag_02();
        Bundle bundle = new Bundle();
        bundle.getString("key",values);
        frag_02.setArguments(bundle);
        return frag_02;
    }

}

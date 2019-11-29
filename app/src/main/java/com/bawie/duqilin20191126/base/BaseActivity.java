package com.bawie.duqilin20191126.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/*
 *@auther:杜其林
 *@Date: 2019/11/26
 *@Time:14:15
 *@Description:${DESCRIPTION}
 * */
public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        initView();
        inittData();
    }

    protected abstract void inittData();

    protected abstract void initView();

    protected abstract int layoutId();
}

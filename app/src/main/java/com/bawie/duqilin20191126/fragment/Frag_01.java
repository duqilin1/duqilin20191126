package com.bawie.duqilin20191126.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bawie.duqilin20191126.Bean;
import com.bawie.duqilin20191126.Main2Activity;
import com.bawie.duqilin20191126.MainActivity;
import com.bawie.duqilin20191126.MyAdapter;
import com.bawie.duqilin20191126.NetUtils;
import com.bawie.duqilin20191126.R;
import com.bawie.duqilin20191126.base.BaseFragment;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * ②　抽取Activity基类和Fragment基类，并在项目中使用。
 ③　时事页面请求接口数据完成列表展示，开始进入app的时候展示图1时事页面。
 ④　其他页面共用一个Fragment，使用传值的方式在Fragment中显示横向菜单的文字，如图2。
 ⑤　使用PullToRefresh库完成列表展示并完成下拉刷新和上拉加载更多（如图1）代码和效果都要实现
 * A simple {@link Fragment} subclass.
 */
public class Frag_01 extends BaseFragment {

    private PullToRefreshListView pullToRefreshListView;
    private int page = 1;
    private List<Bean.ListdataBean> list = new ArrayList<>();

    @Override
    protected void initView(View inflate) {
        pullToRefreshListView = inflate.findViewById(R.id.lv);
        //设置条目点击事件
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                intent.putExtra("key","https://www.thepaper.cn/newsDetail_forward_4923534");
                startActivity(intent);
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_01;
    }

    @Override
    protected void initData() {
        //设置允许上下拉
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                list.clear();
                getData();
                pullToRefreshListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page ++;
                getData();
                pullToRefreshListView.onRefreshComplete();
            }
        });
        getData();
    }

    public void getData() {
        //判断是否有网
        if (NetUtils.getInstance().isWang(getActivity())){
            //设置路径
            String httpUri = "";
            if (page == 1){
                httpUri = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai1.json";
            } else if (page == 2) {
                httpUri = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai2.json";
            }else {
                httpUri = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai3.json";
            }
            //调用解析方法
            NetUtils.getInstance().getJson(httpUri, new NetUtils.JieKo() {
                @Override
                public void ongetJson(String json) {
                    //解析数据
                    Log.e("xx", json);
                    Gson gson = new Gson();
                    Bean bean = gson.fromJson(json, Bean.class);
                    List<Bean.ListdataBean> listdata = bean.getListdata();
                    list.addAll(listdata);
                    //设置适配器
                    MyAdapter myAdapter = new MyAdapter(list);
                    pullToRefreshListView.setAdapter(myAdapter);
                }
            });
        }else {

        }
    }
}

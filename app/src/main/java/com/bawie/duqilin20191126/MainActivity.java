package com.bawie.duqilin20191126;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bawie.duqilin20191126.base.BaseActivity;
import com.bawie.duqilin20191126.fragment.Frag_01;
import com.bawie.duqilin20191126.fragment.Frag_02;

import java.io.IOException;
import java.util.ArrayList;

/*
*①　使用TabLayout+ViewPager+Fragment完成顶部横向菜单。
②　抽取Activity基类和Fragment基类，并在项目中使用。
* */

public class MainActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPage;
    private ArrayList<Fragment> list = new ArrayList<>();
    private ArrayList<String> name = new ArrayList<>();
    private ImageView imageView;
    //这是注释

    @Override
    protected void inittData() {
        Frag_01 frag_01 = new Frag_01();
        //赋值
        Frag_02 frag_02 = Frag_02.getInstance("推荐");
        Frag_02 frag_03 = Frag_02.getInstance("视频");
        //添加到数组
        list.add(frag_01);
        list.add(frag_02);
        list.add(frag_03);
        //标题
        name.add("时事");
        name.add("推荐");
        name.add("视频");
        viewPage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            //设置标题
            public CharSequence getPageTitle(int position) {
                return name.get(position);
            }
        });
        //关联tablayout和viewpage
        tabLayout.setupWithViewPager(viewPage);
    }

    @Override
    protected void initView() {
        tabLayout = findViewById(R.id.tabl);
        viewPage = findViewById(R.id.vp);
        imageView = findViewById(R.id.im);
        //设置图片点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    //接受回传过来的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == -1){
            Uri data1 = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data1);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.bawie.duqilin20191126;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
 *@auther:杜其林
 *@Date: 2019/11/26
 *@Time:14:39
 *@Description:${DESCRIPTION}
 * */
public class MyAdapter extends BaseAdapter{
    private List<Bean.ListdataBean> list;

    public MyAdapter(List<Bean.ListdataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int itemType = list.get(position).getItemType();
        if (itemType == 1){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            if (getItemViewType(position) == 0){
                convertView = View.inflate(parent.getContext(),R.layout.item,null);
            }else {
                convertView = View.inflate(parent.getContext(),R.layout.item,null);
            }
            viewHolder.textView = convertView.findViewById(R.id.tv);
            viewHolder.imageView = convertView.findViewById(R.id.imageview);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Bean.ListdataBean listdataBean = list.get(position);
        viewHolder.textView.setText(listdataBean.getContent());
        String imageurl = listdataBean.getImageurl();
        NetUtils.getInstance().getPage(imageurl,viewHolder.imageView);
        return convertView;
    }
    class ViewHolder{
        private TextView textView;
        private ImageView imageView;
    }
}

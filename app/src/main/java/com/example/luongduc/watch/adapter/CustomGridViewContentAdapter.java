package com.example.luongduc.watch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luongduc.watch.R;
import com.example.luongduc.watch.modul.ListProduct;

import java.util.ArrayList;

public class CustomGridViewContentAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<ListProduct> listProductArrayList;

    public CustomGridViewContentAdapter(Context context, int layout, ArrayList<ListProduct> listProductArrayList) {
        this.context = context;
        this.layout = layout;
        this.listProductArrayList = listProductArrayList;
    }

    @Override
    public int getCount() {
        return listProductArrayList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.item_gridview_content,parent,false);
        ListProduct listProduct = listProductArrayList.get(position);
        ImageView imgGridProduct = convertView.findViewById(R.id.img_grid_content);
        TextView txtGridProduct = convertView.findViewById(R.id.txt_grid_content);
        imgGridProduct.setImageResource(listProduct.getImgProductGridViewContent());
        txtGridProduct.setText(listProduct.getNameProductGridViewContent());
        return convertView;
    }
}

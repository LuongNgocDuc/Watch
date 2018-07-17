package com.example.luongduc.watch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luongduc.watch.Manager;
import com.example.luongduc.watch.R;
import com.example.luongduc.watch.modul.UserManagerInfor;

import java.util.ArrayList;

public class CustomGridViewAdapter extends BaseAdapter {
    public Manager context;
    public int layout;
    public ArrayList<UserManagerInfor> userManagerArrayList;

    public CustomGridViewAdapter(Manager context, int layout, ArrayList<UserManagerInfor> userManagerArrayList) {
        this.context = context;
        this.layout = layout;
        this.userManagerArrayList = userManagerArrayList;
    }


    @Override
    public int getCount() {
        return userManagerArrayList.size();
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
        convertView = layoutInflater.inflate(R.layout.item_manager, parent, false);
        UserManagerInfor userManager = userManagerArrayList.get(position);
        TextView txtGridManager = convertView.findViewById(R.id.txt_grid_manager);
        ImageView imgGridManager = convertView.findViewById(R.id.img_grid_manager);
        txtGridManager.setText(userManager.getNameManager());
        imgGridManager.setImageResource(userManager.getImgManager());
        return convertView;
    }
}

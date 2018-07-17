package com.example.luongduc.watch.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luongduc.watch.R;
import com.example.luongduc.watch.modul.ListViewProductModul;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class CustomLvProductAdapter extends ArrayAdapter<ListViewProductModul> {
    public Context context;
    public int resource;
    public ArrayList<ListViewProductModul> listViewProductArrayList;


    public CustomLvProductAdapter(@NonNull Context context, int resource, ArrayList<ListViewProductModul> listViewProductArrayList) {
        super(context, resource, listViewProductArrayList);
        this.listViewProductArrayList = listViewProductArrayList;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

    if (convertView == null){
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.item_lv_product,null,true);
    }
    ListViewProductModul listViewProductModul = listViewProductArrayList.get(position);

        ImageView img = convertView.findViewById(R.id.img_lv_product);
        Picasso.get().load(listViewProductModul.getImgLvProduct()).resize(130,130).into(img);
    TextView txtName = convertView.findViewById(R.id.txt_name_lv_product);
    txtName.setText(listViewProductModul.getNameLvProduct());
    TextView txtDescrible = convertView.findViewById(R.id.txt_describle_lv_product);
    txtDescrible.setText(listViewProductModul.getDescribleLvProduct());
        return convertView;
    }
}

package com.example.luongduc.watch;

import android.content.Intent;
import android.os.UserManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.luongduc.watch.adapter.CustomGridViewAdapter;
import com.example.luongduc.watch.modul.UserManagerInfor;

import java.util.ArrayList;

public class Manager extends AppCompatActivity {
   public GridView gridView;
   public ArrayList<UserManagerInfor> userManagers;
   public CustomGridViewAdapter customGridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        setUpGridView();
        setUpEventGridView();
    }

    private void setUpEventGridView() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(Manager.this,PostProduct.class));
                        Toast.makeText(Manager.this, "1", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }
            }
        });
    }

    private void setUpGridView() {
        gridView = findViewById(R.id.gridview_manager);
        userManagers = new ArrayList<>();
        userManagers.add(new UserManagerInfor(R.mipmap.domain_manager_icon,"Post Product"));
        userManagers.add(new UserManagerInfor(R.mipmap.domain_manager_icon,"AAAAA"));
        userManagers.add(new UserManagerInfor(R.mipmap.domain_manager_icon,"AAAAA"));
        userManagers.add(new UserManagerInfor(R.mipmap.domain_manager_icon,"AAAAA"));
        userManagers.add(new UserManagerInfor(R.mipmap.domain_manager_icon,"AAAAA"));
        userManagers.add(new UserManagerInfor(R.mipmap.domain_manager_icon,"AAAAA"));
        userManagers.add(new UserManagerInfor(R.mipmap.domain_manager_icon,"AAAAA"));
        userManagers.add(new UserManagerInfor(R.mipmap.domain_manager_icon,"AAAAA"));
        customGridViewAdapter = new CustomGridViewAdapter(this,R.layout.item_manager,userManagers);
        gridView.setAdapter(customGridViewAdapter);
    }
}

package com.example.luongduc.watch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.luongduc.watch.adapter.CustomGridViewContentAdapter;
import com.example.luongduc.watch.modul.ListProduct;
import com.example.luongduc.watch.modul.Watch;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ContentActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    Animation animIn, animOut;
    ImageView imgBannerOne, imgBannerTwo, imgBannerThree;
    public ActionBarDrawerToggle drawerToggle;
    public DrawerLayout drawerLayout;
    GridView gridViewContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        setUpSlide();
        setUpEventBanner();
        setUpTimeTaskShowDialogRegister();
        setUpToolBarContent();
        setUpEventNavigation();
        setUpGridView();
        setUpEventGridView();
    }

    private void setUpEventGridView() {
        gridViewContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Toast.makeText(ContentActivity.this, "1", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ContentActivity.this,ListViewProduct.class));
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
                    case 6:
                        break;

                }

            }
        });

    }

    private void setUpGridView() {
        gridViewContent = findViewById(R.id.grid_content);
        ArrayList<ListProduct> listProductArrayList = new ArrayList<>();
        listProductArrayList.add(new ListProduct(R.drawable.dw_man_classic_bristol_silver,"Dw_man_classic"));
        listProductArrayList.add(new ListProduct(R.drawable.dw_man_classic_bristol_silver,"Dw_man_classic"));
        listProductArrayList.add(new ListProduct(R.drawable.dw_man_classic_bristol_silver,"Dw_man_classic"));
        listProductArrayList.add(new ListProduct(R.drawable.dw_man_classic_bristol_silver,"Dw_man_classic"));
        listProductArrayList.add(new ListProduct(R.drawable.dw_man_classic_bristol_silver,"Dw_man_classic"));
        listProductArrayList.add(new ListProduct(R.drawable.dw_man_classic_bristol_silver,"Dw_man_classic"));
        listProductArrayList.add(new ListProduct(R.drawable.dw_man_classic_bristol_silver,"Dw_man_classic"));
        listProductArrayList.add(new ListProduct(R.drawable.dw_man_classic_bristol_silver,"Dw_man_classic"));

        CustomGridViewContentAdapter customGridViewContentAdapter = new CustomGridViewContentAdapter(this,R.layout.item_gridview_content,listProductArrayList);
        gridViewContent.setAdapter(customGridViewContentAdapter);
    }

    private void setUpEventNavigation() {
        NavigationView navigationView = findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_drawer:
                        Toast.makeText(ContentActivity.this, "1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.introduce_drawer:
                        break;
                    case R.id.account_drawer:
                        startActivity(new Intent(ContentActivity.this, SignInAccountUserActivity.class));
                        break;
                    case R.id.account_manager_drawer:
                        startActivity(new Intent(ContentActivity.this, SignInAccountManagerActivity.class));
                        break;
                    case R.id.support_drawer:
                        break;
                    case R.id.logout_drawer:
                        finish();
                        break;

                }

                return false;
            }
        });

    }


    private void setUpToolBarContent() {
        Toolbar toolbar = findViewById(R.id.tool_bar_content);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_content);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.menu_icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setUpTimeTaskShowDialogRegister() {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ContentActivity.this);
                        builder.setTitle("Register Account");
                        builder.setMessage("Vui lòng đăng kí để được hỗ trợ" + "\n" + "Cảm ơn.");
                        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ContentActivity.this, RegisterAccountActivity.class);
                                startActivity(intent);
                            }
                        });
                        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 3000);
    }

    private void setUpEventBanner() {
        imgBannerOne = findViewById(R.id.img_banner_one);
        imgBannerTwo = findViewById(R.id.img_banner_two);
        imgBannerThree = findViewById(R.id.img_banner_three);
        imgBannerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContentActivity.this, "1", Toast.LENGTH_SHORT).show();
            }
        });
        imgBannerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContentActivity.this, "2", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpSlide() {
        viewFlipper = findViewById(R.id.view_flipper_content);
        animIn = AnimationUtils.loadAnimation(this, R.anim.animation_in);
        animOut = AnimationUtils.loadAnimation(this, R.anim.animation_out);
        viewFlipper.setInAnimation(animIn);
        viewFlipper.setOutAnimation(animOut);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

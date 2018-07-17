package com.example.luongduc.watch;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.luongduc.watch.adapter.CustomLvProductAdapter;
import com.example.luongduc.watch.modul.ListViewProductModul;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ListViewProduct extends AppCompatActivity {
    private ListView lvProduct;
    private ArrayList<ListViewProductModul> listViewProductArrayList;
    private CustomLvProductAdapter customLvProductAdapter;
    public static Bitmap bitmapImg;
    int imgLoad ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_product);

        getData("http://mantan181.webmantan.com/getdatalistproduct.php");
        setUpLv();

    }

    private void setUpLv() {
        lvProduct = findViewById(R.id.lv_product);
        listViewProductArrayList = new ArrayList<>();

        customLvProductAdapter = new CustomLvProductAdapter
                (this,R.layout.item_lv_product,listViewProductArrayList);
        lvProduct.setAdapter(customLvProductAdapter);
    }

    private void getData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                listViewProductArrayList.clear();
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObjectRequest = response.getJSONObject(i);
                        listViewProductArrayList.add(new ListViewProductModul(
                                jsonObjectRequest.getString("Path"),
                                jsonObjectRequest.getString("Nameproduct"),
                                jsonObjectRequest.getString("Describleproduct")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                customLvProductAdapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListViewProduct.this,error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}

package com.example.luongduc.watch;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class PostProduct extends AppCompatActivity {
    public EditText edtNameProduct, edtDescribleProduct, edtPriceProduct;
    public Button btnGalary, btnUpload, btnShow;
    public ImageView img;
    public ImageView imgdecode;
    public static Bitmap bitmap;
    public String imgg;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_product);
        edtNameProduct = findViewById(R.id.edt_name_product);
        edtDescribleProduct = findViewById(R.id.edt_describle_product);
        edtPriceProduct = findViewById(R.id.edt_price_product);
        img = findViewById(R.id.img_product_add);
        imgdecode = findViewById(R.id.img_decode);

        btnShow = findViewById(R.id.btn_show);
        btnGalary = findViewById(R.id.btn_galary);
        btnUpload = findViewById(R.id.btn_upload);
        btnGalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(PostProduct.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 999);

            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upLoadImg("http://mantan181.webmantan.com/dataimgupload.php");
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 999) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "SELECT IMAGE"), 999);
            }
        } else {
            Toast.makeText(this, "Please permision", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK && data != null) {
            Uri filePath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public void upLoadImg(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {
                    Toast.makeText(PostProduct.this, "success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PostProduct.this, "error", Toast.LENGTH_SHORT).show();
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                String nameProduct = edtNameProduct.getText().toString().trim();
                String describleProduct = edtDescribleProduct.getText().toString().trim();
                String priceProduct = edtPriceProduct.getText().toString().trim();
                imgg = getStringImage(bitmap);

                map.put("Nameproduct", nameProduct);
                map.put("Describleproduct", describleProduct);
                map.put("Priceproduct", priceProduct);
                map.put("image", imgg);

                return map;
            }
        };

        requestQueue.add(stringRequest);
    }

    public String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgbyte = byteArrayOutputStream.toByteArray();
        String encodeImage = Base64.encodeToString(imgbyte, Base64.DEFAULT);
        return encodeImage;
    }
}

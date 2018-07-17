package com.example.luongduc.watch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignInAccountManagerActivity extends AppCompatActivity {
    EditText edtAccountManager, edtPasswordManager;
    Button btnManagerSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_account_manager);
        setUpControl();
    }

    private void setUpControl() {
        edtAccountManager = findViewById(R.id.edt_manage_account_sign_in);
        edtPasswordManager = findViewById(R.id.edt_manage_password_sign_in);
        btnManagerSignIn = findViewById(R.id.btn_manager_sign_in);
        btnManagerSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataManage("http://mantan181.webmantan.com/getdatamanager.php");
            }
        });
    }

    private void getDataManage(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //int id = 0;
                String accountManagerSignIn = edtAccountManager.getText().toString().trim();
                String passwordManagerSignIn = edtPasswordManager.getText().toString().trim();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObjectRequest = response.getJSONObject(i);
                        if (
                                accountManagerSignIn.equals(jsonObjectRequest.getString("Account")) &&
                                        passwordManagerSignIn.equals(jsonObjectRequest.getString("Password"))) {
                            Toast.makeText(SignInAccountManagerActivity.this, "Sign in success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignInAccountManagerActivity.this,Manager.class));
                        } else {
                            Toast.makeText(SignInAccountManagerActivity.this, "Mistake Account or password", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

}
}

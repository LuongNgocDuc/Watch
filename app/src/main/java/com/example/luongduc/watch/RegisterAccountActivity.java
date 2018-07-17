package com.example.luongduc.watch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterAccountActivity extends AppCompatActivity {
    EditText edtAccount, edtPassword, edtConfirmPassWord, edtEmail;
    Button btnRegister;
    String urlInsertDataUser = "http://mantan181.webmantan.com/insertdatauser.php";
    public String accountUser;
    public String passwordUser;
    public String confrimPasswordUser;
    public String emailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        Toolbar toolbar = findViewById(R.id.tool_bar_register);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back_icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpEventRegisterUser();
    }

    private void setUpEventRegisterUser() {
        edtAccount = findViewById(R.id.edt_account_register);
        edtPassword = findViewById(R.id.edt_password_register);
        edtConfirmPassWord = findViewById(R.id.edt_confirm_password_register);
        edtEmail = findViewById(R.id.edt_email_register);

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                accountUser = edtAccount.getText().toString().trim();
                passwordUser = edtPassword.getText().toString().trim();
                confrimPasswordUser = edtConfirmPassWord.getText().toString().trim();
                emailUser = edtEmail.getText().toString().trim();
                if (accountUser.isEmpty() || passwordUser.isEmpty() || confrimPasswordUser.isEmpty()
                        || emailUser.isEmpty()) {
                    Toast.makeText(RegisterAccountActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (!passwordUser.equals(confrimPasswordUser)) {
                        Toast.makeText(RegisterAccountActivity.this, "Mật khẩu nhập lại không trùng với mật khẩu", Toast.LENGTH_SHORT).show();
                        edtPassword.setText("");
                        edtConfirmPassWord.setText("");
                        edtPassword.requestFocus();
                    }
                    else if(passwordUser.equals(confrimPasswordUser)) {
                        registerUser(urlInsertDataUser);
                    }
                }
            }
        });
    }

    private void registerUser(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(RegisterAccountActivity.this, "Register success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterAccountActivity.this,SignInAccountUserActivity.class));

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
                Map<String,String> map = new HashMap<>();
                map.put("HoTenUser",edtAccount.getText().toString().trim());
                map.put("PasswordUser",edtPassword.getText().toString().trim());
                map.put("EmailUser",edtEmail.getText().toString().trim());

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

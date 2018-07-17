package com.example.luongduc.watch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class SignInAccountUserActivity extends AppCompatActivity {
    EditText edtAccountUserSignIn, edtPasswordUserSignIn;
    Button btnSignIn, btnRegister;
    TextView txtUserProfireDrawer, txtEmailProfireDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_account_user);
        setUpControl();

    }

    private void setUpControl() {
        txtUserProfireDrawer = findViewById(R.id.txt_account_nav);
        txtEmailProfireDrawer = findViewById(R.id.txt_email_nav);
        edtAccountUserSignIn = findViewById(R.id.edt_account_user_sign_in);
        edtPasswordUserSignIn = findViewById(R.id.edt_password_user_sign_in);

  //      edtAccountUserSignIn.addTextChangedListener(textWatcher);
  //      edtAccountUserSignIn.addTextChangedListener(textWatcher);

        btnRegister = findViewById(R.id.btn_user_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInAccountUserActivity.this,RegisterAccountActivity.class));
            }
        });
        btnSignIn = findViewById(R.id.btn_user_sign_in);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("http://mantan181.webmantan.com/getdatauser.php");
            }
        });
    }
 ////   TextWatcher textWatcher = new TextWatcher() {
 //       @Override
 //       public void beforeTextChanged(CharSequence s, int start, int count, int after) {
 //
 //       }
//
 //       @Override
 //       public void onTextChanged(CharSequence s, int start, int before, int count) {
 //         String a =  edtAccountUserSignIn.getText().toString().trim();
 //         String b = edtPasswordUserSignIn.getText().toString().trim();
 //       }
//
 //       @Override
 //       public void afterTextChanged(Editable s) {
//
 //       }
 //   };
    private void getData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //int id = 0;
                String accountUserSignIn =  edtAccountUserSignIn.getText().toString().trim();
                String passwordUserSignIn = edtPasswordUserSignIn.getText().toString().trim();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObjectRequest = response.getJSONObject(i);
                        if (accountUserSignIn.equals(jsonObjectRequest.getString("Account")) &&
                                        passwordUserSignIn.equals(jsonObjectRequest.getString("Password"))) {
                        //    txtUserProfireDrawer.setText(jsonObjectRequest.getString("Account"));
                           // txtEmailProfireDrawer.setText(jsonObjectRequest.getString("Email"));
                            Toast.makeText(SignInAccountUserActivity.this, "Sign in success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignInAccountUserActivity.this,ContentActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignInAccountUserActivity.this, "Mistake Account or password", Toast.LENGTH_SHORT).show();
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

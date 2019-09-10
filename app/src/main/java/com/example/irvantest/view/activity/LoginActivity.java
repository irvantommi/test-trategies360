package com.example.irvantest.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.irvantest.Base.BaseActivity;
import com.example.irvantest.R;
import com.example.irvantest.rest.login.ApiLogin;
import com.example.irvantest.util.Q;
import com.example.irvantest.util.Util;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    // other class
    private ApiLogin api;
    // from xml
    private EditText etIdentity;
    private EditText etPassword;
    private Button btnLogin;
    // member var
    private String identity;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // init UI
        etIdentity = findViewById(R.id.et_identity);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        api = retrofit.create(ApiLogin.class);
        // setOnClick
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etIdentity.getText().toString().trim();
                if (Util.isExist(id)) {
                    identity = id;
                } else {
                    showToast("email / phone number can't be empty");
                    etIdentity.requestFocus();
                    return;
                }
                String pwd = etPassword.getText().toString().trim();
                if (Util.isExist(pwd)) {
                    password = pwd;
                } else {
                    showToast("password can't be empty");
                    etPassword.requestFocus();
                    return;
                }
                login();
            }
        });
    }

    public void login() { // test@strategies360.com : Demo@123
        api.login(identity, password).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject body = response.body();
                if (body.has(Q.Q_SUCCESS) && body.get(Q.Q_SUCCESS).getAsBoolean()) {
                    if (body.has(Q.Q_DATA)) {
                        JsonObject data = body.getAsJsonObject(Q.Q_DATA);
                        if (data.has(Q.Q_USER)) {
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.putExtra(Q.Q_USER, gson.toJson(data.getAsJsonObject(Q.Q_USER)));
                            startActivity(intent);
                        }
                    } else {
                        showToast(response.errorBody().toString());
                    }
                } else {
                    showToast(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
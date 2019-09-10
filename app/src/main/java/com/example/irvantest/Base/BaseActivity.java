package com.example.irvantest.Base;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.irvantest.rest.ApiClient;
import com.example.irvantest.util.URL;
import com.google.gson.Gson;

import retrofit2.Retrofit;

public abstract class BaseActivity extends AppCompatActivity {
    protected Gson gson = new Gson();
    protected Retrofit retrofit = ApiClient.getClient(URL.VERSION);

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
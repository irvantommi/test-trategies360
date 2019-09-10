package com.example.irvantest.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.irvantest.Base.BaseActivity;
import com.example.irvantest.R;
import com.example.irvantest.model.User;
import com.example.irvantest.util.Q;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // init UI
        EditText etNameFirst = findViewById(R.id.et_name_first);
        EditText etNameLast = findViewById(R.id.et_name_last);
        EditText etPhone = findViewById(R.id.et_phone);
        TextView tvLastLogin = findViewById(R.id.tv_last_login);
        Button btnLogout = findViewById(R.id.btn_logout);
        // setData
        if (getIntent() != null) {
            User user = gson.fromJson(getIntent().getStringExtra(Q.Q_USER), User.class);
            etNameFirst.setText(user.getNameFirst());
            etNameLast.setText(user.getNameLast());
            etPhone.setText(user.getPhoneNo());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault());
        tvLastLogin.setText(sdf.format(new Date()));
        // setOnClick
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
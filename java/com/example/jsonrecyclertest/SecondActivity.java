package com.example.jsonrecyclertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView name, age, email;

    String data1, data2, data3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        name = findViewById(R.id.textView4);
        age = findViewById(R.id.textView5);
        email = findViewById(R.id.textView6);

        getData();
        setData();
    }

    private void getData() {
        data1 = getIntent().getStringExtra("name");
        data2 = getIntent().getStringExtra("age");
        data3 = getIntent().getStringExtra("email");
    }

    private void setData() {
        name.setText(data1);
        age.setText(data2);
        email.setText(data3);
    }
}
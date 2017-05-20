package com.example.ooumoo.result;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {

    TextView answer, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        answer = (TextView) findViewById(R.id.answer);
        result = (TextView) findViewById(R.id.rs);
        String a, r;

        Intent i = getIntent();
        a = i.getStringExtra("set");
        r = i.getStringExtra("rs");

        answer.setText(a);
        result.setText(r);
    }
}

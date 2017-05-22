package com.example.watcharit.gussing;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView answer = (TextView) findViewById(R.id.answer);
        TextView result = (TextView) findViewById(R.id.rs);
        ImageView imageView = (ImageView) findViewById(R.id.fortunateImage);

        String a, r;

        Intent i = getIntent();
        a = i.getStringExtra("set");
        r = i.getStringExtra("rs");

        String uri = "@drawable/" + a.toLowerCase();  // where myresource (without the extension) is the file
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);

        answer.setText(a);
        result.setText(r);

        if(r.equals("CORRECT"))
            result.setTextColor(Color.parseColor("#FF59AC72"));
    }
}

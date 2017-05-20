package com.example.ooumoo.result;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ans;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ans = (EditText) findViewById(R.id.ans);
        send = (Button) findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                String s;
                String set = "DOG";
                s = ans.getText().toString().toUpperCase();
                System.out.println(s);
                if(s.equals(set)){
                    Intent it1 = new Intent(MainActivity.this, Main2Activity.class);
                    it1.putExtra("set", set);
                    it1.putExtra("rs", "CORRECT");
                    startActivity(it1);
                }
                else{
                    Intent it2 = new Intent(MainActivity.this, Main2Activity.class);
                    it2.putExtra("set", set);
                    it2.putExtra("rs", "WRONG");
                    startActivity(it2);
                }
            }
        });
    }

}

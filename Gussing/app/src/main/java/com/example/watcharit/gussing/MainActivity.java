package com.example.watcharit.gussing;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String wordSet[] = {"DOG", "DUCK", "ELEPHANT", "FOOTBALL"};
    String ans;
    int index, count;
    boolean win = false;

    private List<Button> buttons = new ArrayList<Button>();
    private static final int[] BUTTON_IDS = {
            R.id.btnA, R.id.btnB, R.id.btnC, R.id.btnD, R.id.btnE,
            R.id.btnF, R.id.btnG, R.id.btnH, R.id.btnI, R.id.btnJ,
            R.id.btnK, R.id.btnL, R.id.btnM, R.id.btnN, R.id.btnO,
            R.id.btnP, R.id.btnQ, R.id.btnR, R.id.btnS, R.id.btnT,
            R.id.btnU, R.id.btnV, R.id.btnW, R.id.btnX, R.id.btnY, R.id.btnZ
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        index = (int) (Math.random()*10000)%wordSet.length;
        count = 5;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textCount = (TextView)findViewById(R.id.count);
        TextView textView = (TextView)findViewById(R.id.textView);
        textCount.setText(count+"");

        ImageView imageView = (ImageView) findViewById(R.id.fortunateImage);
        String uri = "@drawable/" + wordSet[index].toLowerCase();  // where myresource (without the extension) is the file
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);
        //imageView.setImageResource(pic);

        int i;
        String temp = "";
        for ( i=0; i<wordSet[index].length(); i++){
            temp = temp + "_";
            if(i < wordSet[index].length()-1)
                temp = temp + " ";
        }
        textView.setText(temp);

        for(int id : BUTTON_IDS) {
            Button button = (Button)findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = getApplicationContext();
                    Button btn = (Button)v;
                    char inChar = btn.getText().toString().charAt(0);

                    int point = wordSet[index].indexOf(btn.getText().toString());

                    TextView textView = (TextView)findViewById(R.id.textView);
                    TextView textCount = (TextView)findViewById(R.id.count);
                    if(point < 0 && count > 0) {
                        count--;
                        textCount.setText(count + "");
                    }

                    int i;
                    String temp = textView.getText().toString();
                    String spt [] = temp.split(" ");
                    temp = "";
                    for ( i=0; i<wordSet[index].length(); i++){
                        if(inChar == wordSet[index].charAt(i)){
                            spt[i] = inChar + "";
                        }
                        temp += spt[i];
                        if(i != spt.length-1)
                            temp += " ";
                    }
                    textView.setText(temp);
                    ans = temp.replaceAll("\\s+","");
                    if(ans.equals(wordSet[index])){
                        win = true;
                        Intent it1 = new Intent(MainActivity.this, Main2Activity.class);
                        it1.putExtra("set", wordSet[index]);
                        it1.putExtra("rs", "CORRECT");
                        startActivity(it1);
                    }
                    else  if(count <= 0){
                        win = true;
                        Intent it2 = new Intent(MainActivity.this, Main2Activity.class);
                        it2.putExtra("set", wordSet[index]);
                        it2.putExtra("rs", "WRONG");
                        startActivity(it2);
                    }

                }
            }); // maybe
            buttons.add(button);
        }

        new CountDownTimer(60000, 1000) {
            TextView mTextField = (TextView)findViewById(R.id.textTimer);

            public void onTick(long millisUntilFinished) {
                mTextField.setText(" "+ millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                if (!win) {
                    Intent it2 = new Intent(MainActivity.this, Main2Activity.class);
                    it2.putExtra("set", wordSet[index]);
                    it2.putExtra("rs", "TIME UP");
                    startActivity(it2);
                }
            }

        }.start();
    }
}

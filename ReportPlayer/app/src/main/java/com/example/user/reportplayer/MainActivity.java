package com.example.user.reportplayer;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String reporter="11"; // get reporter id from page result***************
    String reported="22"; // get reported id from page result***************
    String reportDetail;
    String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //EditText detail = (EditText) findViewById(R.id.enterDetail);
        Button submitBtn = (Button) findViewById(R.id.submit);
        Spinner dropdown = (Spinner)findViewById(R.id.spinner);

        //spin
        List<String> list = new ArrayList<String>();
        list.add("Leaving the Game/AFK");
        list.add("Photo/Answer is inappropriate");
        list.add("Using bot");
        list.add("Others");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                //type
                type = parent.getItemAtPosition(pos).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                //detail
                EditText detail = (EditText) findViewById(R.id.enterDetail);
                reportDetail = detail.getText().toString();
                //type,reportDetail,reporter, reported
                insert();
                Toast.makeText(MainActivity.this, "Report has been sent!", Toast.LENGTH_LONG).show();
                // then get back to main page***************
            }
        });
    }
    public void insert(){
        try{
            String link = "http://10.0.2.2/connectReportDB.php";

            String urlParameters  = "type="+type+"&detail="+reportDetail+"&reporter="+reporter+"&reported="+reported;
            byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;
            String request        = "http://10.0.2.2/connectReportDB.php";
            URL    url            = new URL(request);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setDoOutput( true );
            conn.setInstanceFollowRedirects( false );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            conn.setUseCaches( false );
            try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                wr.write(postData);
            }
            conn.connect();
            String a = conn.getResponseMessage()   ;
            String b = conn.getContent().toString();
            //System.out.println(a + " hello konjingjing "+b);
        }catch (MalformedURLException e) {
            System.out.println("error MalformedURLException");
        }catch (IOException e) {
            e.printStackTrace();
        }catch (IOError error) {
            error.printStackTrace();
        }catch(Exception e){
            System.out.println("error here");
            e.printStackTrace();
        }
    }
}

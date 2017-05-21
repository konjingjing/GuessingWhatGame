package com.example.user.reportplayer;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.user.reportplayer.R.drawable.ic_star_rating_press;

public class RatingActivity extends AppCompatActivity {

    Button btnRatingI, btnRatingII, btnRatingIII, btnRatingIV, btnRatingV;
    Button btnReport, btnSubmit;
    Boolean state = false, stateII = false, stateIII = false, stateIV = false, stateV = false;

    //Update UI
    ImageView profileImage;
    TextView profileName;

    UserItemCollectionDao daoCollection;
    UserItemDao dao;

    float ratingDB;
    String profileNameDB, userIDDB, userPlayer2IDDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        initInstances();

        reloadData();


    }

    private void initInstances() {
        /* init Imageview rating */
        btnRatingI = (Button) findViewById(R.id.ivRatingI);
        btnRatingI.setOnClickListener(btnRatingISetOnClickListener);
        btnRatingII = (Button) findViewById(R.id.ivRatingII);
        btnRatingII.setOnClickListener(btnRatingIISetOnClickListener);
        btnRatingIII = (Button) findViewById(R.id.ivRatingIII);
        btnRatingIII.setOnClickListener(btnRatingIIISetOnClickListener);
        btnRatingIV = (Button) findViewById(R.id.ivRatingIV);
        btnRatingIV.setOnClickListener(btnRatingIVSetOnClickListener);
        btnRatingV = (Button) findViewById(R.id.ivRatingV);
        btnRatingV.setOnClickListener(btnRatingVSetOnClickListener);

        /* init Button report and submit */
        btnReport = (Button) findViewById(R.id.btnReport);
        btnReport.setOnClickListener(btnReportSetOnClickListener);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(btnSubmitSetOnClickListener);

        /* init update UI */
        profileImage = (ImageView) findViewById(R.id.profileImage);
        profileName = (TextView) findViewById(R.id.profileName);
    }

    View.OnClickListener btnRatingISetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (state) {
                stateNormal(getResources().getDrawable(R.drawable.ic_star_rating_normal));
                state = false;
                stateII = false;
                stateIII = false;
                stateIV = false;
                stateV = false;
            } else {
                btnRatingI.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_press));
                btnRatingII.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_normal));
                btnRatingIII.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_normal));
                btnRatingIV.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_normal));
                btnRatingV.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_normal));
                state = true;
                stateII = false;
                stateIII = false;
                stateIV = false;
                stateV = false;
            }
        }
    };

    View.OnClickListener btnRatingIISetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (stateII) {
                stateNormal(getResources().getDrawable(R.drawable.ic_star_rating_normal));
                state = false;
                stateII = false;
                stateIII = false;
                stateIV = false;
                stateV = false;
            } else {
                btnRatingI.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_press));
                btnRatingII.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_press));
                btnRatingIII.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_normal));
                btnRatingIV.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_normal));
                btnRatingV.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_normal));
                stateII = true;
                state = false;
                stateIII = false;
                stateIV = false;
                stateV = false;
            }
        }
    };

    View.OnClickListener btnRatingIIISetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (stateIII) {
                stateNormal(getResources().getDrawable(R.drawable.ic_star_rating_normal));
                stateIII = false;
                state = false;
                stateII = false;
                stateIV = false;
                stateV = false;
            } else {
                btnRatingI.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_press));
                btnRatingII.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_press));
                btnRatingIII.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_press));
                btnRatingIV.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_normal));
                btnRatingV.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_normal));
                stateIII = true;
                state = false;
                stateII = false;
                stateIV = false;
                stateV = false;
            }
        }
    };

    View.OnClickListener btnRatingIVSetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (stateIV) {
                stateNormal(getResources().getDrawable(R.drawable.ic_star_rating_normal));
                stateIV = false;
                state = false;
                stateIII = false;
                stateII = false;
                stateV = false;
            } else {
                btnRatingI.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_press));
                btnRatingII.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_press));
                btnRatingIII.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_press));
                btnRatingIV.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_press));
                btnRatingV.setBackground(getResources().getDrawable(R.drawable.ic_star_rating_normal));
                stateIV = true;
                state = false;
                stateII = false;
                stateIII = false;
                stateV = false;
            }
        }
    };

    View.OnClickListener btnRatingVSetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (stateV) {
                stateNormal(getResources().getDrawable(R.drawable.ic_star_rating_normal));
                stateV = false;
                state = false;
                stateII = false;
                stateIII = false;
                stateIV = false;
            } else {
                stateNormal(getResources().getDrawable(R.drawable.ic_star_rating_press));
                stateV = true;
                state = false;
                stateII = false;
                stateIII = false;
                stateIV = false;
            }
        }
    };

    View.OnClickListener btnReportSetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.wtf("Go to Report Page","Success");
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("userID",userIDDB);
            intent.putExtra("userPlayer2ID",userPlayer2IDDB);
            startActivity(intent);
        }
    };

    View.OnClickListener btnSubmitSetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(state){
                updateRating(1);
            }else if(stateII){
                updateRating(2);
            }else if(stateIII){
                updateRating(3);
            }else if(stateIV){
                updateRating(4);
            }else if(stateV){
                updateRating(5);
            }else{
                updateRating(0);
            }
        }
    };

    void updateRating(int rate){
        float temp;
        float amount = 0;
        temp = ratingDB;
        amount = (rate+temp)/2;
        updateData(amount);
        Log.wtf("updated","Show average of rating: "+amount);
    }

    private void updateData(float amountRating) {
        String urlParameters  = "&rating="+amountRating;
        byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
        int    postDataLength = postData.length;
        String request        = "http://localhost/connectUserDB.php";
        URL url            = null;
        try {
            url = new URL(request);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn= null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        conn.setDoOutput( true );
        conn.setInstanceFollowRedirects( false );
        try {
            conn.setRequestMethod( "POST" );
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty( "charset", "utf-8");
        conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
        conn.setUseCaches( false );
        try {
            try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                wr.write(postData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reloadData() {
        Call<UserItemCollectionDao> call = HttpManager.getInstance().getService().loadUserList();
        call.enqueue(new Callback<UserItemCollectionDao>() {
            @Override
            public void onResponse(Call<UserItemCollectionDao> call, Response<UserItemCollectionDao> response) {
                if (response.isSuccessful()){
                    daoCollection = response.body();
                    dao = daoCollection.getUser().get(0);
                    ratingDB = dao.getRating();
                    profileNameDB = dao.getProfileName();
                    userIDDB = dao.getUserID();
                    userPlayer2IDDB = dao.getUserPlayer2ID();

                    /* loadUserName */
                    profileName.setText(profileNameDB);

                    /* loadUserPictureProfile */
                    Glide.with(RatingActivity.this)
                            .load(dao.getProfileImage())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(profileImage);
                }
                else {
                    try {
                        Toast.makeText(getApplicationContext()
                                ,response.errorBody().string()
                                ,Toast.LENGTH_SHORT)
                                .show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserItemCollectionDao> call, Throwable t) {

            }
        });
    }

    private void stateNormal(Drawable drawable) {
        btnRatingI.setBackground(drawable);
        btnRatingII.setBackground(drawable);
        btnRatingIII.setBackground(drawable);
        btnRatingIV.setBackground(drawable);
        btnRatingV.setBackground(drawable);
    }
}

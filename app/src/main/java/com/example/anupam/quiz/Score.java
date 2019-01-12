package com.example.anupam.quiz;

import android.content.Intent;
import android.net.http.SslCertificate;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    TextView Score;
    int score;
    private Button mSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        getSupportActionBar().hide();

        Score = (TextView)findViewById(R.id.scoreCard);

        Intent mIntent = getIntent();
        score = mIntent.getIntExtra("Score", 0);

        Score.setText(String.valueOf(score));

        mSave = (Button)findViewById(R.id.btnSubmit);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}

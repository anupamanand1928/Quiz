package com.example.anupam.quiz;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final long COUNTDOWN_IN_MILLIS = 11000;
    private TextView mScoreView;
    private TextView mQuestion;
    private TextView mQuestionView;
    private TextView textViewCountDown;

    private Button mButtonChoice1,mButtonChoice2,mButtonChoice3,mButtonChoice4;

    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private int mScore,qNumber = 1;
    private int mQuestionNumber = new Random().nextInt((10 - 0) + 1) + 0;
    private String mAnswer;

    private Firebase mQuestionRef,mChoice1Ref,mChoice2Ref,mChoice3Ref,mChoice4Ref,mAnswerRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mScoreView = (TextView)findViewById(R.id.score);
        mQuestion = (TextView)findViewById(R.id.question);
        mQuestionView = (TextView)findViewById(R.id.questionnumber);
        textViewCountDown = findViewById(R.id.text_view_countdown);

        mButtonChoice1 = (Button)findViewById(R.id.choice1);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        mButtonChoice3 = (Button)findViewById(R.id.choice3);
        mButtonChoice4 = (Button)findViewById(R.id.choice4);

        textColorDefaultCd = textViewCountDown.getTextColors();

        updateQuestion();

        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mButtonChoice1.getText().equals(mAnswer)){
                    mScore = mScore + 10;
                    qNumber = qNumber + 1;
                    Toast.makeText(MainActivity.this, "Correct ", Toast.LENGTH_SHORT).show();
                    updateQuestionNumber(qNumber);
                    updateScore(mScore);
                    updateQuestion();

                }else {
                    lastQuestion(mScore);
                }
                if(qNumber == 11) {
                    allCorrect();
                }
            }
        });

        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mButtonChoice2.getText().equals(mAnswer)){
                    mScore = mScore + 10;
                    qNumber = qNumber + 1;
                    Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                    updateQuestionNumber(qNumber);
                    updateScore(mScore);
                    updateQuestion();

                }else {
                    lastQuestion(mScore);
                }
                if(qNumber == 11) {
                    allCorrect();
                }
            }
        });

        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mButtonChoice3.getText().equals(mAnswer)){
                    mScore = mScore + 10;
                    qNumber = qNumber + 1;
                    Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                    updateQuestionNumber(qNumber);
                    updateScore(mScore);
                    updateQuestion();
                }else {
                    lastQuestion(mScore);
                }
                if(qNumber == 11) {
                    allCorrect();
                }
            }
        });

        mButtonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mButtonChoice4.getText().equals(mAnswer)){
                    mScore = mScore + 10;
                    qNumber = qNumber + 1;
                    Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                    updateQuestionNumber(qNumber);
                    updateScore(mScore);
                    updateQuestion();
                }else {
                    //Log.d("VALUE OF SCORE", String.valueOf(mScore));
                    lastQuestion(mScore);
                }
                if(qNumber == 11) {
                    allCorrect();
                }
            }
        });

    }

    private void lastQuestion(int score){
        Intent intent = new Intent(MainActivity.this, Score.class);
        intent.putExtra("Score",score);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        MainActivity.this.finish();
    }

    private void allCorrect(){
        Intent intent = new Intent(MainActivity.this, Congrats.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        MainActivity.this.finish();
    }

    private void updateScore(int score){
        mScoreView.setText("" + mScore);
    }

    private void updateQuestionNumber(int score){
        mQuestionView.setText("" + qNumber);
    }

    public void updateQuestion() {
        mQuestionRef = new Firebase("https://quiz-10777.firebaseio.com/"+mQuestionNumber+"/question");

        mQuestionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String question = dataSnapshot.getValue(String.class);
                mQuestion.setText(question);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mChoice1Ref = new Firebase("https://quiz-10777.firebaseio.com/"+mQuestionNumber+"/choice1");
        mChoice1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                mButtonChoice1.setText(choice);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mChoice2Ref = new Firebase("https://quiz-10777.firebaseio.com/"+mQuestionNumber+"/choice2");
        mChoice2Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                mButtonChoice2.setText(choice);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mChoice3Ref = new Firebase("https://quiz-10777.firebaseio.com/"+mQuestionNumber+"/choice3");
        mChoice3Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                mButtonChoice3.setText(choice);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mChoice4Ref = new Firebase("https://quiz-10777.firebaseio.com/"+mQuestionNumber+"/choice4");
        mChoice4Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                mButtonChoice4.setText(choice);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mAnswerRef = new Firebase("https://quiz-10777.firebaseio.com/"+mQuestionNumber+"/answer");
        mAnswerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mAnswer = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();
        mQuestionNumber+=1;


    }

    private void startCountDown() {
        if(qNumber == 11 ) {
            lastQuestion(mScore);
        }
        countDownTimer = new CountDownTimer(timeLeftInMillis,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                qNumber = qNumber + 1;
                updateQuestionNumber(qNumber);
                updateScore(mScore);
                updateQuestion();
            }
        }.start();

    }
    private void updateCountDownText(){
        int minutes = (int) (timeLeftInMillis/1000)/60;
        int seconds = (int) (timeLeftInMillis/1000)%60;

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        textViewCountDown.setText(timeFormatted);

        if(timeLeftInMillis < 4000){
            textViewCountDown.setTextColor(Color.RED);
        }else{
            textViewCountDown.setTextColor(textColorDefaultCd);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
    }
}
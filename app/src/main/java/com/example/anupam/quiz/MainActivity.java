package com.example.anupam.quiz;

import android.content.Intent;
import android.graphics.Color;
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

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mScoreView;
    private TextView mQuestion;
    private TextView mQuestionView;

    private Button mButtonChoice1,mButtonChoice2,mButtonChoice3,mButtonChoice4;

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

        mButtonChoice1 = (Button)findViewById(R.id.choice1);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        mButtonChoice3 = (Button)findViewById(R.id.choice3);
        mButtonChoice4 = (Button)findViewById(R.id.choice4);

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
        MainActivity.this.finish();
    }

    private void allCorrect(){
        Intent intent = new Intent(MainActivity.this, Congrats.class);
        startActivity(intent);
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

        mQuestionNumber+=1;


    }
}

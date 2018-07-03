package com.sjw.myrecycleview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sjw.myrecycleview.activity.RecyclerCustomRefrenshviewActivity;
import com.sjw.myrecycleview.activity.RecyclerHasAnimateActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void customPullrefrensh(View view) {

        startActivity(new Intent(this, RecyclerCustomRefrenshviewActivity.class));

    }

    public void hasAnimate(View view) {

        startActivity(new Intent(this, RecyclerHasAnimateActivity.class));


    }
}

package com.andy.chen.chainresponsibility;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andy.chen.action.ActionControl;
import com.andy.chen.drink.Drink;
import com.andy.chen.eat.Eat;
import com.andy.chen.learn.Learn;
import com.andy.chen.play.Play;

import static com.andy.chen.action.UIModelType.TYPE_STUDENT;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private Button btnEat;
    private Button btnDrink;
    private Button btnPlayBall;
    private Button btnStudy;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tv_show_text);
        btnEat = findViewById(R.id.btn_eat);
        btnDrink = findViewById(R.id.btn_drink);
        btnPlayBall = findViewById(R.id.btn_play_basketball);
        btnStudy = findViewById(R.id.btn_study);

        btnEat.setOnClickListener(this);
        btnDrink.setOnClickListener(this);
        btnPlayBall.setOnClickListener(this);
        btnStudy.setOnClickListener(this);
        ActionControl.getControl().initAction(this,TYPE_STUDENT,false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_eat:
                Eat.startEat();
                break;
            case R.id.btn_drink:
                Drink.startDrinking();
                break;
            case R.id.btn_play_basketball:
                Play.startPlay();
                break;
            case R.id.btn_study:
                Learn.startLearn();
                break;
        }



    }
}
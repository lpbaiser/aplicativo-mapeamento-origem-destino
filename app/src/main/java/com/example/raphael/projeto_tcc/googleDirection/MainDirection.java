package com.example.raphael.projeto_tcc.googleDirection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.raphael.projeto_tcc.R;

/**
 * Created by raphael on 09/05/2016.
 */
public class MainDirection extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        findViewById(R.id.btn_simple).setOnClickListener(this);
//        findViewById(R.id.btn_transit).setOnClickListener(this);
//        findViewById(R.id.btn_alternative).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
//        if (id == R.id.btn_simple) {
//            goToSimpleDirection();
//        } else if (id == R.id.btn_transit) {
//            goToTransitDirection();
//        } else if (id == R.id.btn_alternative) {
//            goToAlternativeDirection();
//        }
    }

   public void goToSimpleDirection() {
        openActivity(SimpleDirectionActivity.class);
    }

    public void goToTransitDirection() {
        openActivity(TransitDirectionActivity.class);
    }

    public void goToAlternativeDirection() {
        openActivity(AlternativeDirectionActivity.class);
    }

    public void openActivity(Class<?> cs) {
        startActivity(new Intent(this, cs));
    }
}

package com.deviza.lab_1_dam.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.deviza.lab_1_dam.R;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }

    public void MyClick(View v) {
        try {
            Intent myIntentA1A2 = new Intent(MainActivity5.this, Draw1.class);
            startActivityForResult(myIntentA1A2, 1);
        } catch (Exception e) {
            Toast.makeText(getBaseContext(),
                    e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
package com.example.playaround;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Java class is somehow related to this activity
    }

    public void onBtnClick(View view) {
        TextView txtHello = findViewById(R.id.txtMessage);  // This id here should be the exact ID inside the layout file (activity_main.xml)
        txtHello.setText("Hello");
    }
}
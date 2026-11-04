package com.example.excessbaggagecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Toolbar myToolbar;
    Button btnAbout, btnCalculator, btnShare;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);

            myToolbar = (Toolbar) findViewById(R.id.my_toolbar);

            myToolbar.setTitle("ScaleCheck");
            myToolbar.setSubtitle("Excess Baggage Calculator");

            btnAbout = findViewById(R.id.button);
            btnCalculator = findViewById(R.id.button2);
            btnShare = findViewById(R.id.btnShare);

            btnAbout.setOnClickListener(this);
            btnCalculator.setOnClickListener(this);
            btnShare.setOnClickListener(this);
        }

        @Override
            public void onClick(View view) {
            if (view == btnAbout) {
                Intent intent = new Intent(this, About.class);

                startActivity(intent);
            }
            if (view == btnCalculator) {
                Intent intent = new Intent(this, Calculator.class);

                startActivity(intent);
            }
            if (view == btnShare) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"This is ScaleCheck: Excess Baggage Fee Calculator.\n https://github.com/Allysa639/ScaleCheck");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent,null);
                startActivity(shareIntent);
            }
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
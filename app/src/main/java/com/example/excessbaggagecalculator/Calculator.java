package com.example.excessbaggagecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Calculator extends AppCompatActivity {

    Button btnCalculate;
    TextView tnWeight, tnRate, tvExcessWeight, tvResult, tvExcessCharge ;
    RadioButton rbEconomy, rbBusiness;
    RadioGroup rgClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);

        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult =findViewById(R.id.tvResult);
        tnWeight = findViewById(R.id.tnWeight);
        tnRate = findViewById(R.id.tnRate);
        tvExcessWeight = findViewById(R.id.tvExcessWeight);
        tvExcessCharge = findViewById(R.id.tvExcessCharge);
        rgClass = findViewById(R.id.rgClass);
        rbEconomy = findViewById(R.id.rbEconomy);
        rbBusiness = findViewById(R.id.rbBusiness);

        btnCalculate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String weight = tnWeight.getText().toString();
                        String rate = tnRate.getText().toString();

                        if (weight.isEmpty() || rate.isEmpty()) {
                            Toast.makeText(Calculator.this,
                                    "Please enter weight and rate",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (rgClass.getCheckedRadioButtonId() == -1) {
                            Toast.makeText(Calculator.this,
                                    "Please select class",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        double dWeight = Double.parseDouble(weight);
                        double dRate = Double.parseDouble(rate);

                        double StandardWeight = 0;
                        int selectedId = rgClass.getCheckedRadioButtonId();
                        if (selectedId == R.id.rbEconomy) {
                            StandardWeight = 20.0;//Free weight allowed
                        } else if (selectedId == R.id.rbBusiness) {
                            StandardWeight = 40.0;//Free weight allowed
                        }

                        double excessWeight = dWeight - StandardWeight;
                        if (excessWeight < 0) {
                            excessWeight = 0;
                        }
                        double excessCharge = excessWeight * dRate;
                        double result = excessCharge + (excessCharge * 0.08);

                        tvResult.setText(String.format("RM %.2f", result));
                        tvExcessWeight.setText(String.format("Excess Weight is %.2f", excessWeight ,"kg"));
                        tvExcessCharge.setText(String.format("Excess Charge is RM %.2f" , excessCharge));
                    }
                }
        );

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
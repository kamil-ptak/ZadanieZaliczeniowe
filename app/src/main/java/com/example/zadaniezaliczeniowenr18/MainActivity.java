package com.example.zadaniezaliczeniowenr18;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etFirstName;
    private EditText etLastName;
    private Spinner spinner;
    private EditText etPasswordLength;
    private CheckBox cbUpperLower;
    private CheckBox cbDigits;
    private CheckBox cbSpecial;
    private Button btnGenerate;
    private Button btnConfirm;

    private String generatedPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        spinner = findViewById(R.id.spinner);
        etPasswordLength = findViewById(R.id.etPasswordLength);
        cbUpperLower = findViewById(R.id.cbUpperLower);
        cbDigits = findViewById(R.id.cbDigits);
        cbSpecial = findViewById(R.id.cbSpecial);
        btnGenerate = findViewById(R.id.btnGenerate);
        btnConfirm = findViewById(R.id.btnConfirm);


    }
}
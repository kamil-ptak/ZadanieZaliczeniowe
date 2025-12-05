package com.example.zadaniezaliczeniowenr18;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

        String[] positions = {"Kierownik", "Straszy programista", "Młodszy", "Tester"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, positions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSummary();
            }
        });


        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generatePassword();
            }
        });
    }


    private void generatePassword() {
        String lengthStr = etPasswordLength.getText().toString();
        if (lengthStr.isEmpty()) return;

        int length = Integer.parseInt(lengthStr);
        if (length <= 0) return;

        String letters = "qwertyuiopasdfghjklzxcvbnm";
        String lettersUppercase = "QWERTYUIOPASDFGHJKLMNBVCXZ";
        String digits = "1234567890";
        String specialChars = "!@#$%^&*(),./:;{}+-=_";

        Random random = new Random();
        List<Character> passwordChars = new ArrayList<>();

        if (cbUpperLower.isChecked()) {
            passwordChars.add(lettersUppercase.charAt(random.nextInt(lettersUppercase.length())));
        }

        if (cbDigits.isChecked()) {
            passwordChars.add(digits.charAt(random.nextInt(digits.length())));
        }

        if (cbSpecial.isChecked()) {
            passwordChars.add(specialChars.charAt(random.nextInt(specialChars.length())));
        }


        while (passwordChars.size() < length) {
            passwordChars.add(letters.charAt(random.nextInt(letters.length())));
        }

        Collections.shuffle(passwordChars);


        StringBuilder sb = new StringBuilder();
        for (char c : passwordChars) sb.append(c);

        generatedPassword = sb.toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(generatedPassword);
        builder.setPositiveButton("ok", null);
        builder.show();
    }



    private void showSummary() {
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String position = spinner.getSelectedItem().toString();

        String message = "Dane pracownika: " + firstName + " " + lastName + "\n" + "Stanowisko: " + position + "\n" + "Hasło: " + generatedPassword;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton("ok", null);
        builder.show();
    }

}
package com.example.zadaniezaliczeniowenr18;

import android.os.Bundle;
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

        String[] positions = {"CEO", "Senior Developer", "Mid Developer", "Junior Developer"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, positions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }



    private void generatePassword() {
        String lengthStr = etPasswordLength.getText().toString();
        if (lengthStr.isEmpty()) return;

        int length = Integer.parseInt(lengthStr);
        if (length <= 0) return;

        String letters = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
        String digits = "1234567890";
        String specialChars = "!@#$%^&*(),./:;{}+-=_";

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(letters.charAt(random.nextInt(letters.length())));
        }

        if (cbUpperLower.isChecked()) {
            int index = random.nextInt(length);
            char c = sb.charAt(index);
            sb.setCharAt(index, Character.toUpperCase(c));
        }

        if (cbDigits.isChecked()) {
            int index = 0;
            char digit = digits.charAt(random.nextInt(digits.length()));
            sb.setCharAt(index, digit);
        }

        if (cbSpecial.isChecked() && length > 1) {
            int index = 1;
            char special = specialChars.charAt(random.nextInt(specialChars.length()));
            sb.setCharAt(index, special);
        }

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
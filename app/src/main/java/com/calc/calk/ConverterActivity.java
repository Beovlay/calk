package com.calc.calk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View.OnClickListener;
public class ConverterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        final Button clicker;
        final EditText value, result;
        final Spinner spinner1, spinner2, spinner3;


        clicker = findViewById(R.id.buttonConvert);
        value = findViewById(R.id.value);
        result = findViewById(R.id.result);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);

        String[] length_values = getResources().getStringArray(R.array.length_array);
        String[] temperature_values = getResources().getStringArray(R.array.temperature_array);
        String[] weight_values = getResources().getStringArray(R.array.weight_array);

        final ArrayAdapter<String> length_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, length_values);
        length_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> temperatura_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, temperature_values);
        temperatura_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> weight_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, weight_values);
        weight_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String results = extras.getString("results");
            value.setText(results);
        }
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String[] choose = getResources().getStringArray(R.array.types_array);

                if (choose[position].equals("Довжина")) {
                    spinner2.setAdapter(length_adapter);
                    spinner3.setAdapter(length_adapter);
                }

                if (choose[position].equals("Температура")) {
                    spinner2.setAdapter(temperatura_adapter);
                    spinner3.setAdapter(temperatura_adapter);
                }

                if (choose[position].equals("Вага")) {
                    spinner2.setAdapter(weight_adapter);
                    spinner3.setAdapter(weight_adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        clicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((!value.getText().toString().equals(""))) {

                    float inputed = Float.parseFloat(value.getText() + "");
                    switch (spinner2.getSelectedItem().toString()) {
                        case ("Кілометри"):
                            switch (spinner3.getSelectedItem().toString()) {
                                case ("Кілометри"):
                                    result.setText(inputed + "");
                                    break;
                                case ("Метри"):
                                    result.setText(inputed * 1000 + "");
                                    break;
                                case ("Сантиметри"):
                                    result.setText(inputed * 100000 + "");
                                    break;
                            }
                            break;

                        case ("Метри"):
                            switch (spinner3.getSelectedItem().toString()) {
                                case ("Метри"):
                                    result.setText(inputed + "");
                                    break;
                                case ("Сантиметри"):
                                    result.setText(inputed * 100 + "");
                                    break;
                                case ("Кілометри"):
                                    result.setText(inputed / 1000 + "");
                                    break;
                            }
                            break;

                        case ("Сантиметри"):
                            switch (spinner3.getSelectedItem().toString()) {
                                case ("Сантиметри"):
                                    result.setText(inputed + "");
                                    break;
                                case ("Метри"):
                                    result.setText(inputed * 100 + "");
                                    break;
                                case ("Кілометри"):
                                    result.setText(inputed * 100000 + "");
                                    break;
                            }
                            break;

                        case ("Тонни"):
                            switch (spinner3.getSelectedItem().toString()) {
                                case ("Тонни"):
                                    result.setText(inputed + "");
                                    break;
                                case ("Кілограми"):
                                    result.setText(inputed * 1000 + "");
                                    break;
                                case ("Грамми"):
                                    result.setText(inputed * 1000000 + "");
                                    break;
                            }
                            break;

                        case ("Кілограми"):
                            switch (spinner3.getSelectedItem().toString()) {
                                case ("Кілограми"):
                                    result.setText(inputed + "");
                                    break;
                                case ("Тонни"):
                                    result.setText(inputed / 1000 + "");
                                    break;
                                case ("Грамми"):
                                    result.setText(inputed * 1000 + "");
                                    break;
                            }
                            break;

                        case ("Грамми"):
                            switch (spinner3.getSelectedItem().toString()) {
                                case ("Грамми"):
                                    result.setText(inputed + "");
                                    break;
                                case ("Тонни"):
                                    result.setText(inputed / 1000000 + "");
                                    break;
                                case ("Кілограми"):
                                    result.setText(inputed / 1000 + "");
                                    break;
                            }
                            break;

                        case ("Цельсія"):
                            switch (spinner3.getSelectedItem().toString()) {
                                case ("Цельсія"):
                                    result.setText(inputed + "");
                                    break;
                                case ("Кельвіни"):
                                    result.setText(inputed - 273.15 + "");
                                    break;
                            }
                            break;

                        case ("Кельвіни"):
                            switch (spinner3.getSelectedItem().toString()) {
                                case ("Кельвіни"):
                                    result.setText(inputed + "");
                                    break;
                                case ("Цельсія"):
                                    result.setText(inputed + 273.15 + "");
                                    break;
                            }
                            break;
                    }
                }
            }
        });


    }
}

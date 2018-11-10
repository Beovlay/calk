package com.calc.calk;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView resultField;
    EditText numberField;
    TextView operationField;
    Double operand = null;
    String lastOperation = "=";
    Button clear;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultField =(TextView) findViewById(R.id.resultField);
        numberField = (EditText) findViewById(R.id.numberField);
        operationField = (TextView) findViewById(R.id.operationField);
        clear = findViewById(R.id.clear);

        numberField.requestFocus();
        numberField.setShowSoftInputOnFocus(false);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearDispay();
            }
        });


    }
    public void onClearDispay(){
        resultField.setText("");
        numberField.setText("");
        operationField.setText("");

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if(operand!=null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_converter:
               Intent converter_intent = new Intent(MainActivity.this, ConverterActivity.class);
               startActivity(converter_intent);
                break;
            case R.id.action_history:
                Intent history_intent = new Intent(MainActivity.this, ConverterActivity.class);
                startActivity(history_intent);
                break;
            case R.id.action_about:
                Intent about_intent = new Intent(MainActivity.this, ConverterActivity.class);
                startActivity(about_intent);
                break;
            case R.id.action_exit:
                Intent exit_intent = new Intent(MainActivity.this, ConverterActivity.class);
                startActivity(exit_intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand= savedInstanceState.getDouble("OPERAND");
        resultField.setText(operand.toString());
        operationField.setText(lastOperation);
    }

    public void onNumberClick(View view){

        Button button = (Button)view;
        if(button.getText().toString().equals(",") && numberField.getText().toString().contains(",") )
            return;

        numberField.append(button.getText());

        if(lastOperation.equals("=") && operand!=null){
            operand = null;
        }
    }
    public void onOperationClick(View view){
        Button button = (Button)view;
        String op = button.getText().toString();
        String number = numberField.getText().toString();
        if(number.length()>0){
            number = number.replace(',', '.');
            try{
                performOperation(Double.valueOf(number), op);
            }catch (NumberFormatException ex){
                numberField.setText("");
            }
        }
        lastOperation = op;
        operationField.setText(lastOperation);
    }

    private void performOperation(Double number, String operation){

        if(operand ==null){
            operand = number;
        }
        else{
            if(lastOperation.equals("=")){
                lastOperation = operation;
            }
            switch(lastOperation){
                case "=":
                    operand =number;
                    break;
                case "/":
                    operand /=number;
                    break;
                case "*":
                    operand *=number;
                    break;
                case "+":
                    operand +=number;
                    break;
                case "-":
                    operand -=number;
                    break;
            }
        }
        resultField.setText(operand.toString().replace('.', ','));
        numberField.setText("");
    }
}

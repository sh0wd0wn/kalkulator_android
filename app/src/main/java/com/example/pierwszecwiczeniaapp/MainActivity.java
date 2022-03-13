package com.example.pierwszecwiczeniaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int decimalPlaceCounter;
    double lastValue, currentValue;
    String lastOp;
    Boolean opClicked, decimalPlace, isFirstOp, resultActive;

    public void numberButtonOnClick(View view) {
        String numberButtonOutput;
        Button b = (Button) view;

        if (opClicked) {
            textView.setText("0.0");
            currentValue=0.0;
        }
        opClicked = false;
        double buttonValue = Double.parseDouble(b.getText().toString());
        if (!decimalPlace) {
            currentValue = (currentValue * 10.0) + buttonValue;
        } else {
            currentValue = currentValue + (buttonValue / (Math.pow(10, decimalPlaceCounter)));
            decimalPlaceCounter++;
        }
        currentValue = Math.round(currentValue*(Math.pow(10,decimalPlaceCounter)))/Math.pow(10,decimalPlaceCounter);

        numberButtonOutput = doubleToString(currentValue);
        textView.setText(numberButtonOutput);
    }

    public void operationsButtonOnClick(View view) {
        String operationInput;

        Button b = (Button) view;
        operationInput = b.getText().toString();
        operationHandler(operationInput);

        if (!operationInput.equals("=") && !operationInput.equals(",")) {
            lastOp = operationInput;
        }
    }

    public void operationHandler(String operation) {
        String numberButtonOutput;
        if (operation.equals("+")) {
            lastOpHandler();
            if (!opClicked) {
                System.out.println("Wykonano dodawanie");
                lastValue+=currentValue;
                numberButtonOutput = doubleToString(lastValue);
                textView.setText(numberButtonOutput);
            }
            decimalPlace = false;
            decimalPlaceCounter = 0;
            opClicked = true;
        }
        if (operation.equals("-")) {

            lastOpHandler();
            if (!opClicked) {
                lastValue=currentValue-lastValue;
                    numberButtonOutput = doubleToString(lastValue);
                    textView.setText(numberButtonOutput);
            }
            decimalPlace = false;
            decimalPlaceCounter = 0;
            opClicked = true;
        }
        if (operation.equals("X")) {
            lastOpHandler();
            if (!opClicked) {
                if(isFirstOp){
                    lastValue = currentValue*1;
                }
                else{
                    lastValue = currentValue*lastValue;
                }
                isFirstOp=false;
                numberButtonOutput = doubleToString(lastValue);
                textView.setText(numberButtonOutput);
            }
            decimalPlace = false;
            decimalPlaceCounter = 0;
            opClicked = true;
        }
        if (operation.equals("/")) {
            lastOpHandler();
            if (!opClicked) {
                if(isFirstOp){
                    lastValue = currentValue;
                }
                else{
                    lastValue = currentValue/lastValue;
                }
                numberButtonOutput = doubleToString(lastValue);
                textView.setText(numberButtonOutput);
            }
            decimalPlace = false;
            decimalPlaceCounter = 0;
            opClicked = true;
        }
        if (operation.equals("=")) {
            lastOpHandler();
            decimalPlace = false;
            decimalPlaceCounter = 0;
            lastOp="";
            currentValue = 0.0;
        }
    }

    public void lastOpHandler() {
        String numberButtonOutput;
        if (!opClicked) {
            switch (lastOp) {
                case "+":
                    lastValue += currentValue;
                    numberButtonOutput = doubleToString(lastValue);
                    textView.setText(numberButtonOutput);
                    opClicked = true;
                    break;
                case "-":
                    lastValue -= currentValue;
                    numberButtonOutput = doubleToString(lastValue);
                    textView.setText(numberButtonOutput);
                    opClicked = true;
                    break;
                case "X":
                    lastValue *= currentValue;
                    numberButtonOutput = doubleToString(lastValue);
                    textView.setText(numberButtonOutput);
                    opClicked = true;
                    break;
                case "/":
                    lastValue /= currentValue;
                    numberButtonOutput = doubleToString(lastValue);
                    textView.setText(numberButtonOutput);
                    opClicked = true;
                    break;
            }
        }
    }

    public void clearButtonOnClick(View view) {
        lastValue = 0.0;
        currentValue = 0.0;
        lastOp = "";
        opClicked = false;
        isFirstOp=true;
        decimalPlaceCounter = 0;
        decimalPlace = false;
        textView.setText("0.0");
    }

    public void pointButtonOnClick(View view) {
        decimalPlace = true;
        if (decimalPlaceCounter == 0) {
            decimalPlaceCounter++;
        }
    }

    public String doubleToString(Double input) {
        return input.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        decimalPlace = false;
        decimalPlaceCounter = 0;
        lastValue = 0.0;
        currentValue = 0.0;
        lastOp = "";
        opClicked = false;
        isFirstOp = true;
        resultActive = false;
        textView = findViewById(R.id.resultTextView);
        String startString = doubleToString(lastValue);
        textView.setText(startString);
    }
}
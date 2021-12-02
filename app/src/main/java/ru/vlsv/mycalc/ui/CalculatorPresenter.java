package ru.vlsv.mycalc.ui;

import android.util.Log;

import ru.vlsv.mycalc.domain.Calculator;
import ru.vlsv.mycalc.domain.Operation;

public class CalculatorPresenter {

    private final CalculatorView view;
    private final Calculator calculator;

    private String strArgOne = "";
    private String strArgTwo = "";
    private Operation previousOperation = null;
    private boolean dotPressed = false;
    private boolean isResult = false;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    private void logCalculate(String toLog) {
        Log.d("logCalculate", toLog);
    }

    public void onDigitPressed(char digit) {

        logCalculate("------Press button-------");
        logCalculate("btn: " + digit);
        logCalculate("strArgOne: " + strArgOne);
        logCalculate("strArgTwo: " + strArgTwo);
        logCalculate("prev_operator: " + (previousOperation != null ? previousOperation.toString() : "null"));
        logCalculate("isResult: " + isResult);

        if (digit == '.' && dotPressed) {
        } else {
            if (previousOperation != null || isResult) {
                strArgTwo = strArgTwo + digit;
                view.showResult(strArgTwo);
            } else {
                strArgOne = strArgOne + digit;
                view.showResult(strArgOne);
            }
            if (digit == '.') {
                dotPressed = true;
            }
        }
    }

    public void onOperandPressed(Operation operation, String errResult) {

        logCalculate("------Press button-------");
        logCalculate("operator: " + operation.toString());
        logCalculate("strArgOne: " + strArgOne);
        logCalculate("strArgTwo: " + strArgTwo);
        logCalculate("prev_operator: " + (previousOperation != null ? previousOperation.toString() : "null"));
        logCalculate("isResult: " + isResult);

        dotPressed = false;

        if (strArgTwo.equals("")) {

            previousOperation = operation;
            isResult = false;

        } else {

            if (strArgOne.equals("")) {
                strArgOne = "0"; // Если операция выбрана перед любым вводом
            }
            double argOne = Double.parseDouble(strArgOne);
            double argTwo = Double.parseDouble(strArgTwo);

            if (argTwo == 0 && previousOperation == Operation.DIV) {
                onCleanPresser();
                view.showResult(errResult);
            } else {
                double result = calculator.performOperation(argOne, argTwo, previousOperation);
                strArgOne = String.valueOf(result);
                view.showResult(strArgOne);
                strArgTwo = "";
                isResult = true;
                previousOperation = operation;
            }
        }
    }

    public void onCleanPresser() {

        logCalculate("Clean");

        strArgOne = "";
        strArgTwo = "";
        previousOperation = null;
        dotPressed = false;
        isResult = false;
        view.showResult("");
    }


}

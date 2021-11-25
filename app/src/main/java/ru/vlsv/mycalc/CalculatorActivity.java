package ru.vlsv.mycalc;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.HashMap;

import ru.vlsv.mycalc.domain.CalculatorImpl;
import ru.vlsv.mycalc.domain.Operation;
import ru.vlsv.mycalc.ui.CalculatorPresenter;
import ru.vlsv.mycalc.ui.CalculatorView;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private TextView txtResult;

    private CalculatorPresenter presenter;

    private String errResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        presenter = new CalculatorPresenter(this, new CalculatorImpl());

        txtResult = findViewById(R.id.inOutField);
        errResult = getResources().getString(R.string.result_string);

        HashMap<Integer, Character> digits = new HashMap<>();
        digits.put(R.id.number_0, '0');
        digits.put(R.id.number_1, '1');
        digits.put(R.id.number_2, '2');
        digits.put(R.id.number_3, '3');
        digits.put(R.id.number_4, '4');
        digits.put(R.id.number_5, '5');
        digits.put(R.id.number_6, '6');
        digits.put(R.id.number_7, '7');
        digits.put(R.id.number_8, '8');
        digits.put(R.id.number_9, '9');
        digits.put(R.id.dot, '.');

        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDigitPressed(digits.get(v.getId()));

            }
        };

        findViewById(R.id.number_0).setOnClickListener(digitClickListener);
        findViewById(R.id.number_1).setOnClickListener(digitClickListener);
        findViewById(R.id.number_2).setOnClickListener(digitClickListener);
        findViewById(R.id.number_3).setOnClickListener(digitClickListener);
        findViewById(R.id.number_4).setOnClickListener(digitClickListener);
        findViewById(R.id.number_5).setOnClickListener(digitClickListener);
        findViewById(R.id.number_6).setOnClickListener(digitClickListener);
        findViewById(R.id.number_7).setOnClickListener(digitClickListener);
        findViewById(R.id.number_8).setOnClickListener(digitClickListener);
        findViewById(R.id.number_9).setOnClickListener(digitClickListener);
        findViewById(R.id.dot).setOnClickListener(digitClickListener);

        HashMap<Integer, Operation> operands = new HashMap<>();
        operands.put(R.id.operator_plus, Operation.SUM);
        operands.put(R.id.operator_minus, Operation.SUB);
        operands.put(R.id.operator_div, Operation.DIV);
        operands.put(R.id.operator_multiply, Operation.MULT);
        operands.put(R.id.operator_result, Operation.EQ);

        View.OnClickListener operandClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onOperandPressed(operands.get(v.getId()), errResult);
            }
        };

        findViewById(R.id.operator_plus).setOnClickListener(operandClickListener);
        findViewById(R.id.operator_minus).setOnClickListener(operandClickListener);
        findViewById(R.id.operator_div).setOnClickListener(operandClickListener);
        findViewById(R.id.operator_multiply).setOnClickListener(operandClickListener);
        findViewById(R.id.operator_result).setOnClickListener(operandClickListener);

        findViewById(R.id.clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCleanPresser();
            }
        });

    }

    @Override
    public void showResult(String value) {
        txtResult.setText(value);
    }
}
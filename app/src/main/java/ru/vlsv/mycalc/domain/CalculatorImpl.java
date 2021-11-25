package ru.vlsv.mycalc.domain;

public class CalculatorImpl implements Calculator {

    @Override
    public double performOperation(double argOne, double argTwo, Operation operation) {

        switch (operation) {
            case SUM:
                return argOne + argTwo;

            case SUB:
                return argOne - argTwo;

            case MULT:
                return argOne * argTwo;

            case DIV:
                if (argTwo != 0.0)
                    return argOne / argTwo;

            case EQ:
                return argOne;
        }
        return 0;
    }
}

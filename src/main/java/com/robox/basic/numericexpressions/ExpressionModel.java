package com.robox.basic.numericexpressions;

import lombok.Getter;

public class ExpressionModel {
    public static final char NOT_AN_OPERATOR = '0';
    public static final int NOT_A_NUMBER = 666666662;

    private final int SIZE = 20;

    @Getter
    private boolean[] isEnabled = new boolean[SIZE];
    @Getter
    private boolean[] isWhitespace = new boolean[SIZE];
    @Getter
    private boolean[] isOperator = new boolean[SIZE];
    @Getter
    private int[] numberValue = new int[SIZE];
    @Getter
    private char[] operatorValue = new char[SIZE];
    @Getter
    private int usedCapacity = 0;

    private StringHelper stringHelper = new StringHelper();
    private String expression;
    private final CharHelper charHelper = new CharHelper();
    private int index;
    private final String2NumberConverter converter = new String2NumberConverter();
    private Variables variables;

    public ExpressionModel(String expression) {
        this.expression = expression;
        setupIsWhitespaceObject();
        extractNumberValuesAndOperatorsFromExpression(this.expression);
    }

    private void setupIsWhitespaceObject() {
        for (int i = 0; i < SIZE; i++) {
            isWhitespace[i] = true;
        }
    }

    public void setIsOperator(int position) {
        if (isPositionValid(position)) {
            isOperator[position] = true;
            isWhitespace[position] = false;
        }
    }

    public void unsetIsOperator(int position) {
        if (isPositionValid(position)) {
            isOperator[position] = false;
        }
    }

    public void setIsWhitespace(int position) {
        if (isPositionValid(position)) {
            isWhitespace[position] = true;
            isOperator[position] = false;
            numberValue[position] = NOT_A_NUMBER;
            operatorValue[position] = NOT_AN_OPERATOR;
        }
    }

    public void setNumberValue(int position, int value) {
        if (isPositionValid(position)) {
            numberValue[position] = value;
            isWhitespace[position] = false;
        }
    }

    private boolean isPositionValid(int position) {
        if (position <= usedCapacity && position >= 0) {
            return true;
        } else {
            return false;
        }

    }

    private void extractNumberValuesAndOperatorsFromExpression(String expression) {
        index = 0;
        while (index < expression.length()) {
            if (charHelper.isNumber(expression.charAt(index))) {
                addNumber();
            } else if (charHelper.isOperator(expression.charAt(index))) {
                addOperator();
            } else if (charHelper.isLiteral(expression.charAt(index))) {
                addLiteral();
            } else if (charHelper.isWhiteSpace(expression.charAt(index))) {
                index++;
            } else if (charHelper.isEndOfExpression(expression.charAt(index))) {
                break;
            } else {
                System.out.println("ERROR. Invalid character in input arithmetical expression!");
                throw new IllegalArgumentException("ERROR. Invalid character in input arithmetical expression!");
            }
            if (usedCapacity == SIZE) {
                System.out.println("ERROR. No more memory for solving math expression. Maybe change SIZE value hardcoded in code.");
                new OutOfMemoryError("ERROR. No more memory for solving math expression. Maybe change SIZE value hardcoded in code.");
                throw new OutOfMemoryError();
            }
        }
    }

    private void addLiteral() {
        String firstLiteral = stringHelper.getFirstLiteral(expression, 0);
        numberValue[usedCapacity] = variables.getVariableValue(firstLiteral);
        isOperator[usedCapacity] = false;
        operatorValue[usedCapacity] = NOT_AN_OPERATOR;
        isEnabled[usedCapacity] = true;
        usedCapacity++;
        index += firstLiteral.length();
    }

    private void addOperator() {
        operatorValue[usedCapacity] = (expression.charAt(index));
        isEnabled[usedCapacity] = true;
        isOperator[usedCapacity] = true;
        numberValue[usedCapacity] = NOT_A_NUMBER;
        isWhitespace[usedCapacity] = false;
        usedCapacity++;
        index++;
    }

    private void addNumber() {
        String firstNumber = stringHelper.getFirstNumber(expression, index);
        numberValue[usedCapacity] = converter.convert(firstNumber);
        isEnabled[usedCapacity] = true;
        isOperator[usedCapacity] = false;
        operatorValue[usedCapacity] = NOT_AN_OPERATOR;
        isWhitespace[usedCapacity] = false;
        usedCapacity++;
        index += firstNumber.length();
    }

    @Override
    public String toString() {
        String isEnabledAll = "";
        String isWhitespaceAll = "";
        String numberValueAll = "";
        String operatorValueAll = "";
        for (int i = 0; i < SIZE; i++) {
            isEnabledAll += isEnabled[i] ? "1 " : "0 " + " ";
            isWhitespaceAll += isWhitespace[i] ? "1 " : "0 " + " ";
            numberValueAll += (numberValue[i] == NOT_A_NUMBER) ? "NaN " : numberValue[i] + " ";
            operatorValueAll += (operatorValue[i] == NOT_AN_OPERATOR) ? "NaN " : operatorValue[i] + " ";
        }

        return "isEnabled: " + isEnabledAll
                + "\nisSpace: " + isWhitespaceAll
                + "\nnumbers: " + numberValueAll
                + "\noperators: " + operatorValueAll;
    }


    public int getNumberLeftTo(int index) throws InvalidExpression {
        for (int i = index - 1; i >= 0; i--) {
            if (!isWhitespace[i]) {
                return numberValue[i];
            }
        }
        throw new InvalidExpression("Cannot find argument.");
    }

    /**
     * Search for a number at index positions right to base index and return first index where number found.
     * Base index is not searched for a number.
     *
     * @param index base index
     * @return result or InvalidExpression if not found
     */
    public int getNumberRightTo(int index) throws InvalidExpression {
        for (int i = index + 1; i <= usedCapacity; i++) {
            if (!isWhitespace[i]) {
                return numberValue[i];
            }
        }
        throw new InvalidExpression("Cannot find argument.");
    }

    /**
     * Search for a number at index positions left to base index and return first index where number found.
     * Base index is not searched for a number.
     *
     * @param index base index
     * @return result or InvalidExpression if not found
     */
    public int getIndexOfNumberLeftTo(int index) throws InvalidExpression {
        for (int i = index - 1; i >= 0; i--) {
            if (!isWhitespace[i]) {
                return i;
            }
        }
        throw new InvalidExpression("Cannot find argument.");

    }

    public int getIndexOfNumberRightTo(int index) throws InvalidExpression {
        for (int i = index + 1; i <= usedCapacity; i++) {
            if (!isWhitespace[i]) {
                return i;
            }
        }
        throw new InvalidExpression("Cannot find argument.");

    }

    public void unsetIsWhitespace(int position) {
        if (isPositionValid(position)) {
            isWhitespace[position] = false;
        }
    }
}

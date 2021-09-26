package com.robox.basic.numericexpressions;

import lombok.NonNull;

public class NumericExpressionProcessor {
    private ExpressionModel model;
    int processBracketStartSubexpressionIndex = 0;
    int openingBracketsNumber = 0;
    int closingBracketsNumber = 0;

    public int processSimpleExpression(@NonNull String expression) throws InvalidExpression, ExpressionIsEmptyException {
        // arithmetic expression model
        model = new ExpressionModel(expression);
        debugPrint();
        checkForEmptiness(model);
        processByPriority(0, model.getUsedCapacity());
        postProcess();
        return getResultOfLastProcessing();
    }

    private void checkForEmptiness(ExpressionModel model) throws InvalidExpression {
        if (model.getUsedCapacity() == 0) {
            throw new InvalidExpression("Expression is empty.");
        }
    }

    private void processByPriority(int fromIndex, int toIndex) throws InvalidExpression {
        for (int priority = 1; priority <= 3; priority++) {
            for (int index = fromIndex; index < toIndex; index++) {
                if (model.getIsOperator()[index] &&
                        OperatorPriority.of(model.getOperatorValue()[index]).getPriority() == priority) {
                    switch (priority) {
                        case 3:
                            processPriority3operations(index);
                            break;
                        case 2:
                            processPriority2operations(index);
                            break;
                        case 1:
                            processBracket(index);
                            break;
                    }

                }
                debugPrint();
            }
        }
    }

    private void debugPrint() {
        System.out.println(model.toString());
    }

    private void processPriority3operations(int index) throws InvalidExpression {
        int result = 0;
        switch (model.getOperatorValue()[index]) {
            case '+':
                result = addArgs(model.getNumberLeftTo(index), model.getNumberRightTo(index));
                break;
            case '-':
                result = substractArgs(model.getNumberLeftTo(index), model.getNumberRightTo(index));
                break;
            default:
        }
        markTwoArgumentOperationAsProcessed(index);
        placeResultIntoModel(index, result);
    }

    private boolean argumentsAreValidNumbers(int index) throws InvalidExpression {
        if (isValidNumber(model.getNumberLeftTo(index)) &&
                isValidNumber(model.getNumberRightTo(index))) {
            return true;
        } else {
            throw new InvalidExpression("Cannot find argument.");
        }
    }

    private void processPriority2operations(int index) throws InvalidExpression {
        int result = 0;

        switch (model.getOperatorValue()[index]) {
            case '*':
                result = multiplyArgs(model.getNumberLeftTo(index), model.getNumberRightTo(index));
                break;
            case '/':
                result = divideArgs(model.getNumberLeftTo(index), model.getNumberRightTo(index));
                break;
            default:
        }
        markTwoArgumentOperationAsProcessed(index);
        placeResultIntoModel(index, result);
    }

    private boolean isValidNumber(int number) {
        return ExpressionModel.NOT_A_NUMBER != number;
    }

    private void processBracket(int index) throws InvalidExpression {
        switch (model.getOperatorValue()[index]) {
            case '(':
                processBracketStartSubexpressionIndex = index;
                markOperatorAsProcessed(index);
                openingBracketsNumber++;
                break;
            case ')':
                markOperatorAsProcessed(index);
                processByPriority(processBracketStartSubexpressionIndex + 1, index - 1);
                closingBracketsNumber++;
                break;
            default:
        }
    }

    private void markOperatorAsProcessed(int position) {
        model.unsetIsOperator(position);
        model.setIsWhitespace(position);
//        placeResultIntoModel(position, -1);
    }


    private void markTwoArgumentOperationAsProcessed(int position) throws InvalidExpression {
        markOperatorAsProcessed(position);
        markArgumentsAsProcessed(position);
    }

    private void markArgumentsAsProcessed(int position) throws InvalidExpression {
        if (isValidNumber(model.getIndexOfNumberLeftTo(position))) {
            markNumberAsProcessed(model.getIndexOfNumberLeftTo(position));
        }
        if (isValidNumber(model.getIndexOfNumberRightTo(position))) {
            markNumberAsProcessed(model.getIndexOfNumberRightTo(position));
        }
    }

    private void placeResultIntoModel(int position, int newValue) {
        model.unsetIsWhitespace(position);
        model.setNumberValue(position, newValue);
    }


    private void markNumberAsProcessed(int indexOfNumberLeftTo) {
        model.setIsWhitespace(indexOfNumberLeftTo);
    }

    private int substractArgs(int i, int i1) {
        return i - i1;
    }

    private int addArgs(int i, int i1) {
        return i + i1;
    }

    private int multiplyArgs(int number1, int number2) {
        return number1 * number2;
    }

    private int divideArgs(int number1, int number2) {
        return number1 / number2;
    }

    private void postProcess() {
        bracketUnpairedHandle();
    }

    private void bracketUnpairedHandle() {
        if (openingBracketsNumber != closingBracketsNumber) {
            for (int i = 0; i < model.getUsedCapacity(); i++) {
                model.unsetIsWhitespace(i);
            }
        }
    }

    private int getVariableValue(String varName) {
        return 0;
    }

    /**
     * Get result computed by last call of processSimpleExpression() method
     *
     * @return number or NOT_A_NUMBER when error e.g. expression has invalid syntax
     */
    public int getResultOfLastProcessing() {
        for (int i = 0; i < model.getUsedCapacity(); i++) {
            if (!model.getIsWhitespace()[i] && model.getIsEnabled()[i] && !model.getIsOperator()[i]) {
                return model.getNumberValue()[i];
            }
        }
        return ExpressionModel.NOT_A_NUMBER;
    }

    public boolean hasLastProcessingAnError() {
        if (getResultOfLastProcessing() == ExpressionModel.NOT_A_NUMBER) {
            return true;
        }
        return false;
    }
}

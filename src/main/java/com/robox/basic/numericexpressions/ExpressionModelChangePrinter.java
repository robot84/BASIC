package com.robox.basic.numericexpressions;

public class ExpressionModelChangePrinter {
    private ExpressionModel actualModelState;
    private ExpressionModel oldModelState;

    public ExpressionModelChangePrinter(ExpressionModel model) {
        actualModelState = getDeepCopyof(model);
    }

    // nie dziala jeszcze
    @Deprecated
    private ExpressionModel getDeepCopyof(ExpressionModel model) {
        ExpressionModel deepCopy = new ExpressionModel("");
        return null;

    }

}

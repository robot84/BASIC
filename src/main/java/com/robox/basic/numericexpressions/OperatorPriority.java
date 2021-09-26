package com.robox.basic.numericexpressions;

public enum OperatorPriority {
    PRIORITY1(1),
    PRIORITY2(2),
    PRIORITY3(3),
    UNKNOWN(0);

    private final int priority;

    static OperatorPriority of(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return PRIORITY3;
            case '*':
            case '/':
                return PRIORITY2;
            case '(':
            case ')':
                return PRIORITY1;
            default:
                return UNKNOWN;
        }
    }

    OperatorPriority(int priority) {
        this.priority = priority;
    }

    int getPriority() {
        return priority;
    }
}

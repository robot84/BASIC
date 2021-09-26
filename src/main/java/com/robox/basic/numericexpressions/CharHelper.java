package com.robox.basic.numericexpressions;

public class CharHelper {
    public CharHelper() {
    }

    public boolean isNumber(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    public boolean isLiteral(char c) {
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        return false;
    }

    public boolean isOperator(char c) {
        switch (c) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '%':
            case '(':
            case ')':
            case '^':
                return true;
            default:
                return false;
        }
    }

    public boolean isWhiteSpace(char c) {
        switch (c) {
            case ' ':
            case '\t':
                return true;
            default:
                return false;
        }
    }

    public boolean isEndOfExpression(char c) {
        switch (c) {
            case '\n':
            case '\r':
            case 0x00:
                return true;
            default:
                return false;
        }
    }
}
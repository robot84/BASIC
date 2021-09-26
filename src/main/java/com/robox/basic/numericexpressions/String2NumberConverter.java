package com.robox.basic.numericexpressions;

public class String2NumberConverter {

    public int convert(String numberAsString) {
        int result = 0;
        int weight = 1;
        for (int i = numberAsString.length() - 1; i >= 0; i--) {
            result += weight * (numberAsString.charAt(i) - '0');
            weight *= 10;
        }
        return result;
    }
}

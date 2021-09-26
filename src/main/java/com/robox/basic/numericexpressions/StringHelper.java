package com.robox.basic.numericexpressions;

public class StringHelper {
    CharHelper charHelper = new CharHelper();

    public String getFirstNumber(String expressionUpTo255Chars, int startingCharacter) {
        int index = startingCharacter;
        int stopIndex = expressionUpTo255Chars.length();

        while (index < stopIndex && charHelper.isNumber(expressionUpTo255Chars.charAt(index))) {
            index++;
        }
        String number = expressionUpTo255Chars.substring(startingCharacter, index);
        return number;
    }

    public String getFirstLiteral(String expressionUpTo255Chars, int startingCharacter) {
        int index = startingCharacter;
        int stopIndex = expressionUpTo255Chars.length();

        while (charHelper.isLiteral(expressionUpTo255Chars.charAt(index))) {
            index++;
        }
        String result = expressionUpTo255Chars.substring(startingCharacter, index);
        return result;
    }

}

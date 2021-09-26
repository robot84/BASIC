package com.robox.basic.numericexpressions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharHelperTest {
    CharHelper charHelper;

    @Before
    public void setUp() {
        charHelper = new CharHelper();
    }

    @Test
    public void isNumberShouldReturnTrueForNumber() {
        assertTrue(charHelper.isNumber('0'));
        assertTrue(charHelper.isNumber('1'));
        assertTrue(charHelper.isNumber('2'));
        assertTrue(charHelper.isNumber('3'));
        assertTrue(charHelper.isNumber('4'));
        assertTrue(charHelper.isNumber('5'));
        assertTrue(charHelper.isNumber('6'));
        assertTrue(charHelper.isNumber('7'));
        assertTrue(charHelper.isNumber('8'));
        assertTrue(charHelper.isNumber('9'));
    }

    @Test
    public void isNumberShouldReturnFalseForWhitespaceChar() {
        assertFalse(charHelper.isNumber(' '));
        assertFalse(charHelper.isNumber('\n'));
    }

    @Test
    public void isNumberShouldReturnFalseForLetter() {
        assertFalse(charHelper.isNumber('a'));
        assertFalse(charHelper.isNumber('z'));
        assertFalse(charHelper.isNumber('A'));
        assertFalse(charHelper.isNumber('Z'));
    }

    @Test
    public void isLiteralShouldReturnTrue() {
        assertTrue(charHelper.isLiteral('A'));
        assertTrue(charHelper.isLiteral('Z'));
    }

    @Test
    public void isLiteralShouldReturnFalse() {
        assertFalse(charHelper.isLiteral('a'));
        assertFalse(charHelper.isLiteral('3'));
        assertFalse(charHelper.isLiteral('+'));
        assertFalse(charHelper.isLiteral(' '));
        assertFalse(charHelper.isLiteral('\n'));
    }

    @Test
    public void isOperatorShouldReturnTrue() {
        assertTrue(charHelper.isOperator('+'));
        assertTrue(charHelper.isOperator('-'));
        assertTrue(charHelper.isOperator('/'));
        assertTrue(charHelper.isOperator('*'));
        assertTrue(charHelper.isOperator('%'));
        assertTrue(charHelper.isOperator('^'));
    }

    @Test
    public void isOperatorShouldReturnFalse() {
        assertFalse(charHelper.isOperator(' '));
        assertFalse(charHelper.isOperator('3'));
        assertFalse(charHelper.isOperator('a'));
        assertFalse(charHelper.isOperator('D'));
        assertFalse(charHelper.isOperator('\n'));
        assertFalse(charHelper.isOperator('_'));
    }
}
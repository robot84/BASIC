package com.robox.basic.numericexpressions;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringHelperTest {

    @Test
    public void getFirstNumber() {
        StringHelper stringHelper = new StringHelper();

        assertEquals("", stringHelper.getFirstNumber("ARTUR234 ROBERT333", 0));
        assertEquals("234", stringHelper.getFirstNumber("234ARTUR33 ROBERT333", 0));
        assertEquals("2", stringHelper.getFirstNumber("2+2", 0));
        assertEquals("", stringHelper.getFirstNumber("2+2", 1));
        assertEquals("2", stringHelper.getFirstNumber("2+2", 2));
        assertEquals("2", stringHelper.getFirstNumber("2", 0));
    }
}
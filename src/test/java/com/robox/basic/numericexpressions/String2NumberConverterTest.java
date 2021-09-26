package com.robox.basic.numericexpressions;

import org.junit.Test;

import static org.junit.Assert.*;

public class String2NumberConverterTest {

    @Test
    public void convert() {
        String2NumberConverter string2NumberConverter = new String2NumberConverter();
        assertEquals(234, string2NumberConverter.convert("234"));
        assertEquals(2340000, string2NumberConverter.convert("2340000"));
    }
}
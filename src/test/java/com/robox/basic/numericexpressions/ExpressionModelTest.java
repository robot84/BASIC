package com.robox.basic.numericexpressions;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionModelTest {

    @Test
    public void test01() {
        ExpressionModel expressionModel = new ExpressionModel("2+3");
        System.out.println(expressionModel.toString());
    }
}
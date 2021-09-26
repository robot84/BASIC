package com.robox.basic.numericexpressions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class NumericExpressionProcessorTest {

    @Test(expected = InvalidExpression.class)
    public void processEmptyExpression() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("");
        assertEquals(ExpressionModel.NOT_A_NUMBER, processor.getResultOfLastProcessing());
    }

    @Test
    public void processExpressionWithOneArgument() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("2");
        assertEquals(2, processor.getResultOfLastProcessing());
    }

    @Test(expected = InvalidExpression.class)
    public void processExpressionInvalidExpression() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("2+");
        fail();
    }

    @Test(expected = InvalidExpression.class)
    public void processExpressionInvalidExpressionCase02() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("+2");
        fail();

    }

    @Test(expected = InvalidExpression.class)
    public void processExpressionInvalidExpressionCase03() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("+");
        fail();

    }

    @Test
    public void processExpressionWithOneOperatorAndTwoArguments() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("2+2");
        assertEquals(4, processor.getResultOfLastProcessing());
    }

    @Test
    public void processExpressionWithNoOperatorAndTwoArguments() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("2 2");
        assertEquals(2, processor.getResultOfLastProcessing());
    }

    @Test
    public void processExpressionWithOneOperatorAndTwoArgumentsWithSpaces() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression(" 2  + 3");
        assertEquals(5, processor.getResultOfLastProcessing());
    }

    @Test
    public void processExpressionWithOneOperatorAndTwoArgumentsAndSubstractionOperator() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("11-1");
        assertEquals(10, processor.getResultOfLastProcessing());
    }

    @Test
    public void processExpressionWithTwoOperatorsAndThreeArguments() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("11-11+11-11+22");
        assertEquals(22, processor.getResultOfLastProcessing());
    }

    @Test(expected = OutOfMemoryError.class)
    public void processExpressionResultMemoryOverflow() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("11-11+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55+11-11+22+33+44+55");
        assertEquals(22, processor.getResultOfLastProcessing());
    }

    @Test
    public void processExpressionWithTwoOperatorsAndThreeArguments2() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("111+222+333");
        assertEquals(666, processor.getResultOfLastProcessing());
    }

    @Test
    public void processExpressionWithMultiply() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("100*5");
        assertEquals(500, processor.getResultOfLastProcessing());
    }

    @Test
    public void processExpressionWithDivide() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("100/5");
        assertEquals(20, processor.getResultOfLastProcessing());
    }

    @Test
    public void processExpressionWithOperatorsWithDifferentPriorities() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("4+4*4");
        assertEquals(20, processor.getResultOfLastProcessing());
    }

    @Test
    public void processExpressionWithOperatorsWithDifferentPrioritiesAndSpaces() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression(" 4+  4 *4");
        assertEquals(20, processor.getResultOfLastProcessing());
    }


    @Test
    public void processExpressionWithOperatorsWithBrackets() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("(4+4)*4");
        assertEquals(32, processor.getResultOfLastProcessing());
        System.out.println("Result: " + processor.getResultOfLastProcessing());
    }

    @Test
    public void processExpressionWithOperatorsWithRecuringBrackets() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("(((4+4)*4)+2)*(4+4)");
        assertEquals((((4 + 4) * 4) + 2) * (4 + 4), processor.getResultOfLastProcessing());
        System.out.println("Result: " + processor.getResultOfLastProcessing());
    }

    @Test
    public void processExpressionWithOperatorsWithRecuringBracketsCase2() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("(2*((4+4)*4))*(4+4)");
        assertEquals((2 * ((4 + 4) * 4)) * (4 + 4), processor.getResultOfLastProcessing());
        System.out.println("Result: " + processor.getResultOfLastProcessing());
    }

    @Test
    public void processExpressionWithOperatorsWithMoreOpeningBracketsThanClosingBrackets() throws InvalidExpression, ExpressionIsEmptyException {
        NumericExpressionProcessor processor = new NumericExpressionProcessor();
        processor.processSimpleExpression("(2*((4+4*4)*(4+4)");
        assertEquals(ExpressionModel.NOT_A_NUMBER, processor.getResultOfLastProcessing());
        System.out.println("Result: " + processor.getResultOfLastProcessing());
    }
}
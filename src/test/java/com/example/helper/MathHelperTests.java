package com.example.helper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MathHelperTests {

    @Spy
    private MathHelper test = new MathHelper();

    @Test
    public void handleVolumeShouldReturnVolumeStatement() {
        String expected = "The volume of a 6x7x8 rectangle is 336";
        assertEquals(expected, test.handleVolume(6,7,8));
    }

    @Test
    public void handleSumShouldReturn0IfValuesIsNull() {
        List<String> values = null;
        assertEquals("0", test.handleSum(values));
    }

    @Test
    public void handleSumShouldReturn0IfListIsEmpty() {
        List<String> values = new ArrayList<String>();
        assertEquals("0", test.handleSum(values));
    }

    @Test
    public void handleSumShouldReturnNEqualNIfListHas1Value() {
        List<String> values = new ArrayList<String>();
        values.add("1");
        assertEquals("1 = 1", test.handleSum(values));
    }

    @Test
    public void handleSumShouldReturnSumStringIfListHasMoreThan1Value() {
        List<String> values = new ArrayList<String>();
        values.add("1");
        values.add("2");
        values.add("3");
        assertEquals("1 + 2 + 3 = 6", test.handleSum(values));
    }

    @Test
    public void handleOperationShouldReturnAddStringWhenOperationNull() {
        String message = test.handleOperation(null, 2, 1);
        Mockito.verify(test).add(2,1);
        assertEquals("return message should show addition", "2 + 1 = 3", message);
    }

    @Test
    public void handleOperationShouldReturnAddStringWhenOperationIsAdd() {
        String message = test.handleOperation("add", 2, 1);
        Mockito.verify(test).add(2,1);
        assertEquals("return message should show addition", "2 + 1 = 3", message);
    }

    @Test
    public void handleOperationShouldReturnSubStringWhenOperationIsSubtract() {
        String message = test.handleOperation("subtract", 2, 1);
        Mockito.verify(test).subtract(2,1);
        assertEquals("return message should show subtraction", "2 - 1 = 1", message);
    }

    @Test
    public void handleOperationShouldReturnMultiplyStringWhenOperationIsMultiply() {
        String message = test.handleOperation("multiply", 2, 1);
        Mockito.verify(test).multiply(2,1);
        assertEquals("return message should show multiplication", "2 * 1 = 2", message);
    }

    @Test
    public void handleOperationShouldReturnDivideStringWhenOperationIsDivide() {
        String message = test.handleOperation("divide", 2, 1);
        Mockito.verify(test).divide(2,1);
        assertEquals("return message should show division", "2 / 1 = 2", message);
    }

    @Test
    public void addShouldReturnAddString() {
        assertEquals("2 + 1 = 3", test.add(2, 1));
    }

    @Test
    public void subtractShouldReturnSubString() {
        assertEquals("2 - 1 = 1", test.subtract(2, 1));
    }

    @Test
    public void multplyShouldReturnMultiplyString() {
        assertEquals("2 * 1 = 2", test.multiply(2, 1));
    }

    @Test
    public void divideShouldReturnDivideString() {
        assertEquals("2 / 1 = 2", test.divide(2, 1));
    }
}
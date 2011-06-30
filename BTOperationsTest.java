package balancedTernary;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author David Matuszek
 */
public class BTOperationsTest {
    private String minus_six;
    private String minus_five;
    private String minus_four;
    private String minus_three;
    private String minus_two;
    private String minus_one;
    private String zero;
    private String one;
    private String two;
    private String three;
    private String four;
    private String five;
    private String six;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        minus_six = "N10";
        minus_five = "N11";
        minus_four = "NN";
        minus_three ="N0";
        minus_two = "N1";
        minus_one = "N";
        zero = "0";
        one = "1";
        two = "1N";
        three = "10";
        four = "11";
        five = "1NN";
        six = "1N0";
    }

    //---------- Methods to save a bunch of typing ----------

    private String btAdd(String bt1, String bt2) {
        return BTOperations.add(bt1, bt2);
    }

    private String btSubtract(String bt1, String bt2) {
        return BTOperations.subtract(bt1, bt2);
    }

    private String btMultiply(String bt1, String bt2) {
        return BTOperations.multiply(bt1, bt2);
    }

    private String btDivide(String bt1, String bt2) {
        return BTOperations.divide(bt1, bt2);
    }

    private String btNegate(String bt1) {
        return BTOperations.negate(bt1);
    }

    private String intToBt(int n) {
        return BTOperations.intToBt(n);
    }

    private int btToInt(String bt1) {
        return BTOperations.btToInt(bt1);
    }

    //---------- Actual test methods ----------

    /**
     * Test method for {@link balancedTernary.BTOperations#intToBt(int)}.
     */
    @Test
    public void testIntToBt() {
        assertEquals(minus_six, intToBt(-6));
        assertEquals(minus_one, intToBt(-1));
        assertEquals(zero, intToBt(0));
        assertEquals(one, intToBt(1));
        assertEquals(six, intToBt(6));
    }

    /**
     * Test method for {@link balancedTernary.BTOperations#btToInt(java.lang.String)}.
     */
    @Test
    public void testBtToInt() {
        assertEquals(-6, btToInt(minus_six));
        assertEquals(-1, btToInt(minus_one));
        assertEquals(0, btToInt(zero));
        assertEquals(1, btToInt(one));
        assertEquals(6, btToInt(six));
    }

    /**
     * Test method for {@link balancedTernary.BTOperations#add(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testAdd() {
        assertEquals(two, btAdd(zero, two));                    // zero and positive
        assertEquals(zero, btAdd(zero, zero));                  // zero and zero
        assertEquals(zero, btAdd(two, minus_two));              // zero and negative
        assertEquals(five, btAdd(two, three));                  // positive and positive
        assertEquals(three, btAdd(three, zero));                // positive and zero
        assertEquals(two, btAdd(five, minus_three));            // positive and negative
        assertEquals(minus_two, btAdd(minus_six, four));        // negative and positive
        assertEquals(minus_three, btAdd(minus_three, zero));    // negative and zero
        assertEquals(minus_five, btAdd(minus_two, minus_three));// negative and negative
    }

    /**
     * Test method for {@link balancedTernary.BTOperations#subtract(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testSubtract() {
        assertEquals(minus_two, btSubtract(zero, two));          // zero and positive
        assertEquals(zero, btSubtract(zero, zero));              // zero and zero
        assertEquals(four, btSubtract(two, minus_two));          // zero and negative
        assertEquals(minus_one, btSubtract(two, three));         // positive and positive
        assertEquals(three, btSubtract(three, zero));            // positive and zero
        assertEquals(six, btSubtract(four, minus_two));          // positive and negative
        assertEquals(minus_six, btSubtract(minus_two, four));    // negative and positive
        assertEquals(minus_three, btSubtract(minus_three, zero));// negative and zero
        assertEquals(one, btSubtract(minus_two, minus_three));   // negative and negative
    }

    /**
     * Test method for {@link balancedTernary.BTOperations#multiply(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testMultiply() {
        assertEquals(zero, btMultiply(zero, two));            // zero and positive
        assertEquals(zero, btMultiply(zero, zero));           // zero and zero
        assertEquals(zero, btMultiply(zero, minus_two));      // zero and negative
        assertEquals(six, btMultiply(two, three));            // positive and positive
        assertEquals(zero, btMultiply(three, zero));          // positive and zero
        assertEquals(minus_six, btMultiply(two, minus_three));// positive and negative
        assertEquals(minus_four, btMultiply(minus_two, two)); // negative and positive
        assertEquals(zero, btMultiply(minus_three, zero));    // negative and zero
        assertEquals(six, btMultiply(minus_two, minus_three));// negative and negative
    }

    /**
     * Test method for {@link balancedTernary.BTOperations#divide(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testDivide() {
        assertEquals(zero, btDivide(zero, two));            // zero and positive
        assertEquals(zero, btDivide(zero, minus_two));      // zero and negative
        assertEquals(two, btDivide(six, three));            // positive and positive
        assertEquals(minus_two, btDivide(six, minus_three));// positive and negative
        assertEquals(minus_one, btDivide(minus_two, two));  // negative and positive
        assertEquals(two, btDivide(minus_six, minus_three));// negative and negative
        assertEquals(one, btDivide(five, four));            // truncation
        assertEquals(one, btDivide(minus_five, minus_four));// truncation
    }    /**
     * Test method for {@link balancedTernary.BTOperations#divide(java.lang.String, java.lang.String)}.
     */
    @Test(expected=ArithmeticException.class)
    public void testDivideByZero() {
        btDivide(one, zero);
    }

    /**
     * Test method for {@link balancedTernary.BTOperations#negate(java.lang.String)}.
     */
    @Test
    public void testNegate() {
        assertEquals(zero, btNegate(zero));
        assertEquals(minus_one, btNegate(one));
        assertEquals(one, btNegate(minus_one));
    }
}

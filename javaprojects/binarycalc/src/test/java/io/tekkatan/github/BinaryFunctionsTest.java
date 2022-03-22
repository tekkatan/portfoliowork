package io.tekkatan.github;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

public class BinaryFunctionsTest {

    BinaryFunctions bFunctions;

    @BeforeEach
    void setup(){
        bFunctions=new BinaryFunctions();
    }
    // Tests for SQRT method
    @Test
    @DisplayName("Simple example of getting sqrt")
    public void testSqrt()
    {
        assertEquals(5,bFunctions.sqrt(25),
            "Regular sqrt should work");
    }
    @RepeatedTest(2)
    @DisplayName("Ensure that it can handle zero correctly")
    void testSQRTWithZeroValue(){
        assertEquals(0,bFunctions.sqrt(0),
        "The sqrt of 0 should be 0");
    }

    // division(int x, int y)
    @Test
    @DisplayName("Simple test of division")
    public void testDivision(){
        assertEquals(9,bFunctions.division(18, 2),
            "Regular division of 18/2");
    }

    @RepeatedTest(2)
    @DisplayName("Ensure that it can handle zero correctly")
    public void testDivisionWithZeroValue(){
        assertEquals(0,bFunctions.division(0, 0),
            "Division of 0/0");
        assertEquals(3,bFunctions.division(3, 0),
            "Division of 3/0");
        assertEquals(0,bFunctions.division(0, 3),
            "Division of 0/3");
    }

    // multiplication(int x, int y)
    @Test
    @DisplayName("Simple test of multiplication")
    public void testMultiplication(){
        assertEquals(8,bFunctions.multiplication(4, 2),
            "Regular multiplication of 4/2");
    }
    @RepeatedTest(2)
    @DisplayName("Ensure that it can handle zero correctly")
    public void testMultiplicationWithZeroValue(){
        assertEquals(0,bFunctions.multiplication(0, 0),
            "Multiplication of 0*0");
        assertEquals(0,bFunctions.multiplication(3, 0),
            "Multiplication of 3*0");
        assertEquals(0,bFunctions.multiplication(0, 3),
            "Multiplication of 0*3");
    }

    // public int power(int x, int y){
    @Test
    @DisplayName("Simple test of power")
    public void testPower(){
        assertEquals(4,bFunctions.power(2, 2),
            "Regular 2power2");
    }
    @RepeatedTest(2)
    @DisplayName("Ensure that it can handle zero correctly")
    public void testPowerWithZeroValue(){
        assertEquals(1,bFunctions.power(0, 0),
            "Power of 0 to 0");
        assertEquals(1,bFunctions.power(3, 0),
            "Power of 3 to 0");
        assertEquals(0,bFunctions.power(0, 3),
            "Power of 0 to 3");
    }

    // modulo
    @Test
    @DisplayName("Simple test of modulo")
    public void testModulo(){
        assertEquals(3,bFunctions.modulo(3, 7),
            "Regular 3%7");
    }
    @RepeatedTest(2)
    @DisplayName("Ensure that it can handle zero correctly")
    public void testModuloWithZeroValue(){
        assertEquals(0,bFunctions.modulo(0, 0),
            "Modulo of 0 % 0");
        assertEquals(0,bFunctions.modulo(3, 0),
            "Modulo of 3 % 0");
        assertEquals(0,bFunctions.modulo(0, 3),
            "Modulo of 0 % 3");
    }

    // decrement
    @Test
    @DisplayName("Simple test of decrement")
    public void testDecrement(){
        assertEquals(2,bFunctions.decrement(3, 1),
            "Regular 3-1");
    }
    @RepeatedTest(2)
    @DisplayName("Ensure that it can handle zero correctly")
    public void testDecrementWithZeroValue(){
        assertEquals(0,bFunctions.decrement(0, 0),
            "decrement of 0 - 0");
        assertEquals(3,bFunctions.decrement(3, 0),
            "decrement of 3 - 0");
        assertEquals(-3,bFunctions.decrement(0, 3),
            "decrement of 0 - 3");
    }

    // increment
    @Test
    @DisplayName("Simple test of increment")
    public void testIncrement(){
        assertEquals(4,bFunctions.increment(3, 1),
            "Regular 3+1");
    }
    @RepeatedTest(2)
    @DisplayName("Ensure that it can handle zero correctly")
    public void testIncrementWithZeroValue(){
        assertEquals(0,bFunctions.increment(0, 0),
            "increment of 0 + 0");
        assertEquals(3,bFunctions.increment(3, 0),
            "increment of 3 + 0");
        assertEquals(3,bFunctions.increment(0, 3),
            "increment of 0 + 3");
    }
    
}

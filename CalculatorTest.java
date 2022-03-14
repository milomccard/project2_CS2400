import junit.framework.TestCase;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest extends TestCase {
    /**a public unt test class for calculator
     * makes sure that evaluatepostfix method functions properly
     * calls asserts equals and passes the expected and the actual to verify that they match
     * **/
   public void testEvaluateMethod(){
        // Arrange
        String testString = "AB*CA-/DE*+";
        double result;
        Calculator testCalc = new Calculator();

        // Act
        result = testCalc.evaluatePostfix(testString);
        double expectedResult = 33;

        // Assert
        assertEquals(result, expectedResult);
    }

    /**a public test method for the convert method in calculator class
     * used assertEquals and passes in the expected string and the resulting string
     * to make sure that they are equivalent to each other
     */
    public void testConfigMethod(){
       // Arrange
            String anotherTestString = "A*B/(C-A)+D*E";
            String result;
            Calculator anotherTestCalc = new Calculator();

       // Act
            result = anotherTestCalc.convertToPostfix(anotherTestString);
            String expectedResult = "AB*CA-/DE*+";

       // Assert
            assertEquals(result, expectedResult);
    }
}
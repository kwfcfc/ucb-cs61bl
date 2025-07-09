import org.junit.Rule;
import org.junit.Test;
import static com.google.common.truth.Truth.assertWithMessage;

public abstract class TriangleTest {

    /** For autograding purposes; do not change this line. */
    abstract Triangle getNewTriangle();

    /* ***** TESTS ***** */

    // FIXME: Add additional tests for Triangle.java here that pass on a
    // correct Triangle implementation and fail on buggy Triangle implementations.

    @Test
    public void testZeroSides() {
        Triangle t = getNewTriangle();
        boolean resultZero1 = t.sidesFormTriangle(0, 1, 2);
        boolean resultZero2 = t.sidesFormTriangle(3, 0, 2);
        boolean resultZero3 = t.sidesFormTriangle(2, 4, 0);
        assertWithMessage("triangle side must be greater than 0").that(resultZero1 && resultZero2 && resultZero3)
                .isFalse();
    }

    @Test
    public void testNegativeSides() {
        Triangle t = getNewTriangle();
        boolean resultNegative1 = t.sidesFormTriangle(6, 4, -2);
        boolean resultNegative2 = t.sidesFormTriangle(-3, 4, -2);
        boolean resultNegative3 = t.sidesFormTriangle(-3, -4, -5);
        assertWithMessage("triangle side must be greater thane 0")
                .that(resultNegative1 && resultNegative2 && resultNegative3).isFalse();
    }

    @Test
    public void testEqualSides() {
        Triangle t = getNewTriangle();
        boolean result = t.sidesFormTriangle(2, 1, 1);
        assertWithMessage("any the sum of any two sides must be greater than the third side.").that(result).isFalse();
    }

    @Test
    public void testCanFormTriange() {
        Triangle t = getNewTriangle();
        boolean result = t.sidesFormTriangle(3, 4, 5);
        assertWithMessage("expect a valid triangle").that(result).isTrue();
    }

    @Test
    public void testPointsOnOneLine() {
        Triangle t = getNewTriangle();
        boolean result = t.pointsFormTriangle(1, 2, 2, 4, -1, -2);
        assertWithMessage("Expect not forming a triangle").that(result).isFalse();
    }

    @Test 
    public void testPointsNotOnLine() {
        Triangle t = getNewTriangle();
        boolean result = t.pointsFormTriangle(0, 0, 3, 0, 0, 4);
        assertWithMessage("Expect a valid triangle").that(result).isTrue();
    }

    @Test
    public void testScaleneTriangle() {
        Triangle t = getNewTriangle();
        String result = t.triangleType(3, 4, 5);
        assertWithMessage("Expect a scalene triangle type.").that(result).matches("Scalene");
    }

    @Test
    public void testIsoscelesTriangle() {
        Triangle t = getNewTriangle();
        String result = t.triangleType(4, 4, 5);
        assertWithMessage("Expect an isosceles triangle type.").that(result).matches("Isosceles");
    }    

    @Test
    public void testEquilateralTriangle() {
        Triangle t = getNewTriangle();
        String result = t.triangleType(3, 3, 3);
        assertWithMessage("Expect an equilateral triangle type.").that(result).matches("Equilateral");
    }    

    @Test
    public void testSquaredHypotenuseTriangle() {
        Triangle t = getNewTriangle();
        int result = t.squaredHypotenuse(12, 5);
        assertWithMessage("Hypotenuse not correct.").that(result).isEqualTo(13);
    }
}

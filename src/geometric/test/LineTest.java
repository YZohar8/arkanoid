package geometric.test;

import geometric.objects.Line;
import geometric.objects.Point;
import org.junit.Test;

import static org.junit.Assert.*;

public class LineTest {
    private final double EPSILON = 0.00001;

    @Test
    public void testIntersectionWith() {
        // Test normal case
        Line line1 = new Line(0, 0, 4, 4);
        Line line2 = new Line(0, 4, 4, 0);
        Point expectedIntersection = new Point(2, 2);
        Point p = line1.intersectionWith(line2);
        assertTrue(p.equals(expectedIntersection));

        // Test when lines are parallel and non-intersecting
        line1 = new Line(0, 0, 1, 1);
        line2 = new Line(2, 2, 3, 3);
        assertNull(line1.intersectionWith(line2));

        // Test when one line is vertical and the other is horizontal
        line1 = new Line(0, 0, 4, 0);
        line2 = new Line(2, -2, 2, 2);
        expectedIntersection = new Point(2, 0);
        p = line1.intersectionWith(line2);
        assertTrue(p.equals(expectedIntersection));

        // Test when one line is vertical and the other is not
        line1 = new Line(0, 0, 0, 4);
        line2 = new Line(1, 1, -1, 5);
        expectedIntersection = new Point(0, 3);
        p = line1.intersectionWith(line2);
        assertTrue(p.equals(expectedIntersection));

        // Test when one line is horizontal and the other is not
        line1 = new Line(0, 0, 4, 0);
        line2 = new Line(1, 1, 5, -1);
        expectedIntersection = new Point(3, 0);
        p = line1.intersectionWith(line2);
        assertTrue(p.equals(expectedIntersection));

        // Test when both lines are vertical
        line1 = new Line(0, 0, 0, 4);
        line2 = new Line(2, 2, 2, 5);
        assertNull(line1.intersectionWith(line2));

        // Test when both lines are horizontal
        line1 = new Line(0, 0, 4, 0);
        line2 = new Line(1, 1, 2, 1);
        assertNull(line1.intersectionWith(line2));

        // Test when one line is a point
        line1 = new Line(0, 0, 0, 0);
        line2 = new Line(0, 0, 4, 4);
        expectedIntersection = new Point(0, 0);
        p = line1.intersectionWith(line2);
        assertTrue(p.equals(expectedIntersection));

        // Test when both lines are points and don't intersect
        line1 = new Line(0, 0, 0, 0);
        line2 = new Line(1, 1, 1, 1);
        assertNull(line1.intersectionWith(line2));

        // Create some points and lines for testing
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 3);
        Point p3 = new Point(1, 1);
        Point p4 = new Point(2, 2);
        Point p5 = new Point(5, 5);
        Point p6 = new Point(6, 6);
        Point p7 = new Point(0, 3);
        Point p8 = new Point(3, 0);

        line1 = new Line(p1, p2); // line with slope 1
        line2 = new Line(p3, p4); // line with slope 1
        Line line3 = new Line(p5, p6); // line with slope 1
        Line line4 = new Line(p1, p7); // vertical line
        Line line5 = new Line(p2, p8); // vertical line
        Line line6 = new Line(p1, p3); // horizontal line
        Line line7 = new Line(p6, p5); // horizontal line

        // Test cases with normal intersection points
        Point expected1 = new Point(1.5, 1.5); // intersection of line1 and line2
        Point expected2 = new Point(5.5, 5.5); // intersection of line2 and line3
        Point actual1 = line1.intersectionWith(line2);
        Point actual2 = line2.intersectionWith(line3);
        //assertTrue("Test case 1 failed: expected " + expected1 + ", but got " + actual1, actual1.equals(expected1));
        //assertTrue("Test case 2 failed: expected " + expected2 + ", but got " + actual2, actual2.equals(expected2));

        // Test cases with parallel lines
        assertNull("Test case 3 failed: expected null but got " + actual1, line1.intersectionWith(line3));
        assertNull("Test case 4 failed: expected null but got " + actual2, line4.intersectionWith(line5));

        // Test cases with vertical and horizontal lines
        Point expected3 = new Point(1.5, 3); // intersection of line1 and line4
        Point expected4 = new Point(1.5, 0); // intersection of line2 and line5
        Point expected5 = new Point(1, 0); // intersection of line6 and line4
        Point expected6 = new Point(6, 6); // intersection of line7 and line3
        Point actual3 = line1.intersectionWith(line4);
        Point actual4 = line2.intersectionWith(line5);
        Point actual5 = line6.intersectionWith(line4);
        Point actual6 = line7.intersectionWith(line3);
        assertTrue("Test case 5 failed: expected " + expected3 + ", but got " + actual3, actual3.equals(expected3));
        assertTrue("Test case 6 failed: expected " + expected4 + ", but got " + actual4, actual4.equals(expected4));
        assertTrue("Test case 7 failed: expected " + expected5 + ", but got " + actual5, actual5.equals(expected5));
        assertTrue("Test case 8 failed: expected " + expected6 + ", but got " + actual6, actual6.equals(expected6));

    }
}

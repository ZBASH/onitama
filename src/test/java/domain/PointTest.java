package domain;

import domain.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    @Test
    void itCreatesAZeroPoint() {
        Point actual = Point.zero();
        assertEquals(0, actual.getX());
        assertEquals(0, actual.getY());
    }

    @Test
    void itAddsPoints() {
        Point left   = new Point(1, 4);
        Point right  = new Point(2, 3);
        Point actual = left.add(right);
        assertEquals(new Point(3, 7), actual);
    }

    @Test
    void itMirrorsAPointOverTheXAxis() {
        Point point  = new Point(1, 3);
        Point actual = point.mirrorX();
        assertEquals(new Point(1, -3), actual);
    }

    @Test
    void itComparesPointsForEquality() {
        Point actual = new Point(2, 4);
        assertEquals(new Point(2, 4), actual);
        assertNotEquals(Point.zero(), actual);
    }
}
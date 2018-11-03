import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    @Test
    void zero() {
        Point point = Point.zero();
        assertEquals(0, point.getX());
        assertEquals(0, point.getY());
    }

    @Test
    void equals() {
        Point point = new Point(2, 4);
        assertEquals(new Point(2, 4), point);
        assertNotEquals(Point.zero(), point);
    }
}
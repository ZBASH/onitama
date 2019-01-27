package domain;

import domain.Pawn;
import domain.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @Test
    void itMovesToANewPosition() {
        Pawn pawn = new Pawn(Point.zero());
        pawn.moveTo(new Point(5, 3));
        assertEquals(5, pawn.getX());
        assertEquals(3, pawn.getY());
    }
}
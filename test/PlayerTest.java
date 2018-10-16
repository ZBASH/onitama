import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void itStartsAtTheRow() {
        Player player = Player.createAtRow(3);

        ArrayList<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 3));
        expected.add(new Point(1, 3));
        expected.add(new Point(2, 3));
        expected.add(new Point(3, 3));
        expected.add(new Point(4, 3));

        assertEquals(expected, player.getPawns());
    }
}
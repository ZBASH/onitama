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

        ArrayList<Point> actual = new ArrayList<>();
        for(Pawn pawn : player.getPawns()) {
            actual.add(pawn.getPosition());
        }

        assertEquals(expected, actual);
    }

    @Test
    void itStartsWithNoColor() {
        Player player = new Player();
        assertEquals(Color.NONE, player.getColor());
    }

    @Test
    void itCanChooseAColor() {
        Player player = new Player();
        player.chooseColor(Color.RED);
        assertEquals(Color.RED, player.getColor());
    }
}
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void itHasAFirstPlayer() {
       Game game = new Game();
       game.start();

       Player player = game.getPlayers().get(0);
       assertEquals(0, player.getPawns().get(0).getY());
       assertEquals(Color.RED, player.getColor());
    }

    @Test
    void itHasASecondPlayer() {
        Game game = new Game();
        game.start();

        Player player = game.getPlayers().get(1);
        assertEquals(4, player.getPawns().get(0).getY());
        assertEquals(Color.BLUE, player.getColor());
    }
}
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {
    @Test
    void itRequiresABoard() {
        Ui ui = new Ui(new MockPrintStream());

        assertThrows(Ui.NoBoardError.class, () -> ui.flush());
        assertThrows(Ui.NoBoardError.class, () -> ui.render(new ArrayList<>()));
    }

    @Test
    void itRequiresPawnsInsideTheBoard() {
        Ui ui = new Ui(new MockPrintStream());
        ui.render(Board.create(0));

        ArrayList<Player> players = new ArrayList<>();
        players.add(Player.createAtRow(1, 1));

        assertThrows(Ui.OutOfBoardError.class, () -> ui.render(players));
    }

    @Test
    void itPrintsTheBoard() {
        MockPrintStream out = new MockPrintStream();

        Ui ui = new Ui(out);

        String expected =
            "* *\n" +
            "* *\n";

        ui.render(Board.create(2));
        ui.flush();

        assertEquals(expected, out.getOutput());
    }

    @Test
    void itPrintsPlayerPawnsOnTheCorrectRow() {
        MockPrintStream out = new MockPrintStream();

        Ui ui = new Ui(out);

        String expected =
            "* *\n" +
            "# #\n";

        ArrayList<Player> players = new ArrayList<>(1);
        Player player = Player.createAtRow(1, 2);
        players.add(player);

        ui.render(Board.create(2));
        ui.render(players);
        ui.flush();

        assertEquals(expected, out.getOutput());
    }

    @Test
    void itPrintsPlayerPawnsOfTheCorrectColor() {
        MockPrintStream out = new MockPrintStream();

        Ui ui = new Ui(out);

        String expected =
            "\033[0;31m#\033[0m\n";

        Player player = Player.createAtRow(0, 1);
        player.chooseColor(Color.RED);

        ArrayList<Player> players = new ArrayList<>(1);
        players.add(player);

        ui.render(Board.create(1));
        ui.render(players);
        ui.flush();

        assertEquals(expected, out.getOutput());
    }

    // mocks
    private class MockPrintStream extends PrintStream {
        private ByteArrayOutputStream stream;

        MockPrintStream() {
            this(new ByteArrayOutputStream());
        }

        private MockPrintStream(OutputStream out) {
            super(out);
            this.stream = (ByteArrayOutputStream)out;
        }

        // accessors
        String getOutput() {
            return new String(this.stream.toByteArray());
        }
    }
}
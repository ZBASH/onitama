import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {
    @Test
    void itRequiresABoard() {
        Ui ui = new Ui(new MockPrintStream());

        assertThrows(Ui.NoBoardError.class, () -> ui.flush());
        assertThrows(Ui.NoBoardError.class, () -> ui.render(Point.zero()));
    }

    @Test
    void itRequiresAPointInsideTheBoard() {
        Ui ui = new Ui(new MockPrintStream());
        ui.render(Board.create(0));

        assertThrows(Ui.OutOfBoardError.class, () -> ui.render(new Point(1, 0)));
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
    void itPrintsAPoint() {
        MockPrintStream out = new MockPrintStream();

        Ui ui = new Ui(out);

        String expected =
            "* *\n" +
            "# *\n";

        ui.render(Board.create(2));
        ui.render(new Point(0, 1));
        ui.flush();

        assertEquals(expected, out.getOutput());
    }

    @Test
    void itPrintsPlayerPawns() {
        MockPrintStream out = new MockPrintStream();

        Ui ui = new Ui(out);

        String expected =
            "* *\n" +
            "# #\n";

        Player[] players = new Player[1];
        players[0] = Player.createAtRow(1, 2);

        ui.render(Board.create(2));
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
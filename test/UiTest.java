import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {
    @Test
    void itRequiresABoard() {
        Ui ui = new Ui(new MockPrintStream(), new MockInputStream());

        assertThrows(Ui.NoBoardError.class, () -> ui.flush());
        assertThrows(Ui.NoBoardError.class, () -> ui.render(new ArrayList<>()));
    }

    @Test
    void itRequiresPawnsInsideTheBoard() {
        Ui ui = new Ui(new MockPrintStream(), new MockInputStream());
        ui.render(Board.create(0));

        ArrayList<Player> players = new ArrayList<>();
        players.add(Player.createAtRow(1, 1));

        assertThrows(Ui.OutOfBoardError.class, () -> ui.render(players));
    }

    @Test
    void itPrintsTheBoard() {
        MockPrintStream out = new MockPrintStream();

        Ui ui = new Ui(out, new MockInputStream());

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

        Ui ui = new Ui(out, new MockInputStream());

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

        Ui ui = new Ui(out, new MockInputStream());

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

    @Test
    void itPicksAPawnId() {
        MockInputStream in = new MockInputStream();
        Ui ui = new Ui(new MockPrintStream(), in);

        in.setInput("2\n");
        int actualPawnId = ui.pickPawnId();
        assertEquals(2, actualPawnId);
    }

    @Test
    void itRetriesUntilThePawnIdIsANumber() {
        MockInputStream in = new MockInputStream();
        Ui ui = new Ui(new MockPrintStream(), in);

        in.setInput("$\n2\n");
        int actualPawnId = ui.pickPawnId();
        assertEquals(2, actualPawnId);
    }

    @Test
    void itFlashesAnOutOfBoundsMessage() {
        MockPrintStream out = new MockPrintStream();
        Ui ui = new Ui(out, new MockInputStream());
        ui.render(Board.create(0));

        ui.flash(new PendingMove.PositionOutOfBounds(new Point(1,0)));
        ui.flush();

        assertEquals("The position (1,0) is out of bounds.", out.getOutput().trim());
    }

    @Test
    void itFlashesAPositionIsOccupiedMessage() {
        MockPrintStream out = new MockPrintStream();
        Ui ui = new Ui(out, new MockInputStream());
        ui.render(Board.create(0));

        ui.flash(new PendingMove.PositionWasOccuppied(new Point(1,0)));
        ui.flush();

        assertEquals("The position (1,0) is occupied.", out.getOutput().trim());
    }

    @Test
    void itFlashesAnInvalidPawnIdMessage() {
        MockPrintStream out = new MockPrintStream();
        Ui ui = new Ui(out, new MockInputStream());
        ui.render(Board.create(0));

        ui.flash(new PendingMove.InvalidPawnId(3));
        ui.flush();

        assertEquals("There is no pawn with id 3", out.getOutput().trim());
    }

    // mocks
    private class MockPrintStream extends PrintStream {
        private ByteArrayOutputStream stream;

        MockPrintStream() {
            this(new ByteArrayOutputStream());
        }

        private MockPrintStream(OutputStream out) {
            super(out);
            this.stream = (ByteArrayOutputStream) out;
        }

        // accessors
        String getOutput() {
            return new String(this.stream.toByteArray());
        }
    }

    private class MockInputStream extends InputStream {
        private int[] input;
        private int position;

        void setInput(String input) {
            this.input    = input.codePoints().toArray();
            this.position = 0;
        }

        // InputStream
        @Override
        public int read() throws IOException {
            if(input == null) {
                throw new IOException();
            }

            if(position >= input.length) {
                throw new IOException();
            }

            return input[position++];
        }
    }
}
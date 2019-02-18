package view;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RootViewTest {
    @Test
    void itRequiresABoard() {
//        view.RootView ui = new view.RootView(new MockPrintStream(), new MockInputStream());
//
//        assertThrows(view.RootView.NoBoard.class, () -> ui.draw());
//        assertThrows(view.RootView.NoBoard.class, () -> ui.renderGame(new ArrayList<>()));
    }

    @Test
    void itRequiresPawnsInsideTheBoard() {
//        view.RootView ui = new view.RootView(new MockPrintStream(), new MockInputStream());
//        ui.renderGame(domain.Board.create(0));
//
//        ArrayList<domain.Player> players = new ArrayList<>();
//        players.add(domain.Player.createAtRow(1, 1));
//
//        assertThrows(view.RootView.OutOfBoard.class, () -> ui.renderGame(players));
    }

    @Test
    void itPrintsTheBoard() {
//        MockPrintStream out = new MockPrintStream();
//
//        view.RootView ui = new view.RootView(out, new MockInputStream());
//
//        String expected =
//            "* *\n" +
//            "* *\n";
//
//        ui.renderGame(domain.Board.create(2));
//        ui.draw();
//
//        assertEquals(expected, out.getOutput());
    }

    @Test
    void itPrintsPlayerPawnsOnTheCorrectRow() {
//        MockPrintStream out = new MockPrintStream();
//
//        view.RootView ui = new view.RootView(out, new MockInputStream());
//
//        String expected =
//            "* *\n" +
//            "# #\n";
//
//        ArrayList<domain.Player> players = new ArrayList<>(1);
//        domain.Player player = domain.Player.createAtRow(1, 2);
//        players.add(player);
//
//        ui.renderGame(domain.Board.create(2));
//        ui.renderGame(players);
//        ui.draw();
//
//        assertEquals(expected, out.getOutput());
    }

    @Test
    void itPrintsPlayerPawnsOfTheCorrectColor() {
//        MockPrintStream out = new MockPrintStream();
//
//        view.RootView ui = new view.RootView(out, new MockInputStream());
//
//        String expected =
//            "\033[0;31m#\033[0m\n";
//
//        domain.Player player = domain.Player.createAtRow(0, 1);
//        player.chooseColor(core.Color.RED);
//
//        ArrayList<domain.Player> players = new ArrayList<>(1);
//        players.add(player);
//
//        ui.renderGame(domain.Board.create(1));
//        ui.renderGame(players);
//        ui.draw();
//
//        assertEquals(expected, out.getOutput());
    }

    @Test
    void itPicksAPawnId() {
//        MockInputStream in = new MockInputStream();
//        view.RootView ui = new view.RootView(new MockPrintStream(), in);
//
//        in.setInput("2\n");
//        int actualPawnId = ui.pickPawnId();
//        assertEquals(2, actualPawnId);
    }

    @Test
    void itRetriesUntilThePawnIdIsANumber() {
//        MockInputStream in = new MockInputStream();
//        view.RootView ui = new view.RootView(new MockPrintStream(), in);
//
//        in.setInput("$\n2\n");
//        int actualPawnId = ui.pickPawnId();
//        assertEquals(2, actualPawnId);
    }

    @Test
    void itFlashesAnOutOfBoundsMessage() {
//        MockPrintStream out = new MockPrintStream();
//        view.RootView ui = new view.RootView(out, new MockInputStream());
//        ui.renderGame(domain.Board.create(0));
//
//        ui.renderError(new domain.PendingMove.PositionOutOfBounds(new domain.Point(1,0)));
//        ui.draw();
//
//        assertEquals("The position (1,0) is out of bounds.", out.getOutput().trim());
    }

    @Test
    void itFlashesAPositionIsOccupiedMessage() {
//        MockPrintStream out = new MockPrintStream();
//        view.RootView ui = new view.RootView(out, new MockInputStream());
//        ui.renderGame(domain.Board.create(0));
//
//        ui.renderError(new domain.PendingMove.PositionWasOccuppied(new domain.Point(1,0)));
//        ui.draw();
//
//        assertEquals("The position (1,0) is occupied.", out.getOutput().trim());
    }

    @Test
    void itFlashesAnInvalidPawnIdMessage() {
//        MockPrintStream out = new MockPrintStream();
//        view.RootView ui = new view.RootView(out, new MockInputStream());
//        ui.renderGame(domain.Board.create(0));
//
//        ui.renderError(new domain.PendingMove.InvalidPawnId(3));
//        ui.draw();
//
//        assertEquals("There is no pawn with id 3", out.getOutput().trim());
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
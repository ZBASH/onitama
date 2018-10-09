import factories.GridFactory;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {
    @Test
    void itPrintsTheBoard() {
        MockPrintStream out = new MockPrintStream();

        Ui ui = new Ui(out);

        String expected =
            "* *\n" +
            "* *\n";

        ui.renderBoard(GridFactory.grid(expected, 2));

        assertEquals(out.getOutput(), expected);
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
import java.io.PrintStream;

public final class Ui {
    private PrintStream out;

    Ui(PrintStream out) {
        this.out = out;
    }

    void renderBoard(char[][] grid) {
        for(char[] row : grid) {
            int i = 0;

            for(char cell : row) {
                out.print(cell);
                out.print(i++ == row.length - 1 ? '\n' : ' ');
            }
        }
    }
}

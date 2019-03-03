import domain.Game;
import domain.PendingMove;
import domain.Point;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import view.RootView;

import java.io.IOException;

public class App {
    private Game game;
    private RootView view;

    private App() throws IOException {
        game = new Game();
        view = new RootView(buildTerminal());
    }

    private void start() throws IOException, InterruptedException {
        game.start();

        PendingMove pendingMove = null;
        while(true) {
            if(pendingMove == null) {
                pendingMove = new PendingMove(game);
            }

            view.clear();
            view.renderGame(game);
            view.draw();

            Integer pawnId = view.pickPawnId();
            if(pawnId == null) {
                continue;
            }

            pendingMove.pickPawn(pawnId);
            pendingMove.pickCard(new Point(0, 1));

            PendingMove.Result result = pendingMove.getValidMove();
            if (result.getMove() != null) {
                game.makeMove(result.getMove());
            } else if(result.getError() != null) {
                view.renderError(result.getError());
            }
        }
    }

    // dependencies
    private static Terminal buildTerminal() throws IOException {
        Terminal terminal = TerminalBuilder.builder()
            .nativeSignals(true)
            .signalHandler(Terminal.SignalHandler.SIG_IGN)
            .build();

        terminal.enterRawMode();

        return terminal;
    }

    // main
    public static void main(String[] args) {
        try {
            App app = new App();
            app.start();
        } catch (IOException | InterruptedException error) {
            error.printStackTrace();
        }
    }
}


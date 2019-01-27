import domain.Game;
import domain.PendingMove;
import domain.Point;
import view.Ui;

import java.io.IOException;

public class App {
    private Game game;
    private Ui ui;

    App() {
        game = new Game();
        ui   = new Ui(System.out, System.in);
    }

    private void start() throws IOException, InterruptedException {
        game.start();

        PendingMove pendingMove = null;
        while(true) {
            if(pendingMove == null) {
                pendingMove = new PendingMove(game);
            }

            ui.clear();
            ui.render(game);
            ui.flush();

            Integer pawnId = ui.pickPawnId();
            if(pawnId == null) {
                continue;
            }

            pendingMove.pickPawn(pawnId);
            pendingMove.pickCard(new Point(0, 1));

            PendingMove.Result result = pendingMove.getValidMove();
            if (result.getMove() != null) {
                game.makeMove(result.getMove());
            } else if(result.getError() != null) {
                ui.flash(result.getError());
            }
        }
    }

    // main
    public static void main(String[] args) {
        App app = new App();

        try {
            app.start();
        } catch (IOException | InterruptedException error) {
            error.printStackTrace();
        }
    }
}

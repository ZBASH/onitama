import domain.Game;
import domain.PendingMove;
import domain.Point;
import view.RootView;

import java.io.IOException;

public class App {
    private Game game;
    private RootView view;

    App() {
        game = new Game();
        view = new RootView(System.out, System.in);
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

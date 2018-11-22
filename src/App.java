public class App {
    private Game game;
    private Ui ui;

    App() {
        game = new Game();
        ui   = new Ui(System.out);
    }

    private void start() {
        game.start();

        ui.clear();
        ui.render(game.getBoard());
        ui.render(game.getPlayers());
        ui.flush();

        PendingMove pendingMove = new PendingMove(game);
        pendingMove.pickPawn(0);
        pendingMove.pickCard(new Point(0, 1));

        Move move = pendingMove.getValidMove();
        if(move != null) {
            game.makeMove(move);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ui.clear();
        ui.render(game.getBoard());
        ui.render(game.getPlayers());
        ui.flush();
    }

    // main
    public static void main(String[] args) {
        App app = new App();
        app.start();
    }
}

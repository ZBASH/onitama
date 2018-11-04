public class App {
    private Game game;
    private Ui ui;
    private PendingMove pendingMove;

    App() {
        game = new Game();
        ui   = new Ui(System.out);
    }

    private void start() {
        game.start();

        ui.render(game.getBoard());
        ui.render(game.getPlayers());
        ui.flush();
    }

    // main
    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    public PendingMove getPendingMove(Pawn pawn, Point point) {
        return pendingMove;
    }
}

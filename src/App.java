public class App {
    private Game game;
    private Ui ui;

    App() {
        game = new Game();
        ui   = new Ui(System.out);
    }

    private void start() {
        game.start();

        PendingMove pendingMove = getPendingMove();
        if(pendingMove != null) {
        }
        else {
            System.out.println("Invalid move - try again!");
        }

        ui.render(game.getBoard());
        ui.render(game.getPlayers());
        ui.flush();
    }

    // main
    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    public PendingMove getPendingMove() {
        return new PendingMove(this.game);
    }
}

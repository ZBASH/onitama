import java.util.Scanner;

public class PendingMove {
    private Game game;
    private Boolean validMove;

    PendingMove(Game game) {
        this.game = game;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a X coordinate.");
        String input = scanner.nextLine();
        int x = Integer.parseInt(input.trim());
        System.out.println("Please enter a Y coordinate.");
        input = scanner.nextLine();
        int y = Integer.parseInt(input.trim());

        Move move = getMove(game.getCurrentPlayer().pawnToMove(), new Point(x,y));
        validMove = validateMove(game, move);
    }

    private Move getMove(Pawn pawn, Point newPosition) {
        Move move = new Move(pawn, newPosition);
        return validateMove(game, move) ? move : null;
    }

    private Boolean validateMove(Game game, Move move) {
        if(move.getPoint().getX() < Config.BOARD_SIZE && move.getPoint().getY() < Config.BOARD_SIZE && move.getPoint().getX() > -1 && move.getPoint().getY() > -1) {
            return validMove = true;
        }
        return validMove = false;
    }

    public Boolean getValidMove() {

        return validMove;
    }
}

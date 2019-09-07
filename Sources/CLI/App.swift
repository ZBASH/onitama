import Onitama

final class App {
  private let mGame: Game
  private let mView: RootView

  // lifetime
  init() throws {
    let terminal = Terminal()
    mGame = try Game()
    mView = RootView(terminal: terminal)
  }

  // command
  func start() {
    mGame.start()

    var pendingMove = PendingMove(game: mGame)
    while true {
      mView.clear()
      mView.render(game: mGame, move: pendingMove)
      mView.draw()

      switch mView.pickPawn() {
      case .none:
        continue
      case .some(.offsetSelection(let offset)):
        pendingMove.pickPawn(byOffset: offset)
        continue
      case .some(.confirmSelection):
        pendingMove.pickCard(card: Point(0, 1))
      }

      switch pendingMove.getValidMove() {
      case .move(let move):
        mGame.makeMove(move: move)
        pendingMove = PendingMove(game: mGame)
      case .error(let error):
        mView.render(error: error)
      }
    }
  }
}

import Onitama

final class App {
  private let mGame: Game
  private let mView: RootView

  // lifetime
  init() {
    let terminal = Terminal()
    mGame = Game()
    mView = RootView(terminal: terminal)
  }

  // command
  func start() {
    mGame.start()

    var pendingMove = PendingMove(game: mGame)
    while true {
      mView.clear()
      mView.render(game: mGame)
      mView.draw()

      guard let pawnId = mView.pickPawnId() else {
        continue
      }

      pendingMove.pickPawn(byId: pawnId)
      pendingMove.pickCard(card: Point(0, 1))

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

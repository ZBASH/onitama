public final class App {
  private let mGame: Game
  private let mView: RootView

  // lifetime
  public init() {
    let terminal = Terminal()
    terminal.isRaw = true

    mGame = Game()
    mView = RootView(terminal: terminal)
  }

  // command
  public func start() {
    mGame.start()

    var pendingMove: PendingMove? = nil
    while true {
      mView.clear()
      mView.render(game: mGame)
      mView.draw()

      if pendingMove == nil {
        pendingMove = PendingMove(game: mGame)
      }

      guard let pawnId = mView.pickPawnId() else {
        continue
      }

      pendingMove!.pickPawn(byId: pawnId)
      pendingMove!.pickCard(card: Point(0, 1))

      switch pendingMove!.getValidMove() {
        case .move(let move):
          mGame.makeMove(move: move)
        case .error(let error):
          mView.render(error: error)
      }
    }
  }
}

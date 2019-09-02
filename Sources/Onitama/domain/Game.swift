/// `Game` is an entity representing a session of play.
public final class Game {
  // -- dependencies --
  private let mPawns: PawnRepo

  // -- properties --
  /// `mBoard` is the field on which the game takes place.
  public let mBoard: Board = .create()

  var currentPlayerId: Int = 0
  var players: [Player] = []

  // -- lifetime --
  /// `init` constructs a new game.
  public convenience init() {
    self.init(pawns: PawnRepo())
  }

  init(pawns: PawnRepo) {
    mPawns = pawns
  }

  // -- commands --
  /// `start` begins the game, setting up its initial state.
  public func start() {
    let player1 = Player(pawns: mPawns)
    player1.chooseColor(.red)
    players.append(player1)

    let player2 = Player(pawns: mPawns)
    player2.chooseColor(.blue)
    players.append(player2)
  }

  /// `makeMove` resolves a pre-validated move.
  public func makeMove(move: Move) {
    // current player makes move
    guard let pawn = mPawns.findPawn(byId: move.pawnId) else {
      return
    }

    pawn.move(to: move.newPosition)

    // capture opponents pawn if possible
    if let otherPawn = findOtherPlayerPawn(byPosition: move.newPosition) {
      otherPawn.capture()
    }

    // swap current player
    self.currentPlayerId = 1 - currentPlayerId
  }

  // -- queries --
  /// `currentPlayer` is the user presently taking their turn.
  public var currentPlayer: Player {
    return players[currentPlayerId]
  }

  /// `otherPlayer` is the user not presently taking their turn.
  public var otherPlayer: Player {
    return players[1-currentPlayerId]
  }

  var isFirstPlayerCurrentPlayer: Bool {
    return currentPlayerId == 0
  }

  func findCurrentPlayerPawn(byIndex index: Int) -> Pawn? {
    return currentPlayer.findPawn(byIndex: index)
  }

  func findCurrentPlayerPawn(byPosition position: Point) -> Pawn? {
    return currentPlayer.findPawn(byPosition: position)
  }

  func findOtherPlayerPawn(byPosition position: Point) -> Pawn? {
    return otherPlayer.findPawn(byPosition: position)
  }
}

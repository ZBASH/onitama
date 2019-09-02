/// `PendingMove` is a transactional entity representing a user's currently
/// selected, but not necessarily confirmed, move.
public final class PendingMove {
  // -- dependencies --
  let game: Game

  // -- properties --
  var pawnId: Int?

  var card: Point?

  // -- lifetime --
  /// `init` constructs a PendingMove for a specific game.
  public init(game: Game) {
    self.game = game
  }

  // -- commands --
  /// `pickPawn` selects the current user's pawn by ID.
  public func pickPawn(byId id: Int) {
    self.pawnId = id
  }

  /// `pickCard` selects the card to play as a point delta.
  public func pickCard(card: Point) {
    self.card = card
  }

  // -- queries --
  /// `getValidMove` validates the current state to produce a valid move.
  ///
  /// - Returns: A move or a validation error.
  public func getValidMove() -> Result {
    guard let pawnId = pawnId else {
      return .error(.missingPawnId)
    }

    guard let card = card else {
      return .error(.missingCard)
    }

    guard let pawn = game.findCurrentPlayerPawn(byId: pawnId) else {
      return .error(.invalidPawnId(pawnId))
    }

    var delta = card
    if !game.isFirstPlayerCurrentPlayer {
      delta = delta.mirrorX()
    }

    let newPosition = pawn.position + delta
    if !game.board.containsPoint(point: newPosition) {
      return .error(.positionOutOfBounds(position: newPosition))
    }

    if game.findCurrentPlayerPawn(byPosition: newPosition) != nil {
      return .error(.positionWasOccupied(poistion: newPosition))
    }

    return .move(Move(pawnId: pawnId, newPosition: newPosition))
  }

  /// `Result` is a value-type representing either a valid or invalid state.
  public enum Result {
    case error(Error)
    case move(Move)

    var move: Move? {
      switch self {
      case .error(_):
        return nil
      case .move(let move):
        return move
      }
    }

    var error: Error? {
      switch self {
      case .move(_):
        return nil
      case .error(let error):
        return error
      }
    }
  }

  /// `Error` is the set of errors possibly produced by `getValidMove`.
  public enum Error: Swift.Error {
    case missingPawnId
    case missingCard
    case invalidPawnId(Int)
    case positionOutOfBounds(position: Point)
    case positionWasOccupied(poistion: Point)
  }
}

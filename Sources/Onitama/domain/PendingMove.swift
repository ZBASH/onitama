/// `PendingMove` is a transactional entity representing a user's currently
/// selected, but not necessarily confirmed, move.
public final class PendingMove {
  // -- dependencies --
  let mGame: Game

  // -- properties --
  /// `mPawnIndex` is the index of the currently selected pawn.
  public private(set) var mPawnIndex: Int
  /// `mCard` is the currently selected movement card, or none.
  public private(set) var mCard: Point?

  // -- lifetime --
  /// `init` constructs a PendingMove for a specific game.
  public init(game: Game) {
    mGame = game
    mPawnIndex = 0
  }

  // -- commands --
  /// `pickPawn` selects the current user's pawn by ID.
  public func pickPawn(byOffset offset: Int) {
    let nextIndex = mPawnIndex + offset
    if nextIndex < 0 || nextIndex >= mGame.currentPlayer.pawns.count {
      return
    }
    
    mPawnIndex = nextIndex
  }

  /// `pickCard` selects the card to play as a point delta.
  public func pickCard(card: Point) {
    mCard = card
  }

  // -- queries --
  /// `getValidMove` validates the current state to produce a valid move.
  ///
  /// - Returns: A move or a validation error.
  public func getValidMove() -> Result {
    guard let card = mCard else {
      return .error(.missingCard)
    }

    guard let pawn = mGame.findCurrentPlayerPawn(byIndex: mPawnIndex) else {
      return .error(.invalidPawnIndex(mPawnIndex))
    }

    var delta = card
    if !mGame.isFirstPlayerCurrentPlayer {
      delta = delta.mirrorX()
    }

    let newPosition = pawn.position + delta
    if !mGame.mBoard.containsPoint(point: newPosition) {
      return .error(.positionOutOfBounds(position: newPosition))
    }

    if mGame.findCurrentPlayerPawn(byPosition: newPosition) != nil {
      return .error(.positionWasOccupied(poistion: newPosition))
    }

    return .move(Move(pawnId: pawn.id, newPosition: newPosition))
  }
  
  // -- types --
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
    case missingCard
    case invalidPawnIndex(Int)
    case positionOutOfBounds(position: Point)
    case positionWasOccupied(poistion: Point)
  }
}

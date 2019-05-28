final class PendingMove {
  // -- dependencies --
  let game: Game

  // -- properties --
  var pawnId: Int?
  var card:   Point?

  // -- lifetime --
  init(game: Game) {
    self.game = game
  }

  // -- commands --
  func pickPawn(byId id: Int) {
    self.pawnId = id
  }

  func pickCard(card: Point) {
    self.card = card
  }

  // -- queries --
  func getValidMove() -> Result {
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
    if(!game.isFirstPlayerCurrentPlayer) {
      delta = delta.mirrorX()
    }

    let newPosition = pawn.position + delta
    if(!game.board.containsPoint(point: newPosition)) {
      return .error(.positionOutOfBounds(position: newPosition))
    }

    if(game.findCurrentPlayerPawn(byPosition: newPosition) != nil) {
      return .error(.positionWasOccupied(poistion: newPosition))
    }

    return .move(Move(pawnId: pawnId, newPosition: newPosition))
  }

  enum Result {
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

  enum Error: Swift.Error {
    case missingPawnId
    case missingCard
    case invalidPawnId(Int)
    case positionOutOfBounds(position: Point)
    case positionWasOccupied(poistion: Point)
  }
}

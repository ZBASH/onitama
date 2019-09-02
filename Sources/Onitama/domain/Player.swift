/// `Player` is a user participating in the game.
public final class Player {
  /// `color` is this player's, and its corresponding pieces', color.
  public private(set) var color: Color = .none

  /// `pawns` in the list of pawns belonging to this player.
  public private(set) var pawns: [Pawn] = []

  var pawnToMove: Pawn?

  // -- commands --
  func chooseColor(_ color: Color) {
    self.color = color
  }

  func move(pawn: Pawn, to position: Point) {
    self.pawnToMove = pawn
    self.pawnToMove?.move(to: position)
  }

  // -- queries --
  func findPawn(byPosition position: Point) -> Pawn? {
    return pawns.first { pawn in
      pawn.position == position
    }
  }

  // -- factories --
  static func create(atRow row: Int, size: Int = Config.Board.size) -> Player {
    let player = Player()

    for x in 0..<size {
      player.pawns.append(Pawn(position: Point(x, row)))
    }

    return player
  }
}

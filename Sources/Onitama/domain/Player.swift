/// `Player` is a user participating in the game.
public final class Player {
  // -- dependencies --
  private let mPawns: PawnRepo
  
  // -- properties --
  /// `color` is this player's, and its corresponding pieces', color.
  public private(set) var color: Color = .none
  
  // -- lifetime --
  init(pawns: PawnRepo) {
    self.mPawns = pawns
  }

  // -- commands --
  func chooseColor(_ color: Color) {
    self.color = color
  }
  
  // -- queries --
  /// `pawns` in the list of pawns belonging to this player.
  public var pawns: [Pawn] {
    return mPawns.findLivePawns(byColor: color)
  }
  
  func findPawn(byIndex index: Int) -> Pawn? {
    return mPawns.findLivePawn(byColor: color, index: index)
  }
  
  func findPawn(byPosition position: Point) -> Pawn? {
    return mPawns.findLivePawn(byColor: color, position: position)
  }
}

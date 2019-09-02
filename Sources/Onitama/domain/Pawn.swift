/// `Pawn` is an entity representing the state of a piece.
public final class Pawn {
  /// `position` is the pawn's xy position on the board.
  public private(set) var position: Point

  /// `isCaptured` indicates if the pawn has been captured or not.
  public private(set) var isCaptured = false

  // -- lifetime --
  init(position: Point) {
    self.position = position
  }

  // -- commands --
  /// `move` updates the position of the pawn.
  func move(to position: Point) {
    self.position = position
  }

  /// `capture` marks the pawn as captured.
  func capture() {
    self.isCaptured = true
  }
}

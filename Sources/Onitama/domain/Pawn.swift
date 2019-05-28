final class Pawn {
  /// The pawn's xy position on the board.
  var position: Point
  /// If the pawn has been captured or not.
  var isCaptured = false

  // -- lifetime --
  init(position: Point) {
    self.position = position
  }

  // -- commands --
  /// Moves the pawn to a new position.
  func move(to position: Point) {
    self.position = position
  }

  /// Marks the pawn as captured.
  func capture() {
    self.isCaptured = true
  }
}

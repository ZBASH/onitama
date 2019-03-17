final class Pawn {
  // properties
  var position: Point
  var isCaptured = false

  // liftime
  init(position: Point) {
    self.position = position
  }

  // commands
  func move(to position: Point) {
    self.position = position
  }

  func capture() {
    self.isCaptured = true
  }
}

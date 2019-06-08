/// A validated move.
struct Move: Equatable {
  /// The ID of the pawn being moved
  let pawnId: Int
  /// The new position for the pawn being moved
  let newPosition: Point
}

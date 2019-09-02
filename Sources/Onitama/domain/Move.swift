/// `Move` is a value-type representing a pre-validated move.
public struct Move: Equatable {
  /// `pawnId` is the ID of the pawn being moved.
  let pawnId: Int

  /// `newPosition` is the position to move the pawn to.
  let newPosition: Point
}

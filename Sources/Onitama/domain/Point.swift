/// `Point` is a value-type representing a grid position.
public struct Point: Equatable, Decodable {
  /// `x` is the point's column.
  public let x: Int

  /// `y` is the point's row.
  public let y: Int

  // -- lifetime --
  /// `init` constructs a new point.
  public init(_ x: Int, _ y: Int) {
    self.x = x
    self.y = y
  }

  // -- operators --
  func mirrorX() -> Point {
    return Point(
      x,
      y * -1
    )
  }

  static func + (left: Point, right: Point) -> Point {
    return Point(
      left.x + right.x,
      left.y + right.y
    )
  }

  // -- factories --
  static func zero() -> Point {
    return Point(0, 0)
  }
}

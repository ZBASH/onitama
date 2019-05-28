struct Point: Equatable {
  let x: Int
  let y: Int

  // -- lifetime --
  init(_ x: Int, _ y: Int) {
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

  static func +(left: Point, right: Point) -> Point {
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

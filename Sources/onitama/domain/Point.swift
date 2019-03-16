struct Point {
  let x: Int
  let y: Int

  // operators
  static func +(left: Point, right: Point) -> Point {
    return Point(
      x: left.x + right.x,
      y: left.y + right.y
    )
  }

  func mirrorX() -> Point {
    return Point(
      x: x,
      y: y * -1
    )
  }

  // factories
  static func zero() -> Point {
    return Point(x: 0, y: 0)
  }
}

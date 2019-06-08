import XCTest

@testable import Onitama

final class PointTests: XCTestCase {
  func testItCreatesAZeroPoint() {
    let actual = Point.zero()
    XCTAssertEqual(actual.x, 0)
    XCTAssertEqual(actual.y, 0)
  }

  func testItAddsPoints() {
    let left = Point(1, 4)
    let right = Point(2, 3)

    let actual = left + right
    XCTAssertEqual(actual.x, 3)
    XCTAssertEqual(actual.y, 7)
  }

  func testItMirrorsAPointOverTheAxis() {
    let point = Point(1, 4)

    let actual = point.mirrorX()
    XCTAssertEqual(actual.x, 1)
    XCTAssertEqual(actual.y, -4)
  }

  func testItComparesPointsForEquality() {
    let actual = Point(2, 4)
    XCTAssertEqual(actual, Point(2, 4))
  }
}

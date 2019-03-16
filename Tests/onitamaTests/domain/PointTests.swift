import XCTest
@testable 
import Onitama

final class PointTests: XCTestCase {
  func testItCreatesAZeroPoint() {
    let actual = Point.zero()
    XCTAssertEqual(actual.x, 0)
    XCTAssertEqual(actual.y, 0)
  }

  func testItAddsPoints() {
    let left   = Point (x: 1, y: 4)
    let right  = Point (x: 2, y: 3)
    let actual = left + right
    XCTAssertEqual(actual.x, 3)
    XCTAssertEqual(actual.y, 7)
  }
}

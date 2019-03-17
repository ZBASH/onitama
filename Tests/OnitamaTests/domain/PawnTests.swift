import XCTest
@testable
import Onitama

final class PawnTests: XCTestCase {
  func testItMovesToANewPosition() {
    let pawn = Pawn(position: Point.zero())

    pawn.move(to: Point(5, 3))

    XCTAssertEqual(pawn.position.x, 5)
    XCTAssertEqual(pawn.position.y, 3)
  }
}

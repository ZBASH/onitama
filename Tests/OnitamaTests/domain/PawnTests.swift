import XCTest

@testable import Onitama

final class PawnTests: TestCase {
  func testItMovesToANewPosition() {
    let pawn = Pawn(id: 0, color: .red, position: .zero())

    pawn.move(to: Point(5, 3))

    XCTAssertEqual(pawn.position.x, 5)
    XCTAssertEqual(pawn.position.y, 3)
  }
}

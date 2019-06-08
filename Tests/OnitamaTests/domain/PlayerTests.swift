import XCTest

@testable import Onitama

final class PlayerTests: XCTestCase {
  func testItStartsAtTheRow() {
    let player = Player.create(atRow: 3, size: 2)

    let actual = player.pawns.map { $0.position }
    XCTAssertEqual(
      actual,
      [
        Point(0, 3),
        Point(1, 3),
      ]
    )
  }

  func testItStartsWithNoColor() {
    let player = Player()
    XCTAssertEqual(player.color, .none)
  }

  func testItChoosesAColor() {
    let player = Player()

    player.chooseColor(.red)
    XCTAssertEqual(player.color, .red)
  }

  func testItMovesAPawn() {
    let player = Player.create(atRow: 0, size: 1)
    let pawn = player.pawns[0]

    player.move(pawn: pawn, to: Point(4, 3))
    XCTAssertEqual(pawn.position, Point(4, 3))
  }
}

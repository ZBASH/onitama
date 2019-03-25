import XCTest
@testable
import Onitama

final class BoardTests: XCTestCase {
  func testItStartsAtTheRow() {
    let player = Player.create(atRow: 3, size: 2)
    
    let actual = player.pawns.map { $0.position }
    XCTAssertEqual(actual, [
      Point(0, 3),
      Point(1, 3)
      ])
  }
  func testItShowsTheInitialGrid() {
    let board = Board.create()
    
    XCTAssertEqual(board.grid.count, 5)
  }
}



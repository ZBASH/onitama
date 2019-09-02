import XCTest

@testable import Onitama

final class BoardTests: TestCase {
  func testItShowsTheInitialGrid() {
    let board = Board.create()
    XCTAssertEqual(board.grid.count, 5)
  }
}

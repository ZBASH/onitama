import XCTest

@testable import Onitama

final class PlayerTests: TestCase {
  func testItStartsWithNoColor() {
    let player = Player(pawns: PawnRepo(size: 2))
    XCTAssertEqual(player.color, .none)
  }

  func testItChoosesAColor() {
    let player = Player(pawns: PawnRepo(size: 2))

    player.chooseColor(.red)
    XCTAssertEqual(player.color, .red)
  }
  
  func testItHasAllUncapturedPawns() {
    let player = Player(pawns: PawnRepo(size: 2))
    player.chooseColor(.red)
    
    XCTAssertEqual(player.pawns.count, 2)
    XCTAssert(player.pawns.allSatisfy { pawn in
      pawn.color == .red
    })
  }
}

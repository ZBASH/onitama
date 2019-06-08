import XCTest

@testable import Onitama

final class PendingMoveTests: XCTestCase {
  func testItCreatesValidMovesForTheFirstPlayer() {
    let game = Game()
    game.start()
    let pendingMove = PendingMove(game: game)

    pendingMove.pickPawn(byId: 1)
    pendingMove.pickCard(card: Point(0, 1))
    let actual = pendingMove.getValidMove().move

    XCTAssertNotNil(actual)
    XCTAssertEqual(actual?.pawnId, 1)
    XCTAssertEqual(actual?.newPosition, Point(1, 1))
  }

  func testItCreatesValidMovesForTheSecondPlayer() {
    let game = Game()
    game.start()
    game.makeMove(move: Move(pawnId: 0, newPosition: .zero()))
    let pendingMove = PendingMove(game: game)

    pendingMove.pickPawn(byId: 1)
    pendingMove.pickCard(card: Point(0, 1))
    let actual = pendingMove.getValidMove().move

    XCTAssertNotNil(actual)
    XCTAssertEqual(actual?.pawnId, 1)
    XCTAssertEqual(actual?.newPosition, Point(1, 3))
  }

  func testItDisallowsInvalidPawns() {
    let game = Game()
    game.start()
    let pendingMove = PendingMove(game: game)

    pendingMove.pickPawn(byId: 100)
    pendingMove.pickCard(card: Point.zero())
    let actual = pendingMove.getValidMove()

    guard case .error(.invalidPawnId(100)) = actual else {
      XCTFail("Did not produce .error(.invalidPawnId(100))")
      return
    }
  }

  func testItDisallowsOutOfBoundsMoves() {
    let game = Game()
    game.start()
    let pendingMove = PendingMove(game: game)

    pendingMove.pickPawn(byId: 2)
    pendingMove.pickCard(card: Point(3, 0))
    let actual = pendingMove.getValidMove()

    guard case .error(.positionOutOfBounds(position: Point(5, 0))) = actual else {
      XCTFail("Did not produce .error(.positionOutOfBounds(position: Point(5, 0)))")
      return
    }
  }

  func testItDisallowsMovesToOccupiedSpaces() {
    let game = Game()
    game.start()
    let pendingMove = PendingMove(game: game)

    pendingMove.pickPawn(byId: 0)
    pendingMove.pickCard(card: Point(1, 0))
    let actual = pendingMove.getValidMove()

    guard case .error(.positionWasOccupied(poistion: Point(1, 0))) = actual else {
      XCTFail("Did not produce .error(.positionOutOfBounds(position: Point(3, 0)))")
      return
    }
  }
}

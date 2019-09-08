import XCTest

@testable import Onitama

final class PendingMoveTests: TestCase {
  func testItCreatesValidMovesForTheFirstPlayer() throws {
    let game = try Game()
    game.start()
    let pendingMove = PendingMove(game: game)

    pendingMove.pickPawn(byOffset: 1)
    pendingMove.pickCard(card: Point(0, 1))
    let actual = pendingMove.getValidMove().move

    XCTAssertNotNil(actual)
    XCTAssertEqual(actual?.pawnId, 1)
    XCTAssertEqual(actual?.newPosition, Point(1, 1))
  }

  func testItCreatesValidMovesForTheSecondPlayer() throws {
    let game = try Game()
    game.start()
    game.makeMove(move: Move(pawnId: 0, newPosition: .zero()))
    let pendingMove = PendingMove(game: game)

    pendingMove.pickPawn(byOffset: 1)
    pendingMove.pickCard(card: Point(0, 1))
    let actual = pendingMove.getValidMove().move

    XCTAssertNotNil(actual)
    XCTAssertEqual(actual?.pawnId, 6)
    XCTAssertEqual(actual?.newPosition, Point(1, 3))
  }

  func testItDisallowsOutOfBoundsMoves() throws {
    let game = try Game()
    game.start()
    let pendingMove = PendingMove(game: game)

    pendingMove.pickPawn(byOffset: 2)
    pendingMove.pickCard(card: Point(3, 0))
    let actual = pendingMove.getValidMove()

    guard case .error(.positionOutOfBounds(position: Point(5, 0))) = actual else {
      XCTFail("Did not produce .error(.positionOutOfBounds(position: Point(5, 0)))")
      return
    }
  }

  func testItDisallowsMovesToOccupiedSpaces() throws {
    let game = try Game()
    game.start()
    let pendingMove = PendingMove(game: game)

    pendingMove.pickPawn(byOffset: 0)
    pendingMove.pickCard(card: Point(1, 0))
    let actual = pendingMove.getValidMove()

    guard case .error(.positionWasOccupied(poistion: Point(1, 0))) = actual else {
      XCTFail("Did not produce .error(.positionOutOfBounds(position: Point(3, 0)))")
      return
    }
  }
}

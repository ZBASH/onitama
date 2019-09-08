import XCTest

@testable import Onitama

final class GameTests: TestCase {
  func testItHasAFirstPlayer() throws {
    let game = try Game()
    game.start()

    let player = game.players[0]

    XCTAssertEqual(player.pawns[0].position.y, 0)
    XCTAssertEqual(player.color, Color.red)
  }

  func testItHasASecondPlayer() throws {
    let game = try Game()
    game.start()

    let player = game.players[1]

    XCTAssertEqual(player.pawns[0].position.y, 4)
    XCTAssertEqual(player.color, Color.blue)
  }

  func testItMakesAMove() throws {
    let game = try Game()
    game.start()
    let pawn = game.findCurrentPlayerPawn(byIndex: 2)

    let move = Move(pawnId: 2, newPosition: Point(2, 1))
    game.makeMove(move: move)

    XCTAssertEqual(pawn?.position, move.newPosition)
  }

  func testItSwapsTheCurrentPlayerAfterMakingAMove() throws {
    let game = try Game()
    game.start()

    let previousPlayer = game.currentPlayer

    let move = Move(pawnId: 2, newPosition: Point(2, 1))
    game.makeMove(move: move)

    XCTAssert(game.currentPlayer !== previousPlayer)
  }
}

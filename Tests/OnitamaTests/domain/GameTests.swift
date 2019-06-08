import XCTest

@testable import Onitama

final class GameTests: XCTestCase {
  func testItHasAFirstPlayer() {
    let game = Game.init()
    game.start()

    let player = game.players[0]

    XCTAssertEqual(player.pawns[0].position.y, 0)
    XCTAssertEqual(player.color, Color.red)
  }

  func testItHasASecondPlayer() {
    let game = Game.init()
    game.start()

    let player = game.players[1]

    XCTAssertEqual(player.pawns[0].position.y, 4)
    XCTAssertEqual(player.color, Color.blue)
  }

  func testItMakesAMove() {
    let game = Game.init()
    game.start()
    let pawn = game.findCurrentPlayerPawn(byId: 2)

    let move = Move(pawnId: 2, newPosition: Point(2, 1))
    game.makeMove(move: move)

    XCTAssertEqual(pawn?.position, move.newPosition)
  }

  func testItSwapsTheCurrentPlayerAfterMakingAMove() {
    let game = Game.init()
    game.start()

    let previousPlayer = game.currentPlayer

    let move = Move(pawnId: 2, newPosition: Point(2, 1))
    game.makeMove(move: move)

    XCTAssert(game.currentPlayer !== previousPlayer)
  }
}

import XCTest
@testable
import Onitama

final class GameTests: XCTestCase {
  func testItHasAFirstPlayer() {
    let game = Game.init()
    game.start()
    
    let player = game.pawns[0]
    
    XCTAssertEqual(player.pawns[0].y, 0)
    XCTAssertEqual(player.color, Color.red)
  }
  
  func testItHasASecondPlayer() {
    let game = Game.init()
    game.start()
    
    let player = game.players[1]
    
    XCTAssertEqual(player.pawn[0].y, 4)
    XCTAssertEqual(player.color, Color.blue)
  }
  
  func testItMakesAMove() {
    let game = Game.init()
    game.start()
    let pawn = game.findCurrentPlayerPawnById(2)
    
    let move = Move(2, Point(2, 1))
    game.makeMove(move)
    
    XCTAssertEqual(pawn.position, move.newPoistion
  }
  
  func testItSwapsTheCurrentPlayerAfterMakingAMove() {
    let game = Game.init()
    game.start()
    
    let previousPlayer = game.currentPlayer()
    
    let move = Move(2, Point(2, 1))
    game.makeMove(move)
    
    XCTAssertNotEqual(game.currentPlayer, previousPlayer)
  }
}




final class Game {
  // properties
  var currentPlayerId: Int = 0
  let board: Board = Board.create()
  var players = [Player](repeating: Player.init(), count: 2)
  
  // command
  func start() {
    self.players[0].chooseColor(Color.red)
    self.players[1].chooseColor(Color.blue)
  }
  
  func makeMove(move: Move) {
    // current player makes move
    let pawn = findCurrentPlayerById(move.pawnId())
    pawn.moveTo(move.NewPosition())
    
    // capture opponents pawn if possible
    let otherPawn = findOtherPlayerPawnByPosition(move.newPosition())
    if(otherPawn != nil) {
      otherPawn.capture()
    }
    
    // swap current player
    self.currentPlayerId = 1 - currentPlayerId
  }
  
  // queries
  func findCurrentPlayerPawnById(pawnId: Int) -> Pawn? {
    let currentPlayer = self.players[currentPlayerId]
    if(pawnId < 0 || pawnId >= currentPlayer.pawns.count) {
      return nil
    }
    
    let pawn = currentPlayer.pawns[pawnId]
    if(pawn.isCaptured {
      return nil
    }
    return pawn
  }
}

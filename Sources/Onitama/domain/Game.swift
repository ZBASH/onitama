final class Game {
  // properties
  var currentPlayerId: Int = 0
  let board: Board = .create()
  var players: [Player] = []
  
  // command
  func start() {
    let player1 = Player.create(atRow: 0)
    player1.chooseColor(.red)
    players.append(player1)

    let player2 = Player.create(atRow: Config.Board.size - 1)
    player2.chooseColor(.blue)
    players.append(player2)
  }
  
  func makeMove(move: Move) {
    // current player makes move
    guard let pawn = findCurrentPlayerPawn(byId: move.pawnId) else {
      return
    }

    pawn.move(to: move.newPosition)
    
    // capture opponents pawn if possible
    if let otherPawn = findOtherPlayerPawn(byPosition: move.newPosition) {
      otherPawn.capture()
    }
    
    // swap current player
    self.currentPlayerId = 1 - currentPlayerId
  }
  
  // queries
  var currentPlayer: Player {
    return players[currentPlayerId]
  }

  var otherPlayer: Player {
    return players[1-currentPlayerId]
  }

  var isFirstPlayerCurrentPlayer: Bool {
    return currentPlayerId == 0
  }

  func findCurrentPlayerPawn(byId id: Int) -> Pawn? {
    if(id < 0 || id >= currentPlayer.pawns.count) {
      return nil
    }
    
    let pawn = currentPlayer.pawns[id]
    if(pawn.isCaptured) {
      return nil
    }

    return pawn
  }

  func findCurrentPlayerPawn(byPosition position: Point) -> Pawn? {
    return currentPlayer.findPawn(byPosition: position)
  }

  func findOtherPlayerPawn(byPosition position: Point) -> Pawn? {
    return otherPlayer.findPawn(byPosition: position)
  }
}

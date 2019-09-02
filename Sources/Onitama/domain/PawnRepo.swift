class PawnRepo {
  // -- properties --
  private(set) var mAll: [Pawn]
  
  // -- lifetime --
  init(size: Int = Config.Board.size) {
    mAll = []
    
    func appendRow(at row: Int, color: Color) {
      for x in 0..<size {
        mAll.append(Pawn(
          id: mAll.count,
          color: color,
          position: Point(x, row)
        ))
      }
    }
    
    appendRow(at: 0, color: .red)
    appendRow(at: size - 1, color: .blue)
  }
  
  // -- queries --
  func findPawn(byId id: Int) -> Pawn? {
    return mAll.at(id)
  }
  
  var live: [Pawn] {
    return mAll.filter { pawn in
      !pawn.isCaptured
    }
  }
    
  func findLivePawns(byColor color: Color) -> [Pawn] {
    return live.filter { pawn in
      pawn.color == color
    }
  }
  
  func findLivePawn(byColor color: Color, position: Point) -> Pawn? {
    return findLivePawns(byColor: color).first { pawn in
      pawn.position == position
    }
  }
  
  func findLivePawn(byColor color: Color, index: Int) -> Pawn? {
    return findLivePawns(byColor: color).at(index)
  }
}



struct Board: Equatable {
  // properties
  var grid: [Character]
  
  //lifetime
  init(grid: [Character]) {
    self.grid = grid
  }
  
  // queries
  func containsPoint(point: Point) -> Bool {
    return point.x >= 0 && point.x < grid.count &&
           point.y >= 0 && point.y < grid.count
    
  }
  
  // factories
  static func create() -> Board {
    return create(Config.Board.size)
  }
  
  static func create(size: Int) -> Board {
    var grid = [[Character]](repeating: [Character](repeating: Config.Board.space, count: size), count: size)
    return Board.init(grid: grid)
  }
  
}

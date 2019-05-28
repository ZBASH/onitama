struct Board: Equatable {
  // properties
  var grid: [[String]]
  
  //lifetime
  init(grid: [[String]]) {
    self.grid = grid
  }
  
  // queries
  func containsPoint(point: Point) -> Bool {
    return point.x >= 0 && point.x < grid.count &&
           point.y >= 0 && point.y < grid.count
  }
  
  // factories
  static func create() -> Board {
    return create(size: Config.Board.size)
  }
  
  static func create(size: Int) -> Board {
    return Board(grid: Array.grid(repeating: Config.Board.space, count: size))
  }
  
}

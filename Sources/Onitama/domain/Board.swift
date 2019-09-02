/// `Board` is an entity representing the state of the grid the game is played on.
public class Board {
  /// `grid` is the 2D grid of characters.
  public private(set) var grid: [[Character]]

  // -- lifetime --
  init(grid: [[Character]]) {
    self.grid = grid
  }

  // -- queries --
  func containsPoint(point: Point) -> Bool {
    return (
      point.x >= 0 && point.x < grid.count && point.y >= 0 && point.y < grid.count
    )
  }

  // -- factories --
  static func create() -> Board {
    return create(size: Config.Board.size)
  }

  static func create(size: Int) -> Board {
    return Board(grid: Array.grid(repeating: Config.Board.space, count: size))
  }
}

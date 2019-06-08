final class BoardView {
  private let mBuffer: TileBuffer

  // lifetime
  init(buffer: TileBuffer) {
    mBuffer = buffer
  }

  // command
  func render(board: Board) {
    let grid = board.grid

    mBuffer.initialize(size: grid.count)
    for y in 0..<grid.count {
      let row = grid[y]

      for x in 0..<grid.count {
        mBuffer.set(Point(x, y), tile: Tile(glyph: row[x]))
      }
    }
  }
}

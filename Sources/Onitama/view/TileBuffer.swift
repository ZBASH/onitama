final class TileBuffer {
  private let mTerminal: Terminal
  private var mBuffer: [[Tile]]?

  // -- lifetime --
  init(terminal: Terminal) {
    mTerminal = terminal
  }

  // -- commmands --
  func initialize(size: Int) {
    mBuffer = Array.grid(repeating: Tile(), count: size)
  }

  func set(_ point: Point, tile: Tile) {
    guard let buffer = mBuffer else {
      fatalError("buffer was not initialized")
    }

    if point.x >= buffer.count || point.y >= buffer.count {
      fatalError("buffer does not contain \(point)")
    }

    mBuffer?[point.y][point.x] = tile
  }

  func draw() {
    guard let buffer = mBuffer else {
      fatalError("buffer was not initialized")
    }

    for row in buffer {
      for (i, tile) in row.enumerated() {
        mTerminal.write(tile.render())
        mTerminal.write(i == row.count - 1 ? "\n" : " ")
      }
    }

    mBuffer = nil
  }
}

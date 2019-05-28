final class TileBuffer {
  private let mTerminal: Terminal
  private var mBuffer:   [[Tile]]?

  // lifetime
  init(terminal: Terminal) {
    mTerminal = terminal
  }

  // commmands
  func initialize(size: Int) {
    mBuffer = Array.grid(repeating: Tile(), count: size)
  }

  func set(x: Int, y: Int, tile: Tile) {
    mBuffer?[x][y] = tile
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

  // -- queries --
  func contains(x: Int, y: Int) -> Bool {
    guard let buffer = mBuffer else {
      fatalError("buffer was not initialized")
    }

    return y < buffer.count && x < buffer.count
  }
}

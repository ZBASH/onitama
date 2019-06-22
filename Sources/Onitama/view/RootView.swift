final class RootView {
  private let mTerminal: Terminal
  private let mBuffer: TileBuffer
  private let mGameView: GameView
  private let mFlash: Flash
  private let mPickPawnInput: PickPawnInput

  // -- lifetime --
  init(terminal: Terminal) {
    mTerminal = terminal
    mBuffer = TileBuffer(terminal: terminal)
    mGameView = GameView(buffer: mBuffer)
    mFlash = Flash(terminal: terminal)
    mPickPawnInput = PickPawnInput(terminal: terminal)
  }

  // -- commands --
  func pickPawnId() -> Int? {
    mPickPawnInput.call()
    return mPickPawnInput.confirmedPawnIndex
  }

  // -- commands/render
  func render(game: Game) {
    mGameView.render(game: game, selectedPawnIndex: mPickPawnInput.selectedPawnIndex)
  }

  func render(error: PendingMove.Error) {
    mFlash.render(error: error)
  }

  // -- commands/draw
  func clear() {
    mTerminal.write("\u{001B}[H\u{001B}[2J")
  }

  func draw() {
    mBuffer.draw()
    mFlash.draw()
  }
}

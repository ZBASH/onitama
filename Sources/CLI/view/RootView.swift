import Onitama

final class RootView {
  // -- dependencies --
  private let mTerminal: Terminal

  // -- properties --
  private let mBuffer: TileBuffer

  private let mGameView: GameView
  private let mFlash: Flash
  private var mPickPawnInput: PickPawnInput
  private let mMenuInput: MenuInput

  // -- lifetime --
  init(terminal: Terminal) {
    mTerminal = terminal
    mBuffer = TileBuffer(terminal: terminal)
    mGameView = GameView(buffer: mBuffer)
    mFlash = Flash(terminal: terminal)
    mPickPawnInput = PickPawnInput(terminal: terminal)
    mMenuInput = MenuInput(terminal: terminal)
  }

  // -- commands --
  // -- commands/input
  func pickPawn() -> PickPawnInput.Event? {
    mPickPawnInput.render()

    let input = mTerminal.read()
    mMenuInput.handleInput(input)
    mPickPawnInput.handleInput(input)

    return mPickPawnInput.mEvent
  }

  // -- commands/render
  func render(game: Game, move: PendingMove) {
    mGameView.render(game: game, selectedPawnIndex: move.mPawnIndex)
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

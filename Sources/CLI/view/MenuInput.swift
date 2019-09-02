final class MenuInput {
  private let mTerminal: Terminal

  // -- lifetime --
  init(terminal: Terminal) {
    mTerminal = terminal
  }

  // -- commands --
  func handleInput(_ input: [UInt8]) {
    switch input {
    case [Terminal.Code.q]:
      mTerminal.exit()
    default:
      break
    }
  }
}

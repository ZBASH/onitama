import Onitama

final class PickPawnInput {
  // -- dependencies --
  private let mTerminal: Terminal
  
  // -- properties --
  private(set) var mEvent: Event? = nil

  // -- lifetime --
  init(terminal: Terminal) {
    mTerminal = terminal
  }

  // -- commands --
  func render() {
    mTerminal.writeln("\nselect a pawn [< > enter]")
  }
  
  func handleInput(_ input: [UInt8]) {
    switch input {
    case [Terminal.Code.meta1, Terminal.Code.meta2, Terminal.Code.left]:
      mEvent = .offsetSelection(-1)
    case [Terminal.Code.meta1, Terminal.Code.meta2, Terminal.Code.right]:
      mEvent = .offsetSelection(1)
    case [Terminal.Code.enter]:
      mEvent = .confirmSelection
    default:
      mEvent = nil
    }

    mTerminal.writeln()
  }
  
  // -- event --
  enum Event {
    case offsetSelection(Int)
    case confirmSelection
  }
}

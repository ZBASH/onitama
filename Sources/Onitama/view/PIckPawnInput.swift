final class PickPawnInput {
  private let mTerminal:          Terminal
  private var mSelectedPawnIndex: Int
  private var mIsConfirmed:       Bool = false

  // lifetime
  init(terminal: Terminal) {
    mTerminal          = terminal
    mSelectedPawnIndex = 0
  }

  // command
  func call() {
    mTerminal.write("\nselect a pawn [< > enter]")

    switch mTerminal.read() {
    case 37:
      mSelectedPawnIndex -= 1
    case 39:
      mSelectedPawnIndex += 1
    default:
      break
    }

    mTerminal.write("\n")
  }

  // queries
  var selectedPawnIndex: Int? {
    if mIsConfirmed {
      return mSelectedPawnIndex
    } else {
      return nil
    }
  }
}

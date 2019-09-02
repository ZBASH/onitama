import Onitama

final class PickPawnInput {
  private let mTerminal: Terminal
  private var mSelectedPawnIndex: Int
  private var mIsConfirmed: Bool = false

  // -- lifetime --
  init(terminal: Terminal) {
    mTerminal = terminal
    mSelectedPawnIndex = 0
  }

  // -- commands --
  func call() {
    mTerminal.writeln("\nselect a pawn [< > enter]")

    switch mTerminal.read() {
    case [Terminal.Code.meta1, Terminal.Code.meta2, Terminal.Code.left]:
      if mSelectedPawnIndex > 0 {
        mSelectedPawnIndex -= 1
      }
    case [Terminal.Code.meta1, Terminal.Code.meta2, Terminal.Code.right]:
      if mSelectedPawnIndex < Config.Board.size - 1 {
        mSelectedPawnIndex += 1
      }
    case [Terminal.Code.enter]:
      mIsConfirmed = true
    default:
      break
    }

    mTerminal.writeln()
  }

  // -- queries --
  var selectedPawnIndex: Int {
    return mSelectedPawnIndex
  }

  var confirmedPawnIndex: Int? {
    if mIsConfirmed {
      return mSelectedPawnIndex
    } else {
      return nil
    }
  }
}

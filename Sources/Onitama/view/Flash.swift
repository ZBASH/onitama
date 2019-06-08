final class Flash{
  private var mText:     String?
  private let mTerminal: Terminal

  // lifetime
  init(terminal: Terminal) {
    mTerminal = terminal
  }

  // commands
  func render(error: PendingMove.Error) {
    switch error {
    case .positionWasOccupied(let position):
      mText = "The position \(position) is occupied."
    case .positionOutOfBounds(let position):
      mText = "The position \(position) is out of bounds."
    case .invalidPawnId(let id):
      mText = "There is no pawn with id \(id)."
    case .missingCard:
      mText = "The card is missing."
    case .missingPawnId:
      mText = "The pawn id is missing."
    }
  }

  func draw() {
    if let text = mText {
      mTerminal.write("\n\(text)")
    }
    
    mText = nil
  }
}

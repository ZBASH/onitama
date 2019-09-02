import Onitama

struct Tile {
  private let glyph: Character
  private let foreground: Color
  private let background: Color

  // -- lifetime --
  init(glyph: Character = " ", foreground: Color = .none, background: Color = .none) {
    self.glyph = glyph
    self.foreground = foreground
    self.background = background
  }

  // -- queries --
  func render() -> String {
    return prefix() + String(glyph) + suffix()
  }

  // -- queries/formatting
  private func prefix() -> String {
    return prefixBackground() + prefixForeground()
  }

  private func prefixForeground() -> String {
    switch foreground {
    case .red:
      return "\u{001B}[0;31m"
    case .blue:
      return "\u{001B}[0;34m"
    default:
      return ""
    }
  }

  private func prefixBackground() -> String {
    switch background {
    case .red:
      return "\u{001b}[41;1m"
    case .blue:
      return "\u{001b}[44;1m"
    default:
      return ""
    }
  }

  private func suffix() -> String {
    if foreground == .none && background == .none {
      return ""
    }

    return "\u{001b}[0m"
  }
}

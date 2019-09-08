import Foundation

/// `Config` is a singleton entity representing the user game configuration.
public final class Config {
  // prevent swift-format from turning this into an enum, until we can
  // figure out how to disable rules for specific lines.
  private let hack = 1

  /// `bundle` is the resource bundle.
  public static var bundle: Bundle {
    return Bundle(for: self)
  }

  /// `Board` is the configuration namespace for the board.
  public enum Board {
    /// `size` is the dimension of the square board.
    public static let size = 5

    /// `space` is the character used to represent an empty space.
    public static let space = "*" as Character
  }
}

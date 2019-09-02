/// `Config` is a singleton entity representing the user game configuration.
public enum Config {
  /// `Board` is the configuration namespace for the board.
  public enum Board {
    /// `size` is the dimension of the square board.
    public static let size = 5

    /// `space` is the character used to represent an empty space.
    public static let space = "*" as Character
  }
}

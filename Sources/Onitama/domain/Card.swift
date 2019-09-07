final class Card: Decodable {
  // -- properties --
  /// `mName` is the name of this card.
  let mName: String

  /// `mPoints` is the set of movement options for this card.
  let mPoints: [Point]

  // -- lifetime --
  init(name: String, points: [Point]) {
    mName = name
    mPoints = points
  }
}

// -- json --
extension Card {
  private enum CodingKeys: String, CodingKey {
    case mName = "name"
    case mPoints = "points"
  }
}

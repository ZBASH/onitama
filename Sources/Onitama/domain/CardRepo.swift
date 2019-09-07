import Foundation

final class CardRepo {
  // -- properties --
  let mAll: [Card]

  // -- lifetime --
  init(cards: [Card]) {
    mAll = cards
  }

  // -- factories --
  static func fromFile() throws -> CardRepo {
    return try fromJson(CardData.data)
  }

  static func fromJson(_ json: Data) throws -> CardRepo {
    let decoder = JSONDecoder()
    let result = try decoder.decode(CardsJson.self, from: json)
    return CardRepo(cards: result.cards)
  }

  // -- json --
  private struct CardsJson: Decodable {
    // -- properties --
    let cards: [Card]
  }
}

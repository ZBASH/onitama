import Foundation

final class CardRepo {
  // -- properties --
  let mAll: [Card]

  // -- lifetime --
  init(cards: [Card]) {
    mAll = cards
  }

  // -- factories --
  static func fromJson(_ json: Data) throws -> CardRepo {
    let decoder = JSONDecoder()
    let result = try decoder.decode(CardsJson.self, from: json)
    return CardRepo(cards: result.cards)
  }

  static func fromFile() throws -> CardRepo {
    let url = try jsonUrl()
    let data = try Data(contentsOf: url)
    return try fromJson(data)
  }

  private static func jsonUrl() throws -> URL {
    if let url = Bundle.main.url(forResource: "cards", withExtension: "json") {
      return url
    }

    throw Error.jsonDoesNotExist
  }

  // -- errors --
  enum Error: Swift.Error {
    case jsonDoesNotExist
  }

  // -- json --
  private struct CardsJson: Decodable {
    // -- properties --
    let cards: [Card]
  }
}

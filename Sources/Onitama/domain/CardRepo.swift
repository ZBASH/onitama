import Foundation

final class CardRepo {
  // -- properties --
  private(set) var mAll: [Card]

  // -- lifetime --
  init(cards: [Card]) {
    mAll = cards
  }

  // -- commands --
  func draw(count: Int) -> [Card] {
    mAll.shuffle()
    let cards = mAll[mAll.count - count..<mAll.count]
    mAll.removeLast(count)
    return Array(cards)
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
    if let url = Config.bundle.url(forResource: "cards", withExtension: "json") {
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

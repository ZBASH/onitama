import XCTest

@testable import Onitama

final class CardRepoTests: TestCase {
  func testItLoadsCardsFromJson() {
    let json
      = """
      {
        "cards": [
          {
            "name": "Test",
            "points": [
              { "x": 1, "y": 2 }
            ]
          }
        ]
      }
    """

    let repo: CardRepo
    do {
      repo = try CardRepo.fromJson(json.data(using: .utf8)!)
    } catch {
      XCTFail("Failed to decode json: \(error)")
      return
    }

    let card = repo.mAll[0]
    XCTAssertEqual(card.mName, "Test")
    XCTAssertEqual(
      card.mPoints,
      [
        Point(1, 2),
      ]
    )
  }

  func testItDrawsRandomCards() {
    let repo = CardRepo(
      cards: [
        Card(name: "Monkey", points: []),
        Card(name: "Fox", points: []),
        Card(name: "Dragon", points: []),
        Card(name: "Snake", points: []),
      ]
    )

    let cards1 = repo.draw(count: 2)
    let cards2 = repo.draw(count: 2)

    let names1 = Set(
      cards1.map { c in
        c.mName
      }
    )
    let names2 = Set(
      cards2.map { c in
        c.mName
      }
    )

    XCTAssertEqual(cards1.count, 2)
    XCTAssertEqual(cards2.count, 2)
    XCTAssertEqual(names1.intersection(names2), Set())

    XCTAssertEqual(repo.mAll.count, 0)
  }
}

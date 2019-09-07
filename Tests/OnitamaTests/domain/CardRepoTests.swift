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
}

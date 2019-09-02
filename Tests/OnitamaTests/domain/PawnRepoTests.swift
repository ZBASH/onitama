import XCTest

@testable import Onitama

final class PawnRepoTests: TestCase {
  func testItCreatesPawnsForEachPlayer() {
    let repo = PawnRepo(size: 2)

    XCTAssertEqual(repo.mAll.count, 4)

    var pawn = repo.mAll[0]
    XCTAssertEqual(pawn.color, .red)
    XCTAssertEqual(pawn.position, Point(0, 0))

    pawn = repo.mAll[2]
    XCTAssertEqual(pawn.color, .blue)
    XCTAssertEqual(pawn.position, Point(0, 1))
  }

  func testItCreatesARowOfPawns() {
    let repo = PawnRepo(size: 3)

    XCTAssertEqual(repo.mAll.count, 6)

    var pawn = repo.mAll[1]
    XCTAssertEqual(pawn.color, .red)
    XCTAssertEqual(pawn.position, Point(1, 0))

    pawn = repo.mAll[5]
    XCTAssertEqual(pawn.color, .blue)
    XCTAssertEqual(pawn.position, Point(2, 2))
  }

  func testItFindsAPawnById() {
    let repo = PawnRepo(size: 2)
    repo.mAll[1].capture()

    var pawn = repo.findPawn(byId: -1)
    XCTAssertNil(pawn)

    pawn = repo.findPawn(byId: 0)
    XCTAssertNotNil(pawn)
    XCTAssertEqual(pawn?.isCaptured, false)

    pawn = repo.findPawn(byId: 1)
    XCTAssertNotNil(pawn)
    XCTAssertEqual(pawn?.isCaptured, true)
  }

  func testItFindsLivePlayerPawns() {
    let repo = PawnRepo(size: 3)
    repo.mAll[3].capture()

    let pawns = repo.findLivePawns(byColor: .blue)
    XCTAssertEqual(pawns.count, 2)
    XCTAssert(
      pawns.allSatisfy { pawn in
        pawn.color == .blue
      }
    )
  }

  func testItFindsALivePawnByPlayerAndPosition() {
    let repo = PawnRepo(size: 2)
    repo.mAll[2].capture()

    var pawn = repo.findLivePawn(byColor: .blue, position: Point(3, 2))
    XCTAssertNil(pawn)

    pawn = repo.findLivePawn(byColor: .red, position: Point(0, 1))
    XCTAssertNil(pawn)

    pawn = repo.findLivePawn(byColor: .blue, position: Point(0, 1))
    XCTAssertNil(pawn)

    pawn = repo.findLivePawn(byColor: .blue, position: Point(1, 1))
    XCTAssertNotNil(pawn)
    XCTAssertEqual(pawn?.color, .blue)
    XCTAssertEqual(pawn?.position, Point(1, 1))
  }

  func testItFindsALivePawnByPlayerAndIndex() {
    let repo = PawnRepo(size: 2)
    repo.mAll[0].capture()

    var pawn = repo.findLivePawn(byColor: .red, index: -1)
    XCTAssertNil(pawn)

    pawn = repo.findLivePawn(byColor: .red, index: 2)
    XCTAssertNil(pawn)

    pawn = repo.findLivePawn(byColor: .red, index: 1)
    XCTAssertNil(pawn)

    pawn = repo.findLivePawn(byColor: .red, index: 0)
    XCTAssertNotNil(pawn)
    XCTAssertEqual(pawn?.color, .red)
    XCTAssertEqual(pawn?.position, Point(1, 0))
  }
}

import Onitama

final class PlayerView {
  private let mBuffer: TileBuffer

  // -- lifeitme --
  init(buffer: TileBuffer) {
    mBuffer = buffer
  }

  // -- commands --
  func render(player: Player, isCurrentPlayer: Bool, selectedPawnIndex: Int) {
    for (i, pawn) in player.pawns.enumerated() {
      let isHighlighted = isCurrentPlayer && i == selectedPawnIndex

      let tile = Tile(
        glyph: "#",
        foreground: isHighlighted ? .none : pawn.color,
        background: isHighlighted ? pawn.color : .none
      )

      mBuffer.set(pawn.position, tile: tile)
    }
  }
}

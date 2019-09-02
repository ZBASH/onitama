import Onitama

final class GameView {
  private let mBuffer: TileBuffer
  private let mBoardView: BoardView
  private let mPlayerView: PlayerView

  // -- lifetime --
  init(buffer: TileBuffer) {
    mBuffer = buffer
    mBoardView = BoardView(buffer: mBuffer)
    mPlayerView = PlayerView(buffer: mBuffer)
  }

  // -- commands --
  func render(game: Game, selectedPawnIndex: Int) {
    mBoardView.render(board: game.board)

    mPlayerView.render(
      player: game.currentPlayer,
      isCurrentPlayer: true,
      selectedPawnIndex: selectedPawnIndex
    )

    mPlayerView.render(
      player: game.otherPlayer,
      isCurrentPlayer: false,
      selectedPawnIndex: selectedPawnIndex
    )
  }
}

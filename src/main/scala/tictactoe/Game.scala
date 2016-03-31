package tictactoe

import tictactoe.players.Player
import tictactoe.players.tokens.X
import tictactoe.views.BoardView

case class Game(players: (Player, Player), board: Board, view: BoardView) {
  var state = board
  view.printWelcomeMessage()
  view.format(state)

  def currentPlayer = {
    if (state.currentPlayerMark == X) players._1 else players._2
  }

  def run(): Unit = {
    do {
      view.currentTurn(currentPlayer)
      val move = currentPlayer.getMove(state)
      state = state.play(move)
      view.format(state)
    } while(!state.isComplete)
  }
}

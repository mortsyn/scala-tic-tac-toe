package tictactoe

import tictactoe.players.Player
import tictactoe.players.tokens.X
import tictactoe.views.BoardView

case class Game(players: (Player, Player), board: Board, view: BoardView) {
  var state = board
  view.printWelcomeMessage()
  view.printInstructions()
  view.format(state)

  protected def currentPlayer = if (state.currentPlayerMark == X) players._1 else players._2

  protected def winningPlayer: Option[Player] = {
    if (state.isDraw) {
      return None
    } else if(currentPlayer == players._1) {
      Some(players._2)
    } else {
      Some(players._1)
    }
  }

  def run(): Unit = {
    while(!state.isComplete) {
      view.currentTurn(currentPlayer)
      val move = currentPlayer.getMove(state)
      state = state.play(move)
      view.format(state)
    }

    view.printEndOfGameMessage(winningPlayer)
  }
}

package tictactoe

import tictactoe.players.Player

case class Game(players: (Player, Player), board: Board) {
  var state = board

  protected def currentPlayer = if (state.currentPlayerMark == 'X') players._1 else players._2

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
      val move = currentPlayer.getMove(state)
      state = state.play(move)
    }

  }
}

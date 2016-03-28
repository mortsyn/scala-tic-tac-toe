package tictactoe.views

import tictactoe.Board
import tictactoe.players.Player

case class SimpleView() extends BoardView {

  override def playerTurnText(currentPlayer: Player): String = "It's " + currentPlayer.toString + "'s turn..."

  override def format(board: Board): String = {
    val buffer = new StringBuilder

    board.state.grouped(board.size).foreach(row => {
      buffer.append("| " + row.apply(0).toString + " | " + row.apply(1).toString + " | " + row.apply(2).toString + " |\n")
    })
    buffer.append("\n")

    buffer.toString()
  }
}

package tictactoe.views

import tictactoe.Board
import tictactoe.players.Player

trait BoardView {
  def playerTurnText(currentPlayer: Player): String

  def format(board: Board): String
}

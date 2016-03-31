package tictactoe.views

import tictactoe.Board
import tictactoe.players.Player

trait BoardView {
  val output = System.out

  def printWelcomeMessage(): Unit

  def currentTurn(currentPlayer: Player): Unit

  def format(board: Board): Unit

  def printEndOfGameMessage(currentPlayer: Option[Player]): Unit
}

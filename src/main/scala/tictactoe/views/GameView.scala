package tictactoe.views

import tictactoe.Board
import tictactoe.players.Player

trait GameView {
  val output = System.out

  def printWelcomeMessage(): Unit

  def printInstructions(): Unit

  def currentTurn(currentPlayer: Player): Unit

  def format(board: Board): Unit

  def printEndOfGameMessage(currentPlayer: Option[Player]): Unit
}

package tictactoe.views

import tictactoe.Board
import tictactoe.players.{UnbeatableComputer, Human, Player}

object SimpleView extends BoardView {

  override def printWelcomeMessage(): Unit = output.println("Welcome to Tic Tac Toe! \n")

  override def currentTurn(currentPlayer: Player): Unit = currentPlayer match {
    case human: Human => output.println("Human player, choose a move:\n ")
    case computer: UnbeatableComputer => output.println("Computer is thinking...\n")
  }

  override def format(board: Board) = {
    board.state.grouped(board.size).foreach(row => {
      output.println("| " + row.apply(0).toString + " | " + row.apply(1).toString + " | " + row.apply(2).toString + " |")
    })
    output.println("")
  }

  override def printEndOfGameMessage(winner: Option[Player]): Unit = winner match {
    case Some(value) => endGameMessage(value)
    case None => output.println("Game Over! Draw game")
  }

  def endGameMessage(player: Player): Unit = player match {
    case human: Human => output.print("Game Over! Human wins!")
    case computer: UnbeatableComputer =>  output.println("Game Over! Unbeatable Computer wins!")
  }
}

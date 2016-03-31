package tictactoe

import tictactoe.players.{Player, UnbeatableComputer, Human}
import tictactoe.strategies.TicTacToe

import tictactoe.views.{BoardView, SimpleView}

object Main {

  def createNewGame(players: (Player, Player), view: BoardView): Game = {
    new Game(players, Board(3, TicTacToe), view)
  }

  def main(args: Array[String]): Unit = {
    args match {
      case Array("--solo") => createNewGame((Human(), UnbeatableComputer()), SimpleView).run()
      case Array("--two-player") => createNewGame((Human(), Human()), SimpleView).run()
      case Array("--watch") => createNewGame((UnbeatableComputer(), UnbeatableComputer()), SimpleView).run()
      case _  => new Game((Human(), UnbeatableComputer()), Board(3, TicTacToe), SimpleView).run()
    }
  }
}

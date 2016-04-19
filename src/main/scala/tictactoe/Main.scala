package tictactoe

import tictactoe.players.{Player}
import tictactoe.strategies.TicTacToe


object Main {

  def createNewGame(players: (Player, Player)): Game = {
    new Game(players, Board(3, TicTacToe))
  }

  def main(args: Array[String]): Unit = {

  }
}

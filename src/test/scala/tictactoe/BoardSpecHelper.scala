package tictactoe

import tictactoe.players.tokens.{O, X}
import tictactoe.players.{Player, UnbeatableComputer, Human}
import tictactoe.strategies.TicTacToe

trait BoardSpecHelper {

  def createBoardStateFromMoves(players: (Player, Player), moves: Vector[Int]) = {
    var board = Board(3, TicTacToe, (players._1, players._2))
    moves.foreach(move => {
      board = board.play(move)
    })
    board
  }
}

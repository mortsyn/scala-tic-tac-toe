package tictactoe

import tictactoe.players.tokens.{O, X}
import tictactoe.players.{UnbeatableComputer, Human}
import tictactoe.strategies.TicTacToe

trait BoardSpecHelper {

  def createBoardStateFromMoves(moves: Vector[Int]) = {
    var board = Board(3, TicTacToe, (Human(X), UnbeatableComputer(O)))
    moves.foreach(move => {
      board = board.play(move)
    })
    board
  }
}

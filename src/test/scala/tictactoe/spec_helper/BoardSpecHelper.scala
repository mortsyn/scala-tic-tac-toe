package tictactoe.spec_helper

import tictactoe.board.Board
import tictactoe.players.{Human, Player}
import tictactoe.tokens.{O, X}

trait BoardSpecHelper {
  var player1: Player = Human(X)
  var player2: Player = Human(O)

  def getOtherMark(board: Board): Player = if (board.emptyIndexes.length % 2 == 0) player2 else player1

  def createBoardStateFromMoves(moves: Vector[Int]) = {
    var board = Board(3)
    moves.foreach(move => {
      board = board.play(move, getOtherMark(board))
    })
    board
  }
}

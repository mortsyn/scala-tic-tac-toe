package tictactoe

import tictactoe.players.{Human, Player}

trait BoardSpecHelper {
  val player1 = Human(X)
  val player2 = Human(O)

  def getOtherMark(board: Board): Player = if (board.emptyIndexes.length % 2 == 0) player2 else player1

  def createBoardStateFromMoves(moves: Vector[Int]) = {
    var board = Board(3)
    moves.foreach(move => {
      board = board.play(move, getOtherMark(board))
    })
    board
  }
}

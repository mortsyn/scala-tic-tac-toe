package tictactoe

trait BoardSpecHelper {

  def createBoardStateFromMoves(moves: Vector[Int]) = {
    var board = Board(3, TicTacToe)
    moves.foreach(move => {
      board = board.play(move)
    })
    board
  }
}

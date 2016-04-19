package tictactoe

trait BoardSpecHelper {

  def getOtherMark(board: Board): Char = if(board.state.filter(_ == '_').length % 2 == 0) 'O' else 'X'

  def createBoardStateFromMoves(moves: Vector[Int]) = {
    var board = Board(3)
    moves.foreach(move => {
      board = board.play(move, getOtherMark(board))
    })
    board
  }
}

package tictactoe

case class Human(mark: Token, io: IO = IO()) extends Player {

  override def getMove(board: Board): Int = {
      val move = io.nextInt
      if((board.state apply move) == EMPTY) move else getMove(board)
  }
}

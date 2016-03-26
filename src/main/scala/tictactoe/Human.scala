package tictactoe

case class Human(mark: Token, io: IO = IO()) extends Player {

  private def nextInt: Int = {
    while (!io.input.hasNextInt()) {
      io.input.next()
    }

    return io.input.nextInt()
  }

  override def getMove(board: Board): Int = {
      val move = nextInt
      if((board.state apply move) == EMPTY) move else getMove(board)
  }
}

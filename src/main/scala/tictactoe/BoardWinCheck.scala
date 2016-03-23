package tictactoe

trait BoardWinCheck {

  private def singleColumn[A](groupedBoard: IndexedSeq[IndexedSeq[A]], columnIndex : Int) = {
    for(i <- groupedBoard) yield i(columnIndex)
  }

  private def getColumns(board: Board) = {
    for(columnIndex <- 0 until board.size) yield singleColumn(board.getState.toIndexedSeq, columnIndex)
  }

  private def leftDiagonal(state: IndexedSeq[IndexedSeq[String]], seq: IndexedSeq[String] = Vector()): IndexedSeq[String] = {
    if (state.isEmpty) seq else leftDiagonal(state.tail.map(_.tail), seq :+ state.head.head)
  }

  private def rightDiagonal(state: IndexedSeq[IndexedSeq[String]], seq: IndexedSeq[String] = Vector()): IndexedSeq[String] = {
    if (state.isEmpty) seq else rightDiagonal(state.tail.map(_.dropRight(1)), seq :+ state.head.last)
  }

  private def diagonalSpots(state: IndexedSeq[IndexedSeq[String]]): Vector[IndexedSeq[String]] = {
    Vector(leftDiagonal(state)).++(Vector(rightDiagonal(state)))
  }

  private def aggregateWinningSpots(board: Board) = {
    board.getState.++(getColumns(board)).++(diagonalSpots(board.getState.toIndexedSeq))
  }

  def hasWinner(token: String, board: Board) = {
    val result = aggregateWinningSpots(board).toIndexedSeq.find(_.toSet.size == 1)
    result match {
      case None => false
      case Some(value) => result.get.apply(1) == token
    }
  }
}

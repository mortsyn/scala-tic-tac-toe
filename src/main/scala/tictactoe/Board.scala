package tictactoe

case class Board(size: Int = 3) {
  var cells: IndexedSeq[Object] = IndexedSeq.fill((size * size))(EMPTY)

  def place(space : Int) = cells = cells.updated(space, currentPlayerMark())

  def currentPlayerMark() = if (playerXTurn()) X else O

  def playerXTurn() = cells.filter(_ != EMPTY).length % 2 == 0

  def hasWinner(token: Token) = {
    val result = getWinLines.toIndexedSeq.find(_.toSet.size == 1)
    result match {
      case None => false
      case Some(value) => result.get.apply(1) == token
    }
  }

  private def getWinLines = getRows ++ getColumns ++ diagonalSpots(getRows.toIndexedSeq)

  private def getRows = cells.grouped(size)

  private def getColumns = for(columnIndex <- 0 until size) yield singleColumn(getRows.toIndexedSeq, columnIndex)

  private def singleColumn[A](rows: IndexedSeq[IndexedSeq[A]], columnIndex : Int) = {
    for(row <- rows) yield row(columnIndex)
  }

  private def diagonalSpots[A](state: IndexedSeq[IndexedSeq[A]]): Vector[IndexedSeq[A]] = {
    Vector(leftDiagonal(state)) ++ Vector(rightDiagonal(state))
  }

  private def leftDiagonal[A](state: IndexedSeq[IndexedSeq[A]], seq: IndexedSeq[A] = Vector()): IndexedSeq[A] = {
    if (state.isEmpty) seq else leftDiagonal(state.tail.map(_.tail), seq :+ state.head.head)
  }

  private def rightDiagonal[A](state: IndexedSeq[IndexedSeq[A]], seq: IndexedSeq[A] = Vector()): IndexedSeq[A] = {
    if (state.isEmpty) seq else rightDiagonal(state.tail.map(_.dropRight(1)), seq :+ state.head.last)
  }
}

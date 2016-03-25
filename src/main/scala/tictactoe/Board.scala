package tictactoe

object Board {

  def apply(size: Int): Board = {
    new Board(size, Vector.fill(size*size)(EMPTY))
  }
}

case class Board(size: Int, state: IndexedSeq[Token]) {

  def play(move: Int) = new Board(size, state.updated(move, currentPlayerMark))

  def currentPlayerMark = if (state.filter(_ != EMPTY).length % 2 == 0) X else O

  def emptyIndexes = state.zipWithIndex.filter(_._1 == EMPTY).map(_._2)

  def isComplete = isDraw || isWinner(X) || isWinner(O)

  def isDraw = emptyIndexes.size == 0 && !(isWinner(X) || isWinner(O))

  def isWinner(token: Token) = matchWinningSequence(token, getWinLines.toIndexedSeq.find(_.toSet.size == 1))

  private def matchWinningSequence(token: Token, winningSet: Option[IndexedSeq[Token]]) = winningSet match {
    case None => false
    case Some(value) => winningSet.get.apply(1) == token
  }

  private def getWinLines = getRows ++ getColumns ++ diagonalSpots(getRows.toIndexedSeq)

  private def getRows = state.grouped(size)

  private def getColumns = {
    def singleColumn[A](rows: IndexedSeq[IndexedSeq[A]], columnIndex: Int) = for(row <- rows) yield row(columnIndex)

    for(columnIndex <- 0 until size) yield singleColumn(getRows.toIndexedSeq, columnIndex)
  }

  private def diagonalSpots[A](state: IndexedSeq[IndexedSeq[A]]): IndexedSeq[IndexedSeq[A]] = {
    def leftDiagonal[A](state: IndexedSeq[IndexedSeq[A]], seq: IndexedSeq[A] = Vector()): IndexedSeq[A] = {
      if (state.isEmpty) seq else leftDiagonal(state.tail.map(_.tail), seq :+ state.head.head)
    }

    def rightDiagonal[A](state: IndexedSeq[IndexedSeq[A]], seq: IndexedSeq[A] = Vector()): IndexedSeq[A]  = {
      if (state.isEmpty) seq else rightDiagonal(state.tail.map(_.dropRight(1)), seq :+ state.head.last)
    }

    Vector(leftDiagonal(state), rightDiagonal(state))
  }
}

package tictactoe.board

import tictactoe.players.Player

object Board {

  def apply(size: Int): Board = {
    new Board(size, Vector.fill(size*size)(None))
  }
}

case class Board(size: Int, state: IndexedSeq[Option[Player]]) {

  def play(move: Int, player: Player) = new Board(size, state.updated(move-1, Some(player)))

  def emptyIndexes = state.zipWithIndex.filter(_._1 == None).map(_._2 + 1)

  def getWinningLines = getRows ++ getColumns ++ getDiagonals

  def getRows = state.grouped(this.size)

  def getColumns = {
    def singleColumn[A](rows: IndexedSeq[IndexedSeq[A]], columnIndex: Int) = for(row <- rows) yield row(columnIndex)

    for(columnIndex <- 0 until this.size) yield singleColumn(getRows.toIndexedSeq, columnIndex)
  }

  def getDiagonals = {
    def leftDiagonal[Char](state: IndexedSeq[IndexedSeq[Char]], seq: IndexedSeq[Char] = Vector()): IndexedSeq[Char] = {
      if (state.isEmpty) seq else leftDiagonal(state.tail.map(_.tail), seq :+ state.head.head)
    }

    def rightDiagonal[Char](state: IndexedSeq[IndexedSeq[Char]], seq: IndexedSeq[Char] = Vector()): IndexedSeq[Char] = {
      if (state.isEmpty) seq else rightDiagonal(state.tail.map(_.dropRight(1)), seq :+ state.head.last)
    }

    Vector(leftDiagonal(getRows.toIndexedSeq), rightDiagonal(getRows.toIndexedSeq))
  }
}

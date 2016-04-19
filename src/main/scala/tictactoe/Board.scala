package tictactoe

import tictactoe.strategies.Strategy

object Board {

  def apply(size: Int, strategy: Strategy): Board = {
    new Board(size, Vector.fill(size*size)('_'), strategy)
  }
}

case class Board(size: Int, state: IndexedSeq[Char], strategy: Strategy) {

  def play(move: Int) = new Board(size, state.updated(move-1, currentPlayerMark), strategy)

  def currentPlayerMark = if (state.filter(_ != '_').length % 2 == 0) 'X' else 'O'

  def emptyIndexes = state.zipWithIndex.filter(_._1 == '_').map(_._2 + 1)

  def isComplete = isDraw || strategy.winner('X', this) || strategy.winner('O', this)

  def isDraw = emptyIndexes.size == 0 && !(strategy.winner('X', this) || strategy.winner('O', this))

  def isWinner(token: Char) = strategy.winner(token, this)

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

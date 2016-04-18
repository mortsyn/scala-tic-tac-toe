package tictactoe

import tictactoe.players.tokens.{EMPTY, O, X, Token}
import tictactoe.strategies.Strategy

object Board {

  def apply(size: Int, strategy: Strategy): Board = {
    new Board(size, Vector.fill(size*size)(EMPTY), strategy)
  }
}

case class Board(size: Int, state: IndexedSeq[Token], strategy: Strategy) {

  def play(move: Int) = new Board(size, state.updated(move-1, currentPlayerMark), strategy)

  def currentPlayerMark = if (state.filter(_ != EMPTY).length % 2 == 0) X else O

  def emptyIndexes = state.zipWithIndex.filter(_._1 == EMPTY).map(_._2 + 1)

  def isComplete = isDraw || strategy.winner(X, this) || strategy.winner(O, this)

  def isDraw = emptyIndexes.size == 0 && !(strategy.winner(X, this) || strategy.winner(O, this))

  def isWinner(token: Token) = strategy.winner(token, this)

  def getWinningLines = getRows ++ getColumns ++ getDiagonals

  def getRows = state.grouped(this.size)

  def getColumns = {
    def singleColumn[A](rows: IndexedSeq[IndexedSeq[A]], columnIndex: Int) = for(row <- rows) yield row(columnIndex)

    for(columnIndex <- 0 until this.size) yield singleColumn(getRows.toIndexedSeq, columnIndex)
  }

  def getDiagonals = {
    def leftDiagonal[Token](state: IndexedSeq[IndexedSeq[Token]], seq: IndexedSeq[Token] = Vector()): IndexedSeq[Token] = {
      if (state.isEmpty) seq else leftDiagonal(state.tail.map(_.tail), seq :+ state.head.head)
    }

    def rightDiagonal[Token](state: IndexedSeq[IndexedSeq[Token]], seq: IndexedSeq[Token] = Vector()): IndexedSeq[Token] = {
      if (state.isEmpty) seq else rightDiagonal(state.tail.map(_.dropRight(1)), seq :+ state.head.last)
    }

    Vector(leftDiagonal(getRows.toIndexedSeq), rightDiagonal(getRows.toIndexedSeq))
  }
}

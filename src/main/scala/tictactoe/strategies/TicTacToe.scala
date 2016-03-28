package tictactoe.strategies

import tictactoe.Board
import tictactoe.players.tokens.Token

object TicTacToe extends Strategy {

  override def winner(token: Token, board: Board) = matchWinningSequence(token, findWinningSets(board))

  private def matchWinningSequence(token: Token, winningSet: Option[IndexedSeq[Token]]) = winningSet match {
    case None => false
    case Some(value) => winningSet.get.apply(1) == token
  }

  private def findWinningSets(board: Board) = getWinLines(board).toIndexedSeq.find(_.toSet.size == 1)

  private def getWinLines(board: Board) = getRows(board) ++ getColumns(board) ++ diagonalSpots(getRows(board).toIndexedSeq)

  private def getRows(board: Board) = board.state.grouped(board.size)

  private def getColumns(board: Board) = {
    def singleColumn[A](rows: IndexedSeq[IndexedSeq[A]], columnIndex: Int) = for(row <- rows) yield row(columnIndex)

    for(columnIndex <- 0 until board.size) yield singleColumn(getRows(board).toIndexedSeq, columnIndex)
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


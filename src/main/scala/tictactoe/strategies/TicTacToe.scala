package tictactoe.strategies

import tictactoe.Board

object TicTacToe extends Strategy {

  override def winner(token: Char, board: Board) = matchWinningSequence(token, findWinningSets(board))

  private def matchWinningSequence(token: Char, winningSet: Option[IndexedSeq[Char]]) = winningSet match {
    case None => false
    case Some(value) => winningSet.get.apply(1) == token
  }

  private def findWinningSets(board: Board) = board.getWinningLines.toIndexedSeq.find(_.toSet.size == 1)
}


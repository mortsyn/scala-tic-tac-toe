package tictactoe.strategies

import tictactoe.Board
import tictactoe.players.tokens.Token

object TicTacToe extends Strategy {

  override def winner(token: Token, board: Board) = matchWinningSequence(token, findWinningSets(board))

  private def matchWinningSequence(token: Token, winningSet: Option[IndexedSeq[Token]]) = winningSet match {
    case None => false
    case Some(value) => winningSet.get.apply(1) == token
  }

  private def findWinningSets(board: Board) = board.getWinningLines.toIndexedSeq.find(_.toSet.size == 1)
}


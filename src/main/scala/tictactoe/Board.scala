package tictactoe

import tictactoe.players.Player
import tictactoe.players.tokens.{EMPTY, O, X, Token}
import tictactoe.strategies.Strategy

object Board {

  def apply(size: Int, strategy: Strategy): Board = {
    new Board(size, Vector.fill(size*size)(EMPTY), strategy)
  }
}

case class Board(size: Int, state: IndexedSeq[Token], strategy: Strategy) {

  def play(move: Int) = new Board(size, state.updated(move, currentPlayerMark), strategy)

  def currentPlayerMark = if (state.filter(_ != EMPTY).length % 2 == 0) X else O

  def emptyIndexes = state.zipWithIndex.filter(_._1 == EMPTY).map(_._2)

  def isComplete = isDraw || strategy.winner(X, this) || strategy.winner(O, this)

  def isDraw = emptyIndexes.size == 0 && !(strategy.winner(X, this) || strategy.winner(O, this))

  def isWinner(token: Token) = strategy.winner(token, this)
}

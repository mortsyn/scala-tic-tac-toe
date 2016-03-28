package tictactoe

import tictactoe.players.Player
import tictactoe.players.tokens.{EMPTY, O, X, Token}
import tictactoe.strategies.Strategy

object Board {

  def apply(size: Int, strategy: Strategy, players: (Player, Player)): Board = {
    new Board(size, Vector.fill(size*size)(EMPTY), strategy, players)
  }
}

case class Board(size: Int, state: IndexedSeq[Token], strategy: Strategy, players: (Player, Player)) {

  def play(move: Int) = new Board(size, state.updated(move, currentPlayerMark), strategy, (inactivePlayer, currentPlayer))

  def currentPlayerMark = if (state.filter(_ != EMPTY).length % 2 == 0) X else O

  def currentPlayer = if(currentPlayerMark == players._1.mark) players._1 else players._2

  def inactivePlayer: Player = if(currentPlayerMark == players._1.mark) players._2 else players._1

  def emptyIndexes = state.zipWithIndex.filter(_._1 == EMPTY).map(_._2)

  def isComplete = isDraw || strategy.winner(X, this) || strategy.winner(O, this)

  def isDraw = emptyIndexes.size == 0 && !(strategy.winner(X, this) || strategy.winner(O, this))

  def isWinner(token: Token) = strategy.winner(token, this)
}

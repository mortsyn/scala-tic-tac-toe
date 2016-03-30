package tictactoe.players

import tictactoe.players.tokens.{O, X, Token}
import tictactoe.Board

sealed trait Minimax {

  def minimax(state: Board, depth: Int): Int = {
    if(state.isComplete) return scoreOf(state, depth)

    moveOrScore(depth, getBestScore(state, evaluateSuccessor(state, depth)))
  }

  private def scoreOf(state: Board, depth: Int) = {
    if (state.isWinner(X)) {
      10 - depth
    } else if (state.isWinner(O)) {
      depth - 10
    } else {
      0
    }
  }

  private def evaluateSuccessor(state: Board, depth: Int): IndexedSeq[(Int, Int)] = {
    for(move <- state.emptyIndexes) yield move -> minimax(state.play(move), depth+1)
  }

  private def getBestScore(state: Board, scores: IndexedSeq[(Int, Int)]) = {
    if(state.currentPlayerMark == X) {
      scores.maxBy(_._2)
    } else {
      scores.minBy(_._2)
    }
  }

  private def moveOrScore(depth: Int, bestScore: (Int, Int)) = {
    if(depth == 0) {
      bestScore._1
    } else {
      bestScore._2
    }
  }
}

case class UnbeatableComputer() extends Player with Minimax {
  override def getMove(board: Board): Int = minimax(board.copy(), 0)
}

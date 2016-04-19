package tictactoe.players

import tictactoe.{Game}

sealed trait Minimax {

  def minimax(state: Game, depth: Int): Int = {
    if(state.isOver) return scoreOf(state, depth)

    moveOrScore(depth, getBestScore(state, evaluateSuccessor(state, depth)))
  }

  private def scoreOf(state: Game, depth: Int) = {
    if (state.isGameWinner(state.players._1.mark)) {
      10 - depth
    } else if (state.isGameWinner(state.players._2.mark)) {
      depth - 10
    } else {
      0
    }
  }

  private def evaluateSuccessor(state: Game, depth: Int): IndexedSeq[(Int, Int)] = {
    for(move <- state.board.emptyIndexes) yield move -> minimax(state.makeMove(move), depth+1)
  }

  private def getBestScore(state: Game, scores: IndexedSeq[(Int, Int)]) = {
    if(state.activePlayer.mark == state.players._1.mark) {
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

case class UnbeatableComputer(mark: Char) extends Player with Minimax {
  def getMove(game: Game): Int = minimax(game.copy(), 0)
}

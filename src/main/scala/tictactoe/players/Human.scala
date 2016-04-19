package tictactoe.players

import tictactoe.{Board}

case class Human(mark: Char) extends Player {

  def isValid(board: Board, move: Int): Boolean = {
    (move >= 1 && move <= board.state.size) && (board.emptyIndexes contains move)
  }
}

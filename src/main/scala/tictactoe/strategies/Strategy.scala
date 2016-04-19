package tictactoe.strategies

import tictactoe.Board

trait Strategy {
  def winner(token: Char, board: Board): Boolean
}

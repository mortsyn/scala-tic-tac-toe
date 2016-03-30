package tictactoe.players

import tictactoe.Board

trait Player {
  def getMove(board: Board): Int
}

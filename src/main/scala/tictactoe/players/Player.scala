package tictactoe.players

import tictactoe.Board
import tictactoe.players.tokens.Token

trait Player {
  val mark: Token
  def getMove(board: Board): Int
}

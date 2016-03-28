package tictactoe.strategies

import tictactoe.Board
import tictactoe.players.tokens.Token

trait Strategy {
  def winner(token: Token, board: Board): Boolean
}

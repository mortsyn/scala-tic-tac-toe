package tictactoe.players

import tictactoe.game.Game
import tictactoe.tokens.Token

trait Player {
  def mark: Token
  def getMove(game: Game): Int
}

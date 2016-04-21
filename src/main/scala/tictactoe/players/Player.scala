package tictactoe.players

import tictactoe.{Game, Token}

trait Player {
  def mark: Token
  def getMove(game: Game): Int
}

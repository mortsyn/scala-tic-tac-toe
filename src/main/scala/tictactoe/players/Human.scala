package tictactoe.players

import tictactoe.io.UI
import tictactoe.game.Game
import tictactoe.tokens.Token

case class Human(mark: Token, ui: UI = new UI()) extends Player {
  override def getMove(game: Game): Int = ui.getPlayerMove(game)

  override def toString = "Human Player"
}

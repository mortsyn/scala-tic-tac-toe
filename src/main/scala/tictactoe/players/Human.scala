package tictactoe.players

import tictactoe.{UI, Game, Token}

case class Human(mark: Token, ui: UI = new UI()) extends Player {
  override def getMove(game: Game): Int = ui.getPlayerMove(game)
}

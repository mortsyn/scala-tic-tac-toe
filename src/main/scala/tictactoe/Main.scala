package tictactoe

import tictactoe.io.UI
import tictactoe.game.Game

object Main {

  def main(args: Array[String]): Unit = {
    run(new UI(), Game())
  }

  def run(ui: UI, game: Game): Unit = {
    ui.update(game)
    if(!game.isOver) run(ui, game.makeMove(game.activePlayer.getMove(game)))
  }

}

package tictactoe.players

import java.util.Scanner
import org.scalatest.{FunSpec, Matchers}
import tictactoe.io.UI
import tictactoe._
import tictactoe.game.Game
import tictactoe.tokens.X

class HumanTest extends FunSpec with Matchers {

  describe("a human player") {

    it("returns a valid board move") {
      val ui = new UI(new Scanner("1 5"))
      val game = Game(ui)
      val human = Human(X, ui)

      human.getMove(game) should equal(5)
    }

    it("can be represented as a string") {
      val ui = new UI(new Scanner("1 5"))
      val game = Game(ui)
      val human = Human(X, ui)

      human.toString should equal("Human Player")
    }
  }
}

package tictactoe.players

import org.scalatest.{FunSpec, Matchers}
import tictactoe.X

class HumanTest extends FunSpec with Matchers {

  describe("a human player") {

    it("should have a mark represented as a string") {
      val human = Human(X)

      human.toString() should equal("Human Player")
    }
  }
}

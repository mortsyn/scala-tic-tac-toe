package tictactoe.players

import org.scalatest.{FunSpec, Matchers}

class HumanTest extends FunSpec with Matchers {

  describe("a human player") {

    it("should have a mark") {
      val human = Human('X')

      human.mark should equal('X')
    }
  }
}

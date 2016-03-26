package tictactoe

import java.util.Scanner

import org.scalatest.{FunSpec, Matchers}

class HumanTest extends FunSpec with Matchers {

  describe("a human player") {

    it("should return us a move from input") {
      val human = Human(X, IO(new Scanner("1")))

      human.getMove(Board(3)) should equal(1)
    }

    it("should not return moves that are currently on the board") {
      val board = Board(3).play(0)
      val human = Human(X, IO(new Scanner("0 2")))

      human.getMove(board) should equal(2)
    }

    it("should ignore strings and other cruft") {
      val board = Board(3).play(0)
      val human = Human(X, IO(new Scanner("0 sadfj adsf asdfjasdf 8")))

      human.getMove(board) should equal(8)
    }
  }
}

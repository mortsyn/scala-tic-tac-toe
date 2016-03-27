package tictactoe

import org.scalatest.{FunSpec, Matchers}
import java.util.Scanner

class IOTest extends FunSpec with Matchers {

  describe("input") {
    it("should ignore strings and other cruft") {
      val board = Board(3, TicTacToe).play(0)
      val human = Human(X, IO(new Scanner("0 sadfj adsf asdfjasdf 8")))

      human.getMove(board) should equal(8)
    }
  }
}

package tictactoe.players

import java.util.Scanner

import org.scalatest.{FunSpec, Matchers}
import tictactoe.strategies.TicTacToe
import tictactoe.{Board}

class HumanTest extends FunSpec with Matchers {

  describe("a human player") {

    it("should return us a move from input") {
      val human = Human(new Scanner("1 2 8"))

      human.getMove(Board(3, TicTacToe)) should equal(1)
    }

    it("should not return moves that are currently on the board") {
      val human = Human(new Scanner("2 8"))
      val board = Board(3, TicTacToe).play(0)

      human.getMove(board) should equal(2)
    }

    it("should not return moves that are not greater than the board size") {
      val human = Human(new Scanner("10 8 3 4"))
      val board = Board(3, TicTacToe).play(0)

      human.getMove(board) should equal(8)
    }

    it("should ignore strings and other cruft") {
      val board = Board(3, TicTacToe).play(0)
      val human = Human(new Scanner("asdf asdf sdfj 8"))

      human.getMove(board) should equal(8)
    }
  }
}

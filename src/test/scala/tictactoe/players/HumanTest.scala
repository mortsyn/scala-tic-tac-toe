package tictactoe.players

import java.io.ByteArrayInputStream

import org.scalatest.{FunSpec, Matchers}
import tictactoe.strategies.TicTacToe
import tictactoe.{Board}

class HumanTest extends FunSpec with Matchers {

  describe("a human player") {

    it("should return us a move from input") {
      System.setIn(new ByteArrayInputStream("1 2 8".getBytes()))
      val human = Human()

      human.getMove(Board(3, TicTacToe)) should equal(1)
    }

    it("should not return moves that are currently on the board") {
      System.setIn(new ByteArrayInputStream("2 8".getBytes()))
      val board = Board(3, TicTacToe).play(0)
      val human = Human()

      human.getMove(board) should equal(2)
    }

    it("should not return moves that are not greater than the board size") {
      System.setIn(new ByteArrayInputStream("10 8 3 4".getBytes()))
      val board = Board(3, TicTacToe).play(0)
      val human = Human()

      human.getMove(board) should equal(8)
    }

    it("should ignore strings and other cruft") {
      System.setIn(new ByteArrayInputStream("asdf asdf sdfj 8".getBytes()))
      val board = Board(3, TicTacToe).play(0)
      val human = Human()

      human.getMove(board) should equal(8)
    }
  }
}

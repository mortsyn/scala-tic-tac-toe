package tictactoe.players

import java.util.Scanner

import org.scalatest.{FunSpec, Matchers}
import tictactoe.players.tokens.{O, X}
import tictactoe.strategies.TicTacToe
import tictactoe.{Board, IO}

class HumanTest extends FunSpec with Matchers {

  describe("a human player") {

    it("should return us a move from input") {
      val human = Human(IO(new Scanner("1")))

      human.getMove(Board(3, TicTacToe)) should equal(1)
    }

    it("should not return moves that are currently on the board") {
      val board = Board(3, TicTacToe).play(0)
      val human = Human(IO(new Scanner("0 2")))

      human.getMove(board) should equal(2)
    }
  }
}

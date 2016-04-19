package tictactoe.strategies

import org.scalatest.{FunSpec, Matchers}
import tictactoe.{Board, BoardSpecHelper}

class TicTacToeTest extends FunSpec with Matchers with BoardSpecHelper {
  val strategy = TicTacToe

  describe("tic tac toe winning strategy") {

    describe("matching a winning token with a board state") {

      it("should be false on a fresh board") {
        val board = Board(3, TicTacToe)

        strategy.winner('X', board) should equal(false)
      }

      it("should be false whenever the game is in progress") {
        val board = createBoardStateFromMoves(Vector(1, 3, 6, 9))

        strategy.winner('X', board) should equal(false)
      }

      it("should be true when there is a horizontal match for 'X'") {
        val board = createBoardStateFromMoves(Vector(1, 5, 2, 6, 3))

        strategy.winner('X', board) should equal(true)
      }

      it("should be true when there is a horizontal match for 'O'") {
        val board = createBoardStateFromMoves(Vector(1, 4, 8, 5, 3, 6))

        strategy.winner('O', board) should equal(true)
      }

      it("should be true if there is a vertical match for 'X'") {
        val board = createBoardStateFromMoves(Vector(1, 5, 4, 6, 7))

        strategy.winner('X', board) should equal(true)
      }

      it("should be true if there is a vertical match for 'O'") {
        val board = createBoardStateFromMoves(Vector(1, 3, 2, 6, 7, 9))

        strategy.winner('O', board) should equal(true)
      }

      it("should be true if there is a left diagonal match for 'X'") {
        val board = createBoardStateFromMoves(Vector(1, 3, 5, 4, 9))

        strategy.winner('X', board) should equal(true)
      }

      it("should be true if there is a right diagonal match for 'O'") {
        val board = createBoardStateFromMoves(Vector(1, 3, 2, 5, 6, 7))

        strategy.winner('O', board) should equal(true)
      }
    }
  }
}

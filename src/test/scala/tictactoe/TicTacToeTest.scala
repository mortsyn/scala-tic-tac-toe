package tictactoe

import org.scalatest.{Matchers, FunSpec}

class TicTacToeTest extends FunSpec with Matchers with BoardSpecHelper {
  val strategy = TicTacToe

  describe("tic tac toe winning strategy") {

    describe("matching a winning token with a board state") {

      it("should be false on a fresh board") {
        val board = Board(3, TicTacToe, (Human(X), UnbeatableComputer(O)))

        strategy.winner(X, board) should equal(false)
      }

      it("should be false whenever the game is in progress") {
        val board = createBoardStateFromMoves(Vector(0, 2, 5, 8))

        strategy.winner(X, board) should equal(false)
      }

      it("should be true when there is a horizontal match for X") {
        val board = createBoardStateFromMoves(Vector(0, 4, 1, 5, 2))

        strategy.winner(X, board) should equal(true)
      }

      it("should be true when there is a horizontal match for O") {
        val board = createBoardStateFromMoves(Vector(0, 3, 7, 4, 2, 5))

        strategy.winner(O, board) should equal(true)
      }

      it("should be true if there is a vertical match for X") {
        val board = createBoardStateFromMoves(Vector(0, 4, 3, 7, 6))

        strategy.winner(X, board) should equal(true)
      }

      it("should be true if there is a vertical match for O") {
        val board = createBoardStateFromMoves(Vector(0, 2, 1, 5, 6, 8))

        strategy.winner(O, board) should equal(true)
      }

      it("should be true if there is a left diagonal match for X") {
        val board = createBoardStateFromMoves(Vector(0, 2, 4, 3, 8))

        strategy.winner(X, board) should equal(true)
      }

      it("should be true if there is a right diagonal match for O") {
        val board = createBoardStateFromMoves(Vector(0, 2, 1, 4, 5, 6))

        strategy.winner(O, board) should equal(true)
      }
    }
  }
}

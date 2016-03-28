package tictactoe

import org.scalatest._
import tictactoe.players.tokens.{O, X}
import tictactoe.players.{UnbeatableComputer, Human}
import tictactoe.strategies.TicTacToe

class BoardTest extends FunSpec with Matchers with BoardSpecHelper {

  describe("a game board") {

    it("should be a vector of empty spaces with specified size^2") {
      Board(3, TicTacToe, (Human(X), UnbeatableComputer(O))).emptyIndexes.size should equal(9)
    }

    it("can get the indexes of empty spaces") {
      val board = createBoardStateFromMoves(Vector(2))

      board.emptyIndexes should equal(IndexedSeq(0, 1, 3, 4, 5, 6, 7, 8))
    }

    describe("making moves") {

      it("starting mark should be X") {
        Board(3, TicTacToe, (Human(X), UnbeatableComputer(O))).currentPlayerMark should equal(X)
      }

      it("should insert token X on odd moves") {
        val move = 2

        Board(3, TicTacToe, (Human(X), UnbeatableComputer(O))).play(move).state apply move should equal(X)
      }

      it("should insert token O on even moves") {
        val board = createBoardStateFromMoves(Vector(3, 4))

        board.state apply 4 should equal(O)
      }
    }

    describe("end of game") {

      it("should be finished if the board is a draw") {
        val board = createBoardStateFromMoves(Vector(0, 1, 2, 3, 5, 4, 6, 8, 7))

        assert(board.isComplete)
      }

      it("should be finished if X won") {
        val board = createBoardStateFromMoves(Vector(0, 4, 1, 5, 2))

        assert(board.isComplete)
      }

      it("should be finished if O won") {
        val board = createBoardStateFromMoves(Vector(0, 3, 7, 4, 2, 5))

        assert(board.isComplete)
      }
    }
  }
}

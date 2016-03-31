package tictactoe

import org.scalatest._
import tictactoe.players.tokens.{O, X}
import tictactoe.players.{UnbeatableComputer, Human}
import tictactoe.strategies.TicTacToe

class BoardTest extends FunSpec with Matchers with BoardSpecHelper {

  describe("a game board") {

    it("should be a vector of empty spaces with specified size^2") {
      Board(3, TicTacToe).emptyIndexes.size should equal(9)
    }

    it("can get the indexes of empty spaces") {
      val board = createBoardStateFromMoves(Vector(2))

      board.emptyIndexes should equal(IndexedSeq(1, 3, 4, 5, 6, 7, 8, 9))
    }

    describe("making moves") {

      it("starting mark should be X") {
        Board(3, TicTacToe).currentPlayerMark should equal(X)
      }

      it("should insert token X on odd moves") {
        val move = 2

        Board(3, TicTacToe).play(move).state apply move-1 should equal(X)
      }

      it("should insert token O on even moves") {
        val move = 4
        val board = createBoardStateFromMoves(Vector(3, 4))

        board.state apply move-1 should equal(O)
      }
    }

    describe("end of game") {

      it("should be finished if the board is a draw") {
        val board = createBoardStateFromMoves(Vector(1, 2, 3, 4, 6, 5, 7, 9, 8))

        assert(board.isComplete)
      }

      it("should be finished if X won") {
        val board = createBoardStateFromMoves(Vector(1, 5, 2, 6, 3))

        assert(board.isComplete)
      }

      it("should be finished if O won") {
        val board = createBoardStateFromMoves(Vector(1, 4, 8, 5, 3, 6))

        assert(board.isComplete)
      }
    }
  }
}

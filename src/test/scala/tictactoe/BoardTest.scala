package tictactoe

import org.scalatest._
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

    it("can insert moves on the board") {
      val move = 4
      val board = createBoardStateFromMoves(Vector(3, 4))

      board.state apply move-1 should equal('O')
    }

    it("includes the each row in the winning lines") {
      val winningLines = createBoardStateFromMoves(Vector(1, 5, 9)).getWinningLines.toIndexedSeq

      winningLines should contain (Vector('X', '_', '_'))
      winningLines should contain (Vector('_', 'O', '_'))
      winningLines should contain (Vector('_', '_', 'X'))
    }

    it("includes the columns in the winning lines") {
      val winningLines = createBoardStateFromMoves(Vector(1, 4, 7, 2, 5, 8, 3, 6, 9)).getWinningLines.toIndexedSeq

      winningLines should contain (Vector('X', 'O', 'X'))
      winningLines should contain (Vector('O', 'X', 'O'))
      winningLines should contain (Vector('X', 'O', 'X'))
    }

    it("includes the diagonal board spots in the winning lines") {
      val winningLines = createBoardStateFromMoves(Vector(1, 5, 9, 3, 7)).getWinningLines.toIndexedSeq

      winningLines should contain (Vector('X', 'O', 'X'))
      winningLines should contain (Vector('O', 'O', 'X'))
    }

    describe("end of game") {

      it("should be finished if the board is a draw") {
        val board = createBoardStateFromMoves(Vector(1, 2, 3, 4, 6, 5, 7, 9, 8))

        assert(board.isComplete)
      }

      it("should be finished if 'X' won") {
        val board = createBoardStateFromMoves(Vector(1, 5, 2, 6, 3))

        assert(board.isComplete)
      }

      it("should be finished if 'O' won") {
        val board = createBoardStateFromMoves(Vector(1, 4, 8, 5, 3, 6))

        assert(board.isComplete)
      }
    }
  }
}

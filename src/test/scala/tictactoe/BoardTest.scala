package tictactoe

import org.scalatest._
import tictactoe.players.Human

class BoardTest extends FunSpec with Matchers with BoardSpecHelper {

  describe("a game board") {

    it("should be a vector of empty spaces with specified size^2") {
      Board(3).emptyIndexes.size should equal(9)
    }

    it("can get the indexes of empty spaces") {
      val board = createBoardStateFromMoves(Vector(2))

      board.emptyIndexes should equal(IndexedSeq(1, 3, 4, 5, 6, 7, 8, 9))
    }

    it("can insert moves on the board") {
      val move = 4
      val player = Human(X)
      val board = Board(3).play(move, player)

      board.state apply move-1 should equal(Some(player))
    }

    it("includes the each row in the winning lines") {
      val winningLines = createBoardStateFromMoves(Vector(1, 5, 9)).getWinningLines.toIndexedSeq

      winningLines should contain (Vector(Some(player1), None, None))
      winningLines should contain (Vector(None, Some(player2), None))
      winningLines should contain (Vector(None, None, Some(player1)))
    }

    it("includes the columns in the winning lines") {
      val winningLines = createBoardStateFromMoves(Vector(1, 4, 7, 2, 5, 8, 3, 6, 9)).getWinningLines.toIndexedSeq

      winningLines should contain (Vector(Some(player1), Some(player2), Some(player1)))
      winningLines should contain (Vector(Some(player2), Some(player1), Some(player2)))
      winningLines should contain (Vector(Some(player1), Some(player2), Some(player1)))
    }

    it("includes the diagonal board spots in the winning lines") {
      val winningLines = createBoardStateFromMoves(Vector(1, 5, 9, 3, 7)).getWinningLines.toIndexedSeq

      winningLines should contain (Vector(Some(player1), Some(player2), Some(player1)))
      winningLines should contain (Vector(Some(player2), Some(player2), Some(player1)))
    }
  }
}

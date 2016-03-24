package tictactoe

import org.scalatest._

class BoardTest extends FunSpec with Matchers {

  def makeMovesInSequence(board: Board, ints: Vector[Int]) = for(move <- ints) board.place(move)

  describe("a game board") {

    it ("should be a vector of empty spaces with specified size^2") {
      val size = 3
      val board = Board(size)

      board.cells.count(_ == EMPTY) should equal(9)
    }

    it("should insert token \"X\" on odd moves") {
      val board = Board(3)
      val input = 4

      board.place(input)

      board.cells apply input should equal(X)
    }

    it("should insert token \"O\" on even moves") {
      val board = Board(3)
      val input = 5

      board.place(4)
      board.place(input)

      board.cells apply input should equal(O)
    }

    describe("game winning conditions") {

      it("should be false when there is a fresh board") {
        val board = new Board(3)

        assert(board.hasWinner(X) == false)
      }

      it("should be false whenever the game is in progress") {
        val board = new Board(3)

        makeMovesInSequence(board, Vector(0, 2, 5, 8))

        board.hasWinner(X) should equal(false)
      }

      it("should be true when there is a horizontal match for \"X\"") {
        val board = new Board()

        makeMovesInSequence(board, Vector(0, 4, 1, 5, 2))

        board.hasWinner(X) should equal(true)
      }

      it("should be true when there is a horizontal match for \"O\"") {
        val board = new Board()

        makeMovesInSequence(board, Vector(0, 3, 7, 4, 2, 5))

        board.hasWinner(O) should equal(true)
      }

      it("should be true if there is a vertical match for \"X\"") {
        val board = new Board()

        makeMovesInSequence(board, Vector(0, 4, 3, 7, 6))

        board.hasWinner(X) should equal(true)
      }

      it("should be true if there is a vertical match for \"O\"") {
        val board = new Board()

        makeMovesInSequence(board, Vector(0, 2, 1, 5, 6, 8))

        board.hasWinner(O) should equal(true)
      }

      it("should be true if there is a left diagonal match for \"X\"") {
        val board = new Board()

        makeMovesInSequence(board, Vector(0, 2, 4, 3, 8))

        board.hasWinner(X) should equal(true)
      }

      it("should be true if there is a right diagonal match for \"O\"") {
        val board = new Board()

        makeMovesInSequence(board, Vector(0, 2, 1, 4, 5, 6))

        board.hasWinner(O) should equal(true)
      }
    }
  }
}

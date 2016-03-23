package tictactoe

import org.scalatest.{Matchers, FunSpec}

class GameTest extends FunSpec with Matchers {

  def makeMovesInSequence(board: Board, ints: Vector[Int]) = for(move <- ints) board.place(move)

  describe("Board Win Conditions Check") {

    it("should be false when there is a fresh board") {
      val game = new Game()

      assert(game.hasWinner("X", new Board(3)) == false)
    }

    it("should be false whenever the game is in progress") {
      val game = new Game()
      val board = new Board(3)

      makeMovesInSequence(board, Vector(0, 2, 5, 8))

      game.hasWinner("X", board) should equal(false)
    }

    it("should be true when there is a horizontal match for \"X\"") {
      val game = new Game()
      val board = new Board()

      makeMovesInSequence(board, Vector(0, 4, 1, 5, 2))

      game.hasWinner("X", board) should equal(true)
    }

    it("should be true when there is a horizontal match for \"O\"") {
      val game = new Game()
      val board = new Board()

      makeMovesInSequence(board, Vector(0, 3, 7, 4, 2, 5))

      game.hasWinner("O", board) should equal(true)
    }

    it("should be true if there is a vertical match for \"X\"") {
      val game = new Game()
      val board = new Board()

      makeMovesInSequence(board, Vector(0, 4, 3, 7, 6))

      game.hasWinner("X", board) should equal(true)
    }

    it("should be true if there is a vertical match for \"O\"") {
      val game = new Game()
      val board = new Board()

      makeMovesInSequence(board, Vector(0, 2, 1, 5, 6, 8))

      game.hasWinner("O", board) should equal(true)
    }

    it("should be true if there is a left diagonal match for \"X\"") {
      val game = new Game()
      val board = new Board()

      makeMovesInSequence(board, Vector(0, 2, 4, 3, 8))

      game.hasWinner("X", board) should equal(true)
    }

    it("should be true if there is a right diagonal match for \"O\"") {
      val game = new Game()
      val board = new Board()

      makeMovesInSequence(board, Vector(0, 2, 1, 4, 5, 6))

      game.hasWinner("O", board) should equal(true)
    }
  }
}



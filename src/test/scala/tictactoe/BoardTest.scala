package tictactoe

import org.scalatest._

class BoardTest extends FunSpec with Matchers {

  def makeMovesInSequence(moves: Vector[Int]) = {
    var board = Board(3)
    moves.foreach(move => {
      board = board.play(move)
    })
    board
  }

  describe("a game board") {

    it("should be a vector of empty spaces with specified size^2") {
      Board(3).emptyIndexes.size should equal(9)
    }

    it("can get the indexes of empty spaces") {
      val board = makeMovesInSequence(Vector(2))

      board.emptyIndexes should equal(IndexedSeq(0, 1, 3, 4, 5, 6, 7, 8))
    }

    describe("making moves") {

      it("starting mark should be X") {
        Board(3).currentPlayerMark should equal(X)
      }

      it("should insert token \"X\" on odd moves") {
        val move = 2

        Board(3).play(move).state apply move should equal(X)
      }

      it("should insert token \"O\" on even moves") {
        val board = makeMovesInSequence(Vector(3, 4))

        board.state apply 4 should equal(O)
      }
    }

    describe("end of game") {

      it("should be finished if the board is a draw") {
        val board = makeMovesInSequence(Vector(0, 1, 2, 3, 5, 4, 6, 8, 7))

        assert(board.isComplete)
      }

      it("should be finished if X won") {
        val board = makeMovesInSequence(Vector(0, 4, 1, 5, 2))

        assert(board.isComplete)
      }

      it("should be finished if O won") {
        val board = makeMovesInSequence(Vector(0, 3, 7, 4, 2, 5))

        assert(board.isComplete)
      }

      describe("board winning conditions") {

        it("should be false when there is a fresh board") {
          val board = Board(3)

          assert(board.isWinner(X) == false)
        }

        it("should be false whenever the game is in progress") {
          val board = Board(3)

          makeMovesInSequence(Vector(0, 2, 5, 8))

          board.isWinner(X) should equal(false)
        }

        it("should be true when there is a horizontal match for \"X\"") {
          val board = makeMovesInSequence(Vector(0, 4, 1, 5, 2))

          board.isWinner(X) should equal(true)
        }

        it("should be true when there is a horizontal match for \"O\"") {
          val board = makeMovesInSequence(Vector(0, 3, 7, 4, 2, 5))

          board.isWinner(O) should equal(true)
        }

        it("should be true if there is a vertical match for \"X\"") {
          val board = makeMovesInSequence(Vector(0, 4, 3, 7, 6))

          board.isWinner(X) should equal(true)
        }

        it("should be true if there is a vertical match for \"O\"") {
          val board = makeMovesInSequence(Vector(0, 2, 1, 5, 6, 8))

          board.isWinner(O) should equal(true)
        }

        it("should be true if there is a left diagonal match for \"X\"") {
          val board = makeMovesInSequence(Vector(0, 2, 4, 3, 8))

          board.isWinner(X) should equal(true)
        }

        it("should be true if there is a right diagonal match for \"O\"") {
          val board = makeMovesInSequence(Vector(0, 2, 1, 4, 5, 6))

          board.isWinner(O) should equal(true)
        }
      }
    }
  }
}

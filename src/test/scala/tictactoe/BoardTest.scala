package tictactoe

import org.scalatest._

class BoardTest extends FlatSpec with Matchers {

  "A Board state" should "be a vector of empty spaces with specified size^2" in {
    val size = 3
    val expectedBoard = Vector(Vector("_","_","_"),Vector("_","_","_"),Vector("_","_","_"))
    val board = Board(size)

    board.getState.size should equal(3)
    board.getState.toIndexedSeq should equal(expectedBoard)
  }

  it should "insert token \"X\" on odd moves" in {
    val board = Board(3)
    val input = 4

    board.place(input)

    board getMove input should equal("X")
  }

  it should "insert token \"O\" on even moves" in {
    val board = Board(3)
    val moveInput = 5

    board.place(4)
    board.place(moveInput)

    board getMove moveInput should equal("O")
  }
}

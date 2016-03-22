package tictactoe

import org.scalatest._

class BoardTest extends FlatSpec with Matchers {

  "A Board state" should "be a vector of empty spaces with specified size^2" in {
    val size = 3
    val board = Board(size)

    board.cells should equal(Vector("_","_","_","_","_","_","_","_","_"))
    board.cells.size should equal((size * size))
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

package tictactoe

import org.scalatest._

class BoardTest extends FlatSpec with Matchers {

  "A Board" should "have initial state of empty spaces with size^2" in {
    val board = Board(3)

    board.getCellCount() should equal(9)
  }

  it should "place moves and get moves" in {
    val board = Board(3)

    board.place(1, "X")

    assert(board.getMove(1) == "X")
  }
}

package tictactoe.players

import org.scalatest.{FunSpec, Matchers}
import tictactoe.BoardSpecHelper
import tictactoe.players.tokens.X

class UnbeatableComputerTest extends FunSpec with Matchers with BoardSpecHelper {
  val computer = UnbeatableComputer()

  describe("unbeatable move generator") {

    it("should block the opponents move") {
      val board = createBoardStateFromMoves(Vector(0, 2, 6))

      computer.getMove(board) should equal(3)
    }

    it("should return the middle board spot if corner is taken") {
      val board = createBoardStateFromMoves(Vector(0))

      computer.getMove(board) should equal(4)
    }

    it("should make the smart move in tough situations") {
      val board = createBoardStateFromMoves(Vector(1, 6, 5, 7, 8))

      computer.getMove(board) should equal(2)
    }
  }
}

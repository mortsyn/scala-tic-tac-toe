package tictactoe.players

import org.scalatest.{FunSpec, Matchers}
import tictactoe.game.Game
import tictactoe.spec_helper.BoardSpecHelper
import tictactoe.tokens.O

class UnbeatableComputerTest extends FunSpec with Matchers with BoardSpecHelper {
  val computer = UnbeatableComputer(O)

  describe("unbeatable move generator") {

    it("can be represented as a string") {
      computer.toString should equal("UnbeatableComputer Player")
    }

    it("should block the opponents move if they can win on the next turn") {
      val board = createBoardStateFromMoves(Vector(1, 3, 7))
      val game = Game((player1, computer), board)

      computer.getMove(game) should equal(4)
    }

    it("should return the middle board spot if a corner is taken") {
      val board = createBoardStateFromMoves(Vector(1))
      val game = Game((player1, computer), board)

      computer.getMove(game) should equal(5)
    }

    it("should make the move at 3 instead of 5") {
      val board = createBoardStateFromMoves(Vector(2, 7, 6, 8, 9))
      val game = Game((player1, computer), board)

      computer.getMove(game) should equal(3)
    }

    it("should get the winning move at 7") {
      val board = createBoardStateFromMoves(Vector(1, 2, 3, 4, 5, 6))
      val game = Game((player1, computer), board)

      computer.getMove(game) should equal(7)
    }
  }
}

package tictactoe

import org.scalatest.{BeforeAndAfter, FunSpec, Matchers}
import java.util.Scanner

import tictactoe.players.{UnbeatableComputer, Human}

class GameTest extends FunSpec with Matchers with BoardSpecHelper with BeforeAndAfter {

  describe("building a game from user input") {

    it("creates a human vs human game if the game mode is 1") {
      val game = Game(new UI(new Scanner("x o 1")))

      game.players._1 shouldBe a [Human]
      game.players._2 shouldBe a [Human]
      game.activePlayer should be theSameInstanceAs(game.players._1)
      game.board.emptyIndexes.size should equal(9)
    }

    it("creates a human vs computer game if the game mode is 2") {
      val game = Game(new UI(new Scanner("x o 2")))

      game.players._1 shouldBe a [Human]
      game.players._2 shouldBe a [UnbeatableComputer]
    }

    it("creates a computer vs computer game if the game mode is 3") {
      val game = Game(new UI(new Scanner("x o 3")))

      game.players._1 shouldBe a [UnbeatableComputer]
      game.players._2 shouldBe a [UnbeatableComputer]
    }
  }

  describe("making moves") {

    it("changes the current player") {
      val game = Game(new UI(new Scanner("x o 1"))).makeMove(1)

      game.activePlayer should not be theSameInstanceAs(game.players._1)
      game.board.state apply 0 should equal(Some(game.players._1))
    }
  }

  describe("valid moves") {

    it("cannot be less than 0") {
      val game = Game(new UI(new Scanner("x o 1")))

      game.moveIsValid(0) should equal(false)
    }

    it("cannot be more than the boards size") {
      val game = Game(new UI(new Scanner("x o 1")))

      game.moveIsValid(10) should equal(false)
    }

    it("are false if the spot has already been taken") {
      val game = Game(new UI(new Scanner("x o 1"))).makeMove(9)

      game.moveIsValid(9) should equal(false)
    }
  }

  describe("game over") {

    it("should be false on a fresh board") {
      val board = Board(3)
      val game = Game((Human(X), Human(O)), board)

      game.isOver should equal(false)
    }

    it("should be false whenever the game is in progress") {
      val board = createBoardStateFromMoves(Vector(1, 3, 6, 9))
      val game = Game((Human(X), Human(O)), board)

      game.isOver should equal(false)
    }

    it("should be true when there is a horizontal match for X") {
      val board = createBoardStateFromMoves(Vector(1, 5, 2, 6, 3))
      val game = Game((Human(X), Human(O)), board)

      game.isOver should equal(true)
    }

    it("should be true when there is a horizontal match for O") {
      val board = createBoardStateFromMoves(Vector(1, 4, 8, 5, 3, 6))
      val game = Game((player1, player2), board)

      game.isOver should equal(true)
    }

    it("should be true if there is a vertical match for X") {
      val board = createBoardStateFromMoves(Vector(1, 5, 4, 6, 7))
      val game = Game((Human(X), Human(O)), board)

      game.isOver should equal(true)
    }

    it("should be true if there is a vertical match for O") {
      val board = createBoardStateFromMoves(Vector(1, 3, 2, 6, 7, 9))
      val game = Game((Human(X), Human(O)), board)

      game.isOver should equal(true)
    }

    it("should be true if there is a left diagonal match for X") {
      val board = createBoardStateFromMoves(Vector(1, 3, 5, 4, 9))
      val game = Game((Human(X), Human(O)), board)

      game.isOver should equal(true)
    }

    it("should be true if there is a right diagonal match for O") {
      val board = createBoardStateFromMoves(Vector(1, 3, 2, 5, 6, 7))
      val game = Game((Human(X), Human(O)), board)

      game.isOver should equal(true)
    }

    it("should be true if the game is a draw") {
      val board = createBoardStateFromMoves(Vector(1, 2, 3, 4, 6, 5, 7, 9, 8))
      val game = Game((Human(X), Human(O)), board)

      assert(game.isOver)
    }
  }

  describe("draw game") {

    it("should be true if the game is board is full") {
      val board = createBoardStateFromMoves(Vector(1, 2, 3, 4, 6, 5, 7, 9, 8))
      val game = Game((Human(X), Human(O)), board)

      assert(game.isDraw)
    }

    it("should be false if the game is full but X won") {
      val board = createBoardStateFromMoves(Vector(1, 2, 3, 4, 6, 5, 8, 7, 9))
      val game = Game((Human(X), Human(O)), board)

      game.isDraw should equal(false)
    }

    it("should be false if the game O won") {
      val board = createBoardStateFromMoves(Vector(1, 2, 3, 4, 6, 5, 7, 8))
      val game = Game((Human(X), Human(O)), board)

      game.isDraw should equal(false)
    }
  }
}

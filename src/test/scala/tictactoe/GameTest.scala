package tictactoe

import java.io.{PrintStream, ByteArrayOutputStream}
import org.scalatest.{BeforeAndAfter, FunSpec, Matchers}
import tictactoe.players.{UnbeatableComputer, Human}
import java.util.Scanner

import tictactoe.views.{SimpleView}

class GameTest extends FunSpec with Matchers with BoardSpecHelper with BeforeAndAfter {
  val stream = new ByteArrayOutputStream()
  System.setOut(new PrintStream(stream))

  after {
    stream.reset()
  }

  describe("a game") {

    it("should print a welcome message") {
      val game = Game((Human(), Human()), createBoardStateFromMoves(Vector()), SimpleView)

      assert(stream.toString().contains("Welcome to Tic Tac Toe!"))
    }

    it("prints the empty board") {
      val game = Game((Human(), Human()), createBoardStateFromMoves(Vector()), SimpleView)
      val expectedBoard = "\n" +
                          "| _ | _ | _ |" + "\n" +
                          "| _ | _ | _ |" + "\n" +
                          "| _ | _ | _ |" + "\n" + "\n"

      assert(stream.toString().contains(expectedBoard))
    }

    it("takes input from human player") {
      val game = Game((Human(new Scanner("8 4")), UnbeatableComputer()), createBoardStateFromMoves(Vector(2, 3, 5, 6)), SimpleView)
      val expectedBoard = "\n" +
                          "| _ | X | O |" + "\n" +
                          "| _ | X | O |" + "\n" +
                          "| _ | X | _ |" + "\n" + "\n"

      game.run()

      assert(stream.toString().contains(expectedBoard))
    }

    it("takes switches turns and computer player takes turn automatically") {
      val game = Game((Human(new Scanner("1 4 6 3")), UnbeatableComputer()), createBoardStateFromMoves(Vector()), SimpleView)
      val expectedBoard = "\n" +
                          "| X | _ | _ |" + "\n" +
                          "| _ | O | _ |" + "\n" +
                          "| _ | _ | _ |" + "\n" + "\n"

      game.run()

      assert(stream.toString().contains(expectedBoard))
    }

    it("prints a prompt for the human player") {
      val game = Game((Human(new Scanner("7")), Human()), createBoardStateFromMoves(Vector(1, 2, 4, 5)), SimpleView)
      val expectedBoard = "Human player, choose a move:"

      game.run()

      assert(stream.toString().contains(expectedBoard))
    }

    it("prints a prompt for the computer player") {
      val game = Game((Human(new Scanner("6")), UnbeatableComputer()), createBoardStateFromMoves(Vector(1, 2, 4, 5)), SimpleView)
      val expectedBoard = "Computer is thinking..."

      game.run()

      assert(stream.toString().contains(expectedBoard))
    }

    it("prints end of game message for UnbeatableComputer") {
      val game = Game((Human(new Scanner("1 4 6 3")), UnbeatableComputer()), createBoardStateFromMoves(Vector()), SimpleView)
      val expectedBoard = "Game Over! Unbeatable Computer wins"

      game.run()

      assert(stream.toString().contains(expectedBoard))
    }

    it("prints end of game message for Human") {
      val game = Game((Human(new Scanner("1 4 7 3")), Human(new Scanner("2 5 8 3"))), createBoardStateFromMoves(Vector()), SimpleView)
      val expectedBoard = "Game Over! Human wins"

      game.run()

      assert(stream.toString().contains(expectedBoard))
    }

    it("prints end of game message for if a draw") {
      val game = Game((Human(), Human()), createBoardStateFromMoves(Vector(1, 2, 3, 4, 6, 5, 7, 9, 8)), SimpleView)
      val expectedBoard = "Game Over! Draw game"

      game.run()

      assert(stream.toString().contains(expectedBoard))
    }
  }
}

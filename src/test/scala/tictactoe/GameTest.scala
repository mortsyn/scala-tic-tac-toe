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

    it("prints a prompt for the human player") {
      val game = Game((Human(new Scanner("6")), Human()), createBoardStateFromMoves(Vector(0, 1, 3, 4)), SimpleView)
      val expectedBoard = "Human player, choose a move:"

      game.run()

      assert(stream.toString().contains(expectedBoard))
    }

    it("prints a prompt for the computer player") {
      val game = Game((Human(new Scanner("5")), UnbeatableComputer()), createBoardStateFromMoves(Vector(0, 1, 3, 4)), SimpleView)
      val expectedBoard = "Computer is thinking..."

      game.run()

      assert(stream.toString().contains(expectedBoard))
    }

    it("takes input from human player") {
      val game = Game((Human(new Scanner("7 3")), UnbeatableComputer()), createBoardStateFromMoves(Vector(1, 2, 4, 5)), SimpleView)
      val expectedBoard = "\n" +
        "| _ | X | O |" + "\n" +
        "| _ | X | O |" + "\n" +
        "| _ | X | _ |" + "\n" + "\n"

      game.run()

      assert(stream.toString().contains(expectedBoard))
    }

    it("takes switches turns and computer player takes turn automatically") {
      val game = Game((Human(new Scanner("0 3 5 2")), UnbeatableComputer()), createBoardStateFromMoves(Vector()), SimpleView)
      val expectedBoard = "\n" +
                          "| X | _ | O |" + "\n" +
                          "| X | O | X |" + "\n" +
                          "| O | _ | _ |" + "\n" + "\n"

      game.run()

      assert(stream.toString().contains(expectedBoard))
    }
  }
}

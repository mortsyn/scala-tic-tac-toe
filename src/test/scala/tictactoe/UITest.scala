package tictactoe

import java.io.{PrintStream, ByteArrayOutputStream}
import org.scalatest.{BeforeAndAfter, Matchers, FunSpec}
import java.util.Scanner

class UITest extends FunSpec with Matchers with BoardSpecHelper with BeforeAndAfter {
  var stream = new ByteArrayOutputStream()

  before {
    stream = new ByteArrayOutputStream()
    stream.reset()
    System.setOut(new PrintStream(stream))
  }

  after {
    stream.reset()
  }

  describe("The Console UI") {

    describe("output") {

      it("prints out a welcome message if the game has just started") {
        val ui = new UI(new Scanner(""))
        val board = createBoardStateFromMoves(Vector())

        ui.update(board)

        assert(stream.toString.contains("Welcome to Tic Tac Toe"))
      }

      it("prints empty spaces as numbers") {
        val ui = new UI(new Scanner(""))
        val board = createBoardStateFromMoves(Vector())
        val expectedBoard = "| 1 | 2 | 3 |" + "\n" +
                            "| 4 | 5 | 6 |" + "\n" +
                            "| 7 | 8 | 9 |" + "\n" + "\n"

        ui.update(board)

        assert(stream.toString.contains(expectedBoard))
      }

      it("prints out the filled spaces as a token") {
        val ui = new UI(new Scanner(""))
        val board = createBoardStateFromMoves(Vector(1, 2))
        val expectedBoard = "| X | O | 3 |" + "\n" +
                            "| 4 | 5 | 6 |" + "\n" +
                            "| 7 | 8 | 9 |" + "\n" + "\n"

        ui.update(board)

        assert(stream.toString.contains(expectedBoard))
      }
    }

    describe("game mode input") {

      it("is prompted to the user") {
        val ui = new UI(new Scanner("3"))
        val expectedOutput = "Choose a game mode:\n" +
                              "1. Human vs Human\n" +
                              "2. Human vs Computer\n" +
                              "3. Computer vs Computer\n"
        ui.getGameMode

        assert(stream.toString.contains(expectedOutput))
      }

      it("prompts the user with an error message when there is bad input") {
        val ui = new UI(new Scanner("0 3"))
        val expectedOutput = "Invalid input, try again"

        ui.getGameMode

        assert(stream.toString.contains(expectedOutput))
      }

      it("ignores any input that is more than 3") {
        val ui = new UI(new Scanner("4 3"))

        ui.getGameMode should equal(3)
      }

      it("ignores any input that is less than 1") {
        val ui = new UI(new Scanner("0 1"))

        ui.getGameMode should equal(1)
      }
    }
  }
}



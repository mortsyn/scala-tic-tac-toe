package tictactoe.io

import java.io.{ByteArrayOutputStream, PrintStream}
import java.util.Scanner

import org.scalatest.{BeforeAndAfter, FunSpec, Matchers}
import tictactoe.game.Game
import tictactoe.players.{Human, UnbeatableComputer}
import tictactoe.spec_helper.BoardSpecHelper
import tictactoe.tokens.{O, X}

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
        val game = Game((player1, player2), board)

        ui.update(game)

        assert(stream.toString.contains("Welcome to Tic Tac Toe"))
      }

      it("prints empty spaces as numbers") {
        val ui = new UI(new Scanner(""))
        val board = createBoardStateFromMoves(Vector())
        val game = Game((player1, player2), board)
        val expectedBoard = "| 1 | 2 | 3 |" + "\n" +
                            "| 4 | 5 | 6 |" + "\n" +
                            "| 7 | 8 | 9 |" + "\n" + "\n"

        ui.update(game)

        assert(stream.toString.contains(expectedBoard))
      }

      it("prints out the filled spaces as a token") {
        val ui = new UI(new Scanner(""), new PrintStream(stream))
        val board = createBoardStateFromMoves(Vector(1, 2))
        val game = Game((player1, player2), board)
        val expectedBoard = "| X | O | 3 |" + "\n" +
                            "| 4 | 5 | 6 |" + "\n" +
                            "| 7 | 8 | 9 |" + "\n" + "\n"

        ui.update(game)

        assert(stream.toString.contains(expectedBoard))
      }

      it("prints out the winner when Human player1 wins") {
        val ui = new UI(new Scanner(""), new PrintStream(stream))
        val board = createBoardStateFromMoves(Vector(1, 3, 5, 4, 9))
        val game = Game((player1, player2), board)
        val expectedBoard = "Human Player (token X) wins!"
        val gameOverMessage = "Game Over!"

        ui.update(game)

        assert(stream.toString.contains(expectedBoard))
        assert(stream.toString.contains(gameOverMessage))
      }

      it("prints out the winner when Human player2 wins") {
        val ui = new UI(new Scanner(""), new PrintStream(stream))
        val board = createBoardStateFromMoves(Vector(1, 3, 2, 5, 6, 7))
        val game = Game((player1, player2), board)
        val expectedBoard = "Human Player (token O) wins!"
        val gameOverMessage = "Game Over!"

        ui.update(game)

        assert(stream.toString.contains(expectedBoard))
        assert(stream.toString.contains(gameOverMessage))
      }

      it("prints out the winner when UnbeatableComputer player1 wins") {
        val ui = new UI(new Scanner(""), new PrintStream(stream))
        player1 = UnbeatableComputer(X)
        val board = createBoardStateFromMoves(Vector(1, 3, 5, 4, 9))

        val game = Game((player1, player2), board)
        val expectedBoard = "UnbeatableComputer Player (token X) wins!"
        val gameOverMessage = "Game Over!"

        ui.update(game)

        assert(stream.toString.contains(expectedBoard))
        assert(stream.toString.contains(gameOverMessage))
      }

      it("prints out the winner when UnbeatableComputer player2 wins") {
        val ui = new UI(new Scanner(""), new PrintStream(stream))
        player2 = UnbeatableComputer(O)
        val board = createBoardStateFromMoves(Vector(1, 3, 2, 5, 6, 7))
        val game = Game((player1, player2), board)
        val expectedBoard = "UnbeatableComputer Player (token O) wins!"
        val gameOverMessage = "Game Over!"

        ui.update(game)

        assert(stream.toString.contains(expectedBoard))
        assert(stream.toString.contains(gameOverMessage))
      }

      it("prints out 'Draw game' when the game is a draw") {
        val ui = new UI(new Scanner(""), new PrintStream(stream))
        val board = createBoardStateFromMoves(Vector(1, 2, 3, 4, 6, 5, 7, 9, 8))
        val game = Game((player1, player2), board)
        val expectedBoard = "Draw game!"
        val gameOverMessage = "Game Over!"

        ui.update(game)

        assert(stream.toString.contains(expectedBoard))
        assert(stream.toString.contains(gameOverMessage))
      }

      it("prints out a message when it's the computer players turn") {
        player2 = UnbeatableComputer(O)
        val ui = new UI(new Scanner(""))
        val board = createBoardStateFromMoves(Vector(1, 2, 3, 4, 5))
        val game = Game((player1, player2), board)

        ui.update(game)

        assert(stream.toString.contains("Computer thinking..."))
      }

      it("does not print the computer players turn when the game is over") {
        player2 = UnbeatableComputer(O)
        val ui = new UI(new Scanner(""))
        val board = createBoardStateFromMoves(Vector(1, 2, 3, 4, 5, 6, 7))
        val game = Game((player1, player2), board)

        ui.update(game)

        assert(stream.toString.contains("Computer thinking...") == false)
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
        val expectedOutput = "Invalid input, try again"

        ui.getGameMode should equal(3)
        assert(stream.toString.contains(expectedOutput))
      }

      it("ignores any input that is less than 1") {
        val ui = new UI(new Scanner("0 1"))
        val expectedOutput = "Invalid input, try again"

        ui.getGameMode should equal(1)
        assert(stream.toString.contains(expectedOutput))
      }

      it("prints a 'bad string' message when random input is given") {
        val ui = new UI(new Scanner("sadj df saf asdf 1"))
        val expectedOutput = "Please do not input strings, try again"

        ui.getGameMode should equal(1)
        assert(stream.toString.contains(expectedOutput))
      }
    }

    describe("getting user input") {

      it("prompts for players a human players move") {
        val ui = new UI(new Scanner("1"))
        val game = Game((Human(X), Human(O)), createBoardStateFromMoves(Vector()))

        ui.getPlayerMove(game) should equal(1)
        assert(stream.toString.contains("Human, choose a move: "))
      }

      it("ignores moves that are not valid") {
        val ui = new UI(new Scanner("0 1"))
        val game = Game((Human(X), Human(O)), createBoardStateFromMoves(Vector()))

        ui.getPlayerMove(game) should equal(1)
        assert(stream.toString.contains("Invalid input, try again"))
      }

      it("prints a 'bad string' message when random input is given") {
        val ui = new UI(new Scanner("1df 1"))
        val expectedOutput = "Please do not input strings, try again"

        ui.getGameMode should equal(1)
        assert(stream.toString.contains(expectedOutput))
      }
    }
  }
}



package tictactoe

import java.io.{ByteArrayOutputStream, PrintStream}
import java.util.Scanner
import org.scalatest.{BeforeAndAfter, Matchers, FunSpec}
import tictactoe.players.tokens.{O, X}
import tictactoe.players.{UnbeatableComputer, Human}
import tictactoe.strategies.TicTacToe
import tictactoe.views.SimpleView


class GameTest extends FunSpec with Matchers with BeforeAndAfter with BoardSpecHelper {
  val stream = new ByteArrayOutputStream()
  System.setOut(new PrintStream(stream))
  val view = SimpleView()

  after {
    stream.reset()
  }

  describe("a game") {

    describe("on initialization") {

      it("prints out a welcome message") {
        val game = new Game(initalState = createBoardStateFromMoves((Human(X), Human(O)), Vector()),
                            view = view)

        assert(stream.toString().contains("Welcome to Tic Tac Toe"))
      }

      it("should print out the initial board") {
        val game = new Game(initalState = createBoardStateFromMoves((Human(X), Human(O)), Vector()),
                            view = view)
        val expectedBoardOutput = "\n" +
                                  "| _ | _ | _ |\n" +
                                  "| _ | _ | _ |\n" +
                                  "| _ | _ | _ |\n"

        assert(stream.toString.contains(expectedBoardOutput))
      }
    }

    describe("running the game ") {

      it("prints out the computer players turn") {
        val io = new IO(new Scanner(""))
        val game = new Game(io = io,
                            initalState = createBoardStateFromMoves((Human(X, io), UnbeatableComputer(O)), Vector(0, 1, 3, 4, 8)),
                            view = view)
        val expectedBoardOutput = "It's UnbeatableComputer (O)'s turn..."

        game.run()

        assert(stream.toString.contains(expectedBoardOutput))
      }

      it("prints out the human players turn") {
        val io = new IO(new Scanner("6"))
        val game = new Game(io = io,
                            initalState = createBoardStateFromMoves((Human(X, io), UnbeatableComputer(O)), Vector(0, 1, 3, 4)),
                            view = view)
        val expectedBoardOutput = "It's Human (X)'s turn..."

        game.run()

        assert(stream.toString.contains(expectedBoardOutput))
      }

      it("humans can play a game until it's finished") {
        val io = new IO(new Scanner("1 2 3 4 5 6"))
        val game = new Game(io = io,
                            initalState = createBoardStateFromMoves((Human(X, io), Human(O, io)), Vector()),
                            view = view)
        val expectedBoardOutput = "\n| _ | X | O |\n" +
                                    "| X | O | X |\n" +
                                    "| O | _ | _ |\n"

        game.run()

        assert(stream.toString.contains(expectedBoardOutput))
      }

      it("computer player will generate move automatically") {
        val io = new IO(new Scanner("0 1 4 6"))
        val game = new Game(io = io,
                            initalState = createBoardStateFromMoves((Human(X, io), UnbeatableComputer(O)), Vector(0, 1, 3, 4, 5)),
                            view = view)
        val expectedBoardOutput = "\n| X | O | _ |\n" +
                                    "| X | O | X |\n" +
                                    "| _ | O | _ |\n"

        game.run()

        assert(stream.toString.contains(expectedBoardOutput))
      }

      it("bad input is ignored from human player") {
        val io = new IO(new Scanner("1 asdfk df sdjf 2 3 4 sdkf sdf 5 6"))
        val game = new Game(io = io,
                            initalState = createBoardStateFromMoves((Human(X, io), Human(O, io)), Vector()),
                            view = view)
        val expectedBoardOutput = "\n| _ | X | O |\n" +
                                    "| X | O | X |\n" +
                                    "| O | _ | _ |\n"

        game.run()

        assert(stream.toString.contains(expectedBoardOutput))
      }
    }
  }
}

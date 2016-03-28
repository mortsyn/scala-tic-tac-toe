package tictactoe

import java.io.{ByteArrayOutputStream, PrintStream}
import org.scalatest.{Matchers, FunSpec}
import tictactoe.players.tokens.{O, X}
import tictactoe.players.{UnbeatableComputer, Human}
import tictactoe.strategies.TicTacToe
import tictactoe.views.SimpleView


class GameTest extends FunSpec with Matchers {
  val stream = new ByteArrayOutputStream()
  System.setOut(new PrintStream(stream))
  val board = Board(3, TicTacToe, (Human(X), UnbeatableComputer(O)))
  val view = SimpleView()

  describe("a game") {

    describe("before running the game") {

      it("should print out a welcome message") {
        val game = new Game(board = board, view = view)

        assert(stream.toString().contains("Welcome to Tic Tac Toe"))
      }

      it("should also print out an empty board") {
        val game = new Game(board = board, view = view)
        val expectedBoardOutput = "\n" +
                                  "| _ | _ | _ |\n" +
                                  "| _ | _ | _ |\n" +
                                  "| _ | _ | _ |\n"

        assert(stream.toString.contains(expectedBoardOutput))
      }
    }
  }
}


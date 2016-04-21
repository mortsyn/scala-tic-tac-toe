package tictactoe

import java.io.{PrintStream, ByteArrayOutputStream}
import java.util.Scanner

import org.scalatest.{BeforeAndAfter, FunSpec}
import tictactoe.io.UI
import tictactoe.game.Game
import tictactoe.players.{Human, UnbeatableComputer}
import tictactoe.spec_helper.BoardSpecHelper
import tictactoe.tokens.{O, X}

class MainTest extends FunSpec with BeforeAndAfter with BoardSpecHelper {
  val stream = new ByteArrayOutputStream()

  after {
    stream.reset()
  }

  describe("A tic tac toe game") {

    it("can play a human vs human game") {
      player1 = Human(X, new UI(new Scanner("1 4 7")))
      player2 = Human(O, new UI(new Scanner("2 5")))
      val game = Game((player1, player2), createBoardStateFromMoves(Vector()))
      Main.run(new UI(new Scanner("1"), new PrintStream(stream)), game)
      val expectedBoard = "| X | O | 3 |" + "\n" +
                          "| X | O | 6 |" + "\n" +
                          "| X | 8 | 9 |" + "\n" + "\n"

      assert(stream.toString.contains("Welcome to Tic Tac Toe"))
      assert(stream.toString.contains(expectedBoard))
      assert(stream.toString.contains("Game Over!"))
      assert(stream.toString.contains("Human Player (token X) wins!"))
    }

    it("can play with UnbeatableComputer player") {
      player1 = UnbeatableComputer(X)
      player2 = UnbeatableComputer(O)
      val game = Game((player1, player2), createBoardStateFromMoves(Vector(1, 2, 3, 4, 5)))
      Main.run(new UI(new Scanner("1"), new PrintStream(stream)), game)
      val expectedBoard = "| X | O | X |" + "\n" +
                          "| O | X | O |" + "\n" +
                          "| X | 8 | 9 |" + "\n" + "\n"

      assert(stream.toString.contains(expectedBoard))
      assert(stream.toString.contains("Game Over!"))
      assert(stream.toString.contains("UnbeatableComputer Player (token X) wins!"))
    }
  }
}

package tictactoe.players

import tictactoe.players.tokens.{EMPTY}
import tictactoe.{Board}
import java.util.Scanner

case class Human(input: Scanner = new Scanner(System.in)) extends Player {

  def nextInt: Int = {
    while (!input.hasNextInt()) {
      input.next()
    }

    return input.nextInt()
  }

  override def getMove(board: Board): Int = {
    val move = nextInt
    if((move < board.state.size && move >= 0) && (board.state(move) == EMPTY)) move else getMove(board)
  }
}

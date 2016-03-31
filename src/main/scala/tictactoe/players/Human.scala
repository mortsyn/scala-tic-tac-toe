package tictactoe.players

import tictactoe.players.tokens.{EMPTY}
import tictactoe.{Board}
import java.util.Scanner

case class Human(input: Scanner = new Scanner(System.in)) extends Player {

  override def getMove(board: Board): Int = {
    val move = nextInt

    if(isValid(board, move)) move else getMove(board)
  }

  def nextInt: Int = {
    while (!input.hasNextInt()) {
      input.next()
    }

    return input.nextInt()
  }

  def isValid(board: Board, move: Int): Boolean = {
    (move >= 1 && move <= board.state.size) && (board.emptyIndexes contains move)
  }
}

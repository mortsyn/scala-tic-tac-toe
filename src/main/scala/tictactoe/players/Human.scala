package tictactoe.players

import tictactoe.players.tokens.{EMPTY, Token}
import tictactoe.{Board, IO}

case class Human() extends Player with IO {

  override def getMove(board: Board): Int = {
      val move = nextInt
      if((board.state apply move) == EMPTY) move else getMove(board)
  }
}

package tictactoe.players

import tictactoe.players.tokens.{EMPTY, Token}
import tictactoe.{Board, IO}

case class Human() extends Player with IO {

  override def getMove(board: Board): Int = {
    val move = this.nextInt
    if((move < board.state.size && move >= 0) && (board.state(move) == EMPTY)) move else getMove(board)
  }
}

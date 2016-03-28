package tictactoe.views

import java.io.PrintStream

import tictactoe.Board

case class SimpleView() extends BoardView {

  override def format(board: Board, output: PrintStream): Unit = {
    board.state.grouped(board.size).foreach(row => {
      output.println("| " + row.apply(0).toString + " | " + row.apply(1).toString + " | " + row.apply(2).toString + " |")
    })
  }
}

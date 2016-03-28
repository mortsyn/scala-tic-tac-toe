package tictactoe.views

import java.io.PrintStream

import tictactoe.Board

trait BoardView {
  def format(board: Board, output: PrintStream): Unit
}

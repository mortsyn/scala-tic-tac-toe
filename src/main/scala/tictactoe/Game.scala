package tictactoe

import tictactoe.views.BoardView

class Game(io: IO = IO(), board: Board, view: BoardView) {
  io.output.println("Welcome to Tic Tac Toe")
  view.format(board, io.output)
}


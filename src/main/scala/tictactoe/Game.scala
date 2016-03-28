package tictactoe

import tictactoe.views.BoardView

class Game(io: IO = IO(), initalState: Board, view: BoardView) {
  var board = initalState
  io.output.println("Welcome to Tic Tac Toe" + "\n")
  io.output.println(view.format(board))

  def run() = {
    do {
      io.output.println(view.playerTurnText(board.currentPlayer))
      board = board.play(currentPlayerMove)
      io.output.println(view.format(board))
    } while(!board.isComplete)
  }

  protected def currentPlayerMove: Int = board.currentPlayer.getMove(board)
}


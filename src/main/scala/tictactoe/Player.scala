package tictactoe

trait Player {
  def getMove(board: Board): Int
}

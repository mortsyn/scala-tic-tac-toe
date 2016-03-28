package tictactoe

trait Player {
  val mark: Token
  def getMove(board: Board): Int
}

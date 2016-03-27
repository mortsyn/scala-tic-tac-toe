package tictactoe

trait Strategy {
  def winner(token: Token, board: Board): Boolean
}

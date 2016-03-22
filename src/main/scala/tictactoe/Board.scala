package tictactoe

case class Board(size: Int = 3) {
  var cells = for(cell <- 0 until (size * size)) yield "_"

  def place(space : Int) = cells = cells.updated(space, currentPlayerMark())

  def getMove(space: Int) = cells apply space

  private def currentPlayerMark() = if (playerXTurn()) "X" else "O"

  private def playerXTurn() = cells.filter(_ != "_").length % 2 == 0
}

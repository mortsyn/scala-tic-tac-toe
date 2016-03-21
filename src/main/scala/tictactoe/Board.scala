package tictactoe

case class Board(size: Int) {
  private val cells = new Array[String](getCellCount())

  def place(index: Int, gamePiece: String) = cells(index) = gamePiece

  def getMove(space: Int) = cells(space)

  def getCellCount() = (size * size)
}

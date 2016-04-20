package tictactoe

import tictactoe.players._

object Game {

  def apply(ui: UI = new UI()): Game = {
    createGame(ui.getGameMode)
  }

  private def createGame(gameMode: Int) = gameMode match {
    case 1 => new Game((Human(X), Human(O)), Board(3))
    case 2 => new Game((Human(X), UnbeatableComputer(O)), Board(3))
    case 3 => new Game((UnbeatableComputer(X), UnbeatableComputer(O)), Board(3))
  }
}

case class Game(players: (Player, Player), board: Board) {

  def makeMove(index: Int) = new Game(players, board.play(index, activePlayer))

  def moveIsValid(move: Int): Boolean = isInRange(move) && isEmptySpace(move)

  def activePlayer = if (evenNumberOfTurns) players._2 else players._1

  def isOver = isDraw || isGameWinner(players._1) || isGameWinner(players._2)

  def isDraw = board.emptyIndexes.size == 0 && !(isGameWinner(players._1) || isGameWinner(players._2))

  def isGameWinner(player: Player): Boolean = matchWinningSequence(player, findWinningSets(board))

  private def isInRange(move: Int) = (move > 0 && move <= board.state.size)

  private def isEmptySpace(move: Int): Boolean = board.state.apply(move-1) == None

  private def matchWinningSequence(player: Player, winningSet: Option[IndexedSeq[Option[Player]]]) = winningSet match {
    case (Some(Vector(None, None, None)) | None) => false
    case Some(Vector(Some(_), Some(_), Some(_))) => winningSet.get.apply(0) == Some(player)
  }

  private def findWinningSets(board: Board) = board.getWinningLines.toIndexedSeq.find(_.toSet.size == 1)

  private def evenNumberOfTurns: Boolean = board.emptyIndexes.length % 2 == 0
}

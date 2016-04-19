package tictactoe

import tictactoe.players.{UnbeatableComputer, Human, Player}

object Game {

  def apply(ui: UI = new UI()): Game = {
    createGame((ui.getPlayerToken, ui.getPlayerToken), ui.getGameMode)
  }

  private def createGame(tokens: (Char, Char), gameMode: Int) = gameMode match {
    case 1 => new Game((Human(tokens._1), Human(tokens._2)), Board(3))
    case 2 => new Game((Human(tokens._1), UnbeatableComputer(tokens._2)), Board(3))
    case 3 => new Game((UnbeatableComputer(tokens._1), UnbeatableComputer(tokens._2)), Board(3))
  }
}

case class Game(players: (Player, Player), board: Board) {

  def makeMove(index: Int) = new Game(players, board.play(index, activePlayer.mark))

  def activePlayer = if (evenNumberOfTurns) players._2 else players._1

  def isOver = isDraw || isGameWinner(players._1.mark) || isGameWinner(players._2.mark)

  def isDraw = board.emptyIndexes.size == 0 && !(isGameWinner(players._1.mark) || isGameWinner(players._2.mark))

  def isGameWinner(token: Char): Boolean = matchWinningSequence(token, findWinningSets(board))

  private def matchWinningSequence(token: Char, winningSet: Option[IndexedSeq[Char]]) = winningSet match {
    case None => false
    case Some(value) => winningSet.get.apply(1) == token
  }

  private def findWinningSets(board: Board) = board.getWinningLines.toIndexedSeq.find(_.toSet.size == 1)

  private def evenNumberOfTurns: Boolean = board.emptyIndexes.length % 2 == 0
}

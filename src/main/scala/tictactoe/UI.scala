package tictactoe

import java.io.PrintStream
import java.util.Scanner
import tictactoe.players.Player

class UI(input: Scanner = new Scanner(System.in), output: PrintStream = System.out) {

  def promptUserMove = output.print("Human, choose a move: ")

  def getPlayerMove(game: Game): Int = {
    promptUserMove
    val input = nextInt
    if (game.moveIsValid(input)) {
      input
    } else {
      printInvalidInputMessage
      getPlayerMove(game)
    }
  }

  def getGameMode: Int = {
    promptGameMode
    val input = nextInt
    if (isValidGameMode(input)) {
      input
    } else {
      printInvalidInputMessage
      getGameMode
    }
  }


  def printGameOverMessage(game: Game) = {
    output.print("Game Over!")
    output.print(" ")
    if (game.isGameWinner(game.players._1)) {
      printWinnerMessage(game.players._1)
    } else if (game.isGameWinner(game.players._2)) {
      printWinnerMessage(game.players._2)
    } else {
      output.println("Draw game!")
    }
  }

  def update(game: Game) = {
    if (isNewGame(game.board)) printWelcomeMessage
    if (game.isOver) printGameOverMessage(game)
    print(game.board)
  }

  private def print(board: Board) ={
    board.state.zipWithIndex.foreach(spaceWithIndex => {
      output.print("| " + getCellText(spaceWithIndex) + " ")
      if (isLastInRow(board, spaceWithIndex)) output.print("|\n")
    })
    output.println("")
  }

  private def nextInt: Int = {
    while (!input.hasNextInt()) {
      printInvalidStringMessage
      output.print("> ")
      input.next()
    }

    return input.nextInt()
  }

  private def promptGameMode = {
    output.println("Choose a game mode:\n" +
      "1. Human vs Human\n" +
      "2. Human vs Computer\n" +
      "3. Computer vs Computer\n")
  }

  private def printWelcomeMessage = output.println("Welcome to Tic Tac Toe")

  private def printInvalidInputMessage = output.println("Invalid input, try again")

  private def printInvalidStringMessage = output.println("\nPlease do not input strings, try again")

  private def printWinnerMessage(winner: Player) = output.println(winner.toString + " " + "(token " + winner.mark + ") wins!")

  private def isNewGame(board: Board): Boolean = board.emptyIndexes.size == board.state.size

  private def isValidGameMode(input: Int): Boolean = input > 0 && input <= 3

  private def isLastInRow(board: Board, space: (Option[Player], Int)): Boolean = (space._2 + 1) % board.size == 0

  private def getCellText(spaceWithIndex: (Option[Player], Int)): String = spaceWithIndex._1 match {
    case None => (spaceWithIndex._2 + 1).toString
    case Some(_) => spaceWithIndex._1.get.mark.toString
  }
}

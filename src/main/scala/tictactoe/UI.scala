package tictactoe

import java.io.PrintStream
import java.util.Scanner
import java.util.regex.Pattern

class UI(input: Scanner = new Scanner(System.in), output: PrintStream = System.out) {

  def getPlayerToken: Char = {
    promptPlayerToken
    val userInput = input.next()
    if (Pattern.matches("[a-zA-Z]", userInput)) {
      userInput.charAt(0).toUpper
    } else {
      printInvalidInputMessage
      getPlayerToken
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

  def update(board: Board) = {
    if (isNewGame(board)) printWelcomeMessage
    print(board)
  }

  private def print(board: Board) ={
    board.state.zipWithIndex.foreach(space => {
      output.print("| " + getCellOutput(space) + " ")
      if (isLastInRow(board, space)) output.print("|\n")
    })
    output.println("")
  }

  private def nextInt: Int = {
    while (!input.hasNextInt()) input.next()

    return input.nextInt()
  }

  private def printWelcomeMessage = output.println("Welcome to Tic Tac Toe")

  private def printInvalidInputMessage = output.println("Invalid input, try again")

  private def promptPlayerToken = output.print("Please choose a letter from a-z as your token: ")

  private def promptGameMode = {
    output.println("Choose a game mode:\n" +
                    "1. Human vs Human\n" +
                    "2. Human vs Computer\n" +
                    "3. Computer vs Computer\n")
  }

  private def isNewGame(board: Board): Boolean = board.emptyIndexes.size == board.state.size

  private def isValidGameMode(input: Int): Boolean = input > 0 && input <= 3

  private def isLastInRow(board: Board, space: (Char, Int)): Boolean = (space._2+1) % board.size == 0

  private def getCellOutput(space: (Char, Int)): String = {
    if (space._1 == '_') (space._2 + 1).toString else space._1.toString
  }
}

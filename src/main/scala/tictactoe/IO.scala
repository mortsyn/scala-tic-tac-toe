package tictactoe

import java.io.PrintStream
import java.util.Scanner

case class IO(input: Scanner = new Scanner(System.in), output: PrintStream = System.out) {

  def nextInt: Int = {
    while (!input.hasNextInt()) {
      input.next()
    }

    return input.nextInt()
  }
}

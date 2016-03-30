package tictactoe
import java.util.Scanner

trait IO {
  val input = new Scanner(System.in)
  val output = System.out

  def nextInt: Int = {
    while (!input.hasNextInt()) {
      input.next()
    }

    return input.nextInt()
  }
}

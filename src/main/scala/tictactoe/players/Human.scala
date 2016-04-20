package tictactoe.players

import tictactoe.Token

case class Human(mark: Token) extends Player {

  override def toString: String = {
    "Human Player"
  }
}

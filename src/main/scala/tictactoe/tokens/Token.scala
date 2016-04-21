package tictactoe.tokens

sealed trait Token

object X extends Token {
  override def toString: String = {
    "X"
  }
}

object O extends Token {
  override def toString: String = {
    "O"
  }
}

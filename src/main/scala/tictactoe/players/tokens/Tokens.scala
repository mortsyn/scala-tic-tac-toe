package tictactoe.players.tokens

trait Token

object X extends Token {
  override def toString = "X"
}

object O extends Token {
  override def toString = "O"
}

object EMPTY extends Token {
  override def toString = "_"
}

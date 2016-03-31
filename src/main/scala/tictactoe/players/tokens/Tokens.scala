package tictactoe.players.tokens

trait Token

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

object EMPTY extends Token {
  override def toString: String = {
    "_"
  }
}

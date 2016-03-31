[![Build Status](https://travis-ci.org/mortsyn/scala-tic-tac-toe.svg?branch=master)](https://travis-ci.org/mortsyn/scala-tic-tac-toe)
# scala-tic-tac-toe

## Dependencies
  * Scala 2.11.8
  * SBT 0.13.8
  * ScalaTest 2.2.6

## Clone the project
`git clone https://github.com/mortsyn/scala-tic-tac-toe.git`

`cd scala-tic-tac-toe`

## Run the tests
From the project root folder, execute the command: 

`sbt test`

## Playing the game

There are three game modes:
1. Human vs Unbeatable Computer
2. Human vs Human
3. Unbeatable Computer vs Unbeatable Computer

To start the game in the Scala Build Tools console, simply type `sbt` in terminal

Once the console loads, you can run the default mode Human vs. Unbeatable Computer by typing `run` into the console.

Optionally, you can also set the game mode you wish to play by passing in one of three options:

* `run --solo` starts up a game with Human vs. Unbeatable Computer
* `run --two-player` starts up a game with Human vs. Human
* `run --watch` lets you watch an Unbeatable Computer vs. Unbeatable Computer
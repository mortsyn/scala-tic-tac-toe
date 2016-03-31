[![Build Status](https://travis-ci.org/mortsyn/scala-tic-tac-toe.svg?branch=master)](https://travis-ci.org/mortsyn/scala-tic-tac-toe)
# scala-tic-tac-toe

## Dependencies
  * Scala 2.11.8
  * SBT 0.13.8
  * ScalaTest 2.2.6
  
## Installing dependencies
You can install [SBT](http://www.scala-sbt.org/) and [Scala](http://www.scala-lang.org/) using [homebrew](http://brew.sh/)

To do this, run:

`brew install scala`

`brew install sbt`

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

To start the game in the Scala Build Tools console, simply type `sbt run` in terminal. This will start the default mode Human vs. Unbeatable Computer.

Optionally, you can also set the game mode you wish to play by passing in one of three options. This requires you to type `sbt` from the terminal and wait for the sbt console loads. You'll know this is done when you will be prompted by the `>` symbol.  

* `run --solo` starts up a game with Human vs. Unbeatable Computer
* `run --two-player` starts up a game with Human vs. Human
* `run --watch` lets you watch an Unbeatable Computer vs. Unbeatable Computer

### Packaging

If you choose to, you can also package it up into a jar for you to run.

From the project root, type the command `sbt package`

Once you can confirm it has packaged up the files in a .jar, it will be located under the project root in

`target/scala-2.11/scalatictactoe_2.11-1.0.jar`

To run this jar, you type `scala path/to/jar` and you can supply the options listed above. 

For example:

`scala target/scala-2.11/scalatictactoe_2.11-1.0.jar --two-player`

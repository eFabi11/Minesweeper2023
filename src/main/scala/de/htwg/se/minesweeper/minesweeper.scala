package de.htwg.se.minesweeper

import model.{Field, Matrix, Symbols, Status, Player, Game}
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.aview.TUI

  object Main {
  def main(args: Array[String]): Unit = {

    var realGame = new Game(Status.Playing)

    val playerOne = realGame.introMinesweeper
    realGame.currentPlayer = playerOne
    val playerName = realGame.currentPlayer.name

    // chooseDifficulty
    val diff = realGame.chooseDifficulty()
    val side = diff(0)
    val bombs = diff(1)
    realGame.bombs = bombs
    realGame.side = side

    //------------- SET UP FOR MVC ------------------
    var leField = new Field(side, Symbols.Covered)
    println("Now MVC starts")
    val controller = Controller(leField, realGame)
    val tui = TUI(controller)
    // let it go
    tui.run()

  }
}
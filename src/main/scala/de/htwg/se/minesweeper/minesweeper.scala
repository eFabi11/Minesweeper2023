package de.htwg.se.minesweeper

import model.{Field, Matrix, Symbols, Player, Game}
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.aview.TUI
import de.htwg.se.minesweeper.model.Status


  object Minesweeper {
  def main(args: Array[String]): Unit = {

    var realGame = new Game(Status.Playing)

    val playerOne = realGame.introMinesweeper
    realGame.currentPlayer = playerOne
    val playerName = realGame.currentPlayer.name

    // chooseDifficulty
    val diff = realGame.setDifficulty()
    val side = diff(0)
    val bombs = diff(1)
    realGame.anzahBomben = bombs
    realGame.side = side

    //------------- SET UP FOR MVC ------------------
    var coveredField = new Field(side, Symbols.Covered)
    //println("Now MVC starts")
    val controller = Controller(coveredField, realGame)
    val tui = TUI(controller)
    // let it go
    tui.run

  }
}
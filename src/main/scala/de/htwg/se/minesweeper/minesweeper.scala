package de.htwg.se.minesweeper

import model.{Field, Matrix, Symbols, Game}
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.aview.TUI
import de.htwg.se.minesweeper.model.Status


object Minesweeper {
  def main(args: Array[String]): Unit = {
    var msGame = new Game(Status.Playing)

    // Setzen der Schwierigkeit, die intern die gridSize und bombCount aktualisiert
    msGame.setDifficulty()

    // Verwenden von gridSize und bombCount aus dem Difficulty-Level des Spiels
    val gridSize = msGame.difficulty.gridSize
    val bombCount = msGame.difficulty.bombCount

    // Initialisieren Sie das Spielfeld mit der entsprechenden Größe und Bombenanzahl
    var coveredField = new Field(gridSize, Symbols.Covered)
    val controller = Controller(coveredField, msGame)
    val tui = TUI(controller)
 
    tui.run
  }
}
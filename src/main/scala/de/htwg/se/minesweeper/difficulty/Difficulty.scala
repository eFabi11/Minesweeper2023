package de.htwg.se.minesweeper.difficulty

import de.htwg.se.minesweeper.model.{Field, Move}
import de.htwg.se.minesweeper.util.Observable
import de.htwg.se.minesweeper.model.Game


object DifficultyLevel {
  sealed trait Level {
    def gridSize: Int
    def bombCount: Int
  }

  case object Easy extends Level {
    val gridSize = 3
    val bombCount = 1 
  }

  case object Medium extends Level {
    val gridSize = 3
    val bombCount = 6 
  }

  case object Hard extends Level {
    val gridSize = 16
    val bombCount = 40 
  }
}
package de.htwg.se.minesweeper

import model.Field
import model.Bombs.intitializeBombs
import model.Matrix
import model.Symbols
import scala.io.StdIn.readLine
import model.Minecheck.isMine
import model.Difficulty.chooseDifficulty
import model.Adjacent.calcAdjacentMines
import model.Move.openField
import de.htwg.se.minesweeper.model.Numbers.initializeAdjacentNumbers
import model.Flag.setFlag
import model.Flag.delFlag
import model.Welcome.welcomeMinesweeper
import model.Status
import model.Covered.calcCovered
import de.htwg.se.minesweeper.model.Next.nextMove
import model.Replace.replaceBombMatrix
import model.Player


  object Main {
  def main(args: Array[String]): Unit = {
    println("Welcome to Minesweeper")
    
    var game = Status.Playing

    val playerOne = welcomeMinesweeper
    val playerName = playerOne.name

    val diff = chooseDifficulty()
    val side = diff(0)
    val bombs = diff(1)

    
    
    val field1 = new Field(10)
    print(field1.toString())
  }
}
package de.htwg.se.minesweeper

import model.Field
import model.SetBombs.intitializeBombs
import model.Matrix
import model.Symbols
import scala.io.StdIn.readLine
import model.IsBomb.isBomb
import model.Difficulty.chooseDifficulty
import model.Number.numberBombs
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

    var game = Status.Playing

    val playerOne = introMinesweeper
    val playerName = playerOne.name


    // chooseDifficulty test
    val diff = chooseDifficulty()
    val side = diff(0)
    val bombs = diff(1)
    //println(s"side: $side bombs: $bombs")

    val visibleMatrix = new Matrix(side, Symbols.Covered)
    val fieldstart = new Field(visibleMatrix)
    print(fieldstart.toString())

    
    val leHiddenMatrix = new Matrix(side, Symbols.Empty)
    val bombhiddenMatrix = intitializeBombs(leHiddenMatrix, bombs)
    val adjacentHiddenMatrix = initializeAdjacentNumbers(bombhiddenMatrix)

    // here we create a Matrix that can be used for replacement after 1st move
    // so it is not possible to step on a bomb at the first time
    val firstMoveReplaceMatrix = new Matrix(side, Symbols.Empty)

    val fieldx = new Field(adjacentHiddenMatrix)
    print(fieldx.toString())

/*     println("Enter x")
    val oldx = scala.io.StdIn.readInt()
    println("Enter y")
    val oldy = scala.io.StdIn.readInt()
    val adjMinesCount = calcAdjacentMines(oldy, oldx, side, bombhiddenMatrix)
    println (s"amount of Adjacent mines at x= $oldx y= $oldy: $adjMinesCount")

    val isMineTest = isMine(oldy, oldx, bombhiddenMatrix)
    println(s"Is there a mine on coordinates $oldx ,$oldy : $isMineTest") */

    println("now make a move: ")
    println("Enter x")
    var x = scala.io.StdIn.readInt()
    println("Enter y")
    var y = scala.io.StdIn.readInt()

/*     var alternate = adjacentHiddenMatrix

    adjacentHiddenMatrix = if(isMine(y, x, adjacentHiddenMatrix)){
      var newHiddenMat = replaceMine(y, x, side, adjacentHiddenMatrix)
      var result = initializeAdjacentNumbers(newHiddenMat)
      result
    } else {
      alternate
    } */

    game = if(adjacentHiddenMatrix.cell(y, x) == Symbols.Bomb){Status.Lost} else if(bombs == calcCovered(bombhiddenMatrix)){Status.Won} else {Status.Playing}

     // condition for replacing bomb
    val newReplacedbombHidMatrix = if(game == Status.Lost){
      // reset status
      game = Status.Playing
      // new Matrix
      //var replacedHiddenMatrix = replaceMine(y, x, side, bombhiddenMatrix)
      var replacedHiddenMatrix = replaceBombMatrix(firstMoveReplaceMatrix, bombs, x, y)
      val replacedAdjacentHiddenMatrix = initializeAdjacentNumbers(replacedHiddenMatrix)
      
/*       println("now make a move: ")
      println("Enter x")
      x = scala.io.StdIn.readInt()
      println("Enter y")
      y = scala.io.StdIn.readInt() */
      replacedAdjacentHiddenMatrix

    } else {
      game = Status.Playing
      val sameMatrix = adjacentHiddenMatrix
      adjacentHiddenMatrix
    }

    //var newVisibleField = openField(x, y, adjacentHiddenMatrix, visibleMatrix)
    var newVisibleField = openField(x, y, newReplacedbombHidMatrix, visibleMatrix)
    val matrixAfterMove = new Field(newVisibleField)
    print(matrixAfterMove.toString())

    while(game != Status.Lost && game != Status.Won){
      //val resTuple = nextMove(game, bombs, adjacentHiddenMatrix, newVisibleField)
      val resTuple = nextMove(game, bombs, newReplacedbombHidMatrix, newVisibleField)
      game = resTuple._1
      var nextMat = resTuple._2
      newVisibleField = nextMat
    }

    if(game == Status.Won) println(s"$playerName you just won!!!") else if (game == Status.Lost) println(s"$playerName you just Lost!!!") else println(s"$playerName you are still playing!!!")

/*     println("now make a move: ")
    println("Enter x")
    val x = scala.io.StdIn.readInt()

    println("Enter y")
    val y = scala.io.StdIn.readInt()

    val newVisibleField = openField(x, y, adjacentHiddenMatrix, visibleMatrix)
    val matrixAfterMove = new Field(newVisibleField)
    print(matrixAfterMove.toString()) */

/*     println("now flag a cell: ")
    println("Enter x")
    val newx = scala.io.StdIn.readInt()

    println("Enter y")
    val newy = scala.io.StdIn.readInt()

    val newFlaggedVisibleField = setFlag(newx, newy, newVisibleField)
    val flaggedMatrix = new Field(newFlaggedVisibleField)
    print(flaggedMatrix.toString()) */



  }
}
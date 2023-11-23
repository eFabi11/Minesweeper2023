package de.htwg.se.minesweeper.model

import scala.io.StdIn.readLine
import scala.util.Random
import de.htwg.se.minesweeper.difficulty.DifficultyLevel

case class Game(var state: Status, var difficulty: DifficultyLevel.Level = DifficultyLevel.Easy) {
    var gameState = Status.Playing

    def gridSize: Int = difficulty.gridSize
    def bombCount: Int = difficulty.bombCount

    def setDifficulty(): Unit = {
        println("Enter the Difficulty Level")
        println("0 for Easy (3x3 grid, 1 bomb)")
        println("1 for Medium (3x3 grid, 6 bombs)")
        println("2 for Hard (16x16 grid, 40 bombs)")

        val level = scala.io.StdIn.readInt()

        difficulty = level match {
            case 0 => DifficultyLevel.Easy
            case 1 => DifficultyLevel.Medium
            case 2 => DifficultyLevel.Hard
            case _ => DifficultyLevel.Medium
        }

        // Neuinitialisierung des Spielfeldes
        resetField()
    }

    def inArea(x: Int, y: Int): Boolean = {
        x >= 0 && x < gridSize && y >= 0 && y < gridSize
    }

    def resetField(): Unit = {
        // Erstellen Sie ein neues Field-Objekt mit der aktuellen gridSize und bombCount
        val newField = new Field(difficulty.gridSize, Symbols.Covered)
        // Weitere Initialisierungslogik...
    }

    def premierMove(x: Int, y: Int): Field = {
        var playerMatrix = new Matrix(gridSize, Symbols.Covered)
        val emptyMatrix = new Matrix(gridSize, Symbols.Empty)
        val bombenMatrix = setB(emptyMatrix, bombCount, x, y)

        val Bombstart = new Field(bombenMatrix)
        println(Bombstart.toString())
        
        playerMatrix = Num(x, y, bombenMatrix, playerMatrix)

        new Field(playerMatrix, bombenMatrix)
    }
     // initialises hiddenMatrix witch is initialises with bombs with adjacent Numbers
    def Num(x : Int, y : Int, bMatrix: Matrix[Symbols], pMatrix: Matrix[Symbols]): Matrix[Symbols] = {


        var tmpMatrix = pMatrix
        val si = bMatrix.size - 1

        if(!(inArea(x, y)) || pMatrix.cell(y, x) != Symbols.Covered){
            return pMatrix
        }        
        var minesFound = 0
        if(isBomb(x+1, y+1, bMatrix)){minesFound += 1}
        if(isBomb(x, y+1, bMatrix)){minesFound+= 1}
        if(isBomb(x-1, y+1, bMatrix)){minesFound+= 1}
        if(isBomb(x+1, y, bMatrix)){minesFound+= 1}
        if(isBomb(x-1, y, bMatrix)){minesFound+= 1}
        if(isBomb(x+1, y-1, bMatrix)){minesFound+= 1}
        if(isBomb(x, y-1, bMatrix)){minesFound+= 1}
        if(isBomb(x-1, y-1, bMatrix)){minesFound+= 1}

        if(minesFound == 0){
            tmpMatrix = tmpMatrix.replaceCell(y, x, Symbols.Empty)
            
            if(inArea(x+1, y+1))
                tmpMatrix = Num(x+1, y+1, bMatrix, tmpMatrix)
            if(inArea(x, y+1))
                tmpMatrix = Num(x, y+1, bMatrix, tmpMatrix)
            if(inArea(x-1, y+1))
                tmpMatrix = Num(x-1, y+1, bMatrix, tmpMatrix)
            if(inArea(x+1, y))
                tmpMatrix = Num(x+1, y, bMatrix, tmpMatrix)
            if(inArea(x-1, y))
                tmpMatrix = Num(x-1, y, bMatrix, tmpMatrix)
            if(inArea(x+1, y-1))
                tmpMatrix = Num(x+1, y-1, bMatrix, tmpMatrix)
            if(inArea(x, y-1))
                tmpMatrix = Num(x, y-1, bMatrix, tmpMatrix)
            if(inArea(x-1, y-1))
                tmpMatrix = Num(x-1, y-1, bMatrix, tmpMatrix)
            
            return tmpMatrix   
        }else{
            val symb = minesFound match {
                            case 0 => Symbols.Zero
                            case 1 => Symbols.One
                            case 2 => Symbols.Two
                            case 3 => Symbols.Three
                            case 4 => Symbols.Four
                            case 5 => Symbols.Five
                            case 6 => Symbols.Six
                            case 7 => Symbols.Seven
                            case 8 => Symbols.Eight
                        }
            tmpMatrix = tmpMatrix.replaceCell(y, x, symb)
        }
        return tmpMatrix
    }

    def setB(emptyMatrix: Matrix[Symbols], bombCount: Int, x: Int, y: Int): Matrix[Symbols] = {
        val verboten = (y, x)
        var bombsMatrix = emptyMatrix
        val random = new Random()

        var placedBombs = 0
        while (placedBombs < bombCount) {
            val xPos = random.nextInt(gridSize)
            val yPos = random.nextInt(gridSize)
            val tuple = (yPos, xPos)

            if (bombsMatrix.cell(yPos, xPos) != Symbols.Bomb && tuple != verboten) {
                bombsMatrix = bombsMatrix.replaceCell(yPos, xPos, Symbols.Bomb)
                placedBombs += 1
            }
        }
        bombsMatrix
    }

    def isBomb(x: Int, y: Int, m: Matrix[Symbols]): Boolean = {
        val si = m.size-1
        if(inArea(x, y)){
            if(m.cell(y, x) == Symbols.Bomb){
                return true
            }
        }
        return false
    }

    def checkGameState(realgame: Game) =
      if(this.gameState == Status.Won) println("you just won!!!") else if (this.gameState == Status.Lost) println("you just Lost!!!") else print("")

    }
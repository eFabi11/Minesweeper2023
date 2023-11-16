package de.htwg.se.minesweeper.model

import scala.io.StdIn.readLine
import scala.util.Random
import de.htwg.se.minesweeper.model.Field

case class Game(var state: Status):

    var bombs = 0
    var side = 0
    var gameState = Status.Playing
    var currentPlayer = Player("",0)

    // gives back a tuple with (side, bombs), if input is incorrect -> level easy by default
    def chooseDifficulty() = {
        val multilineString = """Enter the Difficulty Level
Press 0 for SUPEREASY (3 * 3 Cells and 1 Mine)
Press 1 for BEGINNER (9 * 9 Cells and 10 Mines)
Press 2 for INTERMEDIATE (16 * 16 Cells and 40 Mines)
Press 3 for ADVANCED (20 * 20 Cells and 85 Mines)"""

        println(multilineString)

        val level = scala.io.StdIn.readInt()
        level match {
            case 0 => (3, 1)
            case 1 => (9, 10)
            case 2 => (16, 40)
            case 3 => (20, 85)
            case _ => (9, 10)
        }
    }

    // Instructions
    def helpMessage = 
      val helpMsg = """******************************************************************************
* This is Minesweeper HELP - MENU                                            *
* Enter your move (<action><x><y>, eg. o02, q to quit, h for help):          *
*                                                                            *
* Possible moves: o03 ----> opens cell on coordinates x=0, y=3               *
*                 f22 ----> flags cell on coordinates x=2, y=2               *
*                 u22 ----> removes flag at cell on coordinates x=2, y=2     *
*                 r   ----> reaveals field                                   *
*                 h   ----> help                                             *
*                 q   ----> quits game                                       *
*                                                                            *
* Copyright: Steve Madoerin                                                  *
*                                                                            *
******************************************************************************"""
      println(s"$helpMsg")

    def introMinesweeper: Player = {
        println("Welcome to Minesweeper:\nEnter your Name")
        val name = readLine()
        println("Enter your Age")
        val age = scala.io.StdIn.readInt()
        println(s"Hi $name ($age). Lets Play!")
        val newPlayer = new Player(name, age)
        newPlayer
    }

/*     // Function to display hiddenfield: for Development purposes
    def displayHiddenField(mat: Matrix[Symbols]) = 
      val fieldx = new Field(mat)
      print(fieldx.toString()) */
    
    def decide(x: Int, y: Int, gam: Game, adjacentMatrix: Matrix[Symbols]): Matrix[Symbols] = {

        val success = if(gam.gameState == Status.Lost){

        
            gam.gameState = Status.Playing // reset status
            val firstMoveReplaceMatrix = new Matrix(gam.side, Symbols.Empty) // Matrix for replacement
            // new Matrix // -> TODO Field.replace
            var replacedHiddenMatrix = replaceBombMatrix(firstMoveReplaceMatrix, gam.bombs, x, y)
            val replacedAdjacentHiddenMatrix = initializeAdjacentNumbers(replacedHiddenMatrix)
            replacedAdjacentHiddenMatrix
        } else {
            gam.gameState = Status.Playing
            val sameMatrix = adjacentMatrix
            adjacentMatrix
        }
      
        success

    }

    //
    // Function for 1st Move
    def premierMove(x: Int, y: Int, field: Field, game: Game): Field = {
      // shows covered playField
      val visibleMatrix = new Matrix(side, Symbols.Covered)
      val fieldstart = new Field(side, Symbols.Covered)
      var (adjacentHiddenMatrix, bombhiddenMatrix) = initaliseInvisibleMatrix(side, bombs)

      // matrux fir replacement after 1st move -> not possible to step on bomb at the first time
      val firstMoveReplaceMatrix = new Matrix(side, Symbols.Empty)
      
      //displayHiddenField(adjacentHiddenMatrix) // to display hidden field -----> DISPLAY HIDDENFIELD <---------

      game.gameState = if(adjacentHiddenMatrix.cell(y, x) == Symbols.Bomb){Status.Lost} else if(bombs == addCoveredAndFlag(bombhiddenMatrix)){Status.Won} else {Status.Playing}

      // condition for replacing bomb
      val newReplacedbombHidMatrix = decide(x, y, game, adjacentHiddenMatrix)

      var newVisibleField = openField(x, y, newReplacedbombHidMatrix, visibleMatrix)
      val matrixAfterMove = new Field(newVisibleField, newReplacedbombHidMatrix)
      matrixAfterMove
    }

    // opens a covered field -> replaces covered Field from visibleMatrix with Field from hiddenMatrix and returns this new visible Matrix
    def openField(x: Int, y: Int, hiddenMatrix: Matrix[Symbols], leVisibleMatrix: Matrix[Symbols]): Matrix[Symbols] = {
        var visMat = leVisibleMatrix
        val replacor = hiddenMatrix.cell(y, x)
        visMat = visMat.replaceCell(y, x, replacor)
        visMat
    }

    def checkGameState(field: Field): Unit = {
        state = if (field.allMinesUncovered()) Status.Won
                else if (field.anyMineExploded()) Status.Lost
                else Status.Playing

        if(state != Status.Playing) {
            println(s"Game Over: $state")
        }
    }

    def calcCovered(visibleMatrix: Matrix[Symbols]): Int =
        val sizze = visibleMatrix.size-1
        val multiIndex = 0 to sizze // define range of valid indices
        multiIndex
            .flatMap(row => multiIndex.map(col => (row, col)))
            .count{ case(row, col) => visibleMatrix.cell(row, col) == Symbols.Covered && isValid(row, col, sizze)}

    def calcFlag(visibleMatrix: Matrix[Symbols]): Int =
        val sizze = visibleMatrix.size-1
        val multiIndex = 0 to sizze
        multiIndex
            .flatMap(row => multiIndex.map(col => (row, col))) //.flatMap and .map to create a new collection of all possible (row, col) pairs
            .count { case (row, col) => visibleMatrix.cell(row, col) == Symbols.F && isValid(row, col, sizze) } // count pairs that meet the condition

    def addCoveredAndFlag(visibleMatrix: Matrix[Symbols]): Int =
        val sum =  calcCovered(visibleMatrix) + calcFlag(visibleMatrix)
        sum

    // calculates the adjacentMines for location row,col
    def calcAdjacentMines(row: Int, col: Int, side: Int, invisibleMatrix: Matrix[Symbols]): Int = {
        
        var count = 0
        val side2 = side

        if(isValid(row-1, col, side2) && isMine(row-1, col, invisibleMatrix)) {count += 1} // 1ST NEIGHBOR : NORTH
        if(isValid(row+1, col, side2) && isMine(row+1, col, invisibleMatrix)) {count += 1} // 2ND NEIGHBOR : SOUTH
        if(isValid(row, col+1, side2) && isMine(row, col+1, invisibleMatrix)) {count += 1} // 3RD NEIGHBOR : EAST
        if(isValid(row, col-1, side2) && isMine(row, col-1, invisibleMatrix)) {count += 1} // 4TH NEIGHBOR : WEST
        if(isValid(row-1, col+1, side2) && isMine(row-1, col+1, invisibleMatrix)) {count += 1} // 5TH NEIGHBOR: NORTH-EAST
        if(isValid(row-1, col-1, side2) && isMine(row-1, col-1, invisibleMatrix)) {count += 1} // 6TH NEIGHBOR: NORTH-WEST
        if(isValid(row+1, col+1, side2) && isMine(row+1, col+1, invisibleMatrix)) {count += 1} // 7TH NEIGHBOR: SOUTH-EAST
        if(isValid(row+1, col-1, side2) && isMine(row+1, col-1, invisibleMatrix)) {count += 1} // 8TH NEIGHBOR: SOUTH-WEST

        count // last function or val, is return value in scala
    }
    
    // check if cell at location x,y has bomb or not
    def isMine(row: Int, col: Int, m: Matrix[Symbols]): Boolean = {if(m.cell(row, col) == Symbols.Bomb) true else false}

    // utility function to check is cell(row, col) is valid or not
    def isValid(row: Int, col: Int, side: Int): Boolean = {row >= 0 && row <= side && col >= 0 && col <= side}



    // initialises hiddenMatrix witch is initialises with bombs with adjacent Numbers
    def initializeAdjacentNumbers(matrix: Matrix[Symbols]): Matrix[Symbols] =
        val si = matrix.size - 1
        val multiIndex = 0 to si // define range of valid indices
        multiIndex
            .flatMap(col => multiIndex.map(row => (row, col))) // .flatMap and .map to create collection of all possible (row, col) pairs
            .foldLeft(matrix) { // .foldLeft to iterate over each pair & update Matrix
                (matr, pos) => val (row, col) = pos
                if(matr.cell(row, col) != Symbols.Bomb && isValid(row, col, si)) {
                    val numero = calcAdjacentMines(row, col, si, matrix)
                    val symb = numero match{
                        case 0 => Symbols.Zero
                        case 1 => Symbols.One
                        case 2 => Symbols.Two
                        case 3 => Symbols.Three
                        case 4 => Symbols.Four
                        case 5 => Symbols.Five
                        case 6 => Symbols.Six
                        case 7 => Symbols.Seven
                        case 8 => Symbols.Eight
                        //case _ => Symbols.Empty
                    }
                    matr.replaceCell(row, col, symb)
                } else matr
            }

    // initialises hiddenMatrix with Bombs with given amount of bombs - randomly
    def intitializeBombs(matrix: Matrix[Symbols], bombs: Int): Matrix[Symbols] = {
        var ma = matrix
        val sizze = ma.size-1
        var minesPlaced = 0
        val random = new Random()
        while (minesPlaced < bombs){
            // randomly sets row and col
            val row = random.nextInt(sizze)
            val col = random.nextInt(sizze)
            // if no bomb is on this specific cell, put a bomb on it
            if (ma.cell(row, col) != Symbols.Bomb){
                ma = ma.replaceCell(row, col, Symbols.Bomb)
                minesPlaced += 1
            }
        }
        return ma
    }

    // replaces the whole matrix and and guarantees that the player doesn't click on a bomb at first move
    def replaceBombMatrix(matrix: Matrix[Symbols], bombs: Int, x: Int, y: Int): Matrix[Symbols] = {
        var ma = matrix
        val sizze = ma.size-1
        var minesPlaced = 0
        val random = new Random()
        while (minesPlaced < bombs){
            val row = random.nextInt(sizze)
            val col = random.nextInt(sizze)
            // if no bomb on  specific cell, put a bomb, exept on cell(y,x)
            if (ma.cell(row, col) != Symbols.Bomb && ma.cell(y, x) != ma.cell(row, col)){
                ma = ma.replaceCell(y, x, Symbols.Empty)
                ma = ma.replaceCell(row, col, Symbols.Bomb)
                minesPlaced += 1
            }
        }
        ma
    }

    // Parameter: si = side, bo = bombs, return value = Tuple(matrix(numbers&bombs), matrix(bombs))
    def initaliseInvisibleMatrix(si: Int, bo: Int ): (Matrix[Symbols], Matrix[Symbols]) = {
      val leHiddenMatrixFunc = new Matrix(si, Symbols.Empty)
      val bombhiddenMatrixFunc = intitializeBombs(leHiddenMatrixFunc, bo)
      val adjacentHiddenMatrixFunc = initializeAdjacentNumbers(bombhiddenMatrixFunc)

      (adjacentHiddenMatrixFunc, bombhiddenMatrixFunc)
    }
    
    def checkGameState(realgame: Game) =
      if(this.gameState == Status.Won) println(s"${currentPlayer.name} you just won!!!") else if (this.gameState == Status.Lost) println(s"${currentPlayer.name} you just Lost!!!") else print("")
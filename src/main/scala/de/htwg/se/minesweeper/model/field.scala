package de.htwg.se.minesweeper.model


// Field extended with 2 parameters (matrix: Matrix[Symbols], hidden: Matrix[Symbols])
case class Field(matrix: Matrix[Symbols], hidden: Matrix[Symbols]):
    // second constructor 
    def this(size: Int, filling: Symbols)= this(new Matrix(size, filling), new Matrix(size, Symbols.Empty))
    val size = matrix.size
    // third constructor
    def this(matrix: Matrix[Symbols])= this(matrix, matrix)
    var visibleMatrix = matrix // Matrix, which the player sees
    var invisibleMatrix = hidden // Matrix, with bombs and numbers, which is hidden from the Player 

    val endl = sys.props("line.separator")
    // defines the bar
    def bar(cellWidth: Int = 3, cellNum: Int = 3) = (("+" + "-" * cellWidth) * cellNum) + "+" + endl
    // defines the cells ("|" + " " * cellWidth) * cellNum + "|" + endl
    def cells(row: Int = 3, cellWidth: Int = 3) = matrix.row(row).map(_.toString).map(" " * ((cellWidth-1)/2) + _ + " " *((cellWidth -1)/2)).mkString("|","|","|") + endl
    // defines the grid and default size is 10x10 field def mesh(cellWidth: Int = 3, cellNum: Int = size) = (bar(cellWidth, cellNum) + cells(cellWidth, cellNum)) * cellNum + bar(cellWidth, cellNum)
    def mesh(cellWidth: Int = 3) = 
        (0 until size).map(cells(_, cellWidth)).mkString(bar(cellWidth, size), bar(cellWidth, size), bar(cellWidth, size))
    // Flags the specified cell
    def putFlag(x: Int, y: Int) = copy(matrix.replaceCell(y, x, Symbols.F))
   // deletes Flag
    def removeFlag(x: Int, y: Int) = copy(matrix.replaceCell(y, x, Symbols.Covered))
    // only open Field function without checking if game is won or not
    def openF(x: Int, y: Int, hiddenMatrix: Matrix[Symbols], leVisibleMatrix: Matrix[Symbols]): Matrix[Symbols] = {
        var visMat = leVisibleMatrix
        val replacor = hiddenMatrix.cell(y, x)
        visMat = visMat.replaceCell(y, x, replacor)
        visMat
    }
    // takes a funktion as Parameter :) (openF)
    def openFi(x: Int, y: Int, spiel: Game, oF:(Int, Int, Matrix[Symbols], Matrix[Symbols]) => Matrix[Symbols]): Field= 
        spiel.gameState = if(invisibleMatrix.cell(y, x) == Symbols.Bomb){Status.Lost} else if(spiel.bombs == spiel.addCoveredAndFlag(visibleMatrix)-1){Status.Won} else {Status.Playing}
        val nextMat = oF(x, y, invisibleMatrix, visibleMatrix)
        visibleMatrix = nextMat
        val nextField = new Field(visibleMatrix, invisibleMatrix)
        nextField
    // open call a function (openFl) who has another function (openF) as parameter
    def open(x: Int, y: Int, spiel: Game): Field = openFi(x, y, spiel, openF)
    // reveals field -> cheat Option
    def reveal =
        val revMat = new Field(this.hidden)
        println(revMat.toString())
        
    def allMinesUncovered(): Boolean = {
        val isEveryMineFlagged = (0 until size).forall { row =>
            (0 until size).forall { col =>
                hidden.cell(row, col) != Symbols.Bomb || visibleMatrix.cell(row, col) == Symbols.F
            }
        }
        val isEveryNonMineCellUncovered = (0 until size).forall { row =>
            (0 until size).forall { col =>
                hidden.cell(row, col) == Symbols.Bomb || visibleMatrix.cell(row, col) != Symbols.Covered
            }
        }
        isEveryMineFlagged && isEveryNonMineCellUncovered
    }

    def anyMineExploded(): Boolean = {
        (0 until size).exists { row =>
            (0 until size).exists { col =>
                hidden.cell(row, col) == Symbols.Bomb && visibleMatrix.cell(row, col) != Symbols.Covered
            }
        }
    }



    // used for printing field in main
    override def toString(): String = mesh()
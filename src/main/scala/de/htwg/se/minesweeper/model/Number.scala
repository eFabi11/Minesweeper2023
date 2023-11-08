package de.htwg.se.minesweeper.model
import de.htwg.se.minesweeper.model.Area.inArea
import de.htwg.se.minesweeper.model.Checkifbomb.isBomb
import de.htwg.se.minesweeper.model.Symbols
import de.htwg.se.minesweeper.model.NoNumber.noNum
import scala.collection.immutable.Set



object Number:

    
    def Num(x : Int, y : Int, bMatrix: Matrix[Symbols], pMatrix: Matrix[Symbols] , bSet: (Set[(Int, Int)])): (Set[(Int, Int)], Matrix[Symbols]) = {


    var tmpMatrix = pMatrix
    var Tuple: (Int, Int) = (x, y)
    val si = bMatrix.size - 1


    if(!(inArea(x, y, si)) || bSet.contains(Tuple)){
        return(bSet,pMatrix)
    }

        var tmpSet = bSet + Tuple

    
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
        val resTupleTmp = noNum(x, y, bMatrix, tmpMatrix, tmpSet)
            tmpSet = resTupleTmp._1
            tmpMatrix = resTupleTmp._2

        
        return(tmpSet, tmpMatrix)

            
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
                        //case _ => Symbols.Empty
                    }

        tmpMatrix = tmpMatrix.replaceCell(y, x, symb)
        (tmpSet,tmpMatrix)
    }
    return(tmpSet,tmpMatrix)
}
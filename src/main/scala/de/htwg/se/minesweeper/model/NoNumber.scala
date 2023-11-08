package de.htwg.se.minesweeper.model
import de.htwg.se.minesweeper.model.Number.Num
import scala.collection.immutable.Set
import de.htwg.se.minesweeper.model.Symbols
import de.htwg.se.minesweeper.model.Area.inArea

object NoNumber:
    // check if cell at location x,y has bomb or not
    
    def noNum(x : Int, y : Int , b2Matrix: Matrix[Symbols], p2Matrix: Matrix[Symbols], b2Set : Set[(Int, Int)]): (Set[(Int, Int)], Matrix[Symbols]) = {
    var noNumSet = b2Set
    var noNumMatrix = p2Matrix
    val si = b2Matrix.size - 1
    //val resTuple1: (Set[(Int, Int)], Matrix[String])

    if(inArea(x+1, y+1, si)){
        val resTuple1 = Num(x+1, y+1, b2Matrix, p2Matrix, noNumSet)
            noNumSet = resTuple1._1
            noNumMatrix = resTuple1._2
    }
    if(inArea(x, y+1, si)){
        val resTuple2 = Num(x, y+1, b2Matrix, noNumMatrix, noNumSet)
            noNumSet = resTuple2._1
            noNumMatrix = resTuple2._2
    }
    if(inArea(x-1, y+1, si)){
        val resTuple3 = Num(x-1, y+1, b2Matrix, noNumMatrix, noNumSet)
            noNumSet = resTuple3._1
            noNumMatrix = resTuple3._2
    }
    if(inArea(x+1, y, si)){
        val resTuple4 = Num(x+1, y, b2Matrix, noNumMatrix, noNumSet)
            noNumSet = resTuple4._1
            noNumMatrix = resTuple4._2
    }
    if(inArea(x-1, y, si)){
        val resTuple5 = Num(x-1, y, b2Matrix, noNumMatrix, noNumSet)
            noNumSet = resTuple5._1
            noNumMatrix = resTuple5._2
    }
    if(inArea(x+1, y-1, si)){
        val resTuple6 = Num(x+1, y-1, b2Matrix, noNumMatrix, noNumSet)
            noNumSet = resTuple6._1
            noNumMatrix = resTuple6._2
    }
    if(inArea(x, y-1, si)){
        val resTuple7 = Num(x, y-1, b2Matrix, noNumMatrix, noNumSet)
            noNumSet = resTuple7._1
            noNumMatrix = resTuple7._2
    }
    if(inArea(x-1, y-1, si)){
        val resTuple8 = Num(x-1, y-1, b2Matrix, noNumMatrix, noNumSet)
            noNumSet = resTuple8._1
            noNumMatrix = resTuple8._2
    }
    (noNumSet, noNumMatrix)

    }
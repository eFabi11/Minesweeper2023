package de.htwg.se.minesweeper.model

import scala.util.Random

// singelton
object Setbombs:

    def setB(emty1Matrix: Matrix[Symbols], anzahlBomben: Int, x: Int, y: Int): Matrix[Symbols] = {
      
        var BombsMatrix = emty1Matrix
        val sizeM = emty1Matrix.size -1
        var AnzahlPlaziert : Int = 0
        var BombSet: Set[(Int, Int)] = Set((y, x))
        val random = new Random()
        while(AnzahlPlaziert < anzahlBomben){
            val x : Int = random.nextInt(sizeM)
            val y : Int = random.nextInt(sizeM)
            val tupel = (y, x)
            if(!(BombSet.contains(tupel))){
                BombsMatrix = BombsMatrix.replaceCell(y, x, Symbols.Bomb)
                AnzahlPlaziert += 1
            }
        }
        return BombsMatrix
    }
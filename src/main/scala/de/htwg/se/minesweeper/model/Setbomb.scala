package de.htwg.se.minesweeper.model

import scala.util.Random

// singelton
object Setbombs:
    // initialises hiddenMatrix with Bombs with given amount of bombs - randomly
    def setB(emty1Matrix: Matrix[Symbols], anzahlBomben: Int, x: Int, y: Int): Matrix[Symbols] = {
      
        var BombsMatrix = emty1Matrix
        val sizeM = emty1Matrix.size -1
        var AnzahlPlaziert : Int = 0
        var BombSet: Set[(Int, Int)] = Set((x, y))
        val random = new Random()
        while(AnzahlPlaziert < anzahlBomben){
            val x : Int = random.nextInt(sizeM)
            val y : Int = random.nextInt(sizeM)
            val tupel = (x, y)
            if(!(BombSet.contains(tupel))){
                BombsMatrix = BombsMatrix.replaceCell(x, y, Symbols.Bomb)
                AnzahlPlaziert += 1
            }
        }
        return BombsMatrix
    }
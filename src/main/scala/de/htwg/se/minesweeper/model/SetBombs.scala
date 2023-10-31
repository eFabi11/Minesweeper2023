package de.htwg.se.minesweeper.model

import scala.util.Random

// singelton
object SetBombs:
    // initialises hiddenMatrix with Bombs with given amount of bombs - randomly
    def intitializeBombs(matrix: Matrix[Symbols], bombs: Int): Matrix[Symbols] = {
        var TmpMatrix = matrix
        val sizze = ma.size-1
        var minesPlaced = 0
        val random = new Random()
        while (minesPlaced < bombs){
            // randomly sets row and col
            val row = random.nextInt(sizze)
            val col = random.nextInt(sizze)
            // if no bomb is on this specific cell, put a bomb on it
            if (TmpMatrix.cell(row, col) != Symbols.Bomb){
                TmpMatrix = TmpMatrix.replaceCell(row, col, Symbols.Bomb)
                minesPlaced += 1
            }
        }
        return TmpMatrix
    }
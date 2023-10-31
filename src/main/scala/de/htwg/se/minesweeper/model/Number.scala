package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.model.IsValid.isValid
import de.htwg.se.minesweeper.model.IsBomb.isBomb



object Number:
    // calculates the adjacentMines for location row,col
    def numberBombs(row: Int, col: Int, side: Int, invisibleMatrix: Matrix[Symbols]): Int = {
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
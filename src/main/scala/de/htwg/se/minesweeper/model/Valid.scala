package de.htwg.se.minesweeper.model

object Valid:
    //validate if the cell and row is correct
    def isValid(row: Int, col: Int, side: Int): Boolean = {row >=0 && row <= side && col >=0 && col <= side}
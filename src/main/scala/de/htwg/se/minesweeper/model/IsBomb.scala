package de.htwg.se.minesweeper.model


object IsBomb:
    // check if cell at location x,y has bomb or not
    def isBomb(row: Int, col: Int, m: Matrix[Symbols]): Boolean = {if(m.cell(row, col) == Symbols.Bomb) true else false}
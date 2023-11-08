package de.htwg.se.minesweeper.model

object Area:
    // utility function to check is cell(row, col) is valid or not
    def inArea(row: Int, col: Int, side: Int): Boolean = {row >= 0 && row <= side && col >= 0 && col <= side}
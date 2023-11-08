package de.htwg.se.minesweeper.model
import de.htwg.se.minesweeper.model.Area.inArea


object Checkifbomb:
    // check if cell at location x,y has bomb or not
    
    def isBomb(row: Int, col: Int, m: Matrix[Symbols]): Boolean = {
        val si = m.size-1//-1 ka woher lol
        if(inArea(row,col,si)){
            if(m.cell(row, col) == Symbols.Bomb){
                return true
            }
        }
        return false
    }

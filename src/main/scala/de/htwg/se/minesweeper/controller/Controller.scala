package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.{Field, Move, Game}
import de.htwg.se.minesweeper.util.Observable

case class Controller(var field: Field, var game: Game) extends Observable {
    def uncoverField(x: Int, y: Int): Unit = {
        field = field.open(x, y, game)
        game.checkGameState(field)
        notifyObservers()
    }

    def flag(x: Int, y: Int): Unit = {
        field = field.putFlag(x, y)
        notifyObservers()
    }

    def unflag(x: Int, y: Int): Unit = {
        field = field.removeFlag(x, y)
        notifyObservers()
    }

    def helpMenu: Unit = game.helpMessage

    def cheat: Unit = {
        field.reveal
        notifyObservers()
    }
}
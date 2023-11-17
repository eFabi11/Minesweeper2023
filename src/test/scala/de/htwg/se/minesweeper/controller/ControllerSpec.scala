package de.htwg.se.minesweeper.controller

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.model.Field
import de.htwg.se.minesweeper.model.Symbols
import de.htwg.se.minesweeper.model.Game
import de.htwg.se.minesweeper.model.Status

class ControllerSpec extends AnyWordSpec{

    "The Controller" should {

        "when observed by an Observer" should{
            val emptyField = new Field(3, Symbols.Empty)
            val newGame = new Game(Status.Playing)
        }
    }
}
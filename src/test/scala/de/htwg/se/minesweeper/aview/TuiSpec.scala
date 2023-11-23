package de.htwg.se.minesweeper
package aview


import controller.Controller
import model.Status
import model.Field
import model.Matrix
import model.Symbols
import model.Game
import model.Move
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec




class TuiSpec extends AnyWordSpec {

  "The TUI" should {
    var msGame = new Game(Status.Playing)


    val diff = msGame.setDifficulty()
    val side = diff(0)
    val bombs = diff(1)
    msGame.anzahBomben = bombs
    msGame.side = side

    var coveredField = new Field(side, Symbols.Covered)

    val controller = Controller(coveredField, msGame)
    "recognize the input o11 as move open Field (1,1)" in {
        val tui1 = TUI(controller)
        tui1.userIn2("o11") should be (Some(Move("open", 1, 1)))
    }
  }
}

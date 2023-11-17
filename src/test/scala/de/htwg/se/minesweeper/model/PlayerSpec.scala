package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec{
    "Le Player" should {
        val testPlayer = new Player("Fabi", 25)
        "have a name" in {
            testPlayer.name should be ("Fabi")
        }
        "have an age" in {
            testPlayer.age should be (25)
        }
    }
}
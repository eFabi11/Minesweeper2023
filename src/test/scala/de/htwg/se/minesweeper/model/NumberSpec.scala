package de.htwg.se.minesweeper.model

import java.io.{ByteArrayOutputStream, PrintStream}
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.model.Number.Num
import de.htwg.se.minesweeper.model.Symbols

class NumberSpec extends AnyWordSpec{
    "Number" should{

        val side3 = 3
        val testMatrix = new Matrix(side3, Symbols.Empty)
        val testBombMatrix = testMatrix.replaceCell(1, 1, Symbols.Bomb)
        var testPlayerMatirx = new Matrix(side3, Symbols.Covered)
        var testAnzahlcoverd : Int = 0
        
        "have a Mine count" in{
            val testTupelN = Number(0, 0, testBombMatrix, testPlayerMatrix, testAnzahlcoverd)
                testPlayerMatrix = testTupelN._1
                testAnzahlcoverd = testTupelN._2

            testAnzahlcoverd should be (1)

            val testTupelN2 = Number(0, 1, testBombMatrix, testPlayerMatrix, testAnzahlcoverd)
                testPlayerMatrix = testTupelN2._1
                testAnzahlcoverd = testTupelN2._2

                testAnzahlcoverd should be (2)

            val testTupelN3 = Number(2, 2, testBombMatrix, testPlayerMatrix, testAnzahlcoverd)
                testPlayerMatrix = testTupelN3._1
                testAnzahlcoverd = testTupelN3._2

            testAnzahlcoverd should be (3)
            testPlayerMatrix.cell(0,0) should be (Symbols.One)
            testPlayerMatrix.cell(0,1) should be (Symbols.One)
            testPlayerMatrix.cell(2,2) should be (Symbols.One)
            testPlayerMatrix.cell(1,1) should be (Symbols.Covered)
            testBombMatrix.cell(1,1) should be (Symbols.Bomb)


        }
    }
}
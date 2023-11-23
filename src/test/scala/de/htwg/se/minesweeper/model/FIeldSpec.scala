package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec 
{
    "A minesweeper Field" when 
    {
        "is empty" should 
        {
            val fieldOne = new Field(1, Symbols.Empty)
            val field2 = new Field(2, Symbols.Empty)
            val field3 = new Field(3, Symbols.Empty)

            val endl = sys.props("line.separator")
            "have a bar as String of form '+---+---+---+'" in 
            {
                field3.bar() should be("+---+---+---+" + endl)
            }
            "also have a bar as String of form '+---+---+---+'" in 
            {
                field2.bar() should be("+---+---+---+" + endl)
            }
            "still have a bar as String of form '+---+---+---+'" in 
            {
                fieldOne.bar() should be("+---+---+---+" + endl)
            }
            "have a scalable bar" in 
            {
                fieldOne.bar(1,1) should be("+-+" + endl)
                field2.bar(2,1) should be("+--+" + endl)
                field2.bar(1,2) should be("+-+-+" + endl)
                field3.bar(3,3) should be("+---+---+---+" + endl)
            }
            "have cells as String of form '|   |   |   |'" in 
            {
                field3.cells(0) should be ("|   |   |   |" + endl)
            }
            "have scalable cells" in 
            {
                fieldOne.cells(0, 1) should be("| |" + endl)
                field2.cells(0, 1) should be("| | |" + endl)
                field3.cells(2,3) should be("|   |   |   |" + endl)
                fieldOne.cells(0,3) should be("|   |" + endl)
            }
            "have a mesh in the form" + 
            "+-+" + 
            "| |" + 
            "+-+" in 
            {
                fieldOne.mesh(1) should be("+-+" + endl + "| |" + endl + "+-+" + endl)
                field3.mesh(1) should be("+-+-+-+" + endl + "| | | |" + endl + "+-+-+-+" + endl + "| | | |" + endl + "+-+-+-+" + endl + "| | | |" + endl + "+-+-+-+" + endl )
                field2.mesh(1) should be("+-+-+" + endl + "| | |" + endl + "+-+-+" + endl + "| | |" + endl + "+-+-+" + endl)
            }
            "filled with Empty" should 
            {
                val field = new Field(3, Symbols.Empty)
                "be empty initially with Field(Symbols.Empty)" in 
                {
                     field.toString() should be("+---+---+---+" + endl + "|   |   |   |" + endl + "+---+---+---+" + endl + "|   |   |   |" + endl + "+---+---+---+" + endl + "|   |   |   |" + endl + "+---+---+---+" +endl)
                }
            }


        }
        "Num" should {
                val testGame9 = new Game(Status.Playing)
                val side3 = 3
                var testMatrix = new Matrix[Symbols](side3, Symbols.Empty)
                val testBombMatrix = testMatrix.replaceCell(1, 1, Symbols.Bomb)
                var testPlayerMatrix = new Matrix(side3, Symbols.Covered)

                "have a Mine count" in{
                    testPlayerMatrix = testGame9.Num(0, 0, testBombMatrix, testPlayerMatrix)

                        testPlayerMatrix.cell(0,0) should be (Symbols.One)
                        testPlayerMatrix.cell(1,0) should be (Symbols.Covered)
                        testPlayerMatrix.cell(2,2) should be (Symbols.Covered)
                        testPlayerMatrix.cell(1,1) should be (Symbols.Covered)
                        testBombMatrix.cell(1,1) should be (Symbols.Bomb)

                    testPlayerMatrix = testGame9.Num(0, 1, testBombMatrix, testPlayerMatrix)
                        
                        testPlayerMatrix.cell(0,0) should be (Symbols.One)
                        testPlayerMatrix.cell(1,0) should be (Symbols.One)
                        testPlayerMatrix.cell(2,2) should be (Symbols.Covered)
                        testPlayerMatrix.cell(1,1) should be (Symbols.Covered)
                        testBombMatrix.cell(1,1) should be (Symbols.Bomb)


                    testPlayerMatrix = testGame9.Num(2, 2, testBombMatrix, testPlayerMatrix)
                        

                        testPlayerMatrix.cell(0,0) should be (Symbols.One)
                        testPlayerMatrix.cell(1,0) should be (Symbols.One)
                        testPlayerMatrix.cell(2,2) should be (Symbols.One)
                        testPlayerMatrix.cell(1,1) should be (Symbols.Covered)
                        testBombMatrix.cell(1,1) should be (Symbols.Bomb)

                
                }
        }

        "Minecheck" should{
            val testGame6 = new Game(Status.Playing)
            val side4 = 3
            val mineCheckMatrix = new Matrix(side4, Symbols.Empty)
            val mineCheckMatrix2 = mineCheckMatrix.replaceCell(1, 1, Symbols.Bomb)

            "check if cell at location x,y has bomb or not" in{
                val boolCheckIsMine = testGame6.isBomb(1, 1, mineCheckMatrix2)
                val boolCheckIsMine2 = testGame6.isBomb(0, 0, mineCheckMatrix2)
                boolCheckIsMine should be (true)
                boolCheckIsMine2 should be (false)
            }
        }

        "inArea" should{

            val side3 = 2
            val testGame11 = new Game(Status.Playing)
            var testMatrix = new Matrix[Symbols](side3+1, Symbols.Empty)
            val testBombMatrix = testMatrix.replaceCell(0, 0, Symbols.Bomb)
            var testPlayerMatrix = new Matrix(side3, Symbols.Covered)
            var testAnzahlcoverd : Int = 0

            "check if xy is in Area" in{
                testGame11.inArea(0, 0, side3) should be (true)
                testGame11.inArea(1, 1, side3) should be (true)
                testGame11.inArea(2, 2, side3) should be (true)
                testGame11.inArea(2, 0, side3) should be (true)
                testGame11.inArea(3, 0, side3) should be (false)
                testGame11.inArea(3, 3, side3) should be (false)
                testGame11.inArea(2, 4, side3) should be (false)
                testGame11.inArea(-1, 0, side3) should be (false)
            }
        }

    }
}
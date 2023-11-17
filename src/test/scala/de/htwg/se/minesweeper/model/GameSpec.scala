package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import java.io.ByteArrayOutputStream

class GameSpec extends AnyWordSpec {
    
    "The setDifficulty method" should {
        val testGame = new Game(Status.Playing)
        "return a tuple representing the chosen difficulty" in {
            val in = new java.io.ByteArrayInputStream("0\n".getBytes)
            Console.withIn(in){
                val (side, bombs) = testGame.setDifficulty()
                side should be (3)
                bombs should be (1)
            }
        }

        "return the beginner difficulty when input is 1" in {
            val in = new java.io.ByteArrayInputStream("1\n".getBytes)
            Console.withIn(in) {
                val (side, bombs) = testGame.setDifficulty()
                side should be (9)
                bombs should be (10)
            }
        }

        "return the intermediate difficulty when input is 2" in {
            val in = new java.io.ByteArrayInputStream("2\n".getBytes)
            Console.withIn(in){
                val (side, bombs) = testGame.setDifficulty()
                side should be (16)
                bombs should be (40)
            }
        }

        "return the advanced difficulty when input is 3" in {
            val in = new java.io.ByteArrayInputStream("3\n".getBytes)
            Console.withIn(in) {
                val (side, bombs) = testGame.setDifficulty()
                side should be (20)
                bombs should be (85)
            }
        }

        "return the beginner difficulty by default when input is not recognized" in{
            val in = new java.io.ByteArrayInputStream("33\n".getBytes)
            Console.withIn(in){
                val (side, bombs) = testGame.setDifficulty()
                side should be (9)
                bombs should be (10)
            }
        }
    }
      "The introMinesweeper method" should {
        val testGame2 = new Game(Status.Playing)
        "prompt the user for name and age" in {
            val in = new java.io.ByteArrayInputStream("Fabi\n18\n".getBytes)
            Console.withIn(in){
                val player = testGame2.introMinesweeper
                player.name should be ("Fabi")
                player.age should be (18)
            }
        }

        "return a new Player object with the input name and age" in{
            val in = new java.io.ByteArrayInputStream("Niklas\n20\n".getBytes)
            Console.withIn(in){
                val player2 = testGame2.introMinesweeper
                player2.name should be ("Niklas")
                player2.age should be (20)
            }
        }
    }

/*
    "Covered" should {
        val testGame4 = new Game(Status.Playing)
        val side2 = 3
        val visibleMatrixTest = new Matrix(side2, Symbols.Covered)
        "count just all the covered Martix Cells" in {
            val calcVisibleMatrixTest = testGame4.calcCovered(visibleMatrixTest)
            calcVisibleMatrixTest should be (9)

            val testRange = 0 to visibleMatrixTest.size-1
            testRange should be (scala.collection.immutable.Range.inclusive(0, 2))
        }
    }


    "Minecheck" should{
        val testGame6 = new Game(Status.Playing)
        val side4 = 3
        val mineCheckMatrix = new Matrix(side4, Symbols.Empty)
        val mineCheckMatrix2 = mineCheckMatrix.replaceCell(1, 1, Symbols.Bomb)

        "check if cell at location x,y has bomb or not" in{
            val boolCheckIsMine = testGame6.isMine(1, 1, mineCheckMatrix2)
            val boolCheckIsMine2 = testGame6.isMine(0, 0, mineCheckMatrix2)
            boolCheckIsMine should be (true)
            boolCheckIsMine2 should be (false)
        }
    }*/

    "Bombs" should {
        val testGame8 = new Game(Status.Playing)
        val side = 4
        val bombs = 9
        val testMineMatrix = new Matrix(side, Symbols.Empty)

        "be initialised random with the given amount of bombs" in{
            val testMineMatrixResult = testGame8.intitializeBombs(testMineMatrix, bombs)
            testMineMatrixResult.cell(0, 0) should be (Symbols.Bomb)
            testMineMatrixResult.cell(1, 1) should be (Symbols.Bomb)
            testMineMatrixResult.cell(2, 2) should be (Symbols.Bomb)
        }
    }

    "Replace calling method replaceBombMatrix" should {
        val testGame10 = new Game(Status.Playing)
        var leTestMatrix10 = new Matrix(3, Symbols.Empty)
        val oneBombMatrix = leTestMatrix10.replaceCell(0, 0, Symbols.Bomb)


        "replace matrix and ensure the player doesn't click on bomb at first move" in{
            //val bombs10 = 8
            //val initBombs = testGame10.intitializeBombs(leTestMatrix10, bombs10)
            val newTestMatrix = testGame10.replaceBombMatrix(oneBombMatrix, 1, 0, 0)
            //newTestMatrix.cell(1,1) should be (Symbols.Empty)
            newTestMatrix.cell(0,0) shouldNot equal (Symbols.Bomb)
        }

    }
    
    "Function premierMove" should {
        val testGame12 = new Game(Status.Playing)
        testGame12.bombs = 2
        testGame12.side = 3
        val testSide = 3
        val testField = new Field(testSide, Symbols.Covered)
   
        "return new Field after 1st move" in{
            val testFieldAfterMove = testGame12.premierMove(1, 1, testField, testGame12)
            testFieldAfterMove should not be (testField)
            testFieldAfterMove.matrix.cell(1, 1) should not be (Symbols.Covered)
        }
    
    }
    
        "Function decide" should{
        val testGame13 = new Game(Status.Lost)
        testGame13.gameState = Status.Lost
        val testSide13 = 3
 
        val testHid13 = new Matrix(testSide13, Symbols.Empty)
        val testBomb = testHid13.replaceCell(1, 1, Symbols.Bomb)
        val testAdjacent = testGame13.initializeAdjacentNumbers(testBomb)

        "return adjacent matrix which is different from input matrix when game is lost" in{
            val testResult13 = testGame13.decide(1, 1, testGame13, testAdjacent)

            testResult13 should not be (testAdjacent)
        }
    }

}
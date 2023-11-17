import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.minesweeper.model.{Field, Matrix, Symbols, Game, Status}

class FieldSpec extends AnyWordSpec {

  "A minesweeper Field" when {

    "empty" should {
      // Test cases for the bar method
      "have a bar in the form of '+---+---+---+'" in {
        val field = new Field(3, Symbols.Empty)
        val endl = sys.props("line.separator")
        field.bar() should be("+---+---+---+" + endl)
      }

      "have a scalable bar" in {
        val field = new Field(3, Symbols.Empty)
        val endl = sys.props("line.separator")
        field.bar(1, 1) should be("+-+" + endl)
        field.bar(2, 1) should be("+--+" + endl)
        field.bar(1, 2) should be("+-+-+" + endl)
        field.bar(3, 3) should be("+---+---+---+" + endl)
      }

      // Test cases for the cells method
      "have cells as String of form '|   |   |   |'" in {
        val field = new Field(3, Symbols.Empty)
        val endl = sys.props("line.separator")
        field.cells(0) should be("|   |   |   |" + endl)
      }

      "have scalable cells" in {
        val field = new Field(3, Symbols.Empty)
        val endl = sys.props("line.separator")
        field.cells(0, 1) should be("| |" + endl)
        field.cells(0, 1) should be("| | |" + endl)
        field.cells(2, 3) should be("|   |   |   |" + endl)
        field.cells(0, 3) should be("|   |" + endl)
      }

      // Test cases for the mesh method
      "have a mesh in a specified form" in {
        val fieldOne = new Field(1, Symbols.Empty)
        val endl = sys.props("line.separator")
        fieldOne.mesh(1) should be("+-+" + endl + "| |" + endl + "+-+" + endl)
      }

      "be initially empty when filled with Symbols.Empty" in {
        val field = new Field(3, Symbols.Empty)
        val endl = sys.props("line.separator")
        field.toString should be("+---+---+---+" + endl + "|   |   |   |" + endl + "+---+---+---+" + endl + "|   |   |   |" + endl + "+---+---+---+" + endl + "|   |   |   |" + endl + "+---+---+---+" + endl)
      }
    }
/*
    // Test cases for the open method
    "opened" should {
      "return a new field with updated cells" in {
        val testGame = new Game(Status.Playing)
        testGame.bombs = 8
        testGame.side = 3
        testGame.gameState = Status.Playing

        val bombMatrix = Game.setB(3, 8) // Assuming you have a method to initialize bombs
        val visibleMatrix = new Matrix(3, Symbols.Covered)
        val field = new Field(visibleMatrix, bombMatrix)

        val resultField = field.open(1, 1, testGame)
        // Assertions based on how open method works and the expected game state
      }
    }*/
  }
}

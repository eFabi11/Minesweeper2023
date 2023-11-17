/*import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.Mockito._

class ControllerTest extends AnyWordSpec with MockitoSugar {

  "Controller" should {

    "correctly handle the first move" in {
      val mockField = mock[Field]
      val mockGame = mock[Game]
      val controller = new Controller(mockField, mockGame)

      // Set up the mock behavior
      when(mockGame.premierMove(any[Int], any[Int], any[Field], any[Game])).thenReturn(mockField)

      controller.firstMove(0, 0, mockGame)

      // Verify the field is updated and observers are notified
      controller.field shouldBe mockField
      verify(mockGame).premierMove(0, 0, mockField, mockGame)
      verify(controller).notifyObservers
    }

    "update the field correctly when uncovering a field" in {
      val mockField = mock[Field]
      val mockGame = mock[Game]
      val controller = new Controller(mockField, mockGame)

      // Set up the mock behavior
      when(mockField.open(any[Int], any[Int], any[Game])).thenReturn(mockField)

      controller.uncoverField(1, 1, mockGame)

      // Verify the field is updated and observers are notified
      controller.field shouldBe mockField
      verify(mockField).open(1, 1, mockGame)
      verify(controller).notifyObservers
    }

  }
}*/
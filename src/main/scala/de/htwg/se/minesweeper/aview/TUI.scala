package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.util.Observer
import scala.io.StdIn.readLine
import de.htwg.se.minesweeper.model.{Move, Status}

class TUI(controller: Controller) extends Observer {
    controller.add(this)

    def run(): Unit = {
        printField()
        handleUserInput()
    }

    private def printField(): Unit = println(controller.field.toString)

     override def update: Unit = printField()

    private def handleUserInput(): Unit = {
        println("Enter your move (<action><x><y>, e.g., o02, q to quit, h for help):")
        parseInput(readLine()) match {
            case None => println("Game Ended")
            case Some(move) => processMove(move)
        }
        if (controller.game.state != Status.Playing) println(s"Game Over: ${controller.game.state}")
        else handleUserInput()
    }

    private def parseInput(input: String): Option[Move] = input match {
        case "q" => None
        case "h" => Some(Move("help", 0, 0))
        case "r" => Some(Move("reveal", 0, 0))
        case _ if input.length >= 3 =>
            val action = input.head match {
                case 'o' => "open"
                case 'f' => "flag"
                case 'u' => "unflag"
                case _ => "help"
            }
            val coords = input.tail.take(2).map(_.toString.toInt)
            Some(Move(action, coords(0), coords(1)))
        case _ => Some(Move("help", 0, 0))
    }

    private def processMove(move: Move): Unit = {
        move.action match {
            case "open" => controller.uncoverField(move.x, move.y)
            case "flag" => controller.flag(move.x, move.y)
            case "unflag" => controller.unflag(move.x, move.y)
            case "help" => controller.helpMenu
            case "reveal" => controller.cheat
        }
    }
}

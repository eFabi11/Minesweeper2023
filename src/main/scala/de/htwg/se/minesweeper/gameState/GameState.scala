package de.htwg.se.minesweeper.gameState

import de.htwg.se.minesweeper.model.{Field, Move}
import de.htwg.se.minesweeper.util.Observable
import de.htwg.se.minesweeper.model.Game
import de.htwg.se.minesweeper.model.Game
import de.htwg.se.minesweeper.gameState.GameContext

trait GameState {
  def doAction(context: GameContext): Unit
}

def startGame(): Unit = {
    context.setState(new StartState)
    context.doAction()
    state = Status.Playing
}

class PlayState extends GameState {
  def doAction(context: GameContext): Unit = {
    println("Game is in play state")
    context.setState(this)
  }

  override def toString: String = "Play State"
}

class WinState extends GameState {
  def doAction(context: GameContext): Unit = {
    println("Game is in win state")
    context.setState(this)
  }

  override def toString: String = "Win State"
}

class LoseState extends GameState {
  def doAction(context: GameContext): Unit = {
    println("Game is in lose state")
    context.setState(this)
  }

  override def toString: String = "Lose State"
}


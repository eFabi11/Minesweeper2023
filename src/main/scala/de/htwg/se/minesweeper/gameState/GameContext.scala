package de.htwg.se.minesweeper.gameState

import de.htwg.se.minesweeper.model.Game

class GameContext(var game: Game) {
  private var state: GameState = new StartState

  def setState(state: GameState): Unit = {
    this.state = state
  }

  def getState: GameState = state

  def doAction(): Unit = {
    state.doAction(this)
  }
}

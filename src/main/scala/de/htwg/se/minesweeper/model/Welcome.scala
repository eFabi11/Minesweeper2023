package de.htwg.se.minesweeper.model

import scala.io.StdIn.readLine

object welcome: 
    
    def welcomeMinesweeper: Player = {
        println("Welcome to Minesweeper!")
        val name = readLine("Please enter your Name: ")
        println("Please enter your age!")
        val age = scala.io.StdIn.readInt()
        println("Hello $name ($age), Let´s play a Round!")
        val newPlayer = new Player(name, age)
        newPlayer
    }

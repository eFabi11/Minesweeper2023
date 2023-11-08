package de.htwg.se.minesweeper.model



enum Symbols(representedAsString: String):
    override def toString = representedAsString

    // UFT-8 in hex is tilde // □ ☺ ■ ▫ ○
    // Covered is before you open field
    //s"${(0x7E).toChar}"
    case Covered extends Symbols("-")
    case F extends Symbols("F")
    case Bomb extends Symbols("*")
    case Empty extends Symbols(" ")
    case Zero extends Symbols("0")
    case One extends Symbols("1")
    case Two extends Symbols("2")
    case Three extends Symbols("3")
    case Four extends Symbols("4")
    case Five extends Symbols("5")
    case Six extends Symbols("6")
    case Seven extends Symbols("7")
    case Eight extends Symbols("8")

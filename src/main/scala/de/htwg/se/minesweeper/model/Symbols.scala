package de.htwg.se.minesweeper.model

enum Symbols(representatedAsString: String):


    // UFT-8 in hex is tilde // □ ☺ ■ ▫ ○
    // Covered is before you open field
    case Covered extends Symbols(s"${(0x7E).toChar}")
    case F extends Symbols ("F")
    case bomb extends Symbols ("*")
    case empty extends Symbols (" ")
    case zero extends Symbols ("0")
    case one extends Symbols ("1")
    case two extends Symbols ("2")
    case three extends Symbols ("3")
    case four extends Symbols ("4")
    case five extends Symbols ("5")
    case six extends Symbols ("6")
    case seven extends Symbols ("7")
    case eight extends Symbols ("8")
    case nine extends Symbols("9")
package de.htwg.se.minesweeper.model



enum Symbols(representedAsString: String):
    override def toString = representedAsString

    // UFT-8 in hex 0x7E is tilde // □ ☺ ■ ▫ ○
    // Covered is before you open field
    case Covered extends Symbols(s"${(0x7E).toChar}")
    case F extends Symbols("F")
    case Bomb extends Symbols("*")
    case Empty extends Symbols(" ")
    case Zero extends Symbols("\u001B[90m"+"0"+"\u001B[37m")
    case One extends Symbols("\u001B[34m"+"1"+"\u001B[37m")
    case Two extends Symbols("\u001B[32m"+"2"+"\u001B[37m")
    case Three extends Symbols("\u001B[35m"+"3"+"\u001B[37m")
    case Four extends Symbols("\u001B[31m"+"4"+"\u001B[37m")
    case Five extends Symbols("\u001B[33m"+"5"+"\u001B[37m")
    case Six extends Symbols("\u001B[36m"+"6"+"\u001B[37m")
    case Seven extends Symbols("\u001B[33m"+"7"+"\u001B[37m")
    case Eight extends Symbols("\u001B[91m"+"8"+"\u001B[37m")
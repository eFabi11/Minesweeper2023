case class Cell(x:Int, y:Int)

val cell1 = Cell(4,5)
cell1.x
cell1.y

val cell2 = Cell(3,7)

case class Field(cells: Array[Cell])

val field1 = Field(Array.ofDim[Cell](1))
field1.cells(0)=cell1
field1.cells(0).x
field1.cells(0).y

case class PiBankFP(val coins:Int = 0)
{
    def insert(newCoins:Int): PiBankFP = copy(coins + newCoins)
    def butcher = coins
}

val p1 = new PiBankFP()
val p2 = p1.insert(7)
println(p2.butcher)

// here we can test our Playfield for Minesweeper

val endl = sys.props("line.separator")
def bar(cellWidth: Int = 3, cellNum: Int = 3)= (("+" + "-" * cellWidth) * cellNum) + "+" + endl

bar(3,9)

//next one

def cells(cellWidth: Int = 3, cellNum: Int = 3) = ("|" + " " * cellWidth) * cellNum + "|" + endl
cells(9,9)

def mesh(cellWidth: Int = 3, cellNum: Int = 10) = (bar(cellWidth, cellNum) + cells(cellWidth, cellNum)) * cellNum + bar(cellWidth, cellNum)

println(mesh())

case class Zelle(value:Int)
{
     def isSet: Boolean = value != 0
}

val cellOne = Zelle(2)
cellOne.isSet

val cellTwo = Zelle(0)
cellTwo.isSet

val fieldOne = Field(Array.ofDim[Cell](1))
fieldOne.cells(0) = cell1

case class Minenfeld(cells:Vector[Cell])
val initMinefield = Minenfeld(Vector(cell1, cell2))

case class MinFel(zellen:Vector[Zelle])
val mf = MinFel(Vector(cellOne, cellTwo))

initMinefield.cells(0).x
initMinefield.cells(0).y

mf.zellen(0).isSet
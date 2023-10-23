case class Employee(name: String, office:String, role:String)

val sly = Employee("Sly", "Zurich", "Programmer")

val king =  sly.copy(name="Joe")

case class Field(cellNumero: Int):
    val endl = sys.props("line.separator")
    // defines the bar
    def bar(cellWidth: Int = 3, cellNum: Int = 3) = (("+" + "-" * cellWidth) * cellNum) + "+" + endl
    // defines the cells
    def cells(cellWidth: Int = 3, cellNum: Int = 3) = ("|" + " " * cellWidth) * cellNum + "|" + endl
    // defines the grid and default size is 10x10 field
    def mesh(cellWidth: Int = 3, cellNum: Int = cellNumero) = (bar(cellWidth, cellNum) + cells(cellWidth, cellNum)) * cellNum + bar(cellWidth, cellNum)
    // prints grid default size is 10x10
    override def toString(): String = mesh()



val f1 = new Field(1)
val f2 = new Field(2)
val f3 = new Field(3)

val res1 = f1.mesh(1,1)
val res2 = f2.mesh(1)
val res3 = f3.mesh(1)
val res4 = f1.mesh()
val resf3 = f3.mesh()

var field = new Field(3)
field.toString

//Chap4: More Scala

case class Person(name: String, age: Int)

val people = List(Person("Steve",32))
val people2 = people :+ Person("Ali",17)
val people3 = Person("Stacy",23)::List(Person("Steve",32), Person("Ali",17))
val people4 = List(Person("Malloc",33))
val people5 = people3 ::: people4
val (minors, adults) = people5 partition (_.age < 18)

def error(message: String): Nothing = {throw new RuntimeException(message)}
def divideTest(x: Int, y: Int): Int = {
    if(y!=0) x/y 
    else error("Division by 0")
}

val div1= divideTest(15,5)
val div2 = divideTest(15, 2)
//val div3 = divideTest(5, 0)
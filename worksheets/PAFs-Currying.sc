//general functions
def curryF(a: Int)(b: Int) = a + b
val curryVal = curryF(5)(4)

def greetSomebody(suffix: String)(prefix: String) = suffix + prefix
val message = greetSomebody("hello ")("world")

//special versions
def specialCurryFV1 = curryF(5)(_)
val specialCurryVal = specialCurryFV1(4)

def greetSomebodyCurried = greetSomebody("hello ")(_)
val prefixedVal = greetSomebodyCurried("Walter")

//Creating curried functions from regular functions

def add(a: Int, b: Int) = a + b

(add _).isInstanceOf[Function2[_, _, _]]

val addCurried = (add _).curried


class User1

val u = new User1

val isAnyRef = u.isInstanceOf[AnyRef]

/*
The hexadecimal number printed after the name of our “User” clas may look a bit odd.
The actual method printing this is the JVM’s java.lang.Object.toString . The
java.lang.Object class is the root of all instances in the JVM, including Scala, and is
essentially equivalent to the Scala root type Any . By printing an instance, the REPL is
invoking the instance’s toString method, which it inherits from the root type. The
actual parent type of our User class is AnyRef (see Table 2-4), the root of all instantiable
types. Thus, invoking toString on our User class resulted in a call to its parent, Any
Ref , then to its parent, Any , which is the same as java.lang.Object and where the
toString method is located.
 */

class User(val name: String) {
  def greet: String = s"Hello from $name"
  override def toString = s"User($name)"
}

val u1 = new User("Abhishek")

u1.greet

val users = List(new User("Shoto"),new User("Art3mis"),
  new User("Aesch"))

users map (_.name.length)

users sortBy (_.name)

val third = users find (_.name contains "3")

third.map(_.greet) getOrElse "Hi"




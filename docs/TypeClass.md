What is a type class?
"Type classes" define classes of types in the same way "class" define classes of objects. 
In Scala, a type class means a trait with at least one type variable. For instance:

```
trait CanChat[A] {
  def chat(x: A): String
}

```
TypeClass - CanChat[A], Ordering[A]
Type instances of A - String, Int, Person, Animal etc.

This defines not just one type, but a set of types. Some particular types which are members of this set would be
CanChat[String], CanChat[Int], CanChat[SomeUserDefinedType], in the same way that “this”, “that”, “the other”
are members of the set defined by class String.

Allowing traits to be parameterised in this way gives you new options
for adding functionality to classes without modifying existing code, as we will see below.

src: https://www.theguardian.com/info/developer-blog/2016/dec/22/parental-advisory-implicit-content
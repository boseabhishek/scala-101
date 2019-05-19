Function Composition
Let’s make two aptly-named functions:
scala> def f(s: String) = "f(" + s + ")"
f: (String)java.lang.String

scala> def g(s: String) = "g(" + s + ")"
g: (String)java.lang.String


compose
compose makes a new function that composes other functions f(g(x))
scala> val fComposeG = f _ compose g _
fComposeG: (String) => java.lang.String = <function>

scala> fComposeG("yay")
res0: java.lang.String = f(g(yay))


andThen
andThen is like compose, but calls the first function and then the second, g(f(x))
scala> val fAndThenG = f _ andThen g _
fAndThenG: (String) => java.lang.String = <function>

scala> fAndThenG("yay")
res1: java.lang.String = g(f(yay))

Most important

The output of f must match the input of g
Ordering using andThen: f(x) andThen g(x) = g(f(x))
Ordering using compose: f(x) compose g(x) = f(g(x))

Currying and PAFs

https://www.grokacademy.com/lessons/demystifying-currying-and-partial-function-application/

Scala Functions Vs Methods

http://jim-mcbeath.blogspot.com/2009/05/scala-functions-vs-methods.html

And,

https://alvinalexander.com/scala/fp-book-diffs-val-def-scala-functions

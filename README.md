# scala-101

Scala learners playground

## Tests
```shell
sbt test
```

## Important:

### 

### JDK vs Scala version compatibility:
Ref: https://docs.scala-lang.org/overviews/jdk-compatibility/overview.html#version-compatibility-table

Whatever Scala version you are using in [build.sbt](build.sbt),
```scala
scalaVersion := "2.12.16"
```
set the corresponding version in Build, Exec... > Build Tools > sbt > JRE > <set to compatible java version>

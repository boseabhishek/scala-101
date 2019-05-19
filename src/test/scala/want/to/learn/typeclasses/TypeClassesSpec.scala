package want.to.learn.typeclasses

import org.scalatest.{FreeSpec, MustMatchers}

class TypeClassesSpec extends FreeSpec with MustMatchers {

  //I am a type class for any type A as long as they extend me
  trait CanChat[A] {
    def chat(x: A): String
  }

  //I am Person. I will be one of A once I extend CanChat[A]
  final case class Person(firstName: String, lastName: String)

  //I am Dog and ditto...
  final case class Dog(name: String)


  "Person and Dog are type of A (1) once they extend the type class CanChat[A]" - {

    "(2) then you can override the behaviour chat and NOT declare them as implicits" - {

      object ChattyAddons {

        object PersonCanChat extends CanChat[Person] {
          def chat(x: Person) = s"Hi, I'm ${x.firstName}"
        }

        object DogCanChat extends CanChat[Dog] {
          def chat(x: Dog) = s"Woof, my name is ${x.name}"
        }

      }

      "followed by (3) using them where needed BUT cannot use implicits as they are NOT" in {

        import ChattyAddons._

        //need to pass them explicitly everytime. Problem is solved by using Implicit.
        object ChatUtil {
          def chat[A](x: A, chattyThing: CanChat[A]) = {
            chattyThing.chat(x)
          }
        }

        ChatUtil.chat(Dog("Jack"), DogCanChat) mustBe "Woof, my name is Jack"

      }


    }

    "(2) then you can override the behaviour chat and declare them as implicits so that they can be used implicitly" - {

      object ChattyAddons {

        implicit object PersonCanChat extends CanChat[Person] {
          def chat(x: Person) = s"Hi, I'm ${x.firstName}"
        }

        implicit object DogCanChat extends CanChat[Dog] {
          def chat(x: Dog) = s"Woof, my name is ${x.name}"
        }

      }

      "followed by (3) using them where needed, by importing the implicits in scope" in {

        import ChattyAddons._
        object ChatUtil {
          def chat[A](x: A)(implicit chattyThing: CanChat[A]) = {
            chattyThing.chat(x)
          }
        }

        ChatUtil.chat(Dog("Jack")) mustBe "Woof, my name is Jack"

      }


    }


  }


}

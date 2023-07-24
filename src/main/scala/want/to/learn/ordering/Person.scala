package want.to.learn.ordering

import org.joda.time.LocalDate

case class Person(name: String, dob: LocalDate)

object Person {

  val byAlphabeticalOrder: Ordering[Person] = Ordering.by { person: Person =>
    person.name
  }

}